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
 * <p>A concrete implementation of {@link ParserPolicy} which parses expression 
 * Strings into an {@link Expression} tree.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class Parser implements ParserPolicy {

	/* 
	 * Currently no expression tree is parsed, seems more like an expression pipe...
	 * 
	 * e.g. (i) #{user.email?:'N/A'} vs (ii) #{(user.profile.prefs['public'] && user.online)?user.email?:user.mobile?:'Contacts N/A'}
	 * 
	 * (i) looks innocent enough, (ii) looks scary...should not encourage service logic in presentation.
	 */
	
	/**
	 * <p>Takes an expression String and parses it into an {@link Expression} tree 
	 * and executed the nodes.
	 * 
	 * @param xp
	 * 			the expression String to be parsed and excuted
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
				
				throw new IllegalSyntaxException("Failed to parse expression " + xp.toString() + ". "); 
			}
		}
		
		xp.delete(0, op.symbol().head().length());
		
		int nextIndex = 0;
		
		if(op.symbol().tail().isEmpty()) {
			
			nextIndex = ParserUtils.indexOfNextHead(xp);
		}
		else {
			
			nextIndex = ParserUtils.indexOfTail(xp, op.symbol());
			xp.delete(nextIndex, op.symbol().tail().length());
		}
		
		String argString = xp.substring(0, nextIndex);
		Object subResult = op.evaluate(target, ParserUtils.extractArgs(argString));  
		
		return (xp.length() == 0)? 
				subResult :parse(subResult, new StringBuilder(xp.substring(nextIndex, xp.length())));
	}
}
