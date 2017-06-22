package view;

import java.util.Timer;
import java.util.TimerTask;

public class Gravity {

	int x, y, u;
	private IMap m;
	Timer timer2 = new Timer();
	TimerTask task = new TimerTask() {
		public void run() {
			System.out.println("run " + y);
			m.setMap(x, y-1, "_");
			m.setMap(x, y, "O");
		}
	};
	
	public void start() {
		timer2.schedule(task, 500);
	}
	
	
	public Gravity(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
