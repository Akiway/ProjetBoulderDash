package view;

import java.util.TimerTask;

public class Rock extends TimerTask{
	
	/*
	 * 
Attribute from the Map aggregation with Rock.
	 */
	private Map m;
	
	/*
	 * @param m 
	 * 	
Attribute of aggregation Map rock   
	 */
	public Rock(Map m) {
		this.m = m;
	}
	
	/*
	 * 
	 * @see java.util.TimerTask#run()
	 * We launch the thread allowing to fall the cascade rocks under condition that there is nothing under it.
	 * 
	 */
	public void run() {
		for(int x = 0; x < 40; x++) {
			for(int y = 20; y>0; y--) {
				if (m.getMap(x, y).equals("O")) {
					switch (m.getMap(x, y+1)) {
					case "_" : m.setMap(x, y, "_"); m.setMap(x, y+1, "O"); break;
					case "Y" : m.setMap(x, y, "_"); m.setMap(x, y+1, "O"); break;
					case "O" : cascade(x, y, "O"); break;
					case "V" : cascade(x, y, "O"); break;
					default : break;
					}
				}/*
				if (m.getMap(x, y).equals("V")) {
					switch (m.getMap(x, y+1)) {
					case "_" : m.setMap(x, y, "_"); m.setMap(x, y+1, "V"); break;
					case "Y" : m.setMap(x, y, "_"); m.setMap(x, y+1, "V"); break;
					case "O" : cascade(x, y, "V"); break;
					case "V" : cascade(x, y, "V"); break;
					default : break;
					}
				}*/
			}
		}
	}

	/*
	 * @param x,y 
	 * 	They are localization attributes
	 * 
	 * 
Allows the element to cascade if access is possible.
	 * 
	 */
	
	private void cascade(int x, int y, String sprite) {
		if (m.getMap(x+1, y).equals("_") && m.getMap(x+1, y+1).equals("_")) {
			m.setMap(x, y, "_");
			m.setMap(x+1, y+1, sprite);
		}
		else if (m.getMap(x-1, y).equals("_") && m.getMap(x-1, y+1).equals("_")) {
			m.setMap(x, y, "_");
			m.setMap(x-1, y+1, sprite);
		}
	}
}
