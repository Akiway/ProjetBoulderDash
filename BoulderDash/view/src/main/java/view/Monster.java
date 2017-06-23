package view;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Monster extends TimerTask {

	private int x, y;
	private Random r;
	private Map m;
	private Player p;
	private Timer timerMonster;
	
	
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
	
	public void moveM() {
		r = new Random();
		int f = r.nextInt(4-0);
		
		switch (f) {
		case 0 : moveUp(); break;
		case 1 : moveDown(); break;
		case 2 : moveLeft(); break;
		case 3 : moveRight(); break;
		default : break;
		}
		
	}
	
	public void moveUp() {
		if(m.getMap(x, y-1).equals("_")) {
			y = y-1;
			m.setMap(x, y, "Y");
			m.setMap(x, y+1, "_");
		} else {
			moveM();
		}
	}
	public void moveDown() {
		if(m.getMap(x, y+1).equals("_")) {
			y = y+1;
			m.setMap(x, y, "Y");
			m.setMap(x, y-1, "_");
		} else {
			moveM();
		}
	}
	public void moveLeft() {
		if(m.getMap(x-1, y).equals("_")) {
			x = x-1;
			m.setMap(x, y, "Y");
			m.setMap(x+1, y, "_");
		} else {
			moveM();
		}
	}
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
