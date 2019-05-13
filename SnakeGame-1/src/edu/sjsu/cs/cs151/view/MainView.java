package edu.sjsu.cs.cs151.view;

import java.awt.Color;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import edu.sjsu.cs.cs151.GameInfo;
import edu.sjsu.cs.cs151.Message;

public class MainView {
	JFrame obj1 = new JFrame("Snake Game");

	public void clear() {
		obj1.dispose();
	}

	public MainView(BlockingQueue<Message> queue) {

		View view = new View();
		view.initView(queue);
		obj1.setBounds(10, 10, 905, 700);
		obj1.setBackground(Color.DARK_GRAY);
		obj1.setResizable(false);
		obj1.setVisible(true);
		obj1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj1.add(view);

	}

	public void update(final GameInfo gameInfo) {
		// check slide 20
		boolean endGame = false;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if(!endGame)
				{
					
				}
					;
			}
		});
	}

}
