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
import acm.util.RandomGenerator;

import java.awt.*;
import java.awt.event.MouseEvent;

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
	private static final int frameTime = 3;
	/** Coefficient dependent on frameTime to use in formulas */
	private static final double frameTimeCoefficient = frameTime/40.0;

	private boolean isGameOver=true;
	/** Button manager to operate buttons*/
	private ButtonManager buttonManager;
	/** Currently chosen game level*/
	private int level;
	private Ball[] ballCollection;
	/** maximum count of balls on canvas */
	private int maxBallCount=5;

	private RandomGenerator randomGenerator;

	private int brickCount;

	private GRect paddle;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		displayMainMenu();
		while(true){
			print("");
			if(isGameOver)
				continue;
			gameUpdate();
			pause(frameTime);
		}
	}

	/**	Is called when program initialises */
	public void init(){
		this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		//this.getGCanvas().setBackground(Color.lightGray);
		buttonManager = new ButtonManager(this);
		randomGenerator = new RandomGenerator();
		ballCollection = new Ball[maxBallCount];
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
		brickCount=NBRICK_ROWS*NBRICKS_PER_ROW;
		addBall();
		//addBall();
	}

	/**Main game cycle
	 * Called once each frame */
	public void gameUpdate(){
		//println("upd");
		moveAllBalls();
		checkAllCollisions();
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
	private void checkAllCollisions(){
		for(int i=0; i<maxBallCount; i++) {
			if(ballCollection[i]!=null)
				checkCollisions(ballCollection[i]);
		}
	}
	/** Checks and processes collision for one ball */
	private void checkCollisions(Ball ball){
		GObject collision, brick1 = null, brick2 = null;
		int dx=0, dy=0;
		for (int i=0;i<2;i++)
			for(int j=0; j<2; j++) {
				collision = getElementAt(ball.getX()+i*ball.getWidth(), ball.getY()+j*ball.getHeight());
				if (collision != null)
					if (isBrick(collision)) {
						//breakBrick(collision);
						if(brick1==null)
							brick1=collision;
//						else if(brick2==null)
//							brick2=collision;
						if(i==0) {
							dx++;
						}
						else {
							dx--;
						}
						if(j==0){
							dy++;
						}
						else{
							dy--;
						}
					}
			}
		if(brick1!=null)
			breakBrick(brick1);
		else if(brick2!=null)
			breakBrick(brick2);
		if(dx!=0)
			ball.setDx(Math.abs(ball.getDx())*dx/Math.abs(dx));
		if(dy!=0)
			ball.setDy(Math.abs(ball.getDy())*dy/Math.abs(dy));
	}
	/** Moves all balls on the field */
	private void moveAllBalls(){
		for(int i=0; i<maxBallCount; i++) {
			if(ballCollection[i]!=null) {
				moveBall(ballCollection[i]);
			}
		}
	}
	/** Moves one ball */
	private void moveBall(Ball ball){
		ball.move(ball.getDx()*ball.getSpeed()*frameTimeCoefficient, ball.getDy()*ball.getSpeed()*frameTimeCoefficient);
		if(ball.getX()>=WIDTH-ball.getWidth()){
			ball.setDx(-Math.abs(ball.getDx()));
			//ball.move(,0);
		}
		if(ball.getX()<=0){
			ball.setDx(Math.abs(ball.getDx()));
			//ball.move(,0);
		}
		if(ball.getY()<=0){
			ball.setDy(Math.abs(ball.getDy()));
			//ball.move(,0);
		}
		if(ball.getY()>=HEIGHT-ball.getHeight()){
			ball.setDy(-ball.getDy());
			ball.setDx(randomGenerator.nextDouble(1,2)*ball.getDx()/Math.abs(ball.getDx()));
			//ball.move(,0);
		}
	}
	/** Adds ball if ball count on canvas is not max */
	private void addBall(){
		for(int i=0; i<maxBallCount; i++) {
			if(ballCollection[i]==null) {
				ballCollection[i] = new Ball(BALL_RADIUS * 2, BALL_RADIUS * 2,
						randomGenerator.nextDouble(1,2)*(randomGenerator.nextBoolean()?1:-1), -2, 10);
				add(ballCollection[i], WIDTH/2, HEIGHT/2);
				break;
			}
		}
	}
	/** Removes brick and updates score */
	private void breakBrick(GObject brick){
		brickCount--;
		remove(brick);
		//TODO Check if brick count = 0
	}
	/** Returns true if GObject is brick */
	private boolean isBrick(GObject obj){
		for(int i=0; i<maxBallCount; i++) {
			if(ballCollection[i]==obj)
				return false;
		}
		return true;
	}
}
