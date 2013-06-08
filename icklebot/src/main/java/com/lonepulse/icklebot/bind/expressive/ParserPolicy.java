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
 * <p>This contract defines the services offered by a parser which parses 
 * an {@link Expression} tree using a given string.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface ParserPolicy {
	
	/**
	 * <p>Takes an expression String and parses it into an {@link Expression} tree 
	 * and executed the nodes.
	 * 
	 * @param target
	 * 			the target which this expression will act on
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
	Object parse(Object target, StringBuilder xp) throws IllegalSyntaxException;
}
