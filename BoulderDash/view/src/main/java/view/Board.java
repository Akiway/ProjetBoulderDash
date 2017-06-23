package view;

import java.awt.*;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.*;
/**
 * 
 * @author remy
 * 
 * <H1> Board class </H1> 
 * @see Jpanel To allow display in a window with the available methods
 * 
 *
 */

public class Board extends JPanel implements ActionListener{
	
/**
 * 

Attribute from the class map and private. It is an aggregation
 */
	private Map m;
/**
 * 
It is the mark of the aggregation between player and Board	
 */
	private Player p;
	private javax.swing.Timer timer;
/**
 * 
Attribute resulting from the aggregation of monster and Board

 */
	private Monster mm;
	
	
	private Image image, dir;
	
	/*
	 *
Attribute of type boolean so that one can change its state. 
	 */
	private boolean cheat = false, stop = false, alive = true;
	
	/**
	 * 
It allows to say change state because it is a boolean type, it will be used to activate the ability to cheat in order to suppress collisions
	 */
	private String cht = "";
	private int score = 0, scoreDiamond = 0, scoreRedDiamond = 0, heart = 3;
	private ArrayList<TimerTask> monsters;
	private java.util.Timer timerMonster, timerRock;
	private Graphics g;
	
	/**
	 * 
Attribute that will be incremented during the collection in order to have a score that is updated
	 */
	
/*
 * 	The constructor of Board 
 */
	public Board() {
		this.monsters = new ArrayList();
		this.m = new Map();
		this.p = new Player();
		TimerTask rr = new Rock(this.m);
		timerRock = new java.util.Timer(true);
		timerRock.scheduleAtFixedRate(rr, 0, 500);
		/*
		 * He will instantiate a map
		 */
		m = new Map();
		/*
		 * He will instantiate a player
		 */
		p = new Player();
		/*
		 *He will instantiate a monster,
		 */
		
		/**
		 * 
		 * He will instantiate a new thread
		 */
		Thread thr = new Thread(mm);
		thr.start();
		/**
		 *
		Adding a keyboard earpiece
		 */
		addKeyListener(new Action());
		setFocusable(true);
		dir = this.m.getPerso_face();
		
		/*
		 * 
Instantiation of a timer and we launch it.
		 */
		this.timer = new javax.swing.Timer(10, this);
		this.timer.start();
		
		/*
		 * It allows us to spawn a monster through a loop that will scan the map and it will add if we recover a "y" add.
		 * 
		 */
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
	
	/*
	 * 
	Refresh if there is an action on an element.
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	
	
	
	public void paint(Graphics g) {
		super.paint(g);
		/*
		 * 
		 * 
Loop to interpret characters by displaying sprites through a case switch. We recover the character in our matrix and we put it in parameter of our switch.
@param x 
	the x
		 */
		for (int x = 0; x<40; x++) {
			/*
			 * @param y
			 * 		the y
			 */
			for (int y = 0; y<21; y++) {
				
				/*
				 * 
In condition we will recover the character and we treat it in a switch case.
				 */
				
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
		
		/*
		 * 
We configure our display with the choice of the size of the font of our score, its color and the display of the score thanks to the method DrawString.

		 * 
		 */
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
<<<<<<< HEAD
=======
		 * Gravity will check if a rock need to fall.
		 * When it can move to the next box, it will replace the sprite with a vacuum depending on the type of character. So we can manage the score.
>>>>>>> branch 'master' of https://github.com/AurelienKlein/ProjetBoulderDash
		 */
		switch (m.getMap(p.getX(), p.getY())) {
		case "X" : m.setMap(p.getX(), p.getY(), "_"); break;
		case "V" : m.setMap(p.getX(), p.getY(), "_"); score += 10; scoreDiamond++; break;
		case "W" : m.setMap(p.getX(), p.getY(), "_"); score += 60; scoreRedDiamond++; break;
		case "_" : break;
		case "Y" : if (cheat == false) {stop = true; alive = false;} break;
		case "O" : if (cheat == false) {stop = true; alive = false;} break;
		}
		
		/*
		 * 
Management as well as color.
		 */
		g.setFont(new Font("Courier", Font.BOLD, 30));
		g.drawString("Score : " + score, 170, 990);
		
		if (cheat) g.drawImage(m.getNyancat(), 0*48, 20*48, 48, 48, this);
		
		heart(g);
		diamond(g);
		g.setFont(new Font("Courier", Font.BOLD, 70));
		End(g);
		g.drawString("Score : " + score, 192, 990);
		
		/*
		 * The stoppage of the game is done when the score is 100.
		 */
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
		
	/*
	 * @param x, y 
	 * 
The element falls until it collides with another element, such as a wall, of the dirt.
	 */
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
	/*
	 * @see KeyAdapter
	 * 
This method allows us to manage the key that is preserved and act accordingly.
*
We will manage the display of the player according to the direction.
	 */
	public class Action extends KeyAdapter {
		
		
		public void keyReleased(KeyEvent e) {
			int keycode = e.getKeyCode();
			if(keycode == KeyEvent.VK_UP && !stop) {
			/*
			 * 
Management of the control to take the height, one will decrement the position Y of our element.
* 
The recovered key is placed in condition.
			 */
			if(keycode == KeyEvent.VK_UP) {
				if(!m.getMap(p.getX(), p.getY()-1).equals("H") && !m.getMap(p.getX(), p.getY()-1).equals("O") || cheat) {
					p.move(0, -1);
				}
				dir = m.getPerso_back();
			}
			if(keycode == KeyEvent.VK_DOWN && !stop) {
			/*
			 * 
Management of the control to take the down, one will increments the position Y of our element.
* 
The recovered key is placed in condition.
			 */
			if(keycode == KeyEvent.VK_DOWN) {
				if(!m.getMap(p.getX(), p.getY()+1).equals("H") && !m.getMap(p.getX(), p.getY()+1).equals("O") || cheat) {
					p.move(0, 1);
				}
				dir = m.getPerso_face();
			}
			if(keycode == KeyEvent.VK_LEFT && !stop) {
			/*
			 * 
Management of the control to take the left, one will desincrements the position X of our element.
* 
The recovered key is placed in condition.
			 */
			
			if(keycode == KeyEvent.VK_LEFT) {
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
			/*
			 * 
Management of the control to take the right, one will increments the position X of our element.
* 
The recovered key is placed in condition.
			 */
			if(keycode == KeyEvent.VK_RIGHT) {
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
			}
		}
	}
}


