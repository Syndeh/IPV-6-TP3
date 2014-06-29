package pacman.scene;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import pacman.components.Ghost;
import pacman.components.Pacman;
import pacman.components.Pill;
import pacman.components.PointsCounter;
import pacman.ghostmovements.rules.SmartMovement;
import pacman.ghostmovements.rules.StupidMovement;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.GraphGameScene;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.graphs.Node;
import com.uqbar.vainilla.graphs.Valuable;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

public class PacmanLevelScene extends GraphGameScene {

	private List<Ghost> ghosts;
	private int pacmanColumn = 114;
	private int pacmanRow = 230;
	private Pacman pacman = new Pacman();
	private List<Pill> pills;
	private PointsCounter counter;
	private Sound pillSound = new SoundBuilder().buildSound("/sounds/pacman_chomp.wav");
	
	public PacmanLevelScene(String map) {
		super(map);
		this.pills = new ArrayList<Pill>();
		this.ghosts = new ArrayList<Ghost>();
	}
	
	@Override
	protected void initializeComponents() {
		this.initializeBackground();
		//this.addStupidGhost();
		this.addComponent(this.getPacman());
		this.addPills();
		this.addGhosts();
		this.addCounter();
	}
	
	private void addSmartGhost() {
		Ghost smartGhost = new Ghost();
		smartGhost.setMovementRule(new SmartMovement());
		this.addComponent(smartGhost);
	}

	private void addStupidGhost() {
		Ghost stupidGhost = new Ghost();
		stupidGhost.setMovementRule(new StupidMovement());
		this.addComponent(stupidGhost);
	}
	private void addCounter() {
		this.counter = new PointsCounter();
		this.addComponent(this.counter);
	}

	private void addGhosts() {
		this.createSmartGhost(3584,"Blinky");
		this.createStupiGhost(3584, "Blinky");
//		createGhost(rosa,"Pinky");
//		createGhost(celeste,"Inky");
//		createGhost(naranja,"Clyde");
	}

	private void createSmartGhost(int color, String name) {
		Point position = this.getMapGraph().getColorsMap().get(color).get(0);
		Ghost ghost = new Ghost(name);
		ghost.setX(position.getX());
		ghost.setY(position.getY());
		ghost.setMovementRule(new SmartMovement());
		this.addComponent(ghost);
		this.ghosts.add(ghost);
	}
	private void createStupiGhost(int color, String name) {
		Point position = this.getMapGraph().getColorsMap().get(color).get(0);
		Ghost ghost = new Ghost(name);
		ghost.setX(108*2);
		ghost.setY(112*2);
		ghost.setMovementRule(new StupidMovement());
		this.addComponent(ghost);
		this.ghosts.add(ghost);
	}

	private void addPills() {
		List<Point> pillsPositions = this.getMapGraph().getColorsMap().get(3584);
		for (Point pillPosition : pillsPositions) {
			Pill pill =  new Pill();
			pill.setX(pillPosition.getX() * 2);
			pill.setY(pillPosition.getY() * 2);
			this.addComponent(pill);
			this.pills.add(pill);
		}
	}

	public void removePill(Pill pill) {
		this.pills.remove(pill);
		pill.destroy();
		this.pillSound.play();
		this.addPoints();
		this.checkWin();
	}
	
	private void checkWin() {
		// TODO SI gano cambiar de escena
//		pills.isEmpty()
	}

	public void addPoints() {
		this.counter.addPoints(100);
	}
	
	private void initializeBackground() {
		GameComponent<GameScene> background = new GameComponent<GameScene>(Sprite.fromImage("images/cleanmap.png").scale(2),0 ,0);
		this.addComponent(background);
	}


	public List<Pill> getPills() {
		return this.pills;
	}

	public void setPills(List<Pill> pills) {
		this.pills = pills;
	}
	
	public List<Ghost> getGhosts() {
		return this.ghosts;
	}

	public void setGhosts(List<Ghost> ghosts) {
		this.ghosts = ghosts;
	}

	public Node<Valuable> getPacmanPosition() {
		return this.getMapGraph().obtainNode(this.getPacmanRow(),this.getPacmanColumn());
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

//	private void initializeScenary() {
//		this.scenary = new Scenary(this.getGame().getDisplayHeight(), this.getGame().getDisplayWidth(), this.getMapParser().getHeight(), this.getMapParser().getWidth());
//		this.addComponent(this.scenary);
//	}

//	private void initializePills() {
//		Pill pill;
//		ArrayList<Double> spawnPoints = this.getMapParser().getPillsSpawnPoints();
//		for (Double spawnPoint : spawnPoints) {
//			spawnPoint = this.scenary.getScenaryPosition(spawnPoint);
//			pill = new Pill();
//			pill.setPosition(spawnPoint);
//			this.addPill(pill);
//		}
//	}

//	private void initializeGhosts() {
//		Ghost ghost;
//		ArrayList<Double> spawnPoints = this.getMapParser().getEnemySpawnPoints();
//		for (Double spawnPoint : spawnPoints) {
//			spawnPoint = this.scenary.getScenaryPosition(spawnPoint);
//			ghost = new Ghost();
//			ghost.setPosition(spawnPoint);
//			this.addGhost(ghost);
//		}
//	}

//	private void initializePacman() {
//		this.pacman = new Pacman();
//		Double spawnPosition = this.scenary.getScenaryPosition(this.getMapParser().getPacmanSpawnPosition());
//		pacman.setPosition(spawnPosition);
//		this.addComponent(this.pacman);
//	}

	public Pacman getPacman() {
		return this.pacman;
	}

	public void setPacman(Pacman pacman) {
		this.pacman = pacman;
	}

	public int getPacmanColumn() {
		return this.pacmanColumn;
	}

	public void setPacmanColumn(int pacmanColumn) {
		this.pacmanColumn = pacmanColumn;
	}

	public int getPacmanRow() {
		return this.pacmanRow;
	}

	public void setPacmanRow(int pacmanRow) {
		this.pacmanRow = pacmanRow;
	}
}
