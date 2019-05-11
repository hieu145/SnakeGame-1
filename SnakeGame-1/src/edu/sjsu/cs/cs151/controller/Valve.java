package edu.sjsu.cs.cs151.controller;

import edu.sjsu.cs.cs151.Message;

public interface Valve {
	public ValveResponse execute(Message message);

}
