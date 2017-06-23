package view;

public class Player {
	
	private int x, y;
	
	/*
	 * 
The private attributes allow us to make spawn according to starting coordinates either an X and a Y.
	 */
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
	
	/*
	 * @param u, v 
	 * 	Attribute corresponding to location coordinates.
		We will add to the basic coordinates, our values ​​which are in parameter.
	 */
	public void move(int u, int v) {
		x += u;
		y += v;
	}
}
