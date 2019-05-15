package edu.sjsu.cs.cs151;

/**
 * The Class DirectionMessage.
 */
public class DirectionMessage extends Message {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The direction. */
	public Directions direction;

	/**
	 * Instantiates a new direction message.
	 *
	 * @param direction the direction
	 */
	public DirectionMessage(Directions direction) {
		this.direction = direction;
	}
}