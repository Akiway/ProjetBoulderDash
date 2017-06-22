package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class imgLoader implements IimgLoader {
	public Image persomenu;
	private Image diamsmenu;
	private Image monstermenu;
	
	public imgLoader() throws IOException {

		persomenu = ImageIO.read(new File("src/main/java/model/perso_menu9.png"));
		diamsmenu = ImageIO.read(new File("src/main/java/model/diams_menu.png"));
		monstermenu = ImageIO.read(new File("src/main/java/model/monster_menu3.png"));

	}

	@Override
	public Image getPerso_menu() {
		  return persomenu;
	}
	@Override
	public Image getDiams_menu() {
		  return diamsmenu;
	}
	@Override
	public Image getMonster_menu() {
		  return monstermenu;
	}
	
}
