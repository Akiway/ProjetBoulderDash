 package view;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Map implements IMap{
	
	private Scanner m;
	private String map2d[][] = new String[21][40];
	private String map1d[] = new String[40];
	private Image sand, wall, empty, rock, diamond, monster;
	private Image perso_face, perso_back, perso_left, perso_right;
	private Image coal_ore, iron_ore, diamond_ore, gold_ore, emerald_ore, redstone_ore, lapis_ore;
	private Menu menu;
	private String skin;
	private int level;
	
	
	public Map(){
		try{
			menu = new Menu();
			skin = menu.getSkin();
			level = menu.getLevel();
		} catch (Exception e) {
			System.out.println(skin + level);
		}
		
		try {
			String basePath = new File("").getAbsolutePath();
			String path = basePath + "\\..\\view\\" + skin + "\\wall.png";
			ImageIcon img = new ImageIcon(path);
			wall = img.getImage();
		} catch (Exception e) {
			System.out.println("load1 fail");
		}
		try {
			String basePath = new File("").getAbsolutePath();
			String path = basePath + "\\..\\view\\" + skin + "\\empty.png";
			ImageIcon img = new ImageIcon(path);
			empty = img.getImage();
		} catch (Exception e) {
			System.out.println("load2 fail");
		}
		try {
			String basePath = new File("").getAbsolutePath();
			String path = basePath + "\\..\\view\\" + skin + "\\rock.png";
			ImageIcon img = new ImageIcon(path);
			rock = img.getImage();
		} catch (Exception e) {
			System.out.println("load3 fail");
		}
		try {
			String basePath = new File("").getAbsolutePath();
			String path = basePath + "\\..\\view\\" + skin + "\\diamond.png";
			ImageIcon img = new ImageIcon(path);
			diamond = img.getImage();
		} catch (Exception e) {
			System.out.println("load4 fail");
		}
		try {
			String basePath = new File("").getAbsolutePath();
			String path = basePath + "\\..\\view\\" + skin + "\\sand.png";
			ImageIcon img = new ImageIcon(path);
			sand = img.getImage();
		} catch (Exception e) {
			System.out.println("load5 fail");
		}
		try {
			String basePath = new File("").getAbsolutePath();
			String path = basePath + "\\..\\view\\" + skin + "\\monster.png";
			ImageIcon img = new ImageIcon(path);
			monster = img.getImage();
		} catch (Exception e) {
			System.out.println("load6 fail");
		}
		try {
			String basePath = new File("").getAbsolutePath();
			String path = basePath + "\\..\\view\\" + skin + "\\perso_face.png";
			ImageIcon img = new ImageIcon(path);
			perso_face = img.getImage();
		} catch (Exception e) {
			System.out.println("load7 fail");
		}
		try {
			String basePath = new File("").getAbsolutePath();
			String path = basePath + "\\..\\view\\" + skin + "\\perso_back.png";
			ImageIcon img = new ImageIcon(path);
			perso_back = img.getImage();
		} catch (Exception e) {
			System.out.println("load8 fail");
		}
		try {
			String basePath = new File("").getAbsolutePath();
			String path = basePath + "\\..\\view\\" + skin + "\\perso_left.png";
			ImageIcon img = new ImageIcon(path);
			perso_left = img.getImage();
		} catch (Exception e) {
			System.out.println("load9 fail");
		}
		try {
			String basePath = new File("").getAbsolutePath();
			String path = basePath + "\\..\\view\\" + skin + "\\perso_right.png";
			ImageIcon img = new ImageIcon(path);
			perso_right = img.getImage();
		} catch (Exception e) {
			System.out.println("load10 fail");
		}
			String basePath = new File("").getAbsolutePath();
			String path = basePath + "\\..\\view\\" + skin + "\\coal_ore.png";
			ImageIcon img = new ImageIcon(path);
			coal_ore = img.getImage();
			
			basePath = new File("").getAbsolutePath();
			path = basePath + "\\..\\view\\" + skin + "\\emerald_ore.png";
			img = new ImageIcon(path);
			emerald_ore = img.getImage();
			
			basePath = new File("").getAbsolutePath();
			path = basePath + "\\..\\view\\" + skin + "\\iron_ore.png";
			img = new ImageIcon(path);
			iron_ore = img.getImage();
			
			basePath = new File("").getAbsolutePath();
			path = basePath + "\\..\\view\\" + skin + "\\diamond_ore.png";
			img = new ImageIcon(path);
			diamond_ore = img.getImage();
			
			basePath = new File("").getAbsolutePath();
			path = basePath + "\\..\\view\\" + skin + "\\gold_ore.png";
			img = new ImageIcon(path);
			gold_ore = img.getImage();
			
			basePath = new File("").getAbsolutePath();
			path = basePath + "\\..\\view\\" + skin + "\\lapis_ore.png";
			img = new ImageIcon(path);
			lapis_ore = img.getImage();
			
			basePath = new File("").getAbsolutePath();
			path = basePath + "\\..\\view\\" + skin + "\\redstone_ore.png";
			img = new ImageIcon(path);
			redstone_ore = img.getImage();
		
		
		openFile();
		try{
		readFile();
		completeMap();
		} catch (Exception e) {
			System.out.println("read/complete fail");
		}
		closeFile();
	}
	
	
	public Image getWall() {
		return wall;
	}
	public Image getEmpty() {
		return empty;
	}
	public Image getRock() {
		return rock;
	}
	public Image getDiamond() {
		return diamond;
	}
	public Image getSand() {
		return sand;
	}
	public Image getMonster() {
		return monster;
	}
	
	
	public Image getCoal_ore() {
		return coal_ore;
	}
	public Image getIron_ore() {
		return iron_ore;
	}
	public Image getGold_ore() {
		return gold_ore;
	}
	public Image getDiamond_ore() {
		return diamond_ore;
	}
	public Image getLapis_ore() {
		return lapis_ore;
	}
	public Image getEmerald_ore() {
		return emerald_ore;
	}
	public Image getRedstone_ore() {
		return redstone_ore;
	}
	
	
	
	public Image getPerso_face() {
		return perso_face;
	}
	public Image getPerso_back() {
		return perso_back;
	}
	public Image getPerso_left() {
		return perso_left;
	}
	public Image getPerso_right() {
		return perso_right;
	}
	
	
	public synchronized String getMap(int x, int y) {
		String sprite = map2d[y][x];
		return sprite;
	}
	public void setMap(int x, int y, String sprite) {
		map2d[y][x] = sprite;
	}
	
	
	public void openFile() {
		try {
			String basePath = new File("").getAbsolutePath();
			String path = basePath + "\\..\\view\\img\\map_" + level + ".txt";
			m = new Scanner(new File(path));
		} catch (Exception e) {
			System.out.println("load_map fail");
		}
	}
	
	public void readFile() {
		while(m.hasNext()) {
			for(int i = 0; i < 21; i++) {
				map1d[i] = m.next();
			}
		}
	}
	
	private void completeMap() {
		for(int x = 0; x < 40; x++) {
			for(int y = 0; y<21; y++) {
				map2d[y][x] = map1d[y].substring(x, x+1);
			}
		}
	}
	
	public void closeFile() {
		m.close();
	}
	
}
