package edu.sjsu.cs.cs151.model;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * The Class Food.
 */
public class Food {
	
	/** The rand pos. */
	private final int RAND_POS = 29; 
	
	/** The dot size. */
	private final int DOT_SIZE = 10;
	
	/** The iia. */
	ImageIcon iia = new ImageIcon("src/resources/apple.png");
    
    /** The apple. */
    private Image apple = iia.getImage();
    
    /** The apple x. */
    int apple_x;
    
    /** The apple y. */
    int apple_y;
    
    /**
     * Gets the apple x.
     *
     * @return the apple x
     */
    public int getApple_x() {
		return apple_x;
	}

	/**
	 * Sets the apple x.
	 *
	 * @param apple_x the new apple x
	 */
	public void setApple_x(int apple_x) {
		this.apple_x = apple_x;
	}

	/**
	 * Gets the apple y.
	 *
	 * @return the apple y
	 */
	public int getApple_y() {
		return apple_y;
	}

	/**
	 * Sets the apple y.
	 *
	 * @param apple_y the new apple y
	 */
	public void setApple_y(int apple_y) {
		this.apple_y = apple_y;
	}

	/**
	 * Locate apple.
	 */
	public void locateApple() {
         int r = (int) (Math.random() * RAND_POS);
         apple_x = ((r * DOT_SIZE));
         r = (int) (Math.random() * RAND_POS);
         apple_y = ((r * DOT_SIZE));
     }
}
