package sjsu.cs.cs151.animation;
import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * The Class ShapeIcon.
 */
public class ShapeIcon implements Icon{

	/** The width. */
	private int width;
	 
 	/** The height. */
 	private int height;
	 
 	/** The shape. */
 	private MoveableShape shape;  
	 
/**
 * Instantiates a new shape icon.
 *
 * @param shape the shape
 * @param width the width
 * @param height the height
 */
public ShapeIcon(MoveableShape shape, int width, int height)
{
	this.shape = shape;
	this.width = width;
	this.height = height;
	
}

/* (non-Javadoc)
 * @see javax.swing.Icon#getIconWidth()
 */
public int getIconWidth()
{
	return width;
}

/* (non-Javadoc)
 * @see javax.swing.Icon#getIconHeight()
 */
public int getIconHeight()
{
	return height;
}

/* (non-Javadoc)
 * @see javax.swing.Icon#paintIcon(java.awt.Component, java.awt.Graphics, int, int)
 */
public void paintIcon(Component c, Graphics g, int x, int y)
{
	Graphics2D g2 = (Graphics2D) g; 
	shape.draw(g2);
}
 
	
}