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

/**
 * The Class Controller.
 */
public class Controller {
	
	/** The view. */
	MainView view;
	
	/** The model. */
	Model model;
	
	/** The queue. */
	BlockingQueue<Message> queue;
	
	/** The direction valve. */
	private DirectionValve directionValve;
	
	/** The timer valve. */
	private TimerValve timerValve;
	
	/** The new game valve. */
	private NewGameValve newGameValve;
	
	/** The game info. */
	private GameInfo gameInfo;

	/**
	 * Instantiates a new controller.
	 *
	 * @param View the view
	 * @param model the model
	 * @param queue the queue
	 * @param gameInfo the game info
	 */
	public Controller(MainView View, Model model, BlockingQueue<Message> queue, GameInfo gameInfo) {
		this.gameInfo = gameInfo;
		this.view = View;
		this.model = model;
		this.queue = queue;
		this.directionValve = new DirectionValve();
		this.timerValve = new TimerValve();
		this.newGameValve = new NewGameValve();
	}


	/**
	 * Main loop.
	 *
	 * @throws Exception the exception
	 */
	public void mainLoop() throws Exception {		
		while (true) {
			Message message;
			while (!queue.isEmpty()) {
				message = queue.take();
				if (message instanceof DirectionMessage) {
					directionValve.execute(message);
				}
				else if(message instanceof TimerMessage) {
					timerValve.execute(message);
				}
				else if(message instanceof NewGameMessage) {
					newGameValve.execute(message);
				}
			}
			
			
			model.move();
			view.update(gameInfo);
			Thread.sleep(125);
		}
	}

	/**
	 * The Class NewGameValve.
	 */
	private class NewGameValve implements Valve {
		
		/* (non-Javadoc)
		 * @see edu.sjsu.cs.cs151.controller.Valve#execute(edu.sjsu.cs.cs151.Message)
		 */
		public ValveResponse execute(Message message) {
			if (message.getClass() != NewGameMessage.class) {
				return ValveResponse.MISS;
			}
			gameInfo.score=0;
			gameInfo.setInGame(true);
			gameInfo.leftDirection = false;
			gameInfo.rightDirection = true;
			gameInfo.upDirection = false;
			gameInfo.downDirection = false;
			model.initGame();
	
			view.update(gameInfo);

			return ValveResponse.EXECUTE;
		}
	}

	/**
	 * The Class TimerValve.
	 */
	private class TimerValve implements Valve {
		
		/* (non-Javadoc)
		 * @see edu.sjsu.cs.cs151.controller.Valve#execute(edu.sjsu.cs.cs151.Message)
		 */
		public ValveResponse execute(Message message) {
			if (message.getClass() != TimerMessage.class) {
				return ValveResponse.MISS;
			}
			if (model.getInGame()) {

				model.checkApple();
				model.checkCollision();
				model.move();
				
			}
			
			view.update(gameInfo);

			return ValveResponse.EXECUTE;
		}
	}

	/**
	 * The Class DirectionValve.
	 */
	private class DirectionValve implements Valve {
		
		/* (non-Javadoc)
		 * @see edu.sjsu.cs.cs151.controller.Valve#execute(edu.sjsu.cs.cs151.Message)
		 */
		public ValveResponse execute(Message message) {

			if (message.getClass() != DirectionMessage.class) {
				return ValveResponse.MISS;
			}
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
			view.update(gameInfo);
			return ValveResponse.EXECUTE;
		}
	}
}