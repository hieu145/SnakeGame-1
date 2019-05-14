package edu.sjsu.cs.cs151.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;


import edu.sjsu.cs.cs151.model.Model;
import edu.sjsu.cs.cs151.DirectionMessage;
import edu.sjsu.cs.cs151.TimerMessage;
import edu.sjsu.cs.cs151.Message;
import edu.sjsu.cs.cs151.NewGameMessage;
import edu.sjsu.cs.cs151.Directions;
import edu.sjsu.cs.cs151.GameInfo;
import edu.sjsu.cs.cs151.view.MainView;
import edu.sjsu.cs.cs151.view.View;

public class Controller {
	MainView view;
	Model model;
	BlockingQueue<Message> queue;
	private List<Valve> valves = new LinkedList<Valve>();
	private GameInfo gameInfo;

	public Controller(MainView View, Model model, BlockingQueue<Message> queue) {
		this.view = View;
		this.model = model;
		this.queue = queue;
	}

	private void updateGameInfo() {
		gameInfo.downDirection = model.downDirection;
		gameInfo.upDirection = model.upDirection;
		gameInfo.rightDirection = model.rightDirection;
		gameInfo.leftDirection = model.leftDirection;
		int score = model.getScore();
		int appleX = model.getApple_x();
		int appleY = model.getApple_y();
		int[] xSnake = model.getSnakeX();
		int[] ySnake = model.getSnakeY();
	}
	public void mainLoop() throws Exception {
		valves.add(new DoNewGameValve());
		valves.add(new DoDirectionMessage());
		ValveResponse response = ValveResponse.EXECUTE;
		Message message = null;
		while (response != ValveResponse.FINISH) {
			try {
				message = (Message) queue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private class DoNewGameValve implements Valve {
		public ValveResponse execute(Message message) {
			if (message.getClass() != NewGameMessage.class) {
				return ValveResponse.MISS;
			}
			// action in Model
			model.setScore(0);
			model.getInGame();
			model.leftDirection = false;
			model.rightDirection = true;
			model.upDirection = false;
			model.downDirection = false;
			model.initGame();
			updateGameInfo();

			// action in View
			view.update(gameInfo);

			return ValveResponse.EXECUTE;
		}
	}

	private class DoTimerMessage implements Valve {
		public ValveResponse execute(Message message) {
			if (message.getClass() != TimerMessage.class) {
				return ValveResponse.MISS;
			}
			// action in Model
			if (model.getInGame()) {

				model.checkApple();
				model.checkCollision();
				model.move();
				updateGameInfo();
			}
			
			// action in View
			view.update(gameInfo);

			return ValveResponse.EXECUTE;
		}
	}

	private class DoDirectionMessage implements Valve {
		public ValveResponse execute(Message message) {

			if (message.getClass() != DirectionMessage.class) {
				return ValveResponse.MISS;
			}
			// action in Model
			DirectionMessage msg = (DirectionMessage) message;
			if ((msg.direction == Directions.LEFT) && (!model.rightDirection)) {
				model.leftDirection = true;
				model.upDirection = false;
				model.downDirection = false;
				
			}

			if ((msg.direction == Directions.RIGHT) && (!model.leftDirection)) {
				model.rightDirection = true;
				model.upDirection = false;
				model.downDirection = false;
			
			}

			if ((msg.direction == Directions.UP) && (!model.downDirection)) {
				model.upDirection = true;
				model.rightDirection = false;
				model.leftDirection = false;
			
			}

			if ((msg.direction == Directions.DOWN) && (!model.upDirection)) {
				model.downDirection = true;
				model.rightDirection = false;
				model.leftDirection = false;
				
			}
			updateGameInfo();
			// action in View
			view.update(gameInfo);

			return ValveResponse.EXECUTE;
		}
	}
}