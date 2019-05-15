package edu.sjsu.cs.cs151.model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

/**
 * The Class Snake.
 */
public class Snake {
    
    /** The b width. */
    private final int B_WIDTH = 300; //import from background later
    
    /** The b height. */
    private final int B_HEIGHT = 300; //import from background later
    
    /** The dot size. */
    private final int DOT_SIZE = 10;
    
    /** The all dots. */
    private final int ALL_DOTS = 900;
    
    /** The x. */
    private final int x[] = new int[ALL_DOTS];
    
    /** The y. */
    private final int y[] = new int[ALL_DOTS];
    
    /** The dots. */
    private int dots;
	
	/** The iid. */
	ImageIcon iid = new ImageIcon("body.png");
    
    /**
     * Gets the dots.
     *
     * @return the dots
     */
    public int getDots() {
		return dots;
	}

	/**
	 * Sets the dots.
	 *
	 * @param dots the new dots
	 */
	public void setDots(int dots) {
		this.dots = dots;
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int[] getX() {
		return x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int[] getY() {
		return y;
	}

	/** The ball. */
	private Image ball = iid.getImage();
    
    /** The iih. */
    ImageIcon iih = new ImageIcon("head.png");
    
    /** The head. */
    private Image head = iih.getImage();
    
    /**
     * Inits the snake.
     */
    public void initSnake() {
        dots = 3;
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
    }
    
    /**
     * Inits the snake.
     *
     * @param g the g
     */
    private void initSnake(Graphics g) {
        for (int z = 0; z < dots; z++)        
    if (z == 0) {
                g.drawImage(head, x[z], y[z], null);
            } else {
                g.drawImage(ball, x[z], y[z], null);
            }
        }
    
    

	/** The left direction. */
	private boolean leftDirection = false;
    
    /** The right direction. */
    private boolean rightDirection = true;
    
    /** The up direction. */
    private boolean upDirection = false;
    
    /** The down direction. */
    private boolean downDirection = false;
    
    /**
     * Checks if is left direction.
     *
     * @return true, if is left direction
     */
    public boolean isLeftDirection() {
		return leftDirection;
	}

	/**
	 * Sets the left direction.
	 *
	 * @param leftDirection the new left direction
	 */
	public void setLeftDirection(boolean leftDirection) {
		this.leftDirection = leftDirection;
	}

	/**
	 * Checks if is right direction.
	 *
	 * @return true, if is right direction
	 */
	public boolean isRightDirection() {
		return rightDirection;
	}

	/**
	 * Sets the right direction.
	 *
	 * @param rightDirection the new right direction
	 */
	public void setRightDirection(boolean rightDirection) {
		this.rightDirection = rightDirection;
	}

	/**
	 * Checks if is up direction.
	 *
	 * @return true, if is up direction
	 */
	public boolean isUpDirection() {
		return upDirection;
	}

	/**
	 * Sets the up direction.
	 *
	 * @param upDirection the new up direction
	 */
	public void setUpDirection(boolean upDirection) {
		this.upDirection = upDirection;
	}

	/**
	 * Checks if is down direction.
	 *
	 * @return true, if is down direction
	 */
	public boolean isDownDirection() {
		return downDirection;
	}

	/**
	 * Sets the down direction.
	 *
	 * @param downDirection the new down direction
	 */
	public void setDownDirection(boolean downDirection) {
		this.downDirection = downDirection;
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
}
