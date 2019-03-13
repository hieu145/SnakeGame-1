package snakeGame;
import java.awt.Color;
import javax.swing.*;
import java.awt.Dimension;
public class Background extends JFrame {
/**
 * initializes the number of rows and columns
 * of the background 
 * testingsadasdsa
 */

final int ROW_NUM ;
final int COL_NUM;
Block[][] blocks; 
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
private void createBoard()
{
	setBackground(Color.PINK);
	setPreferredSize(new Dimension(ROW_NUM, COL_NUM));
}
}
