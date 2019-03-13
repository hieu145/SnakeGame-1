package snakeGame;

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
}
