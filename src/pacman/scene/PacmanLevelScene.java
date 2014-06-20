package pacman.scene;

import pacman.components.Ghost;

import com.uqbar.vainilla.GraphGameScene;

public class PacmanLevelScene extends GraphGameScene {

	private Ghost ghost = new Ghost();
	
	public PacmanLevelScene(String map) {
		super(map);
	}

	@Override
	protected void initializeComponents() {
		this.addComponent(this.getGhost());

	}

	public Ghost getGhost() {
		return ghost;
	}

	public void setGhost(Ghost ghost) {
		this.ghost = ghost;
	}



}
