package com.lonepulse.icklebot.bind;

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


import java.util.regex.Pattern;

import android.widget.Button;
import android.widget.TextView;

import com.lonepulse.icklebot.bind.expressive.ExpressionParser;
import com.lonepulse.icklebot.bind.expressive.ExpressionTokenizer;
import com.lonepulse.icklebot.bind.expressive.Parser;
import com.lonepulse.icklebot.bind.expressive.Symbol;
import com.lonepulse.icklebot.bind.expressive.Tokenizer;

/**
 * <p>An extension of {@link TextBinder} which provides an implementation of an 
 * {@link ExpressiveBindingStrategy}.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class ExpressiveTextBinder extends TextBinder implements ExpressiveBindingStrategy<TextView, Object> {

	
	/**
	 * <p>The {@link Symbol} used for tokenizing expressions within the text 
	 * content of the {@link TextView}.
	 */
	public static final Symbol SYMBOL = new Symbol("${", "}");
	
	/**
	 * <p>The {@link Parser} which is used for parsing the {@link TextView} content. 
	 */
	private Parser<StringBuilder, Object> parser;
	
	/**
	 * <p>The {@link Tokenizer} which is used for extracting the expressions from 
	 * the {@link TextView} content.
	 */
	private Tokenizer<String> tokenizer;
	
	
	/**
	 * <p>Instantiates a new {@link ExpressiveTextBinder} with the given 
	 * {@link TextView} and data. 
	 * 
	 * @param textView
	 * 			the {@link TextView} to which the data is to be bound; this could 
	 * 			be any extension of {@link TextView} such as a {@link Button}
	 * 
	 * @param data
	 * 			the {@link Object} on which {@code toString()} will be 
	 * 			invoked to retrieve the String to bind to the {@link TextView}
	 *
	 * @since 1.1.0
	 */
	public ExpressiveTextBinder(TextView textView, Object data) {
		
		super(textView, data);
		
		this.parser = new ExpressionParser();
		this.tokenizer = new ExpressionTokenizer(SYMBOL, textView.getText().toString());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void xbind() throws BindException {

		String content = getWidget().getText().toString();
		Object target = getData();
		
		for (String xpToken : tokenizer) {
			
			try {
			
				Object result = parser.parse(target, new StringBuilder(xpToken));
				
				content = content.replaceAll(Pattern.quote(xpToken), result.toString());
			}
			catch(Exception e) {
				
				throw (e instanceof BindException)? (BindException)e :new BindException(e);
			}
		}
		
		getWidget().setText(content);
	}
}
