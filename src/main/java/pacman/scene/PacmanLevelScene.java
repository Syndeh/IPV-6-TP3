package pacman.scene;

import pacman.components.Pacman;

import com.uqbar.vainilla.GameScene;

public class PacmanLevelScene extends GameScene {

	@Override
	protected void initializeComponents() {
		this.addComponent(new Pacman());
	}

}
