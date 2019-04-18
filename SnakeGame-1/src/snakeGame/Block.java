package snakeGame;

public class Block {
/**
 * initialize variables to make background
 */
	private BlockType blockType;
	final int row; 
	final int col;
	
	
	public Block(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
	public int getRow(){
		return row;
	}
	public int getCol() {
		return col; 
	}
	public BlockType getBlockType() {
		return blockType;
	}
	public void setBlockType() {
		this.blockType = blockType;
	}
	public enum BlockType{
		EMPTY,FOOD, SNAKE;
		
	}
}
