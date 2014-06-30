package pacman.scene;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import pacman.components.Ghost;
import pacman.components.Pacman;
import pacman.components.Pill;
import pacman.components.PointsCounter;
import pacman.ghostmovements.rules.SmartMovement;
import pacman.ghostmovements.rules.StupidMovement;
import pacman.utils.GlobalResources;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.GraphGameScene;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.graphs.MapGraph;
import com.uqbar.vainilla.graphs.Node;
import com.uqbar.vainilla.graphs.Valuable;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;
import com.uqbar.vainilla.utils.ClassLoaderResourcesProvider;
import com.uqbar.vainilla.utils.ResourceUtil;

public class PacmanLevelScene extends GraphGameScene {

	private final static String CLEANMAP = ResourceUtil.getResourceString("PacmanLevelScene.CLEANMAP");
	private List<Ghost> ghosts;
	private Pacman pacman;
	private List<Pill> pills;
	private PointsCounter counter;
	private Sound pillSound = new SoundBuilder().buildSound("/sounds/pacman_chomp.wav");
	
	public PacmanLevelScene(String map) throws Exception {
		this.setMapGraph(new MapGraph<Valuable>(ImageIO.read(new ClassLoaderResourcesProvider().getResource(map))));
		this.pills = new ArrayList<Pill>();
		this.ghosts = new ArrayList<Ghost>();
	}
	
	@Override
	protected void initializeComponents() {
		this.initializeBackground();
		this.addPacman();
		this.addPills();
		this.addGhosts();
		this.addCounter();
	}
	
	private void addPacman() {
		Point position = this.getMapGraph().getColorsMap().get(16711936).get(0);
		this.pacman  = new Pacman(position);
		this.addComponent(this.pacman);
	}

	private void addCounter() {
		this.counter = new PointsCounter();
		this.addComponent(this.counter);
	}

	private void addGhosts() {
		this.createSmartGhost(65536,"Blinky");
		this.createStupiGhost(65281, "Pinky");
		this.createStupiGhost(16711681, "Inky");
		this.createStupiGhost(32768, "Clyde");
	}

	private void createSmartGhost(int color, String name) {
		Point position = this.getMapGraph().getColorsMap().get(color).get(0);
		Ghost ghost = new Ghost(name, position);
		ghost.setMovementRule(new SmartMovement());
		this.addComponent(ghost);
		this.ghosts.add(ghost);
	}
	private void createStupiGhost(int color, String name) {
		Point position = this.getMapGraph().getColorsMap().get(color).get(0);
		Ghost ghost = new Ghost(name,position);
		ghost.setMovementRule(new StupidMovement());
		this.addComponent(ghost);
		this.ghosts.add(ghost);
	}

	private void addPills() {
		List<Point> pillsPositions = this.getMapGraph().getColorsMap().get(3584);
		for (Point pillPosition : pillsPositions) {
			Pill pill =  new Pill();
			pill.setX(pillPosition.getX() * GlobalResources.SCALEFACTOR);
			pill.setY(pillPosition.getY() * GlobalResources.SCALEFACTOR);
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
		GameComponent<GameScene> background = new GameComponent<GameScene>(Sprite.fromImage(CLEANMAP).scale(GlobalResources.SCALEFACTOR),0 ,0);
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
		return this.getMapGraph().obtainNode(this.getPacman().getRow(),this.getPacman().getColumn());
	}

	public Pacman getPacman() {
		return this.pacman;
	}

	public void setPacman(Pacman pacman) {
		this.pacman = pacman;
	}
}
