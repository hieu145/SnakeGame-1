package edu.sjsu.cs.cs151.model;



import javax.swing.Timer;

//Create the Model for the snakegame including board, snake and apple
public class Model {
	//create the board
	final int B_WIDTH = 650;
    final int B_HEIGHT = 650;
	private final int ALL_DOTS = 625;
    private final int RAND_POS = 25;
    private final int DOT_SIZE = 25;
    private final int DELAY = 140;
    
    //create the snake
    int dots = 3;
    public final int x[] = new int[ALL_DOTS];
    public final int y[] = new int[ALL_DOTS];
    
    public int getApple_x() {
		return apple_x;
	}

	public int getApple_y() {
		return apple_y;
	}

	//create the apple
    private int apple_x;
    private int apple_y;
    
    //moving snake. snake move to the right at the begining
    boolean leftDirection = false;
    boolean rightDirection = false;
    boolean upDirection = false;
    boolean downDirection = false;
    public boolean inGame = true;
    
    //initialize 
    public void initGame() {
        x[2] = 50;
        x[1] = 75;
        x[0] = 100;
        y[2] = 100;
        y[1] = 100;
        y[0] = 100;
        
        locateApple();

    }
    
    //check the snake eat apple and make snake longer
    void checkApple() {
        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            dots++;
            locateApple();
        }
    }
    
    //Move the snake
    void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    //check the collision for Gameover
    void checkCollision() {

        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }
    }
    
    //create apple location
    public void locateApple() {

        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
    }
}
