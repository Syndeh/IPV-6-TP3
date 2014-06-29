package pacman.scene;



import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Sprite;

public class GameOverScene extends GameScene{
	@Override
	public void initializeComponents() {
		this.initializeBackGround();
		
	}

	private void initializeBackGround() {
		GameComponent<GameScene> background = new GameComponent<GameScene>(Sprite.fromImage("images/killedpacman.jpg").crop(553, 301, 800, 500).scaleTo(461, 491),0 ,0);
		this.addComponent(background);
	}
}
