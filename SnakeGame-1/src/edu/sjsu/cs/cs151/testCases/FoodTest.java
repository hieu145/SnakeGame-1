package edu.sjsu.cs.cs151.testCases;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.sjsu.cs.cs151.model.Food;

/**
 * The Class FoodTest.
 */

public class FoodTest {

	/**
	 * Test.
	 */
	@Test
	public void test() {
		Food food = new Food();
		assertNotNull(food.getApple_x());
		assertNotNull(food.getApple_y());
		food.locateApple();
		assertNotNull(food);
        System.out.print("position of food" + Integer.toString(food.getApple_x()) + "," + Integer.toString(food.getApple_y()));
	}

}
