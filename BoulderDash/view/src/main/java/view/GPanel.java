package view;

import java.awt.*;
import java.io.File;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GPanel extends JPanel{

	private GFrame gf;
	Image image = gf.getWall();
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
        g.translate(6, 40);
		for (int y = 0; y<20; y++) {
			for (int x = 0; x<40; x++) {
				
				/*try{
                String t = Map[y][x];
				if(t == "X") {
					g.drawImage(sand, y*16, x*16, 16, 16, null);
				}
				else if(t == "H") {
					g.drawImage(wall, y*16, x*16, 16, 16, null);
				}
				else if(t == "_") {
					g.drawImage(empty, y*16, x*16, 16, 16, null);
				}
				else if(t == "V") {
					g.drawImage(diamond, y*16, x*16, 16, 16, null);
				}
				else if(t == "O") {
					g.drawImage(rock, y*16, x*16, 16, 16, null);
				}
				System.out.println("spite success");
				} catch (Exception e) {
					System.out.println("spite fail");
				}*/
				
				try{
				switch (gf.Map[y][x]) {
				case "X" : image = gf.getSand(); break;
				case "H" : image = gf.getWall(); break;
				case "V" : image = gf.getDiamond(); break;
				case "O" : image = gf.getRock(); break;
				case "_" : image = gf.getEmpty(); break;
				default : image = gf.getWall(); break;
				}
				System.out.println("case success");
				} catch (Exception e) {
					System.out.println("case fail");
				}
				try{
				g.drawImage(image, y*16, x*16, 16, 16, null);
				System.out.println("draw success");
				} catch (Exception e) {
					System.out.println("draw fail");
				}
				/*
				Color color;
				switch (gf.Map[y][x]) {
				case "X" : color = Color.ORANGE; break;
				case "H" : color = Color.BLACK; break;
				case "V" : color = Color.BLUE; break;
				case "O" : color = Color.GRAY; break;
				case "_" : color = Color.WHITE; break;
				default : color = Color.BLACK; break;
				}
				g.setColor(color);
                g.fillRect(16 * x, 16 * y, 16, 16);
                g.setColor(Color.BLACK);
                g.drawRect(16 * x, 16 * y, 16, 16);*/
			}
		}
	}
}
