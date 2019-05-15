package edu.sjsu.cs.cs151.testCases;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.sjsu.cs.cs151.model.Background;

/**
 * The Class BackgroundTest.
 */
public class BackgroundTest {

	/**
	 * Test.
	 */
	@Test
	public void test() {
		Background background = new Background(900,900);
		assertNotNull(background);
		assertEquals(900, background.ROW_NUM);
		assertEquals(900, background.COL_NUM);
	}

}
