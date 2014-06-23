package pacman.components;

import pacman.scene.PacmanLevelScene;
import pacman.utils.SpriteManager;

import com.uqbar.vainilla.GameComponent;

public class Ghost extends GameComponent<PacmanLevelScene> {
	
	public Ghost() {
		this.setAppearance(SpriteManager.INSTANCE.getAnimation(this.getClass().getSimpleName() + "DOWN"));
	}
	
}