package edu.sjsu.cs.cs151;

import java.util.Random;

import edu.sjsu.cs.cs151.model.Model;
import javax.swing.Timer;

/**
 * The Class GameInfo.
 */
public class GameInfo {
	/** The b width. */
	public final int B_WIDTH = 650;
	
	/** The b height. */
	public final int B_HEIGHT = 650;
	
	/** The all dots. */
	private final int ALL_DOTS = 625;
	
	/** The rand pos. */
	private final int RAND_POS = 25;
	
	/** The dot size. */
	public final int DOT_SIZE = 25;
	
	/** The delay. */
	private final int DELAY = 140;
	
	/** The b y. */
	public final int B_Y = 75;
	
	/** The b x. */
	public final int B_X=225;
	
	/** The random. */
	private Random random = new Random();

	/** The dots. */
	public int dots = 3;
	
	/** The x snake. */
	private int xSnake[];
	
	/** The y snake. */
	private int ySnake[];
	
	/** The apple X. */
	public int appleX;
	
	/** The apple Y. */
	public int appleY;

	/** The left direction. */
	public boolean leftDirection = false;
	
	/** The right direction. */
	public boolean rightDirection = true;
	
	/** The up direction. */
	public boolean upDirection = false;
	
	/** The down direction. */
	public boolean downDirection = false;
	
	/** The in game. */
	private boolean inGame = true;
	/** The score. */
	public int score;
	
	/** The high score. */
	public int highScore;

	/**
	 * Instantiates a new game info.
	 */
	public GameInfo() {
		
		xSnake = new int[650];
		ySnake = new int[650];

	}



	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int[] getX() {
		return xSnake;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int[] getY() {
		return ySnake;
	}
	
	/**
	 * Sets the in game.
	 *
	 * @param inGame the new in game
	 */
	public void setInGame(boolean inGame) {
		this.inGame = inGame;
		
	}
	
	/**
	 * Gets the apple X.
	 *
	 * @return the apple X
	 */
	public int getAppleX() {
		return appleX;
	}
	
	/**
	 * Gets the apple Y.
	 *
	 * @return the apple Y
	 */
	public int getAppleY() {
		return appleY;
	}
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore()
	{
		return score;
	}
	
	/**
	 * Gets the high score.
	 *
	 * @return the high score
	 */
	public int getHighScore()
	{
		return highScore;
	}
	
	/**
	 * Gets the in game.
	 *
	 * @return the in game
	 */
	public boolean getInGame()
	{
		return inGame;
	}
	
	/**
	 * Check collision.
	 */
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
