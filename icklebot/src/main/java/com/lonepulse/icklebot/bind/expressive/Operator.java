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
 * <p>This contract defines the services offered by an <b>operator</b> which 
 * takes data and executes an evaluation strategy to produce any results.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface Operator {

	
	/**
	 * <p>The textual symbol which identifies this operator.
	 *
	 * @return the textual symbol which identifies this operator
	 * 
	 * @since 1.1.0
	 */
	Symbol getSymbol();
	
	/**
	 * <p>Takes an argument(s), executes the operator's evaluation strategy 
	 * on the given target and returns the resulting data.
	 *
	 * @param target
	 * 			the target of the operation
	 * 
	 * @param args
	 * 			the argument(s) to the operation
	 * 
	 * @return the data resulting from the execution strategy, else 
	 * 		   {@code null} if this operator does not produce any results
	 * 
	 * @throws OperationFailedException
	 * 			if the operation failed to evaluate successfully
	 * 
	 * @since 1.1.0
	 */
	Object evaluate(Object target, Object... args) throws OperationFailedException;
}
