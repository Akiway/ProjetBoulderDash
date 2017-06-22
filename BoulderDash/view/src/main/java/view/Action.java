package view;

import java.awt.Image;
import java.awt.event.*;

import controller.IAction;
import model.*;

public class Action extends KeyAdapter implements IAction{
	
	private IMap m;
	private IPlayer p;
	private boolean cheat = false;
	private String cht = "";
	private Image dir;
	
	public void keyPressed(KeyEvent e) {
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
			}
			dir = m.getPerso_left();
		}
		if(keycode == KeyEvent.VK_RIGHT) {
			if(!m.getMap(p.getX()+1, p.getY()).equals("H") && !m.getMap(p.getX()+1, p.getY()).equals("O") || cheat) {
				p.move(1, 0);
			}
			dir = m.getPerso_right();
		}
		if(keycode == KeyEvent.VK_C) {
			if (cheat) {
				cheat = false;
				cht = "";
			} else {
				cheat = true;
				cht = "Cheat ON";
			}
		}
	}
	
	public Image getDir() {
		return dir;
	}
	public String getCht() {
		return cht;
	}
}