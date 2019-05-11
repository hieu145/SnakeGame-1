package edu.sjsu.cs.cs151;

public class DirectionMessage extends Message {
	public Directions direction;

	public DirectionMessage(Directions direction) {
		this.direction = direction;
	}
}

