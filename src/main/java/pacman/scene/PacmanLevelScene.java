package pacman.scene;

import pacman.components.Pacman;

import com.uqbar.vainilla.GameScene;

public class PacmanLevelScene extends GameScene {

	private Pacman pacman;

	@Override
	protected void initializeComponents() {
		this.pacman = new Pacman();
		this.pacman.setX(100);
		this.pacman.setY(100);
		this.addComponent(this.pacman);
	}

}
