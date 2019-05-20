package edu.sjsu.cs.cs151.testCases;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.sjsu.cs.cs151.model.Snake;

/**
 * The Class SnakeTest.
 */
public class SnakeTest {

	/**
	 * Test.
	 */
	@Test
	public void test() {
		Snake snake = new Snake();
		
		assertNotNull(snake.getDots());
		assertNotNull(snake.getX());
		assertNotNull(snake.getX());
		
		int arr2[]={50,40,30,20};
		int arr1[]={50,50,50};
		snake.initSnake();
		assertEquals(3,snake.getDots());
		
		
		assertTrue(snake.isRightDirection());
		assertFalse(snake.isLeftDirection());
		assertFalse(snake.isUpDirection());
		assertFalse(snake.isDownDirection());
	}

}
