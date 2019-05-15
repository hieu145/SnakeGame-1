package edu.sjsu.cs.cs151.view;

import java.awt.Color;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import edu.sjsu.cs.cs151.GameInfo;
import edu.sjsu.cs.cs151.Message;
import edu.sjsu.cs.cs151.model.Model;

public class MainView {
	private GameInfo gameInfo;
	
	Model model; 
	JFrame obj1 = new JFrame("Snake Game");
	View view;
	public void clear() {
		obj1.dispose();
	}

	public MainView(BlockingQueue<Message> queue, GameInfo gameInfo) {

		this.gameInfo = gameInfo;
		//view.loadImages();
		view = new View (queue, gameInfo);
		obj1.setBounds(10, 10, 905, 700);
		obj1.setBackground(Color.DARK_GRAY);
		obj1.setResizable(false);
		obj1.setVisible(true);
		obj1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj1.add(view);

	}

	public void update() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				view.update(gameInfo);
				obj1.repaint();
			}
		});

	}

}
