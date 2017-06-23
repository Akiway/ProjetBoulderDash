package view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.*;

public class Board extends JPanel implements ActionListener{

	
	private Map m;
	private Player p;
	private javax.swing.Timer timer;
	private Image image, dir;
	private boolean cheat = false, stop = false, alive = true;
	private String cht = "";
	private int score = 0, scoreDiamond = 0, scoreRedDiamond = 0, heart = 3;
	private ArrayList<TimerTask> monsters;
	private java.util.Timer timerMonster, timerRock;
	private Graphics g;
	
	
	public Board() {
		this.monsters = new ArrayList();
		this.m = new Map();
		this.p = new Player();
		TimerTask rr = new Rock(this.m);
		timerRock = new java.util.Timer(true);
		timerRock.scheduleAtFixedRate(rr, 0, 750);
		
		addKeyListener(new Action());
		setFocusable(true);
		dir = this.m.getPerso_face();
		
		this.timer = new javax.swing.Timer(10, this);
		this.timer.start();
		for (int x = 0; x<40; x++) {
			for (int y = 0; y<21; y++) {
				if (m.getMap(x, y).equals("Y")) {
				this.monsters.add(new Monster(this.m, this.p, this.timerMonster, x, y));
				}
			}
		}
		for (TimerTask tt : this.monsters) {
			
			timerMonster = new java.util.Timer(true);
			timerMonster.scheduleAtFixedRate(tt, 0, 500);
		}
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
				case "W" : image = m.getRedDiamond(); break;
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
		if (alive) {
		g.drawImage(dir, p.getX()*48, p.getY()*48, 48, 48, this);
		} else {
			g.drawImage(m.getBlood(), p.getX()*48, p.getY()*48, 48, 48, this);
			g.setFont(new Font("Courier", Font.BOLD, 150));
			g.setColor(Color.RED);
			g.drawString("GAME OVER", 550, 504);
		}
		/**
		 * Change the sprite behind the player.
		 */
		switch (m.getMap(p.getX(), p.getY())) {
		case "X" : m.setMap(p.getX(), p.getY(), "_"); break;
		case "V" : m.setMap(p.getX(), p.getY(), "_"); score += 10; scoreDiamond++; break;
		case "W" : m.setMap(p.getX(), p.getY(), "_"); score += 60; scoreRedDiamond++; break;
		case "_" : break;
		case "Y" : if (cheat == false) {stop = true; alive = false;} break;
		case "O" : if (cheat == false) {stop = true; alive = false;} break;
		}
		
		g.setFont(new Font("Courier", Font.BOLD, 30));
		g.drawString("Score : " + score, 170, 990);
		
		if (cheat) g.drawImage(m.getNyancat(), 0*48, 20*48, 48, 48, this);
		
		heart(g);
		diamond(g);
		g.setFont(new Font("Courier", Font.BOLD, 70));
		End(g);
	}
	
	
	
	
	public void End(Graphics g) {
		this.g = g;
		switch (m.getLevel()) {
		case 1 : if (score >= 100) {stop = true; g.drawString("Well Played !", 760, 504);} break;
		case 2 : if (score >= 90) {stop = true; g.drawString("Well Played !", 760, 504);} break;
		case 3 : if (score >= 90) {stop = true; g.drawString("Well Played !", 760, 504);} break;
		case 4 : if (score >= 100) {stop = true; g.drawString("Well Played !", 760, 504);} break;
		case 5 : if (score >= 60) {stop = true; g.drawString("Well Played !", 760, 504);} break;
		default : break;
		}
	}
	
	
	public void heart(Graphics g) {
		this.g = g;
		int i = 0;
		g.drawString("Life ", 1710, 990);
		while (i<4) {
			g.drawImage(m.getHeartL(), -i*48+1650, 960, 40, 40, this);
			i++;
		}
		for (i=0; i<heart; i++) {
			g.drawImage(m.getHeart(), -i*48+1650, 960, 40, 40, this);
		}
		if (alive == false) {
			g.drawImage(m.getHeartB(), (-heart+1)*48+1650, 960, 40, 40, this);
		}
	}
	
	public void diamond(Graphics g) {
		this.g= g;
		for (int i=0; i<scoreRedDiamond; i++) {
			g.drawImage(m.getRedDiamondT(), i*48+390, 955, 48, 48, this);
		}
		for (int i=0; i<scoreDiamond; i++) {
			g.drawImage(m.getDiamondT(), (scoreRedDiamond+i)*48+390, 955, 48, 48, this);
		}
	}
	
	
	public class Action extends KeyAdapter {
		
		
		public void keyReleased(KeyEvent e) {
			int keycode = e.getKeyCode();
			
			if(keycode == KeyEvent.VK_UP && !stop) {
				if(!m.getMap(p.getX(), p.getY()-1).equals("H") && !m.getMap(p.getX(), p.getY()-1).equals("O") || cheat) {
					p.move(0, -1);
				}
				dir = m.getPerso_back();
			}
			if(keycode == KeyEvent.VK_DOWN && !stop) {
				if(!m.getMap(p.getX(), p.getY()+1).equals("H") && !m.getMap(p.getX(), p.getY()+1).equals("O") || cheat) {
					p.move(0, 1);
				}
				dir = m.getPerso_face();
			}
			if(keycode == KeyEvent.VK_LEFT && !stop) {
				if(!m.getMap(p.getX()-1, p.getY()).equals("H") && !m.getMap(p.getX()-1, p.getY()).equals("O") || cheat) {
					p.move(-1, 0);
				} else if (m.getMap(p.getX()-1, p.getY()).equals("O") && m.getMap(p.getX()-2, p.getY()).equals("_")) {
					m.setMap(p.getX()-1, p.getY(), "_");
					m.setMap(p.getX()-2, p.getY(), "O");
					p.move(-1, 0);
				}
				dir = m.getPerso_left();
			}
			if(keycode == KeyEvent.VK_RIGHT && !stop) {
				if(!m.getMap(p.getX()+1, p.getY()).equals("H") && !m.getMap(p.getX()+1, p.getY()).equals("O") || cheat) {
					p.move(1, 0);
				} else if (m.getMap(p.getX()+1, p.getY()).equals("O") && m.getMap(p.getX()+2, p.getY()).equals("_")) {
					m.setMap(p.getX()+1, p.getY(), "_");
					m.setMap(p.getX()+2, p.getY(), "O");
					p.move(1, 0);
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


