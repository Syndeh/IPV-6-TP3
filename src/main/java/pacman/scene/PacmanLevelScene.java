package pacman.scene;

import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

import pacman.components.Ghost;
import pacman.components.Pacman;
import pacman.components.Pill;
import pacman.components.Scenary;
import pacman.utils.PacmanImageMapParser;

import com.uqbar.vainilla.GameScene;

public class PacmanLevelScene extends GameScene {

	private Pacman pacman;
	private Scenary scenary;
	private PacmanImageMapParser mapParser;
	
	private ArrayList<Pill> pills = new ArrayList<Pill>();
	private ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
	
	public PacmanLevelScene() {
		super();
		this.setMapParser(new PacmanImageMapParser("images/levels/level1.png"));
	}

	@Override
	protected void initializeComponents() {
		initializeScenary();
		initializePacman();
		initializePills();
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
		this.addComponent(this.pacman);
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
