package pacman.scene;



import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.utils.ResourceUtil;

public class GameOverScene extends GameScene{
	
	private static final String BACKGROUNDIMAGE = ResourceUtil.getResourceString("GameOverScene.BACKGROUNDIMAGE");
	private static final int CROPX = ResourceUtil.getResourceInt("GameOverScene.CROP.X");
	private static final int CROPY = ResourceUtil.getResourceInt("GameOverScene.CROP.Y");
	private static final int CROPWIDHT = ResourceUtil.getResourceInt("GameOverScene.CROP.WIDHT");
	private static final int CROPHEIGHT = ResourceUtil.getResourceInt("GameOverScene.CROP.HEIGHT");
	private static final int SCALEHEIGHT = ResourceUtil.getResourceInt("GameOverScene.SCALE.HEIGHT");
	private static final int SCALEWIDHT = ResourceUtil.getResourceInt("GameOverScene.SCALE.WIDHT");
			
	@Override
	public void initializeComponents() {
		this.initializeBackGround();
		
	}

	private void initializeBackGround() {
		GameComponent<GameScene> background = new GameComponent<GameScene>(Sprite.fromImage(BACKGROUNDIMAGE).crop(CROPX, CROPY, CROPWIDHT, CROPHEIGHT).scaleTo(SCALEWIDHT, SCALEHEIGHT),0 ,0);
		this.addComponent(background);
	}
}
