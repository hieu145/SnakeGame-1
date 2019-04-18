package snakeGame;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
public class MoveableSnake implements MoveableShape {

public MoveableSnake(int x, int y, int width)
{
	this.x = x;
	this.y = y;
	this.width = width;
}
public void move() {

	x++;
}
public void draw(Graphics2D g2) {
	Ellipse2D.Double head = new Ellipse2D.Double(x+40,y,20,20);
	Ellipse2D.Double body = new Ellipse2D.Double(x+20, y,20,20);
	Ellipse2D.Double body2 = new Ellipse2D.Double(x,y,20,20);
	g2.draw(head);
	g2.draw(body);
	g2.draw(body2);
	g2.setColor(Color.PINK);
	g2.fill(head);
	g2.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
	g2.drawString("Welcome", 100, 100);
}

	

private int x;
private int y;
private int width;
}

