package com.lonepulse.icklebot.bind.expressive;

/*
 * #%L
 * IckleBot
 * %%
 * Copyright (C) 2013 Lonepulse
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


/**
 * <p>A concrete implementation of {@link Parser} which parses expression Strings 
 * into an {@link Expression} pipe. The expression which are parsed conforms to a 
 * limited subset of the <i>Unified Expression Language</i> (JSR-245).
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class ExpressionParser implements Parser<StringBuilder, Object> {

	
	/**
	 * <p>Takes an expression String and parses it into an {@link Expression} pipe 
	 * and executes the nodes.
	 * 
	 * @param xp
	 * 			the expression String to be parsed and executed
	 * 
	 * @return the result of the parsed expression
	 * 
	 * @throws IllegalSyntaxException
	 * 			if the given expression {@link String} failed to be parsed
	 * 
	 * @since 1.1.0
	 */
	@Override
	public Object parse(Object target, StringBuilder xp) throws IllegalSyntaxException {
		
		Op op = null;
		
		try {
			
			op = Op.resolve(xp.substring(0, 2));
		}
		catch(OperatorResolutionFailedException orfe) {
			
			try {
				
				op = Op.resolve(String.valueOf(xp.charAt(0)));
			} 
			catch (OperatorResolutionFailedException orfe2) {
				
				StringBuilder errorContext = new StringBuilder()
				.append("Malformed expression segment ")
				.append(xp.toString())
				.append(" cannot be parsed. ");
				
				throw new IllegalSyntaxException(errorContext.toString());
			}
		}
		
		xp.delete(0, op.symbol().head().length());
		
		if(!op.symbol().tail().isEmpty()) {
			
			try {
			
				int tailIndex = ParserUtils.indexOfTail(xp, op.symbol());
				xp.delete(tailIndex, tailIndex + op.symbol().tail().length());
			}
			catch(IndexNotFoundException infe) {
				
				StringBuilder errorContext = new StringBuilder()
				.append("Malformed expression segment ")
				.append(xp.toString())
				.append(" cannot be parsed. ");
				
				throw new IllegalSyntaxException(errorContext.toString());
			}
		}
		
		int nextHeadIndex = 0;
		
		try {
			
			nextHeadIndex = ParserUtils.indexOfNextHead(xp);
		}
		catch(IndexNotFoundException infe) {
			
			return op.evaluate(target, ParserUtils.extractArgs(xp.toString()));
		}
		
		String argString = xp.substring(0, nextHeadIndex);
		Object subResult = op.evaluate(target, ParserUtils.extractArgs(argString));
		
		return parse(subResult, new StringBuilder(xp.substring(nextHeadIndex, xp.length()).trim()));
	}
}
