package pacman.components;

import pacman.scene.PacmanLevelScene;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;

public class Pill extends GameComponent<PacmanLevelScene> {

	public Pill() {
		this.setAppearance(Sprite.fromImage("images/pacmansprite.png").crop(43, 3, 13, 13).scaleTo(10, 10));
	}
	
}
