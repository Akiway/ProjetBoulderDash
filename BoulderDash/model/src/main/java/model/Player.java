package model;


public class Player implements IPlayer{
	
	private int x, y;
	
	public void generate() {
		new Player();
	}
	
	public Player() {
		x = 3;
		y = 3;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void move(int u, int v) {
		x += u;
		y += v;
	}

}
