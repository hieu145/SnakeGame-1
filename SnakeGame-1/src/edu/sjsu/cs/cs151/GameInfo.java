package edu.sjsu.cs.cs151;

import java.util.Random;

import edu.sjsu.cs.cs151.model.Model;

public class GameInfo {
	// create the board
	final int B_WIDTH = 650;
	final int B_HEIGHT = 650;
	private final int ALL_DOTS = 625;
	private final int RAND_POS = 25;
	private final int DOT_SIZE = 25;
	private final int DELAY = 140;

	private Random random = new Random();

	// create the snake
	int dots = 3;
	private int xSnake[];
	private int ySnake[];
	
	// create the apple
	private int appleX;
	private int appleY;

	// moving snake. snake move to the right at the begining
	public boolean leftDirection = false;
	public boolean rightDirection = true;
	public boolean upDirection = false;
	public boolean downDirection = false;
	public boolean inGame = true;

	// create the score
	private int score;
	private int highScore;

	public GameInfo(Model model) {
		xSnake = model.getSnakeX();
		ySnake = model.getSnakeY();
		appleX = model.getApple_x();
		appleY = model.getApple_y();
		score = model.getScore();
		highScore = model.getHighscore();
		inGame = true;
	}

	public int[] getX() {
		return this.xSnake;
	}

	public int[] getY() {
		return this.ySnake;
	}
	public int getAppleX() {
		return this.appleX;
	}
	public int getAppleY() {
		return this.appleY;
	}
	

}
