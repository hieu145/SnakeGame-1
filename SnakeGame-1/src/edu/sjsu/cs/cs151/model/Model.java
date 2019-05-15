package edu.sjsu.cs.cs151.model;
import javax.swing.Timer;

import edu.sjsu.cs.cs151.GameInfo;

import java.util.Random;
//Create the Model for the snakegame including board, snake and apple
//test commit
public class Model {
	// create the board
	GameInfo gameInfo;
	
	public Model(GameInfo gameInfo) {
		this.gameInfo = gameInfo;
		initGame();
	}
	public boolean getInGame()
	{
		return gameInfo.getInGame();
	}
	public int[] getSnakeX() {
		return gameInfo.getX();
	}

	public int[] getSnakeY() {
		return gameInfo.getY();
	}

	public int getApple_x() {
		return gameInfo.getAppleX();
	}

	public int getApple_y() {
		//locateApple();
		return gameInfo.getAppleY();
	}

	// initialize
	public void initGame() {
		
				
		gameInfo.getX()[2] = 250;
		gameInfo.getX()[1] = 275;
		gameInfo.getX()[0] = 300;
		gameInfo.getY()[2] = 250;
		gameInfo.getY()[1] = 250;
		gameInfo.getY()[0] = 250;

		locateApple();
		

	}

	// check the snake eat apple and make snake longer
	public void checkApple() {
		if ((gameInfo.getX()[0] == gameInfo.getAppleX()) && (gameInfo.getY()[0] == gameInfo.getAppleY())) {
			gameInfo.dots++;
			gameInfo.score++;
			sethighScore(gameInfo.score);
			locateApple();
		}
	}

	public int getScore() {
		return gameInfo.getScore();
	}

public void setScore(int s) {
	gameInfo.score = s;
}
	public int getHighscore() {
		return gameInfo.getHighScore();
	}

	private void sethighScore(int x) {
		if (gameInfo.getHighScore() < x)
			gameInfo.highScore = x;
	}

	// Move the snake
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

	// check the collision for Gameover
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

	// create apple location
	public void locateApple() {
	//	Object random = null;
		int r = (int) (Math.random() * gameInfo.DOT_SIZE);
		gameInfo.appleX = ((r * gameInfo.DOT_SIZE) + 225);
		r = (int) (Math.random() * gameInfo.DOT_SIZE);
		gameInfo.appleY = ((r * gameInfo.DOT_SIZE) + 75);

	}

}
