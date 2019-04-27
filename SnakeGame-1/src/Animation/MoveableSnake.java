package Animation;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
public class MoveableSnake implements MoveableShape {
private int speed = 3;
private int x;
private int y;
private int width;
public MoveableSnake(int x, int y, int width)
{
	this.x = x;
	this.y = y;
	this.width = width;
}

public void draw(Graphics2D g2) {
	Ellipse2D.Double head = new Ellipse2D.Double(x+40,10,20,20);
	Ellipse2D.Double body = new Ellipse2D.Double(x+20, 10,20,20);
	Ellipse2D.Double body2 = new Ellipse2D.Double(x,10,20,20);
	g2.draw(head);
	g2.draw(body);
	g2.draw(body2);
	g2.setColor(Color.BLACK);
	g2.fill(head);
	g2.setColor(Color.WHITE);
	g2.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
	g2.drawString("Welcome", 850, 450);
}
public void move() {
	x = x+speed;
	if(x>1900)
	{
		x=0;
	}
	y++;
}
	


}

