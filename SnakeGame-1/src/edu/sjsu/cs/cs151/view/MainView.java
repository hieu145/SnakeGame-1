package edu.sjsu.cs.cs151.view;

import java.awt.Color;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import edu.sjsu.cs.cs151.GameInfo;
import edu.sjsu.cs.cs151.Message;
import edu.sjsu.cs.cs151.model.Model;


/**
 * The Class MainView.
 */
public class MainView {
	
	/** The game info. */
	private GameInfo gameInfo;
	
	/** The model. */
	Model model; 
	
	/** The obj 1. */
	JFrame obj1 = new JFrame("Snake Game");
	
	/** The view. */
	View view;
	
	/**
	 * Clear.
	 */
	public void clear() {
		obj1.dispose();
	}

	/**
	 * Instantiates a new main view.
	 *
	 * @param queue the queue
	 * @param gameInfo the game info
	 */
	public MainView(BlockingQueue<Message> queue, GameInfo gameInfo) {

		this.gameInfo = gameInfo;
		view = new View (queue, gameInfo);
		obj1.setBounds(10, 10, 905, 700);
		obj1.setBackground(Color.DARK_GRAY);
		obj1.setResizable(false);
		obj1.setVisible(true);
		obj1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj1.add(view);

	}

	/**
	 * Update.
	 */
	public void update() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				view.update(gameInfo);
				obj1.repaint();
			}
		});

	}

}
