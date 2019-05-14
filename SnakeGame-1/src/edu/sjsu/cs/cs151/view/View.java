package edu.sjsu.cs.cs151.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.BlockingQueue;

import javax.swing.*;

import edu.sjsu.cs.cs151.DirectionMessage;
import edu.sjsu.cs.cs151.Directions;
import edu.sjsu.cs.cs151.GameInfo;
import edu.sjsu.cs.cs151.Message;
import edu.sjsu.cs.cs151.NewGameMessage;
import edu.sjsu.cs.cs151.TimerMessage;
//import edu.sjsu.cs.cs151.model.Model;

//draw the model

public class View extends JPanel implements ActionListener {
	//Model model;
	//private boolean inGame = true;
	private Image rightmouth, leftmouth, upmouth, downmouth, modelbody;
	private Image apple;
	private Image titleImage;
	BlockingQueue<Message> queue;
	int dots = 3;
	private int viewXSnake[];
	private int viewYSnake[];
	
	// create the apple
	private int viewAppleX;
	private int viewAppleY;

	// moving snake. snake move to the right at the begining
	public boolean leftDirection = false;
	public boolean rightDirection = true;
	public boolean upDirection = false;
	public boolean downDirection = false;
	public boolean inGame = true;

	// create the score
	private int viewScore;
	private int viewHighScore;

	public void initView(BlockingQueue<Message> queue) {
		this.queue = queue;
		loadImages();
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		addKeyListener(new TAdapter());
		Timer timer = new Timer(140, this);
		timer.start();
	}

	// draw image of the model and apple
	void loadImages() {
		ImageIcon rm = new ImageIcon("rightmouth.png");
		rightmouth = rm.getImage();

		ImageIcon lm = new ImageIcon("leftmouth.png");
		leftmouth = lm.getImage();

		ImageIcon um = new ImageIcon("upmouth.png");
		upmouth = um.getImage();

		ImageIcon dm = new ImageIcon("downmouth.png");
		downmouth = dm.getImage();

		ImageIcon sb = new ImageIcon("modelimage.png");
		modelbody = sb.getImage();

		ImageIcon ap = new ImageIcon("enemy.png");
		apple = ap.getImage();

		ImageIcon tt = new ImageIcon("modeltitle.jpg");
		titleImage = tt.getImage();
	}
	public void update(GameInfo gameInfo) {
		this.viewXSnake = gameInfo.getX();
		this.viewYSnake = gameInfo.getY();
		this.viewAppleX = gameInfo.getAppleX();
		this.viewAppleY = gameInfo.getAppleY();
		this.inGame = gameInfo.getInGame();
		this.viewScore = gameInfo.getScore();
		this.viewHighScore = gameInfo.getHighScore();
	}

	// draw model
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		doDrawing(g);
	}

	private void doDrawing(Graphics g) {

		// draw title Image boder
		g.setColor(Color.WHITE);
		g.drawRect(24, 10, 851, 55);

		// draw the title image
		g.drawImage(titleImage, 25, 11, this);

		// draw the boder for the gameplay
		g.setColor(Color.WHITE);
		g.drawRect(224, 74, 652, 652);

		// draw background for the gameplay
		g.setColor(Color.black);
		g.fillRect(225, 75, 650, 650);

		// draw score
		g.setColor(Color.black);
		g.setFont(new Font("arial", Font.PLAIN, 24));
		g.drawString("Scores: " + viewScore, 25, 400);

		// draw highest score
		g.setColor(Color.yellow);
		g.fillRect(35, 100, 150, 150);
		g.setColor(Color.BLUE);
		g.setFont(new Font("arial", Font.PLAIN, 24));
		g.drawString("Highest", 75, 135);
		g.drawString(" Score:", 75, 170);
		g.drawString("    " + viewHighScore, 75, 210);

		//create New game button
	    JButton btNewGame = new JButton("New Game");
	    btNewGame.setBounds(30,600,150,50);
	    btNewGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					queue.put(new NewGameMessage());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	    	
	    });
	    add(btNewGame);

		if (inGame) {

			g.drawImage(apple, viewAppleX, viewAppleY, this);

			for (int z = 0; z < dots; z++) {
				if (z == 0) {
					if (rightDirection)
						g.drawImage(rightmouth, viewXSnake[z], viewYSnake[z], this);
					if (leftDirection)
						g.drawImage(leftmouth, viewXSnake[z], viewYSnake[z], this);
					if (upDirection)
						g.drawImage(upmouth, viewXSnake[z], viewYSnake[z], this);
					if (downDirection)
						g.drawImage(downmouth, viewXSnake[z], viewYSnake[z], this);
				} else {
					g.drawImage(modelbody, viewXSnake[z], viewYSnake[z], this);
				}
			}

			Toolkit.getDefaultToolkit().sync();

		} else {

			gameOver(g);
		}
	}

	// draw game over screen
	private void gameOver(Graphics g) {

		String msg = "Game Over";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, (650 - metr.stringWidth(msg)) / 2, 650 / 2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			queue.put(new TimerMessage());
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		// if (model.inGame) {

		// model.checkApple();
		// model.checkCollision();
		// model.move();

		// view.repaint();
	}

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {

			int key = e.getKeyCode();

			if (key == KeyEvent.VK_LEFT) // && (!model.rightDirection))
			{
				try {
					queue.put(new DirectionMessage(Directions.LEFT));
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				// model.leftDirection = true;
				// model.upDirection = false;
				// model.downDirection = false;
			}

			if (key == KeyEvent.VK_RIGHT) // && (!model.leftDirection))
			{
				try {
					queue.put(new DirectionMessage(Directions.RIGHT));
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				// model.rightDirection = true;
				// model.upDirection = false;
				// model.downDirection = false;
			}

			if (key == KeyEvent.VK_UP) // && (!model.downDirection))
			{
				try {
					queue.put(new DirectionMessage(Directions.UP));
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				// model.upDirection = true;
				// model.rightDirection = false;
				// model.leftDirection = false;
			}

			if (key == KeyEvent.VK_DOWN) // && (!model.upDirection))
			{
				try {
					queue.put(new DirectionMessage(Directions.DOWN));
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				// model.downDirection = true;
				// model.rightDirection = false;
				// model.leftDirection = false;

				if (key == KeyEvent.VK_SPACE) // && (!model.upDirection))
				{
					try {
						queue.put(new NewGameMessage());
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
}

