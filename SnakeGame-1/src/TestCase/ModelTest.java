package TestCase;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Model;

class ModelTest {

	@Test
	void test() {
		Model model = new Model();
		
		//test location of apple
		assertNull(model.getApple_x());
		assertNull(model.getApple_y());
		model.initGame();
		assertNotNull(model);
        System.out.print("position of food" + Integer.toString(model.getApple_x()) + "," + Integer.toString(model.getApple_y()));
		
        //check collision 
        assertTrue(model.inGame);
        model.x[0] = 0;
        assertFalse(model.inGame);
        model.x[0] = 300;
        assertTrue(model.inGame);
        model.x[0] = 650;
        assertFalse(model.inGame);
        model.x[0] = 50;
        assertTrue(model.inGame);
        
        model.y[0] = 0;
        assertFalse(model.inGame);
        model.y[0] = 100;
        assertTrue(model.inGame);
        model.y[0] = 650;
        assertFalse(model.inGame);
        
        
        
	}

}
