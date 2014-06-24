package pacman.scene;

import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

import pacman.components.Ghost;
import pacman.components.Pacman;
import pacman.components.Pill;
import pacman.components.Scenary;
import pacman.utils.PacmanImageMapParser;
import pacman.utils.PacmanTerrainGenerator;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.GraphGameScene;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.graphs.MapGraph;
import com.uqbar.vainilla.graphs.Node;
import com.uqbar.vainilla.graphs.Valuable;

import pacman.utils.PacmanDensityMapGenerator;

public class PacmanLevelScene extends GraphGameScene {

	private Ghost ghost = new Ghost();
	private int pacmanColumn = 114;
	private int pacmanRow = 230;
	private Pacman pacman;
	private Scenary scenary;
	private PacmanImageMapParser mapParser;
	
	private ArrayList<Pill> pills = new ArrayList<Pill>();
	private ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
	
	public PacmanLevelScene() {
		super();
		this.setMapParser(new PacmanImageMapParser("images/levels/level1.png"));
		
	}
	
	public void onSetAsCurrent() {
		super.onSetAsCurrent();
		PacmanDensityMapGenerator terrainGenerator = new PacmanDensityMapGenerator(
				this.getMapParser()
				, this.getGame().getDisplayHeight()
				, this.getGame().getDisplayWidth()
				, this.getMapParser().getHeight()
				, this.getMapParser().getWidth()
				);
		this.setMapGraph(new MapGraph<Valuable>(terrainGenerator.getImage()));
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
		return this.getMapGraph().obtainNode(this.getPacmanRow(),this.getPacmanColumn());
	}

	public void changePacmanPosition() {
		
		this.pacmanRow=10;
		this.pacmanColumn=10;
		
	}


	@Override
	protected void initializeComponents() {
		initializeScenary();
		initializePacman();
//		initializePills();
		initializeGhosts();
	}

	private void initializeScenary() {
		this.scenary = new Scenary(this.getGame().getDisplayHeight(), this.getGame().getDisplayWidth(), this.getMapParser().getHeight(), this.getMapParser().getWidth());
		this.addComponent(this.scenary);
	}

	private void initializePills() {
		Pill pill;
		ArrayList<Double> spawnPoints = this.getMapParser().getPillsSpawnPoints();
		for (Double spawnPoint : spawnPoints) {
			spawnPoint = this.scenary.getScenaryPosition(spawnPoint);
			pill = new Pill();
			pill.setPosition(spawnPoint);
			this.addPill(pill);
		}
	}

	private void initializeGhosts() {
		Ghost ghost;
		ArrayList<Double> spawnPoints = this.getMapParser().getEnemySpawnPoints();
		for (Double spawnPoint : spawnPoints) {
			spawnPoint = this.scenary.getScenaryPosition(spawnPoint);
			ghost = new Ghost();
			ghost.setPosition(spawnPoint);
			this.addGhost(ghost);
		}
	}

	private void initializePacman() {
		this.pacman = new Pacman();
		Double spawnPosition = this.scenary.getScenaryPosition(this.getMapParser().getPacmanSpawnPosition());
		pacman.setPosition(spawnPosition);
		pacman.setRow((int)this.getMapParser().getPacmanSpawnPosition().getY());
		pacman.setColumn((int)this.getMapParser().getPacmanSpawnPosition().getX());
		this.addComponent(this.pacman);
	}


	public Pacman getPacman() {
		return pacman;
	}


	public void setPacman(Pacman pacman) {
		this.pacman = pacman;
	}


	public int getPacmanColumn() {
		return pacmanColumn;
	}


	public void setPacmanColumn(int pacmanColumn) {
		this.pacmanColumn = pacmanColumn;
	}


	public int getPacmanRow() {
		return pacmanRow;
	}


	public void setPacmanRow(int pacmanRow) {
		this.pacmanRow = pacmanRow;
	}

	// ------------------------------------------
	// Getters & Setters
	// ------------------------------------------
	
	private void addPill(Pill pill) {
		this.addComponent(pill);
		this.pills.add(pill);
	}
	
	private void addGhost(Ghost ghost) {
		this.addComponent(ghost);
		this.ghosts.add(ghost);
	}
	
	public PacmanImageMapParser getMapParser() {
		return mapParser;
	}

	private void setMapParser(PacmanImageMapParser mapParser) {
		this.mapParser = mapParser;
	}

}
