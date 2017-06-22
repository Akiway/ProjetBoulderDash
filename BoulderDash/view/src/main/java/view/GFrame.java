package view;

import java.awt.*;
import java.io.File;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GFrame extends JFrame{

	private Scanner m;
	public String Map[][] = new String[20][40];
	private String map[] = new String [40];
	private Image sand, wall, empty, rock, diamond;
	private ImageIcon img;
	
	public GFrame() {
		setTitle("BoulderDash Game");
        setSize(656, 368);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        Map();
        setContentPane(new GPanel());
	}
	
	public void Map() {
		try{
		img = new ImageIcon("C:\\Users\\KLEIN Aurélien\\git\\ProjetJava\\BoulderDash\\view\\img\\wall.png");
		wall = img.getImage();
		img = new ImageIcon("C:\\Users\\KLEIN Aurélien\\git\\ProjetJava\\BoulderDash\\view\\img\\empty2.png");
		empty = img.getImage();
		img = new ImageIcon("C:\\Users\\KLEIN Aurélien\\git\\ProjetJava\\BoulderDash\\view\\img\\rock.png");
		rock = img.getImage();
		img = new ImageIcon("C:\\Users\\KLEIN Aurélien\\git\\ProjetJava\\BoulderDash\\view\\img\\diamond3.png");
		diamond = img.getImage();
		img = new ImageIcon("C:\\Users\\KLEIN Aurélien\\git\\ProjetJava\\BoulderDash\\view\\img\\sand.png");
		sand = img.getImage();
		System.out.println("dl success");
		} catch (Exception e) {
			System.out.println("dl fail");
		}
		
		try {
			img = new ImageIcon("img/wall.png");
			System.out.println("dl1 success");
		} catch (Exception e) {
			System.out.println("dl1 fail");
		}
		wall = img.getImage();
		
		try {
			img = new ImageIcon("img/empty2.png");
			System.out.println("dl2 success");
		} catch (Exception e) {
			System.out.println("dl2 fail");
		}
		empty = img.getImage();
		
		try {
			img = new ImageIcon("img/rock.png");
			System.out.println("dl3 success");
		} catch (Exception e) {
			System.out.println("dl3 fail");
		}
		rock = img.getImage();
		
		try {
			img = new ImageIcon("img/diamond3.png");
			System.out.println("dl4 success");
		} catch (Exception e) {
			System.out.println("dl4 fail");
		}
		diamond = img.getImage();
		
		try {
			img = new ImageIcon("img/sand.png");
			System.out.println("dl5 success");
		} catch (Exception e) {
			System.out.println("dl5 fail");
		}
		sand = img.getImage();
		
		openFile();
		try {
		readFile();
		System.out.println("read success");
		} catch (Exception e) {
			System.out.println("read fail");
		}
		try{
		completeMap();
		System.out.println("complete success");
		} catch (Exception e) {
			System.out.println("complete fail");
		}
		closeFile();
		
		int x = 0;
		while (x < 20) {
			System.out.println(map[x]);
			x++;
		}
		
		int i = 0;
		int j;
		while (i < 20) {
			j = 0;
			while(j < 40) {
				System.out.println(Map[i][j]);
				j++;
			}
			i++;
		}
	}
	
	public String getMap(int x, int y) {
		String index = Map[y][x];
		return index;
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
	
	private void openFile() {
		try {
			m = new Scanner(new File("C:/Users/KLEIN Aurélien/Desktop/Projet/6- Projet Java/map_1.txt"));
		} catch (Exception e) {
			System.out.println("error loading map");
		}
	}
	private void readFile() {
		while(m.hasNext()) {
			for(int i = 0; i<20; i++) {
				map[i] = m.next();
			}
		}
	}
	private void completeMap() {
		for(int y = 0; y < 20; y++) {
			for(int x = 0; x<40; x++) {
				Map[y][x] = map[y].substring(x, x+1);
			}
		}
	}
	private void closeFile() {
		m.close();
	}
}
