package TestCase;

import static org.junit.Assert.*;

import org.junit.Test;

import snakeGame.Food;

public class FoodTest {

	@Test
	public void test() {
		Food food = new Food();
		assertNull(food.getApple_x());
		assertNull(food.getApple_y());
		food.locateApple();
		assertNotNull(food);
        System.out.print("position of food" + Integer.toString(food.getApple_x()) + "," + Integer.toString(food.getApple_y()));
	}

}
