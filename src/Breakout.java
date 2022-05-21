/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

	/** Time between frames in ms*/
	private static final int frameTime = 10;

	private boolean isGameOver=true;
	/** Button manager to operate buttons*/
	private ButtonManager buttonManager;
	/** Currently chosen game level*/
	private int level;

	private GRect paddle;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		displayMainMenu();
		while(true){
			if(isGameOver)
				continue;
			gameUpdate();
		}
	}

	/**	Is called when program initialises */
	public void init(){
		this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		//this.getGCanvas().setBackground(Color.lightGray);
		buttonManager = new ButtonManager(this);
		addMouseListeners();
	}

	/** Is called once each time game is started
	 * Setups everything when level is started*/
	public void gameStart(int level){
		removeAll();
		addPaddle();
		this.level=level;
		isGameOver=false;
		generateBricks();
	}

	/**Main game cycle
	 * Called once each frame */
	public void gameUpdate(){

	}
	/** Draws level choosing menu*/
	private void displayMainMenu(){
		for(int i=1; i<=3; i++) {
			add(new GButton(WIDTH/2, HEIGHT/6, i, "Level "+i, buttonManager), WIDTH/4, HEIGHT*(1+3*(i-1))/12);
		}
	}
	/** Generates bricks and adds them to the canvas */
	private void generateBricks(){
		for(int i=0; i<NBRICK_ROWS; i++){
			for(int j=0; j<NBRICKS_PER_ROW; j++){
				GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);

				Color color;
				if(i<NBRICKS_PER_ROW/5)
					color=Color.red;
				else if (i<NBRICKS_PER_ROW*2/5)
					color=Color.orange;
				else if (i<NBRICKS_PER_ROW*3/5)
					color=Color.yellow;
				else if (i<NBRICKS_PER_ROW*4/5)
					color=Color.green;
				else color=Color.cyan;

				brick.setFillColor(color);
				brick.setColor(color);
				add(brick, j*(BRICK_WIDTH+BRICK_SEP), BRICK_Y_OFFSET+i*(BRICK_HEIGHT+BRICK_SEP));
			}
		}
	}
	/** Checks collisions for all balls on field */
	private void checkCollisions(){

	}
	/** Checks collision for one ball */
	private void checkCollision(){
	}

	public void mouseMoved(MouseEvent e){
		movePaddle(e);
	}

	private void movePaddle(MouseEvent e){
		if(paddle!=null) {
			if (e.getX() <= WIDTH - paddle.getWidth()/2 && e.getX()>=paddle.getWidth()/2)
				paddle.move(e.getX() - paddle.getX()-paddle.getWidth()/2, 0);
		}
	}

	private void addPaddle(){
		paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setColor(Color.BLACK);
		add(paddle, WIDTH/2-PADDLE_WIDTH/2, HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT);
	}
	private void addBonus(){
	}

}
