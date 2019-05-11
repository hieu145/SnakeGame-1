package edu.sjsu.cs.cs151.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

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
		gameInfo.score = model.score;
		gameInfo.apple_x = model.getApple_x();
		gameInfo.apple_y = model.getApple_y();
		gameInfo.x = model.getSnakeX();
		gameInfo.y = model.getSnakeY();
	}
	public void mainLoop() throws Exception {
		valves.add(new DoNewGameMessage());
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

	private class DoNewGameMessage implements Valve {
		public ValveResponse execute(Message message) {
			if (message.getClass() != NewGameMessage.class) {
				return ValveResponse.MISS;
			}
			// action in Model
			model.score = 0;
			model.inGame = true;
			model.leftDirection = false;
			model.rightDirection = true;
			model.upDirection = false;
			model.downDirection = false;
			model.initGame();
			updateGameInfo();

			// action in View
			view.update();

			return ValveResponse.EXECUTE;
		}
	}

	private class DoTimerMessage implements Valve {
		public ValveResponse execute(Message message) {
			if (message.getClass() != TimerMessage.class) {
				return ValveResponse.MISS;
			}
			// action in Model
			if (model.inGame) {

				model.checkApple();
				model.checkCollision();
				model.move();
			}

			// action in View
			view.update();

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
			// action in View
			view.update();

			return ValveResponse.EXECUTE;
		}
	}
}