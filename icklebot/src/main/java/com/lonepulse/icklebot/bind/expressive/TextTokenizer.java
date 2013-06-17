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


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <p>An abstract implementation of {@link Tokenizer} which tailors a specific 
 * tokenization strategy for expressions identified by a {@link Symbol}.</p>
 *  
 * <p><b>Note</b>: this {@link Tokenizer} can only be used on {@link Symbol}s 
 * which have both a head and a tail. Nested tokenization is not supported.</p> 
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class TextTokenizer implements Tokenizer<String> {

	
	/**
	 * <p>The {@link Symbol} which is used to identify and delimit the tokens.
	 */
	private final Symbol symbol;
	
	/**
	 * <p>The list of tokens which were extracted from the content.
	 */
	private final List<String> tokens;
	
	
	/**
	 * <p>Instantiates a new {@link Tokenizer} based on the given {@link Symbol} and 
	 * the textual content to be tokenized.
	 * 
	 * @param symbol
	 * 			the {@link Symbol} which delimits the token
	 * 
	 * @param content
	 * 			the textual content to be tokenized
	 * 
	 * @throws TokenizerException
	 * 			if instantiation failed due to the absence of a symbol tail 
	 * 			or if tokenizing failed on the given content
	 *
	 * @since 1.1.0
	 */
	public TextTokenizer(Symbol symbol, String content) throws TokenizerException {
	
		this.symbol = symbol;
		
		if(symbol.tail().isEmpty()) {
			
			StringBuilder errorContext = new StringBuilder()
			.append("The symbol with the head ")
			.append(symbol.head())
			.append(" is missing a tail. ")
			.append(getClass().getName())
			.append(" requires a symbol with a tail. ");
			
			throw new TokenizerException(errorContext.toString());
		}
		
		this.tokens = tokenize(content);
	}
	
	/**
	 * <p>Performs tokenization for the {@link Symbol} with which this {@link Tokenizer} 
	 * was instantiated and returns the list of tokens.</p> 
	 * 
	 * This method is invoked when the {@link TextTokenizer} is first instantiated. 
	 * Override this method to implement a custom tokenizing strategy.</p>
	 *
	 * @param content
	 * 			the textual content to be tokenized
	 * 
	 * @return the list of tokenized expressions
	 * 
	 * @throws TokenizerException
	 * 			if tokenization failed for the given {@link Symbol} on some content
	 * 
	 * @since 1.1.0
	 */
	protected List<String> tokenize(String content) throws TokenizerException {
	
		List<String> extractedTokens = new ArrayList<String>();
		String[] unrefinedTokens = content.split("\\Q" + symbol.head() + "\\E");
		
		for (String unrefinedToken : unrefinedTokens) {
			
			int index = unrefinedToken.lastIndexOf(symbol.tail());
			
			if(index > 0) {
				
				extractedTokens.add(unrefinedToken.substring(0, index));
			}
		}
		
		return extractedTokens;
	}

	/**
	 * <p>Returns an instance of {@link Iterator} which can be used 
	 * to iterate overall all the extracted tokens.
	 * 
	 * @since 1.1.0
	 */
	@Override
	public Iterator<String> iterator() {
		
		return tokens.iterator();
	}

	/**
	 * <p>Retrieves the list of tokens which were extracted by this 
	 * implementation of {@link Tokenizer}. 
	 * 
	 * @return the list of tokens
	 * 
	 * @since 1.1.0
	 */
	@Override
	public List<String> getTokens() {
		
		return Collections.unmodifiableList(tokens);
	}
}
