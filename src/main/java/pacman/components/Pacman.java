package pacman.components;

import java.awt.Point;

import pacman.scene.PacmanLevelScene;
import pacman.utils.GlobalResources;
import pacman.utils.SpriteManager;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Animation;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.colissions.CollisionDetector;
import com.uqbar.vainilla.events.constants.Key;
import com.uqbar.vainilla.graphs.Node;
import com.uqbar.vainilla.graphs.Valuable;
import com.uqbar.vainilla.utils.ResourceUtil;
import com.uqbar.vainilla.utils.Vector2D;

public class Pacman extends GameComponent<PacmanLevelScene> {
	private static int WAITINGTIME = ResourceUtil.getResourceInt("Pacman.WAITINGTIME");
	private static int WAITINGTIMEFACTOR = ResourceUtil.getResourceInt("Pacman.WAITINGTIMEFACTOR");
	private Vector2D direction;
	private int row = 181;
	private int column = 115;
	private double waitingTime = 0;
	private Point previousPosition = new Point();
	private boolean isAlive = true;
	
	public Pacman() {
		super();
		this.setDirection(GlobalResources.DIRECTION_LEFT);
		this.setAppearance(SpriteManager.INSTANCE.getAnimation(Pacman.class.getSimpleName()+ "LEFT"));
	}
	
	public Pacman(Point position){
		super(SpriteManager.INSTANCE.getAnimation(
				Pacman.class.getSimpleName()+ "LEFT"),
				position.getX() * GlobalResources.SCALEFACTOR,
				(position.getY()+1) * GlobalResources.SCALEFACTOR);
		this.setRow((1+position.y));
		this.setColumn(position.x);
		this.setDirection(GlobalResources.DIRECTION_LEFT);
	}

	public void resetAppearance() {
		this.setAppearance(this.getDefaultAppearance());
	}

	@Override
	public void update(DeltaState deltaState) {
//		if(this.isAlive()){
			this.changeDirection(deltaState);
			this.doMovement(deltaState);
			this.eatPill();
			super.update(deltaState);
//		}else{
//			this.deathSound.play(1);
//			this.getGame().setCurrentScene(new GameOverScene());
//		}
	}

	private Appearance getDefaultAppearance() {
		Animation animation = null;
		if (this.getDirection().asVersor().equals(GlobalResources.DIRECTION_UP)) {
			animation = SpriteManager.INSTANCE.getAnimation("PacmanUP");
		} else if (this.getDirection().asVersor().equals(GlobalResources.DIRECTION_DOWN)) {
			animation = SpriteManager.INSTANCE.getAnimation("PacmanDOWN");
		} else if (this.getDirection().asVersor().equals(GlobalResources.DIRECTION_RIGHT)) {
			animation = SpriteManager.INSTANCE.getAnimation("PacmanRIGHT");
		} else if (this.getDirection().asVersor().equals(GlobalResources.DIRECTION_LEFT)) {
			animation = SpriteManager.INSTANCE.getAnimation("PacmanLEFT");
		}
		return animation;
	}

	private void doMovement(DeltaState deltaState) {
		if (this.getWaitingTime() > WAITINGTIME) {
			if(this.canMove()){
				this.column = this.obtainNextCol();
				this.getPreviousPosition().setLocation(this.getColumn(), this.getRow());
				this.row = this.row + (int) this.direction.getY();
				this.setX(this.column * GlobalResources.SCALEFACTOR);
				this.setY(this.row * GlobalResources.SCALEFACTOR);
				this.setWaitingTime(0);
			}else{
//				baja el frame rate porque esta seteando la apariencia a cada rato
//				this.setAppearance(SpriteManager.INSTANCE.getCroppedPacmanSprite(489,1, 13, 13).scaleTo(WIDHT, HEIGHT);
			}
		} else {
			this.increaseWaitingTime(deltaState.getDelta());
		}
	}

	private void eatPill() {
		for (Pill pill : this.getScene().getPills()) {
			if (this.canEatPill(pill)) {
				this.getScene().removePill(pill);
				break;
			}
		}
	}

	private boolean canEatPill(Pill pill) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstRect(pill.getX(), pill.getY(), 5, this.getX(),this.getY(), this.getAppearance().getWidth(), this.getAppearance().getHeight());
	}

	private int obtainNextCol() {
		Node<Valuable> node = this.getScene().getMapGraph()
				.obtainNode(this.getRow(), this.getColumn());
		if (this.getDirection().equals(GlobalResources.DIRECTION_LEFT)) {
			return (int) node.getLeftAdjacency().getColumn();
		}
		if (this.getDirection().equals(GlobalResources.DIRECTION_RIGHT)) {
			return (int) node.getRightAdjacency().getColumn();
		}
		return this.getColumn();
	}

	private boolean canMove() {
		if (this.direction.getY() == -1) {
			return !this.checkUpCollision();
		}
		if (this.direction.getY() == 1) {
			return !this.checkDownCollision();
		}
		if (this.direction.getX() == -1) {
			return !this.checkLeftCollision();
		}
		if (this.direction.getX() == 1) {
			return !this.checkRightCollision();
		}
		return false;
	}

	private void changeDirection(DeltaState deltaState) {
		if (this.getWaitingTime() > 1) {
			if (deltaState.isKeyBeingHold(Key.UP) && !this.getDirection().equals(GlobalResources.DIRECTION_UP) && !this.checkUpCollision()) {
				this.setDirection(GlobalResources.DIRECTION_UP);
				this.resetAppearance();
			} else if (deltaState.isKeyBeingHold(Key.DOWN) && !this.getDirection().equals(GlobalResources.DIRECTION_DOWN) && !this.checkDownCollision()) {
				this.setDirection(GlobalResources.DIRECTION_DOWN);
				this.resetAppearance();
			} else if (deltaState.isKeyBeingHold(Key.LEFT) && !this.getDirection().equals(GlobalResources.DIRECTION_LEFT) && !this.checkLeftCollision()) {
				this.setDirection(GlobalResources.DIRECTION_LEFT);
				this.resetAppearance();
			} else if (deltaState.isKeyBeingHold(Key.RIGHT) && !this.getDirection().equals(GlobalResources.DIRECTION_RIGHT) && !this.checkRightCollision()) {
				this.setDirection(GlobalResources.DIRECTION_RIGHT);
				this.resetAppearance();
			}
		}
	}

	private boolean checkUpCollision() {
		Node<Valuable> currentNode = this.getScene().getMapGraph().obtainNode(this.row, this.column);
		return !currentNode.isHasUpAdjacency();
	}

	private boolean checkDownCollision() {
		Node<Valuable> currentNode = this.getScene().getMapGraph().obtainNode(this.row, this.column);
		return !currentNode.isHasDownAdjacency();
	}

	private boolean checkRightCollision() {
		Node<Valuable> currentNode = this.getScene().getMapGraph().obtainNode(this.row, this.column);
		return !currentNode.isHasRightAdjacency();
	}

	private boolean checkLeftCollision() {
		Node<Valuable> currentNode = this.getScene().getMapGraph().obtainNode(this.row, this.column);
		return !currentNode.isHasLeftAdjacency();
	}

	private void increaseWaitingTime(double delta) {
		this.setWaitingTime(this.getWaitingTime() + delta * WAITINGTIMEFACTOR);
	}

	protected double getWaitingTime() {
		return this.waitingTime;
	}

	protected void setWaitingTime(double waitingTime) {
		this.waitingTime = waitingTime;
	}

	public Vector2D getDirection() {
		return this.direction;
	}

	public void setDirection(Vector2D direction) {
		this.direction = direction;
	}

	public int getRow() {
		return this.row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return this.column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	public Point getPreviousPosition() {
		return this.previousPosition;
	}

	public void setPreviousPosition(Point previousPosition) {
		this.previousPosition = previousPosition;
	}

	public boolean isAlive() {
		return this.isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}
