package view;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Monster extends TimerTask {

	/*
	 * 
Attribute indicating the coordinate of an element in a double input array.
	 */
	private int x, y;
	/*
	 * It will serve us to instantiate a Random.
	 * 
	 */
	private Random r;
	
	/*
	 * 
We have two private attributes from two aggregations, Map and Player class
	 */
	private Map m;
	private Player p;
	
	/*
	 * 
It serves us so that the monster moves with a frequency therefore according to a time.
	 */
	private Timer timerMonster;
	
	/*
	 * 
It is used to launch the thread so that the monster can move independently from the game.
	 * @see java.util.TimerTask#run()
	 */
	
	@Override
	public void run() {
		moveM();
		if (m.getMap(x, y-1).equals("O")) {
			m.setMap(x, y, "W");
			m.setMap(x, y-1, "_");
			timerMonster.cancel();
		}
	}
	
	public Monster(Map m, Player p, Timer timerMonster, int x, int y) {
		this.m = m;
		this.x = x;
		this.y = y;
		this.p = p;
		this.timerMonster = timerMonster;
		
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	/*
	 * 
This method allows us the displacement of the monster in a random way.
	 */
	public void moveM() {
		
		/*
		 * 
			Instantiation of a random
		 */
		r = new Random();
		
		/*
		 * 
We store the result of the random which will be put in condition of the switch case.
		 */
		int f = r.nextInt(4-0);
		/*
		 * 
According to the result of the random, the corresponding method.
@param f 
		
Result of random.
		 */
		
		switch (f) {
		case 0 : moveUp(); break;
		case 1 : moveDown(); break;
		case 2 : moveLeft(); break;
		case 3 : moveRight(); break;
		default : break;
		}
		
	}
	
	/*
	 * 
It allows us to move the monster upwards by incrementing by one the Y.
	 */
	
	public void moveUp() {
		if(m.getMap(x, y-1).equals("_")) {
			y = y-1;
			m.setMap(x, y, "Y");
			m.setMap(x, y+1, "_");
		} else {
			moveM();
		}
	}
	/*
	 * 

It allows us to move the monster down by incrementing by one the Y.
	 */
	
	public void moveDown() {
		if(m.getMap(x, y+1).equals("_")) {
			y = y+1;
			m.setMap(x, y, "Y");
			m.setMap(x, y-1, "_");
		} else {
			moveM();
		}
	}
	/*
	 * It allows us to move the monster to the left by decrementing the X.
	 */
	
	public void moveLeft() {
		if(m.getMap(x-1, y).equals("_")) {
			x = x-1;
			m.setMap(x, y, "Y");
			m.setMap(x+1, y, "_");
		} else {
			moveM();
		}
	}
	/*
	 * 
It allows us to move the monster to the right by incrementing the X.
	 */
	public void moveRight() {
		if(m.getMap(x+1, y).equals("_")) {
			x = x+1;
			m.setMap(x, y, "Y");
			m.setMap(x-1, y, "_");
		} else {
			moveM();
		}
	}
	
}
