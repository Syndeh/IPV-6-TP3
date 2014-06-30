package pacman.components;

import java.awt.Point;

import pacman.scene.PacmanLevelScene;
import pacman.utils.GlobalResources;
import pacman.utils.SpriteManager;

import com.uqbar.vainilla.GameComponent;

public class LifeCounter extends GameComponent<PacmanLevelScene> {

	public LifeCounter(Point point) {
		this.setAppearance(SpriteManager.INSTANCE.getCounterImage());
		this.setX(point.getX() * GlobalResources.SCALEFACTOR);
		this.setY((point.getY()+2) * GlobalResources.SCALEFACTOR);
	}
}
