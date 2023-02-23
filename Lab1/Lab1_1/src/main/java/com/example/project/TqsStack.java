package com.example.project;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TqsStack<T> {
	LinkedList<T> stack = new LinkedList<T>();
	Integer bound = null;

	public TqsStack() {
		this.stack = new LinkedList<>();
	}
	
	public TqsStack(int bound) {
		this.stack = new LinkedList<>();
		this.bound = bound;
	}

	public void push(T w){
		if ((this.bound != null) && (this.size() > this.bound - 1) ) {
			throw new IllegalStateException("Error pushing element!");
		
		} 
		else { stack.add(w); }
	}
	public int size(){
		return stack.size();
	}

	public T peek(){
		if (this.stack.isEmpty()) { 
			throw new NoSuchElementException("Error peeking element"); 
		
		  } else { return this.stack.peek(); }
	}

	public T pop(){
		if (this.stack.isEmpty()) { 
			throw new NoSuchElementException("Error popping element"); 
		
		} 
		else { return this.stack.pop(); }
		
	}

	public Boolean isEmpty(){
		return stack.isEmpty();
	}
	
	public void clear(){
		stack.removeAll(stack);
	}

}
