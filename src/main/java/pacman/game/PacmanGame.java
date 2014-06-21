package pacman.game;

import java.awt.Dimension;


import pacman.scene.PacmanLevelScene;

import com.uqbar.vainilla.DesktopGameLauncher;
import com.uqbar.vainilla.Game;

public class PacmanGame extends Game {

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
		PacmanLevelScene scene = new PacmanLevelScene("mapa1.png");
		this.setCurrentScene(scene);

	}

	@Override
	public Dimension getDisplaySize() {
		return new Dimension(460, 490);

	}

	@Override
	public String getTitle() {
		return "Pacman";
	}

}
