package pacman.components;

import java.awt.Color;

import pacman.scene.PacmanLevelScene;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;
import com.uqbar.vainilla.utils.ResourceUtil;

public class Pill extends GameComponent<PacmanLevelScene> {

	private static int DIAMETER = ResourceUtil.getResourceInt("Pill.DIAMETER");
	
	public Pill() {
		this.setAppearance(new Circle(Color.YELLOW, DIAMETER));
	}
	
}
