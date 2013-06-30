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
 * <p>Represents the textual format of an {@link Operator}. All symbols must 
 * have a <b>head</b>, although the <b>tail</b> is optional. The data which 
 * an {@link Operator} acts on is bound between the head and tail. 
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class Symbol {

	
	/**
	 * <p>Identifies the left bounds of an operator.
	 */
	private final String head;
	
	/**
	 * <p>Identifies the right bounds of an operator.
	 */
	private final String tail;

	
	/**
	 * <p>Instantiates a new {@link Symbol} using the given head and tail, if 
	 * the tail is {@code null}, an empty string is used.
	 * 
	 * @param head
	 * 			identifies the left bounds of an operator
	 * 
	 * @param tail
	 * 			identifies the right bound of an operator
	 * 
	 * @throws IllegalArgumentException
	 * 			if the supplied {@link #head} is {@code null} or empty
	 *
	 * @since 1.1.0
	 */
	public Symbol(String head, String tail) {
		
		if(head == null || head.isEmpty())
			throw new IllegalArgumentException("A symbol cannot be constructed without a head. ");
		
		if(head.length() > 2)
			throw new IllegalArgumentException("The symbol head can consist of a maximum of 2 characters. ");
		
		if(tail != null && tail.length() > 2)
				throw new IllegalArgumentException("The symbol tail can consist of a maximum of 2 characters. ");
		
		this.head = head;
		this.tail = (tail == null || tail.isEmpty())? "" :tail;
	}

	/**
	 * <p>Accessor for {@link #head}.
	 *
	 * @return the {@link #head}
	 */
	public String getHead() {
		
		return head;
	}

	/**
	 * <p>Accessor for {@link #tail}.
	 *
	 * @return the {@link #tail}
	 */
	public String getTail() {
		
		return tail;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		
		final int prime = 31;
		
		int result = 1;
		result = prime * result + ((head == null) ? 0 : head.hashCode());
		result = prime * result + ((tail == null) ? 0 : tail.hashCode());
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		Symbol other = (Symbol) obj;
		
		if (head == null) {
			
			if (other.head != null) return false;
		} 
		else if (!head.equals(other.head)) return false;
		
		if (tail == null) {
		
			if (other.tail != null) return false;
		} 
		else if (!tail.equals(other.tail)) return false;
		
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		
		return new StringBuilder()
				.append(" ").append(head).append(" ")
				.append(tail).append(" ").toString();
	}
}
