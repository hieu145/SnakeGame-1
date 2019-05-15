package edu.sjsu.cs.cs151.model;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The Class Board.
 */
public class Board extends JPanel implements ActionListener {

    /** The b width. */
    private final int B_WIDTH = 650;
    
    /** The b height. */
    private final int B_HEIGHT = 650;
    
    /** The dot size. */
    private final int DOT_SIZE = 25;
    
    /** The all dots. */
    private final int ALL_DOTS = 676;
    
    /** The rand pos. */
    private final int RAND_POS = 25;
    
    /** The delay. */
    private final int DELAY = 140;

    /** The x. */
    private final int x[] = new int[ALL_DOTS];
    
    /** The y. */
    private final int y[] = new int[ALL_DOTS];

    /** The dots. */
    private int dots;
    
    /** The apple x. */
    private int apple_x;
    
    /** The apple y. */
    private int apple_y;

    /** The left direction. */
    private boolean leftDirection = false;
    
    /** The right direction. */
    private boolean rightDirection = true;
    
    /** The up direction. */
    private boolean upDirection = false;
    
    /** The down direction. */
    private boolean downDirection = false;
    
    /** The in game. */
    private boolean inGame = true;

    /** The timer. */
    private Timer timer;
    
    /** The title image. */
    private Image rightmouth, leftmouth, upmouth, downmouth, snakebody, titleImage;
    
    /** The apple. */
    private Image apple;
    
    /** The score. */
    private int score = 0;
    
    /** The highest score. */
    private int highestScore = 0;

    /**
     * Instantiates a new board.
     */
    public Board() {
        
        initBoard();
    }
    
    /**
     * Inits the board.
     */
    private void initBoard() {

        addKeyListener(new TAdapter());
//        setBackground(Color.black);
        setFocusable(true);

//        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }

    /**
     * Load images.
     */
    private void loadImages() {
    	ImageIcon rm = new ImageIcon ("rightmouth.png");
    	rightmouth = rm.getImage();
    	
    	ImageIcon lm = new ImageIcon ("leftmouth.png");
    	leftmouth = lm.getImage();
    	
    	ImageIcon um = new ImageIcon ("upmouth.png");
    	upmouth = um.getImage();
    	
    	ImageIcon dm = new ImageIcon ("downmouth.png");
    	downmouth = dm.getImage();
    	
    	ImageIcon sb = new ImageIcon ("snakeimage.png");
    	snakebody = sb.getImage();
    	
    	ImageIcon ap = new ImageIcon ("enemy.png");
    	apple = ap.getImage();
    	
    	ImageIcon tt = new ImageIcon ("snaketitle.jpg");
    	titleImage = tt.getImage();
    }

    /**
     * Inits the game.
     */
    private void initGame() {

        dots = 3;

        x[2] = 250;
        x[1] = 275;
        x[0] = 300;
        y[2] = 250;
        y[1] = 250;
        y[0] = 250;
        
        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    /* (non-Javadoc)
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
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
    	
    	//draw title Image boder
		g.setColor(Color.WHITE);
		g.drawRect(24, 10, 851, 55);
		
		//draw the title  image
		g.drawImage(titleImage,25,11,this);
		
		//draw the boder for the gameplay
		g.setColor(Color.WHITE);
		g.drawRect(224,74,652,652);
		
		//draw background for the gameplay
		g.setColor(Color.black);
		g.fillRect(225,75,B_WIDTH,B_HEIGHT);
		
		//draw score
		g.setColor(Color.black);
		g.setFont(new Font("arial", Font.PLAIN, 24));
		g.drawString("Scores: " + score, 25, 400);
		
		//draw highest score
		g.setColor(Color.yellow);
		g.fillRect(35, 100, 150, 150);
		g.setColor(Color.BLUE);
		g.setFont(new Font("arial", Font.PLAIN, 24));
		g.drawString("Highest", 75, 135);
		g.drawString(" Score:", 75, 170);
		g.drawString("    " + highestScore, 75, 210);
		
	    JButton btNewGame = new JButton("New Game");
	    btNewGame.setBounds(30,600,150,50);
	    btNewGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				score = 0;
    			initGame();
    			leftDirection = false;
    		    rightDirection = true;
    		    upDirection = false;
    		    downDirection = false;
    			repaint();
			}
	    	
	    });
	    add(btNewGame);
        
        if (inGame) {

            g.drawImage(apple, apple_x, apple_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                	if(rightDirection) g.drawImage(rightmouth, x[z], y[z], this);
                	if(leftDirection) g.drawImage(leftmouth, x[z], y[z], this);
                	if(upDirection) g.drawImage(upmouth, x[z], y[z], this);
                	if(downDirection) g.drawImage(downmouth, x[z], y[z], this);
                } else {
                    g.drawImage(snakebody, x[z], y[z], this);
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
    private void gameOver(Graphics g) {
        
        g.setColor(Color.red);
		g.setFont(new Font("arial", Font.BOLD, 50));
		g.drawString("Game Over", 300, 300);
		
		g.setFont(new Font("arial", Font.BOLD, 20));
		g.drawString("Space to Restart", 350, 340);
    }

    /**
     * Check apple.
     */
    private void checkApple() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {

            dots++;
            score++;
            highScore(score);
            locateApple();
        }
    }

    /**
     * Move.
     */
    private void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    /**
     * Check collision.
     */
    private void checkCollision() {

        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= 700) {
            inGame = false;
        }

        if (y[0] < 100) {
            inGame = false;
        }

        if (x[0] >= 850) {
            inGame = false;
        }

        if (x[0] < 250) {
            inGame = false;
        }
        
        if (!inGame) {
            timer.stop();
        }
    }

    /**
     * High score.
     *
     * @param s the s
     */
    private void highScore (int s) {
    	if (highestScore < s)
    	highestScore = s;
    }
    
    /**
     * Locate apple.
     */
    private void locateApple() {

        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE)+225);

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE)+75);
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkApple();
            checkCollision();
            move();
        }

        repaint();
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

            int key = e.getKeyCode();
            
            

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
            
            if((key == KeyEvent.VK_SPACE) && (!inGame)){
    			score = 0;
    			inGame = true;
    			initGame();
    			leftDirection = false;
    		    rightDirection = true;
    		    upDirection = false;
    		    downDirection = false;
    			repaint();
    		}
        }
    }
}
