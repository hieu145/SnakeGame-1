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
	private DirectionValve directionValve;
	private TimerValve timerValve;
	private NewGameValve newGameValve;
	private GameInfo gameInfo;

	public Controller(MainView View, Model model, BlockingQueue<Message> queue, GameInfo gameInfo) {
		this.gameInfo = gameInfo;
		this.view = View;
		this.model = model;
		this.queue = queue;
		this.directionValve = new DirectionValve();
		this.timerValve = new TimerValve();
		this.newGameValve = new NewGameValve();
	}


	public void mainLoop() throws Exception {		
		while (true) {
			Message message;
			while (!queue.isEmpty()) {
				message = queue.take();
				//System.out.println("isRunning");
				if (message instanceof DirectionMessage) {
					//System.out.println("Exectuing move");
					directionValve.execute(message);
				}
				else if(message instanceof TimerMessage) {
					timerValve.execute(message);
					//System.out.println("hello");
				}
				else if(message instanceof NewGameMessage) {
					newGameValve.execute(message);
					//System.out.println("running");
				}
			}
			
			
			model.move();
			view.update();
			Thread.sleep(125);
			//System.out.println("what");
		}
	}

	private class NewGameValve implements Valve {
		public ValveResponse execute(Message message) {
			if (message.getClass() != NewGameMessage.class) {
				return ValveResponse.MISS;
			}
			// action in Model
			gameInfo.score=0;
//			
			gameInfo.setInGame(true);
			gameInfo.leftDirection = false;
			gameInfo.rightDirection = true;
			gameInfo.upDirection = false;
			gameInfo.downDirection = false;
			model.initGame();
	

			// action in View
			view.update();

			return ValveResponse.EXECUTE;
		}
	}

	private class TimerValve implements Valve {
		public ValveResponse execute(Message message) {
			if (message.getClass() != TimerMessage.class) {
				return ValveResponse.MISS;
			}
			// action in Model
			if (model.getInGame()) {

				model.checkApple();
				model.checkCollision();
				model.move();
				
			}
			
			// action in View
			view.update();

			return ValveResponse.EXECUTE;
		}
	}

	private class DirectionValve implements Valve {
		public ValveResponse execute(Message message) {

			if (message.getClass() != DirectionMessage.class) {
				return ValveResponse.MISS;
			}
			// action in Model
			DirectionMessage msg = (DirectionMessage) message;
			if ((msg.direction == Directions.LEFT) && (!gameInfo.rightDirection)) {
				gameInfo.leftDirection = true;
				gameInfo.upDirection = false;
				gameInfo.downDirection = false;
				
			}

			if ((msg.direction == Directions.RIGHT) && (!gameInfo.leftDirection)) {
				gameInfo.rightDirection = true;
				gameInfo.upDirection = false;
				gameInfo.downDirection = false;
			
			}

			if ((msg.direction == Directions.UP) && (!gameInfo.downDirection)) {
				gameInfo.upDirection = true;
				gameInfo.rightDirection = false;
				gameInfo.leftDirection = false;
			
			}

			if ((msg.direction == Directions.DOWN) && (!gameInfo.upDirection)) {
				gameInfo.downDirection = true;
				gameInfo.rightDirection = false;
				gameInfo.leftDirection = false;
				
			}
			// action in View
			view.update();
//			System.out.println("execute");
			return ValveResponse.EXECUTE;
		}
	}
}