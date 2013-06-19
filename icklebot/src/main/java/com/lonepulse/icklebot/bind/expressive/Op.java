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
 * <p>Aggregates all available operators and delegates services calls to 
 * their individual instances.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public enum Op implements Operation {
	
	
	/**
	 * <p>Performs a projection from a model attribute.</p>
	 * 
	 * <b>Usage:</b> <pre>${attr1.attr2.attr3}</pre>
	 */
	PROJECT(new Projection()),
	
	/**
	 * <p>Initiates the evaluation of an expression.</p>
	 * 
	 * <b>Usage:</b> <pre>${&lt;your-expression-here&gt;}</pre>
	 */
	EVALUATE(new Evaluation());

	
	/**
	 * <p>The operator which this instance of {@link Op}s 
	 * delegates its calls to.
	 */
	private Operation operator;
	

	/**
	 * <p>Adds the given operator to this aggregation.
	 * 
	 * @param operator
	 * 			the {@link Operation} to which service calls are delegated
	 *
	 * @since 1.1.0
	 */
	private Op(Operation operator) {
		
		this.operator = operator;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Symbol symbol() {
		
		return operator.symbol();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object evaluate(Object target, Object... args) throws OperationFailedException {
		
		return operator.evaluate(target, args);
	}

	/**
	 * <p>Resolves an {@link Operation} for the given {@link Symbol#head()}.
	 * 
	 * @param head
	 * 			the {@link Symbol#head()} for the {@link Operation} 
	 * 
	 * @return the resolved {@link Op}
	 * 
	 * @throws OperatorResolutionFailedException
	 * 			if the no operator was found with the given symbol-head
	 * 
	 * @since 1.1.0
	 */
	public static final Op resolve(String head) throws OperatorResolutionFailedException {
		
		Op[] values = values();
		
		for (Op op : values) {
			
			if(op.symbol().head().equals(head)) return op;
		}
		
		throw new OperatorResolutionFailedException();
	}
}
