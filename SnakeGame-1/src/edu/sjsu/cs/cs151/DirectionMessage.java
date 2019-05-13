package edu.sjsu.cs.cs151;

public class DirectionMessage extends Message {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Directions direction;

	public DirectionMessage(Directions direction) {
		this.direction = direction;
	}
}

