package edu.sjsu.cs.cs151;

import java.util.Random;

import edu.sjsu.cs.cs151.model.Model;
import javax.swing.Timer;
public class GameInfo {
	// create the board
	//updates
	final int B_WIDTH = 650;
	final int B_HEIGHT = 650;
	private final int ALL_DOTS = 625;
	private final int RAND_POS = 25;
	private final int DOT_SIZE = 25;
	private final int DELAY = 140;

	private Random random = new Random();

	// create the snake
	int dots = 3;
	public int xSnake[];
	public int ySnake[];
	
	// create the apple
	public int appleX;
	public int appleY;

	// moving snake. snake move to the right at the begining
	public boolean leftDirection = false;
	public boolean rightDirection = true;
	public boolean upDirection = false;
	public boolean downDirection = false;
	private boolean inGame;

	// create the score
	public int score;
	public int highScore;

	public GameInfo(Model model) {
		xSnake = model.getSnakeX();
		ySnake = model.getSnakeY();
		appleX = model.getApple_x();
		appleY = model.getApple_y();
		score = model.getScore();
		highScore = model.getHighscore();
		this.inGame = model.getInGame();
	}
public void initGame() {
		
		dots = 3;
				
		xSnake[2] = 250;
		xSnake[1] = 275;
		xSnake[0] = 300;
		ySnake[2] = 250;
		ySnake[1] = 250;
		ySnake[0] = 250;

		
		

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
	public int getScore()
	{
		return score;
	}
	public int getHighScore()
	{
		return highScore;
	}
	public boolean getInGame()
	{
		return inGame;
	}
	public void checkCollision() {
		for (int z = dots; z > 0; z--) {

			if ((z > 4) && (xSnake[0] == xSnake[z]) && (ySnake[0] == ySnake[z])) {
				inGame = false;
			}
		}

		if (ySnake[0] >= B_HEIGHT) {
			inGame = false;
		}

		if (ySnake[0] < 0) {
			inGame = false;
		}

		if (xSnake[0] >= B_WIDTH) {
			inGame = false;
		}

		if (xSnake[0] < 0) {
			inGame = false;
		}
	}
	
}
