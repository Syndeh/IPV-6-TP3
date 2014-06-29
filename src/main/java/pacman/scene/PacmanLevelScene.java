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
		this.addComponent(this.getPacman());
		this.addPills();
		this.addGhosts();
		this.addCounter();
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
		return this.getMapGraph().obtainNode((int)this.getPacman().getRow(),(int)this.getPacman().getColumn());
	}



	public Pacman getPacman() {
		return this.pacman;
	}

	public void setPacman(Pacman pacman) {
		this.pacman = pacman;
	}


}
