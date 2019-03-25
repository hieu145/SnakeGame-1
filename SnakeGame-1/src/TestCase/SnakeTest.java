package TestCase;

import static org.junit.Assert.*;

import org.junit.Test;

import snakeGame.Snake;

public class SnakeTest {

	@Test
	public void test() {
		Snake snake = new Snake();
		Snake snake1 = new Snake();
		assertNull(snake.getDots());
		assertNull(snake.getX());
		assertNull(snake.getX());
		
		int arr2[]={50,40,30};
		int arr1[]={50,50,50};
		snake.initSnake();
		assertEquals(3,snake.getDots());
		assertEquals(arr2,snake.getX());
		assertEquals(arr1,snake.getY());
		
		assertTrue(snake.isRightDirection());
		assertFalse(snake.isLeftDirection());
		assertFalse(snake.isUpDirection());
		assertFalse(snake.isDownDirection());
	}

}
