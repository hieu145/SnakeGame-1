package snakeGame;

public class Block {
/**
 * initialize variables to make background
 */
	final static int BLOCK_EMPTY=0;
	final static int BLOCK_FOOD = 20;
	final static int BLOCK_SNAKE = 30;
	final int row; 
	final int col;
	int type;
	
	public Block(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
}
