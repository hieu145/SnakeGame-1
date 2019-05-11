package edu.sjsu.cs.cs151.model;

import java.awt.Image;

import javax.swing.ImageIcon;
//asdf
public class Food {
	private final int RAND_POS = 29; 
	private final int DOT_SIZE = 10;
	ImageIcon iia = new ImageIcon("src/resources/apple.png");
    private Image apple = iia.getImage();
    int apple_x;
    int apple_y;
    
    public int getApple_x() {
		return apple_x;
	}

	public void setApple_x(int apple_x) {
		this.apple_x = apple_x;
	}

	public int getApple_y() {
		return apple_y;
	}

	public void setApple_y(int apple_y) {
		this.apple_y = apple_y;
	}

	public void locateApple() {
         int r = (int) (Math.random() * RAND_POS);
         apple_x = ((r * DOT_SIZE));
         r = (int) (Math.random() * RAND_POS);
         apple_y = ((r * DOT_SIZE));
     }
}
