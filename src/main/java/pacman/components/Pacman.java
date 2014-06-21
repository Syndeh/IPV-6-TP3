package pacman.components;

import pacman.scene.PacmanLevelScene;
import pacman.utils.SpriteManager;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.events.constants.Key;
import com.uqbar.vainilla.graphs.Node;
import com.uqbar.vainilla.graphs.Valuable;
import com.uqbar.vainilla.utils.Vector2D;

public class Pacman extends GameComponent<PacmanLevelScene> {

	private Vector2D direction;
	private int row = 181;
	private int column = 115;
	private double waitingTime = 0;

	public Pacman() {
		super(SpriteManager.INSTANCE.getAnimation(Pacman.class.getSimpleName()
				+ "LEFT"), 115*2, 181*2);
		this.direction = new Vector2D(1, 0);
	}

	@Override
	public void update(DeltaState deltaState) {
		super.update(deltaState);
		this.changeDirection(deltaState);
		this.doMovement(deltaState);
	}

	private void doMovement(DeltaState deltaState) {

		if(this.canMove() && this.getWaitingTime()>1){
			this.column = this.column + (int)this.direction.getX();
			this.row = this.row + (int)this.direction.getY();
			this.setX(this.column*2);
			this.setY(this.row*2);
			this.setWaitingTime(0);
		}else{
			this.increaseWaitingTime(deltaState.getDelta());
		}
	}

	private boolean canMove() {
		if(this.direction.getY()==-1){
			return !this.checkUpCollision();
		}
		if(this.direction.getY()==1){
			return !this.checkDownCollision();
		}
		
		if(this.direction.getX()==-1){
			return !this.checkLeftCollision();
		}
		
		if(this.direction.getX()==1){
			return !this.checkRightCollision();
		}
		return false;
	}

	private Vector2D getPosition() {
		return new Vector2D(this.getX(), this.getY());
	}

	private void changeDirection(DeltaState deltaState) {
		if(this.getWaitingTime()>1){
		if (deltaState.isKeyPressed(Key.UP) || deltaState.isKeyBeingHold(Key.UP)) {
			if(!this.checkUpCollision()){
				this.direction = new Vector2D(0, -1);
//				this.setAppearance(SpriteManager.INSTANCE.getAnimation(Pacman.class
//						.getSimpleName() + "UP"));
			}
		} else if (deltaState.isKeyPressed(Key.DOWN) || deltaState.isKeyBeingHold(Key.DOWN)) {
			if(!this.checkDownCollision()){
				this.direction = new Vector2D(0, 1);
//				this.setAppearance(SpriteManager.INSTANCE.getAnimation(Pacman.class
//						.getSimpleName() + "DOWN"));
			}
		} else if (deltaState.isKeyPressed(Key.LEFT) || deltaState.isKeyBeingHold(Key.LEFT)) {
			if(!this.checkLeftCollision()){
				this.direction = new Vector2D(-1, 0);
//				this.setAppearance(SpriteManager.INSTANCE.getAnimation(Pacman.class
//						.getSimpleName() + "LEFT"));
			}
		} else if (deltaState.isKeyPressed(Key.RIGHT)|| deltaState.isKeyBeingHold(Key.RIGHT)) {
			if(!this.checkRightCollision())
			{
				this.direction = new Vector2D(1, 0);
//				this.setAppearance(SpriteManager.INSTANCE.getAnimation(Pacman.class
//						.getSimpleName() + "RIGHT"));
			}
		}
		}
	}

	private boolean checkUpCollision() {
		Node<Valuable> currentNode = this.getScene().getMapGraph().obtainNode(row, column);
		return !currentNode.isHasUpAdjacency();
	}
	
	private boolean checkDownCollision() {

		Node<Valuable> currentNode = this.getScene().getMapGraph().obtainNode(this.row, column);
		return !currentNode.isHasDownAdjacency();
	}
	private boolean checkRightCollision() {
		Node<Valuable> currentNode = this.getScene().getMapGraph().obtainNode(row, column);
		return !currentNode.isHasRightAdjacency();
	}
	
	private boolean checkLeftCollision() {
		Node<Valuable> currentNode = this.getScene().getMapGraph().obtainNode(row, column);
		return !currentNode.isHasLeftAdjacency();
	}
	
	private void increaseWaitingTime(double delta) {
		System.out.println(delta);
		this.setWaitingTime(this.getWaitingTime()+delta*100);
		
	}

	protected double getWaitingTime() {
		return waitingTime;
	}

	protected void setWaitingTime(double waitingTime) {
		this.waitingTime = waitingTime;
	}

}
