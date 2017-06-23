package view;

import java.util.TimerTask;

public class Rock extends TimerTask{
	
	private Map m;
	
	public Rock(Map m) {
		this.m = m;
	}
	
	public void run() {
		for(int x = 0; x < 40; x++) {
			for(int y = 20; y>0; y--) {
				if (m.getMap(x, y).equals("O")) {
					switch (m.getMap(x, y+1)) {
					case "_" : m.setMap(x, y, "_"); m.setMap(x, y+1, "O"); break;
					case "Y" : m.setMap(x, y, "_"); m.setMap(x, y+1, "O"); break;
					case "O" : cascade(x, y); break;
					default : break;
					}
				}
			}
		}
	}

	private void cascade(int x, int y) {
		if (m.getMap(x+1, y).equals("_") && m.getMap(x+1, y+1).equals("_")) {
			m.setMap(x, y, "_");
			m.setMap(x+1, y+1, "O");
		}
		else if (m.getMap(x-1, y).equals("_") && m.getMap(x-1, y+1).equals("_")) {
			m.setMap(x, y, "_");
			m.setMap(x-1, y+1, "O");
		}
	}
}
