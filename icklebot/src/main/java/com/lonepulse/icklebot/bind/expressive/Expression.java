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


import java.util.List;

/**
 * <p>Represents a single node on an <b>expression tree</b>. Each individual 
 * expression contains a single {@link Operator} which acts on a target <b>LHS</b> 
 * using the arguments from it's <b>RHSM</b> which may be another set of expressions.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class Expression {

	
	/**
	 * <p>The target of this expression, which could be the <b>result</b> 
	 * of an expression itself.
	 */
	private final Object parent;
	
	/**
	 * <p>The operator which is used by this expression for <i>evaluation</i>. 
	 */
	private final Operator operator;
	
	/**
	 * <p>The list of child {@link Expression}s which supply the argument(s) 
	 * to this node 
	 */
	private final List<Expression> children;

	
	/**
	 * <p>Constructs a new node in an <b>expression tree</b>. 
	 * 
	 * @param parent
	 * 			the target of this expression, which could be the <b>result</b> 
	 * 			of an expression itself. 
	 * 
	 * @param operator
	 * 			the operator which is used by this expression for <i>evaluation</i>.
	 * 			
	 * @param children
	 * 			the list of child {@link Expression}s which supply the argument(s) 
	 * 			to this node
	 *
	 * @since 1.1.0
	 */
	public Expression(Object parent, Operator operator, List<Expression> children) {
		
		this.parent = parent;
		this.operator = operator;
		this.children = children;
	}

	/**
	 * <p>Accessor for {@link #parent}.
	 *
	 * @return the {@link #parent}
	 */
	public Object getParent() {
		
		return parent;
	}

	/**
	 * <p>Accessor for {@link #operator}.
	 *
	 * @return the {@link #operator}
	 */
	public Operator getOperator() {
		
		return operator;
	}

	/**
	 * <p>Accessor for {@link #children}.
	 *
	 * @return the {@link #children}
	 */
	public List<Expression> getChildren() {
		
		return children;
	}
}
