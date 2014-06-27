package pacman.scene;

import java.awt.Point;
import java.util.List;

import pacman.components.Ghost;
import pacman.components.Pacman;
import pacman.components.Pill;
//import pacman.components.Scenary;
//import pacman.utils.PacmanImageMapParser;



import pacman.ghostmovements.rules.SmartMovement;
import pacman.ghostmovements.rules.SlowMovement;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.GraphGameScene;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.graphs.Node;
import com.uqbar.vainilla.graphs.Valuable;

public class PacmanLevelScene extends GraphGameScene {

	private int pacmanColumn = 114;
	private int pacmanRow = 230;
	private Pacman pacman = new Pacman();
	
	
	
	public PacmanLevelScene(String map) {
		super(map);
	}
	

	@Override
	protected void initializeComponents() {
		this.initializeBackground();
		this.addSmartGhost();
		//this.addStupidGhost();
		this.addComponent(new Pacman());
		this.addPills();
	}
	
	private void addSmartGhost() {
		Ghost smartGhost = new Ghost();
		smartGhost.setMovementRule(new SmartMovement());
		this.addComponent(smartGhost);
	}

	private void addStupidGhost() {
		Ghost stupidGhost = new Ghost();
		stupidGhost.setMovementRule(new SlowMovement());
		this.addComponent(stupidGhost);
	}
	
	private void addPills() {

		List<Point> pillsPositions = this.getMapGraph().getColorsMap().get(3584);
		for (Point pillPosition : pillsPositions) {
			Pill pill =  new Pill();
			pill.setX(pillPosition.getX() * 2);
			pill.setY(pillPosition.getY() * 2);
			this.addComponent(pill);
		}
		
	}


	private void initializeBackground() {
		GameComponent<GameScene> background = new GameComponent<GameScene>(Sprite.fromImage("images/cleanmap.png").scale(2),0 ,0);
		this.addComponent(background);
	}

	public Node<Valuable> getPacmanPosition() {
		return this.getMapGraph().obtainNode(this.getPacmanRow(),this.getPacmanColumn());
	}

	public void changePacmanPosition() {
		
		this.pacmanRow=10;
		this.pacmanColumn=10;
		
	}

//	private Pacman pacman;
//	private Scenary scenary;
//	private PacmanImageMapParser mapParser;
//	
//	private ArrayList<Pill> pills = new ArrayList<Pill>();
//	private ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
//	
//	public PacmanLevelScene() {
//		super();
//		this.setMapParser(new PacmanImageMapParser("images/levels/level1.png"));
//	}

//	@Override
//	protected void initializeComponents() {
//		initializeScenary();
//		initializePacman();
//		initializePills();
//		initializeGhosts();
//	}

	private void initializeScenary() {
//		this.scenary = new Scenary(this.getGame().getDisplayHeight(), this.getGame().getDisplayWidth(), this.getMapParser().getHeight(), this.getMapParser().getWidth());
//		this.addComponent(this.scenary);
	}

	private void initializePills() {
//		Pill pill;
//		ArrayList<Double> spawnPoints = this.getMapParser().getPillsSpawnPoints();
//		for (Double spawnPoint : spawnPoints) {
//			spawnPoint = this.scenary.getScenaryPosition(spawnPoint);
//			pill = new Pill();
//			pill.setPosition(spawnPoint);
//			this.addPill(pill);
//		}
	}

	private void initializeGhosts() {
//		Ghost ghost;
//		ArrayList<Double> spawnPoints = this.getMapParser().getEnemySpawnPoints();
//		for (Double spawnPoint : spawnPoints) {
//			spawnPoint = this.scenary.getScenaryPosition(spawnPoint);
//			ghost = new Ghost();
//			ghost.setPosition(spawnPoint);
//			this.addGhost(ghost);
//		}
	}

	private void initializePacman() {
//		this.pacman = new Pacman();
//		Double spawnPosition = this.scenary.getScenaryPosition(this.getMapParser().getPacmanSpawnPosition());
//		pacman.setPosition(spawnPosition);
//		this.addComponent(this.pacman);
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
	
//	private void addPill(Pill pill) {
//		this.addComponent(pill);
//		this.pills.add(pill);
//	}
//	
//	private void addGhost(Ghost ghost) {
//		this.addComponent(ghost);
//		this.ghosts.add(ghost);
//	}
	
//	public PacmanImageMapParser getMapParser() {
//		return mapParser;
//	}
//
//	private void setMapParser(PacmanImageMapParser mapParser) {
//		this.mapParser = mapParser;
//	}

}
