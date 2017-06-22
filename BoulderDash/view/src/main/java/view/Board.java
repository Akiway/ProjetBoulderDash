package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JPanel implements ActionListener{

	private Map m;
	private Player p;
	private Monster mm;
	private Timer timer, timer2;
	private Image image, dir;
	private boolean cheat = false;
	private String cht = "";
	private int score = 0;
	
	
	
	public Board() {
		m = new Map();
		p = new Player();
		mm = new Monster();
		Thread thr = new Thread(mm);
		thr.start();
		addKeyListener(new Action());
		setFocusable(true);
		dir = m.getPerso_face();
		
		timer = new Timer(10, this);
		timer.start();
		//Monster mm = new Monster();
		//mm.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		for (int x = 0; x<40; x++) {
			for (int y = 0; y<21; y++) {
				
				switch (m.getMap(x, y)) {
				case "X" : image = m.getSand(); break;
				case "H" : image = m.getWall(); break;
				case "V" : image = m.getDiamond(); break;
				case "O" : image = m.getRock(); break;
				case "_" : image = m.getEmpty(); break;
				case "Y" : image = m.getMonster(); break;
				case "c" : image = m.getCoal_ore(); break;
				case "i" : image = m.getIron_ore(); break;
				case "d" : image = m.getDiamond_ore(); break;
				case "g" : image = m.getGold_ore(); break;
				case "e" : image = m.getEmerald_ore(); break;
				case "l" : image = m.getLapis_ore(); break;
				case "r" : image = m.getRedstone_ore(); break;
				default : image = m.getWall(); break;
				}
				g.drawImage(image, x*48, y*48, 48, 48, null);
			}
		}
		g.setFont(new Font("Courier", Font.BOLD, 20));
		g.setColor(Color.WHITE);
		g.drawString(cht, 48, 990);
		g.drawImage(dir, p.getX()*48, p.getY()*48, 48, 48, this);
		
		/**
		 * Change the sprite behind the player.
		 * Gravity will check if a rock need to fall.
		 */
		switch (m.getMap(p.getX(), p.getY())) {
		case "X" : m.setMap(p.getX(), p.getY(), "_"); Gravity(p.getX(), p.getY()); break;
		case "V" : m.setMap(p.getX(), p.getY(), "_"); score += 10; Gravity(p.getX(), p.getY()); break;
		case "W" : m.setMap(p.getX(), p.getY(), "_"); score += 60; Gravity(p.getX(), p.getY()); break;
		case "_" : Gravity(p.getX(), p.getY()); break;
		}
		g.setFont(new Font("Courier", Font.BOLD, 30));
		g.drawString("Score : " + score, 192, 990);
		if (score == 100) {
			g.setFont(new Font("Courier", Font.BOLD, 70));
			g.drawString("Well Played !", 760, 504);
			/*try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				System.out.println("TimeSleep fail");
			}*/
		}
	}
		
	public void Gravity(int x, int y) {
		
		if (m.getMap(x, y-1).equals("O")) {
			while (m.getMap(x, y).equals("_")) {
				//Gravity gra = new Gravity(x, y);
				//gra.start();
				//timer2 = new Timer(25, this);
				//timer2.setInitialDelay(500);
				//timer2.start();
				m.setMap(x, y-1, "_");
				m.setMap(x, y, "O");
				y++;
			}
		}
	}
	
	public class Action extends KeyAdapter {
		
		public void keyReleased(KeyEvent e) {
			int keycode = e.getKeyCode();
			
			if(keycode == KeyEvent.VK_UP) {
				if(!m.getMap(p.getX(), p.getY()-1).equals("H") && !m.getMap(p.getX(), p.getY()-1).equals("O") || cheat) {
					p.move(0, -1);
				}
				dir = m.getPerso_back();
			}
			if(keycode == KeyEvent.VK_DOWN) {
				if(!m.getMap(p.getX(), p.getY()+1).equals("H") && !m.getMap(p.getX(), p.getY()+1).equals("O") || cheat) {
					p.move(0, 1);
				}
				dir = m.getPerso_face();
			}
			if(keycode == KeyEvent.VK_LEFT) {
				if(!m.getMap(p.getX()-1, p.getY()).equals("H") && !m.getMap(p.getX()-1, p.getY()).equals("O") || cheat) {
					p.move(-1, 0);
				} else if (m.getMap(p.getX()-1, p.getY()).equals("O") && m.getMap(p.getX()-2, p.getY()).equals("_")) {
					m.setMap(p.getX()-1, p.getY(), "_");
					m.setMap(p.getX()-2, p.getY(), "O");
					p.move(-1, 0);
					Gravity(p.getX()-1, p.getY()+1);
				}
				dir = m.getPerso_left();
			}
			if(keycode == KeyEvent.VK_RIGHT) {
				if(!m.getMap(p.getX()+1, p.getY()).equals("H") && !m.getMap(p.getX()+1, p.getY()).equals("O") || cheat) {
					p.move(1, 0);
				} else if (m.getMap(p.getX()+1, p.getY()).equals("O") && m.getMap(p.getX()+2, p.getY()).equals("_")) {
					m.setMap(p.getX()+1, p.getY(), "_");
					m.setMap(p.getX()+2, p.getY(), "O");
					p.move(1, 0);
					Gravity(p.getX()+1, p.getY()+1);
				}
				dir = m.getPerso_right();
			}
			if(keycode == KeyEvent.VK_C) {
				if (cheat) {
					cheat = false; cht = "";
				} else {
					cheat = true; cht = "Cheat ON";
				}
			}
			if(keycode == KeyEvent.VK_ESCAPE) System.exit(0);
		}
	}
}


