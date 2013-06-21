package com.lonepulse.icklebot.bind.expressive;

import java.util.regex.Pattern;


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
 * <p>This utility class provides common services which are used by {@link ExpressionParser}s.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public final class ParserUtils {

	
	/**
	 * <p>The delimiter which is used to separate multiple arguments 
	 * to an operation. The default value is {@code ":"} escaped to 
	 * be taken as a literal in a regex string.
	 */
	public static final String ARG_DELIMITER = Pattern.quote(":");
	

	/**
	 * <p>Constructor visibility is restricted. Instantiation is nonsensical.
	 * 
	 * @since 1.1.0
	 */
	private ParserUtils() {}
	
	
	/**
	 * <p>Discovers the index of the next operator-head in the given expression String.
	 *
	 * @param xp
	 * 			the expression String to scan for the next operator-head
	 * 
	 * @return the starting index of the next expression
	 * 
	 * @throws IndexNotFoundException
	 * 			if there are no more operators in the given expression String
	 * 
	 * @since 1.1.0
	 */
	public static int indexOfNextHead(StringBuilder xp) throws IndexNotFoundException {
		
		Op[] ops = Op.values();
		
		for (int i = 0; i < xp.length(); i++) {
			
			for (Op op : ops) {
				
				String head = op.symbol().head();

				if(xp.charAt(i) == head.charAt(0)) {
					
					if(head.length() == 2) {
						
						if(xp.substring(i, i+2).equals(head)) {
							
							return i;
						}
					}
					else {
						
						return i;
					}
				}
			}
		}
		
		throw new IndexNotFoundException();
	}
	
	/**
	 * <p>Discovers the index of the tail associated with an operator-head.
	 *
	 * @param xp
	 * 			the expression String to scan for the next operator-tail
	 * 
	 * @param symbol
	 * 			the {@link Symbol} of the operator whose tail it to be scanned
	 * 
	 * @return the starting index of the tail
	 * 
	 * @throws IndexNotFoundException
	 * 			if the index of the tail cannot be found
	 * 
	 * @since 1.1.0
	 */
	public static int indexOfTail(StringBuilder xp, Symbol symbol) throws IndexNotFoundException {
		
		String head = symbol.head();
		String tail = symbol.tail();
		
		int headLength = head.length();
		int tailLength = tail.length();
		int tailsToSkip = 0;
		
		for (int i = 0; i < xp.length(); i+=tailLength) {
			
			if(xp.substring(i, i + tailLength).equals(tail)) {
				
				if(tailsToSkip == 0) return i;
				else --tailsToSkip;
			}
			else if (xp.substring(i, i + headLength).equals(head)) {
				
				++tailsToSkip;
			}
		}
		
		throw new IndexNotFoundException();
	}
	
	/**
	 * <p>Extracts arguments from a String and Removes all leading and trailing single quotes 
	 * from String arguments.
	 *
	 * @param argString
	 * 			the argument String to refine
	 * 
	 * @return the refined argument array
	 * 
	 * @since 1.1.0
	 */
	public static Object[] extractArgs(String argString) {
		
		String[] args = argString.split(ARG_DELIMITER);
		
		Object[] refinedArgs = new Object[args.length];
		
		for (int i = 0; i < args.length; i++) {
			
			String arg = args[i];
			
			if((arg.startsWith("'") && arg.endsWith("'"))) {
				
				StringBuilder builder = new StringBuilder(arg);
				builder.deleteCharAt(0).deleteCharAt(builder.length() - 1);
				refinedArgs[i] = builder.toString();
			}
			if((arg.startsWith("\'") && arg.endsWith("\'"))
				|| (arg.startsWith("\\\"") && arg.endsWith("\\\""))) {
				
				StringBuilder builder = new StringBuilder(arg);
				builder.delete(0, 2).delete(builder.length() - 2, builder.length());
				refinedArgs[i] = builder.toString();
			}
			else if(arg.equals("true") || arg.equals("false")) {
				
				refinedArgs[i] = Boolean.parseBoolean(arg);
			}
			else {
				
				try {
					
					refinedArgs[i] = Long.parseLong(arg);
				}
				catch(NumberFormatException nfe) {
				
					try {
					
						refinedArgs[i] = Double.parseDouble(arg);
					}
					catch(NumberFormatException nfe2) {
						
						refinedArgs[i] = arg;
					}
				}
			}
		}
		
		return refinedArgs;
	}
}
