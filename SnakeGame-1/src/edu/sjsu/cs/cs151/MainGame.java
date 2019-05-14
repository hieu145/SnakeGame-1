package edu.sjsu.cs.cs151;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.*;
import javax.swing.JFrame;
import javax.swing.Timer;

import edu.sjsu.cs.cs151.controller.Controller;
import edu.sjsu.cs.cs151.model.Model;
import edu.sjsu.cs.cs151.view.MainView;
import edu.sjsu.cs.cs151.view.View;
public class MainGame {
	private static MainView view;
	private static Model model;
	private static BlockingQueue<Message>queue = new LinkedBlockingQueue<Message>();
	public static void main(String[] args) throws Exception {
		view = new MainView(queue);
		model = new Model();
		Controller controller = new Controller(view, model, queue);
		try {
		controller.mainLoop();
		} catch (Exception e) {
			e.printStackTrace();
		}
		view.clear();
		queue.clear();	
	}
}

