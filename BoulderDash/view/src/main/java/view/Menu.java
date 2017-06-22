package view;

import javax.swing.*;
import java.awt.Image;
import java.io.*;
import javax.imageio.*;

public class Menu {
	private int level = 1;
	private String skin = "Classic";
	public Image persoMenu, diamsMenu, monsterMenu;
	
	
	/**
	 * Constructor Menu() will create a new menu to let the user pick a skin and a level for the game.
	 * It will also set 'level' to a number corresponding to the map; and set 'skin' to the selected skin.
	 * @throws IOException 
	 * 
	 */
	public Menu() throws Exception {
		  String Newligne=System.getProperty("line.separator");
		  
		  String[] lvl = {"Level 1", "Level 2", "Level 3", "Level 4", "Level 5"}, skn = {"Classic", "Minecraft"};


		  //Image persomenu = new getPerso_menu;
		  persoMenu = ImageIO.read(new File("img/perso_menu9.png"));
	      ImageIcon perso_menu = new ImageIcon(persoMenu);
	      diamsMenu = ImageIO.read(new File("img/diams_menu.png"));
	      ImageIcon diams_menu = new ImageIcon(diamsMenu);
	      monsterMenu = ImageIO.read(new File("img/monster_menu3.png"));
	      ImageIcon monster_menu = new ImageIcon(monsterMenu);
/*		  ImageIcon perso_menu = new ImageIcon("img/perso_menu9.png");
		  ImageIcon diams_menu = new ImageIcon("img/diams_menu.png");
		  ImageIcon monster_menu = new ImageIcon("img/monster_menu3.png");
*/
		  
		  skin = (String)JOptionPane.showInputDialog(null, "Select the skin :", "BoulderDash Menu", JOptionPane.QUESTION_MESSAGE, perso_menu, skn, skn[1]);
		  if (skin == null) System.exit(0); 
		  //JOptionPane.showMessageDialog(null, skin + " skin selected !", "BoulderDash Menu", JOptionPane.INFORMATION_MESSAGE, perso_menu);		  
		  
		  
		  level = JOptionPane.showOptionDialog(null, "Select a level :", "BoulderDash Menu", JOptionPane.DEFAULT_OPTION,
				  JOptionPane.QUESTION_MESSAGE, perso_menu, lvl, lvl[0]);
		  level++;
		  if (level != 5) {
			  JOptionPane.showMessageDialog(null, "Level " + level + " selected !" + Newligne + "Goal : Find all diamonds", "BoulderDash Menu", JOptionPane.INFORMATION_MESSAGE, diams_menu);
		  }
		  else {
			  JOptionPane.showMessageDialog(null, "Level " + level + " selected !" + Newligne + "Goal : Kill the monster", "BoulderDash Menu", JOptionPane.INFORMATION_MESSAGE, monster_menu);
		  }
		  skin = "skin" + skin;
		  System.out.println(level);
	  }
	
	
	/**
	 * getLevel will return the number of the selected map (By default, the first map is selected).
	 * getSkin will return the selected skin (By default, Classic skin is selected).
	 */
	public int getLevel() {
		return level;
	}

	public String getSkin() {
		return skin;
	}


	
}
