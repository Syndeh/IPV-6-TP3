package pacman.game;

import java.awt.Dimension;

import pacman.scene.PacmanLevelScene;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.utils.ResourceUtil;

public class PacmanGame extends Game {

	private final static String MAP = ResourceUtil.getResourceString("PacmanGame.MAP");
	private final static int WIDHT = ResourceUtil.getResourceInt("PacmanGame.WIDHT");
	private final static int HEIGHT = ResourceUtil.getResourceInt("PacmanGame.HEIGHT");
	private final static String TITLE = ResourceUtil.getResourceString("PacmanGame.TITLE"); 
	
	public static void main(String[] args) throws Exception {
		new DesktopGameLauncher(new PacmanGame()).launch();
	}
	
	public PacmanGame(){
		super();
	}
	
	
	@Override
	protected void initializeResources() {

	}

	@Override
	protected void setUpScenes() {
		PacmanLevelScene scene = new PacmanLevelScene(MAP);
		this.setCurrentScene(scene);

	}

	@Override
	public Dimension getDisplaySize() {
		return new Dimension(WIDHT, HEIGHT);

	}

	@Override
	public String getTitle() {
		return TITLE;
	}

}
