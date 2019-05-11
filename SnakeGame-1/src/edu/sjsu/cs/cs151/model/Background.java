package edu.sjsu.cs.cs151.model;
import java.awt.Color;
import javax.swing.*;
import java.awt.Dimension;
public class Background extends JFrame {
/**
 * initializes the number of rows and columns
 * of the background 
 * testing
 */

public final int ROW_NUM ;
public final int COL_NUM;
private Block[][] blocks; 
/**
 * creates a board
 */
public Background(int rows, int cols)
{
	ROW_NUM = rows;
	COL_NUM = cols;
	blocks = new Block[rows][cols];
	for (int row = 0; row<rows; row++)
	{
		for (int column = 0; column<cols; column++)
		{
			blocks[row][column] = new Block(row, column);
		}
	}
	createBoard();
}
public Block[][]getBlocks(){
	return blocks;
}
public void setBlocks(Block[][] blocks) {
	this.blocks = blocks;
}
private void createBoard()
{
	setBackground(Color.PINK);
	setPreferredSize(new Dimension(ROW_NUM, COL_NUM));
}
}
