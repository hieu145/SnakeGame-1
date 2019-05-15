package edu.sjsu.cs.cs151.controller;

import edu.sjsu.cs.cs151.Message;

/**
 * The Interface Valve.
 */
public interface Valve {
	
	/**
	 * Execute.
	 *
	 * @param message the message
	 * @return the valve response
	 */
	public ValveResponse execute(Message message);

}
