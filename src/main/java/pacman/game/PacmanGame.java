package pacman.game;

import java.awt.Dimension;

import pacman.scene.PacmanLevelScene;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;

public class PacmanGame extends Game {

	public static void main(String[] args) throws Exception {
		new DesktopGameLauncher(new PacmanGame()).launch();
	}
	
	@Override
	protected void initializeResources() {
	}

	@Override
	protected void setUpScenes() {
		this.setCurrentScene(new PacmanLevelScene());
	}

	@Override
	public Dimension getDisplaySize() {
		return new Dimension(400, 600);
	}

	@Override
	public String getTitle() {
		return "PACMAN";
	}

}
