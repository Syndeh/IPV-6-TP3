package pacman.components;

import pacman.scene.PacmanLevelScene;
import pacman.utils.SpriteManager;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.events.constants.Key;
import com.uqbar.vainilla.utils.Vector2D;

public class Pacman extends GameComponent<PacmanLevelScene> {

	private Vector2D direction;
	private int speed = 100;

	public Pacman() {
		super(SpriteManager.INSTANCE.getAnimation(Pacman.class.getSimpleName()
				+ "LEFT"), 100, 100);
		this.direction = new Vector2D(-1, 0);
	}

	@Override
	public void update(DeltaState deltaState) {
		this.changeDirection(deltaState);
		this.doMovement(deltaState);
	}

	private void doMovement(DeltaState deltaState) {
		Vector2D result = this.getPosition().suma(
				this.direction.producto(this.speed * deltaState.getDelta()));
		this.setX(result.getX());
		this.setY(result.getY());
	}

	private Vector2D getPosition() {
		return new Vector2D(this.getX(), this.getY());
	}

	private void changeDirection(DeltaState deltaState) {
		if (deltaState.isKeyPressed(Key.UP)) {
			this.direction = new Vector2D(0, -1);
			this.setAppearance(SpriteManager.INSTANCE.getAnimation(Pacman.class
					.getSimpleName() + "UP"));
		} else if (deltaState.isKeyPressed(Key.DOWN)) {
			this.direction = new Vector2D(0, 1);
			this.setAppearance(SpriteManager.INSTANCE.getAnimation(Pacman.class
					.getSimpleName() + "DOWN"));
		} else if (deltaState.isKeyPressed(Key.LEFT)) {
			this.direction = new Vector2D(-1, 0);
			this.setAppearance(SpriteManager.INSTANCE.getAnimation(Pacman.class
					.getSimpleName() + "LEFT"));
		} else if (deltaState.isKeyPressed(Key.RIGHT)) {
			this.direction = new Vector2D(1, 0);
			this.setAppearance(SpriteManager.INSTANCE.getAnimation(Pacman.class
					.getSimpleName() + "RIGHT"));
		}
	}

}
