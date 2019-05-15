package edu.sjsu.cs.cs151.model;

/**
 * The Class Block.
 */
public class Block {

/** initialize variables to make background. */
	private BlockType blockType;
	
	/** The row. */
	final int row; 
	
	/** The col. */
	final int col;
	
	
	/**
	 * Instantiates a new block.
	 *
	 * @param row the row
	 * @param col the col
	 */
	public Block(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
	
	/**
	 * Gets the row.
	 *
	 * @return the row
	 */
	public int getRow(){
		return row;
	}
	
	/**
	 * Gets the col.
	 *
	 * @return the col
	 */
	public int getCol() {
		return col; 
	}
	
	/**
	 * Gets the block type.
	 *
	 * @return the block type
	 */
	public BlockType getBlockType() {
		return blockType;
	}
	
	/**
	 * Sets the block type.
	 */
	public void setBlockType() {
		this.blockType = blockType;
	}
	
	/**
	 * The Enum BlockType.
	 */
	public enum BlockType{
		
		/** The empty. */
		EMPTY,
/** The food. */
FOOD, 
 /** The snake. */
 SNAKE;
		
	}
}
