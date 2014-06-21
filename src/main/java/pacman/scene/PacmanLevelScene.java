package pacman.scene;

import pacman.components.Ghost;

import com.uqbar.vainilla.GraphGameScene;
import com.uqbar.vainilla.graphs.Node;
import com.uqbar.vainilla.graphs.Valuable;

public class PacmanLevelScene extends GraphGameScene {

	private Ghost ghost = new Ghost();
	private int pacmanColumn = 507;
	private int pacmanRow = 230;
	
	
	
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

	public Node<Valuable> getPacmanPosition() {
		return this.getMapGraph().obtainNode(this.pacmanRow, this.pacmanColumn);
	}

	public void changePacmanPosition() {
		
		this.pacmanRow=10;
		this.pacmanColumn=10;
		
	}



}
