package pacman.components;

import pacman.scene.PacmanLevelScene;
import pacman.utils.SpriteManager;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Animation;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.events.constants.Key;
import com.uqbar.vainilla.utils.Vector2D;

public class Pacman extends GameComponent<PacmanLevelScene> {

	private Vector2D direction;
	private int speed = 100;
	
	public static final Vector2D DIRECTION_UP = new Vector2D(0,-1);
	public static final Vector2D DIRECTION_DOWN = new Vector2D(0, 1);
	public static final Vector2D DIRECTION_LEFT = new Vector2D(-1, 0);
	public static final Vector2D DIRECTION_RIGHT = new Vector2D(1, 0);
	
	public Pacman() {
		this.setDirection(DIRECTION_RIGHT);
		this.initRules();
		this.resetAppearance();
	}

	public void resetAppearance() {
		this.setAppearance(this.getDefaultAppearance());
	}

	@Override
	public void update(DeltaState deltaState) {
		this.changeDirection(deltaState);	
		this.doMovement(deltaState);
		super.update(deltaState);
	}

	private Appearance getDefaultAppearance() {
		Sprite[] sprites = null;
		if( this.getDirection().asVersor().equals(DIRECTION_UP)) {
			sprites = SpriteManager.INSTANCE.getPacmanUP();
		} else if ( this.getDirection().asVersor().equals(DIRECTION_DOWN)) {
			sprites = SpriteManager.INSTANCE.getPacmanDOWN();
		} else if (this.getDirection().asVersor().equals(DIRECTION_RIGHT) ){
			sprites = SpriteManager.INSTANCE.getPacmanRIGHT();
		} else {
			sprites = SpriteManager.INSTANCE.getPacmanLEFT();
		}
		return new Animation(0.1, sprites);
	}

	private void doMovement(DeltaState deltaState) {
		Vector2D result = this.getPosition().suma(
				this.getDirection().producto(this.speed * deltaState.getDelta()));
		this.setX(result.getX());
		this.setY(result.getY());
	}

	private Vector2D getPosition() {
		return new Vector2D(this.getX(), this.getY());
	}
	
	private void changeDirection(DeltaState deltaState) {
		if (deltaState.isKeyPressed(Key.UP)) {
			this.setDirection(DIRECTION_UP);
			this.resetAppearance();
		} else if (deltaState.isKeyPressed(Key.DOWN)) {
			this.setDirection(DIRECTION_DOWN);
			this.resetAppearance();
		} else if (deltaState.isKeyPressed(Key.LEFT)) {
			this.setDirection(DIRECTION_LEFT);
			this.resetAppearance();
		} else if (deltaState.isKeyPressed(Key.RIGHT)) {
			this.setDirection(DIRECTION_RIGHT);
			this.setAppearance(SpriteManager.INSTANCE.getAnimation(Pacman.class
					.getSimpleName() + "RIGHT"));
		}
	}

	public Vector2D getDirection() {
		return direction;
	}

	public void setDirection(Vector2D direction) {
		this.direction = direction;
	}

}

