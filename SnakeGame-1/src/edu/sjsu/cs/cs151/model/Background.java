package edu.sjsu.cs.cs151.model;
import java.awt.Color;
import javax.swing.*;
import java.awt.Dimension;

/**
 * The Class Background.
 */
public class Background extends JFrame {

/** The row num. */
public final int ROW_NUM ;

/** The col num. */
public final int COL_NUM;

/** The blocks. */
private Block[][] blocks; 

/**
 * Instantiates a new background.
 *
 * @param rows the rows
 * @param cols the cols
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

/**
 * Gets the blocks.
 *
 * @return the blocks
 */
public Block[][]getBlocks(){
	return blocks;
}

/**
 * Sets the blocks.
 *
 * @param blocks the new blocks
 */
public void setBlocks(Block[][] blocks) {
	this.blocks = blocks;
}

/**
 * Creates the board.
 */
private void createBoard()
{
	setBackground(Color.PINK);
	setPreferredSize(new Dimension(ROW_NUM, COL_NUM));
}
}
