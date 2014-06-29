package pacman.components;

import java.awt.Color;

import pacman.scene.PacmanLevelScene;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;

public class Pill extends GameComponent<PacmanLevelScene> {

	public Pill() {
		//this.setAppearance(Sprite.fromImage("images/pacmansprite.png").crop(43, 3, 13, 13).scaleTo(5, 5));
		this.setAppearance(new Circle(Color.YELLOW, 5));
	}
	
}
