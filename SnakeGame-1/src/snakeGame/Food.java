package snakeGame;

import java.awt.Image;

import javax.swing.ImageIcon;
//asdf
public class Food {
	private final int RAND_POS = 29; 
	private final int DOT_SIZE = 10;
	ImageIcon iia = new ImageIcon("src/resources/apple.png");
    private Image apple = iia.getImage();
    
    private void locateApple() {
         int r = (int) (Math.random() * RAND_POS);
         int apple_x = ((r * DOT_SIZE));
         r = (int) (Math.random() * RAND_POS);
         int apple_y = ((r * DOT_SIZE));
     }
}
