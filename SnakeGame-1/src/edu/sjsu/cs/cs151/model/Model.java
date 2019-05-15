package edu.sjsu.cs.cs151.model;
import javax.swing.Timer;

import edu.sjsu.cs.cs151.GameInfo;

import java.util.Random;

/**
 * The Class Model.
 */
public class Model {
	
	/** The game info. */
	GameInfo gameInfo;
	
	/**
	 * Instantiates a new model.
	 *
	 * @param gameInfo the game info
	 */
	public Model(GameInfo gameInfo) {
		this.gameInfo = gameInfo;
		initGame();
	}
	
	/**
	 * Gets the in game.
	 *
	 * @return the in game
	 */
	public boolean getInGame()
	{
		return gameInfo.getInGame();
	}
	
	/**
	 * Gets the snake X.
	 *
	 * @return the snake X
	 */
	public int[] getSnakeX() {
		return gameInfo.getX();
	}

	/**
	 * Gets the snake Y.
	 *
	 * @return the snake Y
	 */
	public int[] getSnakeY() {
		return gameInfo.getY();
	}

	/**
	 * Gets the apple x.
	 *
	 * @return the apple x
	 */
	public int getApple_x() {
		return gameInfo.getAppleX();
	}

	/**
	 * Gets the apple y.
	 *
	 * @return the apple y
	 */
	public int getApple_y() {
		return gameInfo.getAppleY();
	}

	/**
	 * Inits the game.
	 */
	public void initGame() {
		
				
		gameInfo.getX()[2] = 250;
		gameInfo.getX()[1] = 275;
		gameInfo.getX()[0] = 300;
		gameInfo.getY()[2] = 250;
		gameInfo.getY()[1] = 250;
		gameInfo.getY()[0] = 250;

		locateApple();
		

	}

	/**
	 * Check apple.
	 */
	public void checkApple() {
		if ((gameInfo.getX()[0] == gameInfo.getAppleX()) && (gameInfo.getY()[0] == gameInfo.getAppleY())) {
			gameInfo.dots++;
			gameInfo.score++;
			sethighScore(gameInfo.score);
			locateApple();
		}
	}

	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return gameInfo.getScore();
	}

/**
 * Sets the score.
 *
 * @param s the new score
 */
public void setScore(int s) {
	gameInfo.score = s;
}
	
	/**
	 * Gets the highscore.
	 *
	 * @return the highscore
	 */
	public int getHighscore() {
		return gameInfo.getHighScore();
	}

	/**
	 * Sets the high score.
	 *
	 * @param x the new high score
	 */
	private void sethighScore(int x) {
		if (gameInfo.getHighScore() < x)
			gameInfo.highScore = x;
	}

	/**
	 * Move.
	 */
	public void move() {
		if (!gameInfo.getInGame()) return;
		
		for (int z = gameInfo.dots; z > 0; z--) {
			gameInfo.getX()[z] = gameInfo.getX()[(z - 1)];
			gameInfo.getY()[z] = gameInfo.getY()[(z - 1)];
		}

		if (gameInfo.leftDirection) {
			gameInfo.getX()[0] -= gameInfo.DOT_SIZE;
		}

		if (gameInfo.rightDirection) {
			gameInfo.getX()[0] += gameInfo.DOT_SIZE;
		}

		if (gameInfo.upDirection) {
			gameInfo.getY()[0] -= gameInfo.DOT_SIZE;
		}

		if (gameInfo.downDirection) {
			gameInfo.getY()[0] += gameInfo.DOT_SIZE;
		}
		checkApple();
		checkCollision();
	}

	/**
	 * Check collision.
	 */
	public void checkCollision() {

		for (int z = gameInfo.dots; z > 0; z--) {

			if ((z > 4) && (gameInfo.getX()[0] == gameInfo.getX()[z]) && (gameInfo.getY()[0] == gameInfo.getY()[z])) {
				gameInfo.setInGame(false);
			}
		}

		if (gameInfo.getY()[0] >= gameInfo.B_HEIGHT+ gameInfo.B_Y) {
			gameInfo.setInGame(false);
		}

		if (gameInfo.getY()[0] <= 55) {
			gameInfo.setInGame(false);
		}

		if (gameInfo.getX()[0] + 12 >= gameInfo.B_WIDTH+gameInfo.B_X) {
			gameInfo.setInGame(false);
		}

		if (gameInfo.getX()[0] <= gameInfo.B_X) {
			gameInfo.setInGame(false);
		}
		
		
	}

	/**
	 * Locate apple.
	 */
	public void locateApple() {
		int r = (int) (Math.random() * gameInfo.DOT_SIZE);
		gameInfo.appleX = ((r * gameInfo.DOT_SIZE) + 225);
		r = (int) (Math.random() * gameInfo.DOT_SIZE);
		gameInfo.appleY = ((r * gameInfo.DOT_SIZE) + 75);

	}

}
