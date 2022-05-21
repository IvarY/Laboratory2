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

	private int speed = 0;

	GPoint click;

	private GRect playAgainBottom;
	private GRect endGameBottom;

	private int livesLeft = 0;
	private int blocksLeft = 1;


	private boolean isGameOver=false;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		addStartMenu();
		gameStart();
	}

	/**	Is called when program initialises */
	public void init(){
		this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}


	/**
	 * Is called once each time game start
	 */
	public void gameStart(){
		while(!isGameOver){
			gameUpdate();
		}
	}

	/**
	 * Main game cycle
	 * Called once each frame
	 */
	public void gameUpdate(){

	}

	/**adds start menu*/
	private void addStartMenu() {
		addKeyListeners();

		GLabel hello = new GLabel("Hello! This a Breakout game.", 90,50);
		hello.setFont("Calibre-16");
		hello.setColor(Color.BLACK);
		add(hello);

		GLabel hello1 = new GLabel("Press on your keyboard the difficulty level!",35,80);
		hello1.setFont("Calibre-16");
		hello1.setColor(Color.BLACK);
		add(hello1);

		GRect rect = new GRect(150,50);
		rect.setColor(Color.green);
		rect.setFilled(true);
		add(rect,125,150);

		GRect rect2 = new GRect(150,50);
		rect2.setColor(Color.yellow);
		rect2.setFilled(true);
		add(rect2,125,250);

		GRect rect3 = new GRect(150,50);
		rect3.setColor(Color.red);
		rect3.setFilled(true);
		add(rect3,125,350);

		GLabel level1 = new GLabel("LEVEL 1",155,185);
		level1.setFont("Calibre-24");
		level1.setColor(Color.BLACK);
		add(level1);

		GLabel level2 = new GLabel("LEVEL 2",155,285);
		level2.setFont("Calibre-24");
		level2.setColor(Color.BLACK);
		add(level2);

		GLabel level3 = new GLabel("LEVEL 3",155,385);
		level3.setFont("Calibre-24");
		level3.setColor(Color.BLACK);
		add(level3);


	}

	/**adds end menu*/
	private void addPlayAgainMenu() {

		playAgainBottom = new GRect(200,50);
		playAgainBottom.setColor(Color.CYAN);
		playAgainBottom.setFilled(true);
		add(playAgainBottom,100, 200);

		endGameBottom = new GRect(200,50);
		endGameBottom.setFilled(true);
		endGameBottom.setColor(Color.red);
		add(endGameBottom, 100,315);

		GLabel play = new GLabel("Play Again",140,235);
		play.setFont("Calibre-24");
		play.setColor(Color.BLACK);
		add(play);

		GLabel end = new GLabel("End Game",145,350);
		end.setFont("Calibre-24");
		end.setColor(Color.BLACK);
		add(end);

	}

	/**If we paly again returns all veriables to the start position*/
	private void restart(){
		removeAll();
		livesLeft=3;
		blocksLeft=15;
		speed=0;
	}

	public void mousePressed(MouseEvent e) {
		if (livesLeft == 0 || blocksLeft == 0) {
			click = new GPoint(e.getPoint());
			GObject ag = getElementAt(click);
			if (ag.equals(playAgainBottom)) {
				restart();
			}
			if (ag == endGameBottom) {
				removeAll();
				livesLeft = 3;
				blocksLeft = 15;
				speed = 0;

			}
		}
	}

	private void playOrNo(MouseEvent j){
		GObject ag = getElementAt(j.getX(), j.getY());
		if(ag.equals(playAgainBottom)){
			restart();
		}
		if(ag==endGameBottom){
			removeAll();
			livesLeft=3;
			blocksLeft=15;
			speed=0;
			println(2);

		}
	}

	/**key event with the help of which we decide the speed of the ball*/
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == '1' && speed == 0) {
			removeAll();
			addMouseListeners();
			speed = 20;
		}

		if (e.getKeyChar() == '2' && speed == 0) {
			removeAll();
			addMouseListeners();
			speed = 12;
		}

		if (e.getKeyChar() == '3' && speed == 0) {
			removeAll();
			addMouseListeners();
			speed = 6;
		}
	}



}
