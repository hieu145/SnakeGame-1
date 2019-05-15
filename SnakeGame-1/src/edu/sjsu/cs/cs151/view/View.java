package edu.sjsu.cs.cs151.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import javax.swing.*;

import edu.sjsu.cs.cs151.DirectionMessage;
import edu.sjsu.cs.cs151.Directions;
import edu.sjsu.cs.cs151.GameInfo;
import edu.sjsu.cs.cs151.Message;
import edu.sjsu.cs.cs151.NewGameMessage;
import edu.sjsu.cs.cs151.TimerMessage;
import edu.sjsu.cs.cs151.model.Model;

/**
 * The Class View.
 */
public class View extends JPanel implements ActionListener {
	
	/** The model. */
	Model model;
	
	/** The modelbody. */
	private Image rightmouth, leftmouth, upmouth, downmouth, modelbody;
	
	/** The apple. */
	private Image apple;
	
	/** The title image. */
	private Image titleImage;
	
	/** The queue. */
	BlockingQueue<Message> queue;
	
	/** The dots. */
	int dots = 3;
	
	/** The view X snake. */
	private int viewXSnake[];
	
	/** The view Y snake. */
	private int viewYSnake[];
	
	/** The view apple X. */
	private int viewAppleX;
	
	/** The view apple Y. */
	private int viewAppleY;
	
	/** The left direction. */
	public boolean leftDirection = false;
	
	/** The right direction. */
	public boolean rightDirection = true;
	
	/** The up direction. */
	public boolean upDirection = false;
	
	/** The down direction. */
	public boolean downDirection = false;
	
	/** The in game. */
	public boolean inGame = true;
	
	/** The game info. */
	private GameInfo gameInfo;
	
	/** The view score. */
	private int viewScore;
	
	/** The view high score. */
	private int viewHighScore;
	
	/**
	 * Instantiates a new view.
	 *
	 * @param queue the queue
	 * @param gameInfo the game info
	 */
	public View(BlockingQueue<Message> queue, GameInfo gameInfo) {
		this.queue = queue;
		this.gameInfo = gameInfo;
		loadImages();
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		addKeyListener(new TAdapter());
		Timer timer = new Timer(140, this);
		timer.start();
		update(gameInfo);
	}

	

	/**
	 * Load images.
	 * All images are sourced from Google Images
	 */
	void loadImages() {
		ImageIcon rm = new ImageIcon("resource/rightmouth.png");
		rightmouth = rm.getImage();
		
		ImageIcon lm = new ImageIcon("resource/leftmouth.png");
		leftmouth = lm.getImage();

		ImageIcon um = new ImageIcon("resource/upmouth.png");
		upmouth = um.getImage();

		ImageIcon dm = new ImageIcon("resource/downmouth.png");
		downmouth = dm.getImage();

		ImageIcon sb = new ImageIcon("resource/snakeimage.png");
		modelbody = sb.getImage();

		ImageIcon ap = new ImageIcon("resource/enemy.png");
		apple = ap.getImage();

		ImageIcon tt = new ImageIcon("resource/snaketitle.jpg");
		titleImage = tt.getImage();
	}
	
	/**
	 * Update.
	 *
	 * @param gameInfo the game info
	 */
	public void update(GameInfo gameInfo) {
	
		this.viewXSnake = gameInfo.getX();
		this.viewYSnake = gameInfo.getY();
		this.viewAppleX = gameInfo.getAppleX();
		this.viewAppleY = gameInfo.getAppleY();
		
		this.viewScore = gameInfo.getScore();
		this.viewHighScore = gameInfo.getHighScore();
		
	
		}
	

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}

	/**
	 * Do drawing.
	 *
	 * @param g the g
	 */
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
		g.setColor(Color.pink);
		g.fillRect(225, gameInfo.B_Y, gameInfo.B_WIDTH, gameInfo.B_HEIGHT);

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

		if (gameInfo.getInGame()) {

			g.drawImage(apple, viewAppleX, viewAppleY, this);

			for (int z = 0; z < gameInfo.dots; z++) {
				if (z == 0) {
					//System.out.println(this.viewXSnake);
					//System.out.println(this.viewYSnake);
					if (rightDirection)
						g.drawImage(rightmouth, this.viewXSnake[z],this.viewYSnake[z], this);
					if (leftDirection)
						g.drawImage(leftmouth, this.viewXSnake[z],  this.viewYSnake[z], this);
					if (upDirection)
						g.drawImage(upmouth, this.viewXSnake[z],  this.viewYSnake[z], this);
					if (downDirection)
						g.drawImage(downmouth, this.viewXSnake[z],  this.viewYSnake[z], this);
				} else {
					g.drawImage(modelbody, this.viewXSnake[z],  this.viewYSnake[z], this);
				}
			}

			Toolkit.getDefaultToolkit().sync();

		} else {

			gameOver(g);
		}
	}

	/**
	 * Game over.
	 *
	 * @param g the g
	 */
	// draw game over screen
	private void gameOver(Graphics g) {

		String msg = "Game Over";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = getFontMetrics(small);

		g.setColor(Color.red);
		g.setFont(small);
		g.drawString(msg, (650 - metr.stringWidth(msg)) / 2, 650 / 2);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
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

	/**
	 * The Class TAdapter.
	 */
	private class TAdapter extends KeyAdapter {

		/* (non-Javadoc)
		 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			//System.out.println("hi");

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
				//System.out.println("Hi");
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
			}
			
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

