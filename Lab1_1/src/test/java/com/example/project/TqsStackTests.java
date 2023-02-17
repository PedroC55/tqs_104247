package com.example.project;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TqsStackTests {

	private TqsStack<String> Stack;

		@BeforeEach // Execute before each test
		public void testBeforeEach() throws Exception {
			Stack = new TqsStack<>();
		}

    @AfterEach
    public void clear() {
        Stack.clear();
    }


	@Test
	@DisplayName("Stack is empty on construction")
	void isEmpty() {
		assertTrue(Stack.isEmpty());
		Stack.push("1");
		assertFalse(Stack.isEmpty());
	}

	@Test
	@DisplayName("Stack has size 0 on construction")
	void size() {

		assertEquals(0, Stack.size());

		Stack.push("word");
		assertEquals(1, Stack.size());

		Stack.pop();
		assertEquals(0, Stack.size());
	}

	@Test
	@DisplayName("After n pushes to an emptystack, n > 0, the stack is not empty and its size is n")
	void pushOnEmpty() {
		assertTrue(Stack.isEmpty());

		Stack.push("a");
		Stack.push("b");
		Stack.push("c");
		Stack.push("d");

		assertFalse(Stack.isEmpty());
		assertEquals(4, Stack.size());
		
	}

	@Test
	@DisplayName("If one pushes x then pops, the value popped is x")
	void pushthenpop() {
		Stack.push("must pop");
		assertEquals("must pop", Stack.pop());
		
	}

	@Test
	@DisplayName("If one pushes x then peeks, the value returned is x, but the size stays the same")
	void pushthenpeek() {
		Stack.push("peeked");
		assertEquals("peeked", Stack.peek());
		assertEquals(1, Stack.size());
		
	}

	@Test
	@DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")
	void emptyAfterPop() {
		Stack.push("1");
		Stack.push("2");
		Stack.push("3");
		Stack.push("4");
		assertEquals(4, Stack.size());

		Stack.pop();
		Stack.pop();
		Stack.pop();
		Stack.pop();

		assertEquals(0, Stack.size());
		
	}

	@Test
	@DisplayName("Popping from an empty stack does throw a NoSuchElementException")
	void popthenException() {
		assertTrue(Stack.isEmpty());

		
		assertThrows(NoSuchElementException.class, () -> Stack.pop()); 
		
	}

	@Test
	@DisplayName("Peeking into an empty stack does throw a NoSuchElementException")
	void peekthenException() {
		assertTrue(Stack.isEmpty());

		
		assertThrows(NoSuchElementException.class, () -> Stack.peek()); 
		
	}

	@DisplayName("For bounded stacks only:pushing onto a full stack does throw an IllegalStateException")
    @Test
    void pushForBound(){
        TqsStack<String> boundStack = new TqsStack<>(5);

        boundStack.push("a");
        boundStack.push("b");
        boundStack.push("c");
		boundStack.push("d");
		boundStack.push("e");

        assertThrows(IllegalStateException.class, () -> boundStack.push("f")); 

	}


	
}
