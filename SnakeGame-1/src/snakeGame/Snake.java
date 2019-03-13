package MySnakeGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
/asdf
public class Snake {
    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];
    private int dots;
	ImageIcon iid = new ImageIcon("body.png");
    private Image ball = iid.getImage();
    ImageIcon iih = new ImageIcon("head.png");
    private Image head = iih.getImage();
    
    private void initSnake(Graphics g) {
        dots = 3;
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
            if (z == 0) {
                g.drawImage(head, x[z], y[z], null);
            } else {
                g.drawImage(ball, x[z], y[z], null);
            }
        }
    }
    
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    
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
