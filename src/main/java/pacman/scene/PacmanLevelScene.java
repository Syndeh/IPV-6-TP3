package pacman.scene;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import pacman.components.Ghost;
import pacman.components.LifeCounter;
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
	private final static String MAP = ResourceUtil.getResourceString("PacmanGame.MAP");
	private final Sound deathSound = new SoundBuilder().buildSound(ResourceUtil.getResourceString("Pacman.DEATHSOUND"));
	private List<Ghost> ghosts;
	private Pacman pacman;
	private List<Pill> pills;
	private PointsCounter counter;
	private List<LifeCounter> lives;
	private final Sound pillSound = new SoundBuilder().buildSound("/sounds/pacman_chomp.wav");
	
	public PacmanLevelScene(String map) throws Exception {
		this.setMapGraph(new MapGraph<Valuable>(ImageIO.read(new ClassLoaderResourcesProvider().getResource(map))));
		this.pills = new ArrayList<Pill>();
		this.ghosts = new ArrayList<Ghost>();
		this.lives = new ArrayList<LifeCounter>();
	}
	
	public PacmanLevelScene(String map, List<Pill> pills,List<LifeCounter> lives, PointsCounter counter) throws IOException, Exception {
		this.setMapGraph(new MapGraph<Valuable>(ImageIO.read(new ClassLoaderResourcesProvider().getResource(map))));
		this.pills = pills;
		this.ghosts = new ArrayList<Ghost>();
		this.lives = lives;
		this.counter = counter;
	}

	@Override
	protected void initializeComponents() {
		this.initializeBackground();
		this.addPacman();
		this.addPills();
		this.addGhosts();
		this.addCounter();
		this.addLifes();
	}
	
	private void addPacman() {
		Point position = this.getMapGraph().getColorsMap().get(16711936).get(0);
		this.pacman  = new Pacman(position);
		this.addComponent(this.pacman);
	}

	private void addCounter() {
		if(this.counter == null){
			this.counter = new PointsCounter();
		}
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
		if(this.pills.isEmpty()){
			List<Point> pillsPositions = this.getMapGraph().getColorsMap().get(3584);
			for (Point pillPosition : pillsPositions) {
				Pill pill =  new Pill();
				pill.setX(pillPosition.getX() * GlobalResources.SCALEFACTOR);
				pill.setY(pillPosition.getY() * GlobalResources.SCALEFACTOR);
				this.addComponent(pill);
				this.pills.add(pill);
			}
		}else{
			for (Pill pill : this.pills) {
				this.addComponent(pill);
			}
		}
	}

	public void removePill(Pill pill) {
		this.pills.remove(pill);
		pill.destroy();
		this.pillSound.play(new Float(0.5));
		this.addPoints();
		this.checkWin();
	}
	
	private void checkWin() {
		if(this.pills.isEmpty()){
			try {
				this.getGame().setCurrentScene(new PacmanLevelScene(MAP,new ArrayList<Pill>(),this.getLives(),this.counter));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void addLifes() {
		if(this.lives.isEmpty()){
			for (Point point : this.getMapGraph().getColorsMap().get(16744448)) {
				LifeCounter live = new LifeCounter(point);
				this.addComponent(live);
				this.getLives().add(live);
			}
		}else
			for (LifeCounter life : this.lives) {
				this.addComponent(life);
			}
	}
	
	public void killPacman() {
		this.deathSound.play(1);
		if(this.lives.size() > 1){
			try {
				this.getLives().remove(0);
				this.getGame().setCurrentScene(new PacmanLevelScene(MAP,this.getPills(),this.getLives(),this.counter));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.getGame().setCurrentScene(new GameOverScene());
		}
	}
	
	public void addPoints() {
		this.counter.addPoints(10);
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

	public List<LifeCounter> getLives() {
		return this.lives;
	}

	public void setLives(List<LifeCounter> lives) {
		this.lives = lives;
	}
}
