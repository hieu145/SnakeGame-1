package edu.sjsu.cs.cs151.model;




//Create the Model for the snakegame including board, snake and apple
public class Model {
	// create the board
	final int B_WIDTH = 650;
	final int B_HEIGHT = 650;
	private final int ALL_DOTS = 625;
	private final int RAND_POS = 25;
	private final int DOT_SIZE = 25;
	private int score = 0;
	private int highScore = 0;

	// create the snake
	public int dots = 3;
	public int x[] = new int[ALL_DOTS];
	public int y[] = new int[ALL_DOTS];

	// create the apple
	private int apple_x;
	private int apple_y;

	// moving snake. snake move to the right at the begining
	public boolean leftDirection = false;
	public boolean rightDirection = true;
	public boolean upDirection = false;
	public boolean downDirection = false;
	private boolean inGame = true;

	public Model() {
		initGame();
	}
	public boolean getInGame()
	{
		return inGame;
	}
	public int[] getSnakeX() {
		return x;
	}

	public int[] getSnakeY() {
		return y;
	}

	public int getApple_x() {
		return apple_x;
	}

	public int getApple_y() {
		return apple_y;
	}

	// initialize
	public void initGame() {
		
		dots = 3;
				
		x[2] = 250;
		x[1] = 275;
		x[0] = 300;
		y[2] = 250;
		y[1] = 250;
		y[0] = 250;

		locateApple();
		

	}

	// check the snake eat apple and make snake longer
	public void checkApple() {
		if ((x[0] == apple_x) && (y[0] == apple_y)) {
			dots++;
			score++;
			sethighScore(score);
			locateApple();
		}
	}

	public int getScore() {
		return score;
	}

public void setScore(int s) {
score = s;
}
	public int getHighscore() {
		return highScore;
	}

	private void sethighScore(int x) {
		if (highScore < x)
			highScore = x;
	}

	// Move the snake
	public void move() {

		for (int z = dots; z > 0; z--) {
			x[z] = x[(z - 1)];
			y[z] = y[(z - 1)];
		}

		if (leftDirection) {
			x[0] -= DOT_SIZE;
		}

		if (rightDirection) {
			x[0] += DOT_SIZE;
		}

		if (upDirection) {
			y[0] -= DOT_SIZE;
		}

		if (downDirection) {
			y[0] += DOT_SIZE;
		}
	}

	// check the collision for Gameover
	public void checkCollision() {

		for (int z = dots; z > 0; z--) {

			if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
				inGame = false;
			}
		}

		if (y[0] >= B_HEIGHT) {
			inGame = false;
		}

		if (y[0] < 0) {
			inGame = false;
		}

		if (x[0] >= B_WIDTH) {
			inGame = false;
		}

		if (x[0] < 0) {
			inGame = false;
		}
	
	}

	// create apple location
	public void locateApple() {

		int r = (int) (Math.random() * RAND_POS);
		apple_x = ((r * DOT_SIZE) + 225);
		r = (int) (Math.random() * RAND_POS);
		apple_y = ((r * DOT_SIZE) + 75);

	}

}
