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
 * <p>This is an abstract implementation of {@link Operation} which mandates the 
 * provision of a {@link Symbol} and implementation of {@link Operation#evaluate(Object...)}.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public abstract class AbstractOperation implements Operation {


	/**
	 * <p>The {@link Symbol} which represents this operator in an expression. 
	 */
	private final Symbol symbol;
	
	
	/**
	 * <p>Instantiates a new {@link Operation} with the given {@link Symbol}.
	 * 
	 * @param symbol
	 * 			the {@link Symbol} associated with this {@link Operation}
	 * 
	 * @since 1.1.0
	 */
	public AbstractOperation(Symbol symbol) {
		
		if(symbol == null) {
			
			throw new IllegalArgumentException(
				"An operator cannot be constructed without a " + Symbol.class.getName() + ". ");
		}
		
		this.symbol = symbol;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Symbol symbol() {
		
		return symbol;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object evaluate(Object target, Object... args) throws OperationFailedException {
		
		try {
			
			return onEvaluate(target, args);
		}
		catch(Exception e) {
			
			throw (e instanceof OperationFailedException)? 
					(OperationFailedException)e :new OperationFailedException(this, target, args);
		}
	}
	
	/**
	 * <p>This callback must be overridden to define the evaluation stratagem.</p> 
	 *
	 * <p>See {@link Operation#evaluate(Object, Object...)}.
	 * 
	 * @since 1.1.0
	 */
	protected abstract Object onEvaluate(Object target, Object... args);
}
