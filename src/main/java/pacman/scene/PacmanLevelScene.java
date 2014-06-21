package pacman.scene;

import pacman.components.Ghost;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.GraphGameScene;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.graphs.Node;
import com.uqbar.vainilla.graphs.Valuable;

public class PacmanLevelScene extends GraphGameScene {

	private Ghost ghost = new Ghost();
	private int pacmanColumn = 115;
	private int pacmanRow = 182;
	
	
	
	public PacmanLevelScene(String map) {
		super(map);
	}
	

	@Override
	protected void initializeComponents() {
		this.initializeBackground();
		this.addComponent(this.getGhost());
		

	}
	
	private void initializeBackground() {
		GameComponent<GameScene> background = new GameComponent<GameScene>(Sprite.fromImage("images/cleanmap.png").scale(2),0 ,0);
		this.addComponent(background);
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
