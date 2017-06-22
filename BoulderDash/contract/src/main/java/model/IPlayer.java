package model;

public interface IPlayer {

	public void generate();
	public int getX();
	public int getY();
	public void move(int u, int v);
}
