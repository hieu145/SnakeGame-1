package snakeGame;
import java.awt.Color;

public class Background {
/**
 * initializes the number of rows and columns
 * of the background asdasdsa
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
	
}
}
