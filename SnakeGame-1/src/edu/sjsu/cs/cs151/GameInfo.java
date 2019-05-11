package edu.sjsu.cs.cs151;

import java.util.Random;

public class GameInfo {
	//create the board
		final int B_WIDTH = 650;
	    final int B_HEIGHT = 650;
		private final int ALL_DOTS = 625;
	    private final int RAND_POS = 25;
	    private final int DOT_SIZE = 25;
	    private final int DELAY = 140;
	    
	    private Random random = new Random();
	    
	    //create the snake
	    int dots = 3;
	    public int x[] = new int[ALL_DOTS];
	    public int y[] = new int[ALL_DOTS];
	  //create the apple
	    public int apple_x;
	    public int apple_y;
	    
	    //moving snake. snake move to the right at the begining
	    public boolean leftDirection = false;
	    public boolean rightDirection = true;
	    public boolean upDirection = false;
	    public boolean downDirection = false;
	    public boolean inGame = true;
	    
	    //create the score
	    public int score = 0;
	     
}
