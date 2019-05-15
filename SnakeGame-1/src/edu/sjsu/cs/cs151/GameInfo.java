package edu.sjsu.cs.cs151;

import java.util.Random;

import edu.sjsu.cs.cs151.model.Model;
import javax.swing.Timer;
public class GameInfo {
	// create the board
	//updates
	public final int B_WIDTH = 650;
	public final int B_HEIGHT = 650;
	private final int ALL_DOTS = 625;
	private final int RAND_POS = 25;
	public final int DOT_SIZE = 25;
	private final int DELAY = 140;
	public final int B_Y = 75;
	public final int B_X=225;
	private Random random = new Random();

	// create the snake
	public int dots = 3;
	private int xSnake[];
	private int ySnake[];
	
	// create the apple
	public int appleX;
	public int appleY;

	// moving snake. snake move to the right at the begining
	public boolean leftDirection = false;
	public boolean rightDirection = true;
	public boolean upDirection = false;
	public boolean downDirection = false;
	private boolean inGame = true;
	//Model model = new Model();
	// create the score
	public int score;
	public int highScore;

	public GameInfo() {
		
		xSnake = new int[650];
		ySnake = new int[650];

	}



	public int[] getX() {
		return xSnake;
	}

	public int[] getY() {
		return ySnake;
	}
	public void setInGame(boolean inGame) {
		this.inGame = inGame;
		
	}
	public int getAppleX() {
		return appleX;
	}
	public int getAppleY() {
		return appleY;
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
