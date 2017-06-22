package view;

public class Player {
	
	private int x, y;
	
	
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
