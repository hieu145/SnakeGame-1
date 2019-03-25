package snakeGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Snake {
    private final int B_WIDTH = 300; //import from background later
    private final int B_HEIGHT = 300; //import from background later
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];
    private int dots;
	ImageIcon iid = new ImageIcon("body.png");
    public int getDots() {
		return dots;
	}

	public void setDots(int dots) {
		this.dots = dots;
	}

	public int[] getX() {
		return x;
	}

	public int[] getY() {
		return y;
	}

	private Image ball = iid.getImage();
    ImageIcon iih = new ImageIcon("head.png");
    private Image head = iih.getImage();
    
    public void initSnake() {
        dots = 3;
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
    }
    private void initSnake(Graphics g) {
        for (int z = 0; z < dots; z++)        
    if (z == 0) {
                g.drawImage(head, x[z], y[z], null);
            } else {
                g.drawImage(ball, x[z], y[z], null);
            }
        }
    
    

	private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    
    public boolean isLeftDirection() {
		return leftDirection;
	}

	public void setLeftDirection(boolean leftDirection) {
		this.leftDirection = leftDirection;
	}

	public boolean isRightDirection() {
		return rightDirection;
	}

	public void setRightDirection(boolean rightDirection) {
		this.rightDirection = rightDirection;
	}

	public boolean isUpDirection() {
		return upDirection;
	}

	public void setUpDirection(boolean upDirection) {
		this.upDirection = upDirection;
	}

	public boolean isDownDirection() {
		return downDirection;
	}

	public void setDownDirection(boolean downDirection) {
		this.downDirection = downDirection;
	}
 
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
