package view;

import javax.swing.*;

public class Game extends JFrame{
	
	public Game() {
		this.setTitle("BoulderDash");
	    this.add(new Board());
	    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setSize(1080, 720);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
