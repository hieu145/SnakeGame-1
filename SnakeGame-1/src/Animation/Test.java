package Animation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Test {
	private static final int ICON_WIDTH = 1900;
	private static final int SNAKE_WIDTH = 100;
	private static final int ICON_HEIGHT = 1900;
public static void main(String[] args)
{
	
	JFrame frame = new JFrame();
	final MoveableShape shape = new MoveableSnake(0,0,SNAKE_WIDTH);
	ShapeIcon icon = new ShapeIcon(shape, ICON_WIDTH, ICON_HEIGHT);
	final JLabel label = new JLabel(icon);
	frame.setLayout(new FlowLayout());
	frame.add(label);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.pack();
	frame.setVisible(true);
	frame.getContentPane().setBackground(Color.PINK);
	
	final int delay = 10;
	Timer t = new Timer(delay, event -> 
	{
		shape.move();
		label.repaint();
		});
			t.start();
}
}
	


