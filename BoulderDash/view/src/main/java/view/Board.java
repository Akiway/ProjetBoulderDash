package view;

import java.awt.*;

import java.awt.event.*;
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
/**
 * 
Attribute resulting from the aggregation of monster and Board

 */
	private Monster mm;
	
	
	private Timer timer, timer2;
	private Image image, dir;
	
	/**
	 * 
It allows to say change state because it is a boolean type, it will be used to activate the ability to cheat in order to suppress collisions
	 */
	private boolean cheat = false;
	private String cht = "";
	
	/**
	 * 
Attribute that will be incremented during the collection in order to have a score that is updated
	 */
	private int score = 0;
	
	
/*
 * 	The constructor of Board 
 */
	public Board() {
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
		mm = new Monster();
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
		/*
		 * 
		 * 
Loop to interpret characters by displaying sprites through a case switch. We recover the character in our matrix and we put it in parameter of our switch.

		 */
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
		
		/*
		 * 
We configure our display with the choice of the size of the font of our score, its color and the display of the score thanks to the method DrawString.

		 * 
		 */
		g.setFont(new Font("Courier", Font.BOLD, 20));
		g.setColor(Color.WHITE);
		g.drawString(cht, 48, 990);
		g.drawImage(dir, p.getX()*48, p.getY()*48, 48, 48, this);
		
		/**
		 * Change the sprite behind the player.
		 * Gravity will check if a rock need to fall.
		 * When it can move to the next box, it will replace the sprite with a vacuum depending on the type of character. So we can manage the score.
		 */
		switch (m.getMap(p.getX(), p.getY())) {
		case "X" : m.setMap(p.getX(), p.getY(), "_"); Gravity(p.getX(), p.getY()); break;
		case "V" : m.setMap(p.getX(), p.getY(), "_"); score += 10; Gravity(p.getX(), p.getY()); break;
		case "W" : m.setMap(p.getX(), p.getY(), "_"); score += 60; Gravity(p.getX(), p.getY()); break;
		case "_" : Gravity(p.getX(), p.getY()); break;
		}
		
		/*
		 * 
Management as well as color.
		 */
		g.setFont(new Font("Courier", Font.BOLD, 30));
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
					Gravity(p.getX()-1, p.getY()+1);
				}
				dir = m.getPerso_left();
			}
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


