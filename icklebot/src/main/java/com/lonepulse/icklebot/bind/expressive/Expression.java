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
	 * <p>The parent node of this expression tree.
	 */
	private Expression parent;
	
	/**
	 * <p>The operator which is used by this expression for <i>evaluation</i>. 
	 */
	private Operator operator;
	
	/**
	 * <p>The list of child {@link Expression}s which supply the argument(s) 
	 * to this node 
	 */
	private List<Expression> children;

	
	/**
	 * <p>Instantiates a new {@link Expression} without any state.
	 *
	 * @since 1.1.0
	 */
	public Expression() {}
	
	/**
	 * <p>Constructs a new node in an <b>expression tree</b>. 
	 * 
	 * @param parent
	 * 			the parent node of this {@link Expression} tree 
	 * 
	 * @param operator
	 * 			the operator which is used by this expression for <i>evaluation</i>
	 * 			
	 * @param children
	 * 			the list of child {@link Expression}s which supply the argument(s) 
	 * 			to this node
	 *
	 * @since 1.1.0
	 */
	public Expression(Expression parent, Operator operator, List<Expression> children) {
		
		this.parent = parent;
		this.operator = operator;
		this.children = children;
	}

	/**
	 * <p>Accessor for {@link #parent}.
	 *
	 * @return the {@link #parent}
	 */
	public Expression getParent() {
		
		return parent;
	}

	/**
	 * <p>Mutator for {@link #parent}.
	 *
	 * @param parent 
	 *			the {@link #parent} to set
	 */
	public void setParent(Expression parent) {
		
		this.parent = parent;
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
	 * <p>Mutator for {@link #operator}.
	 *
	 * @param operator 
	 *			the {@link #operator} to set
	 */
	public void setOperator(Operator operator) {
		
		this.operator = operator;
	}

	/**
	 * <p>Accessor for {@link #children}.
	 *
	 * @return the {@link #children}
	 */
	public List<Expression> getChildren() {
		
		return children;
	}

	/**
	 * <p>Mutator for {@link #children}.
	 *
	 * @param children 
	 *			the {@link #children} to set
	 */
	public void setChildren(List<Expression> children) {
		
		this.children = children;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("Expression [parent=");
		builder.append(parent);
		builder.append(", operator=");
		builder.append(operator);
		builder.append(", children=");
		builder.append(children);
		builder.append("]");
		
		return builder.toString();
	}
}
