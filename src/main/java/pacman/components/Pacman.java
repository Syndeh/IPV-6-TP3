package pacman.components;

import pacman.scene.PacmanLevelScene;
import pacman.utils.SpriteManager;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Animation;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.events.constants.Key;
import com.uqbar.vainilla.graphs.Node;
import com.uqbar.vainilla.graphs.Valuable;
import com.uqbar.vainilla.utils.Vector2D;

public class Pacman extends GameComponent<PacmanLevelScene> {

	private Vector2D direction;
	private int row = 0;
	private int column = 0;
	private double waitingTime = 0;
	public static final Vector2D DIRECTION_UP = new Vector2D(0,-1);
	public static final Vector2D DIRECTION_DOWN = new Vector2D(0, 1);
	public static final Vector2D DIRECTION_LEFT = new Vector2D(-1, 0);
	public static final Vector2D DIRECTION_RIGHT = new Vector2D(1, 0);

	public Pacman() {
		
		this.setDirection(DIRECTION_RIGHT);
		this.resetAppearance();
	}

	public void resetAppearance() {
		this.setAppearance(this.getDefaultAppearance());
	}

	@Override
	public void update(DeltaState deltaState) {
		super.update(deltaState);
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
		
		if( this.canMove() && this.getWaitingTime()>1 ){
		//if( this.canMove() ){
			this.column = this.column + (int)this.direction.getX();
			this.row = this.row + (int)this.direction.getY();
			this.setX(this.column*40);
			this.setY(this.row*40);
			this.getScene().setPacmanRow(this.row);
			this.getScene().setPacmanColumn(this.column);
			this.setWaitingTime(0);
		}
		else{
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

	
	private void changeDirection(DeltaState deltaState) {

		if(this.getWaitingTime()>1){
			if (deltaState.isKeyPressed(Key.UP) || deltaState.isKeyBeingHold(Key.UP)) {
				if(!this.getDirection().equals(DIRECTION_UP)){
					if(!this.checkUpCollision()){
						this.setDirection(DIRECTION_UP);
						this.resetAppearance();
					}
				}
			} else if (deltaState.isKeyPressed(Key.DOWN) || deltaState.isKeyBeingHold(Key.DOWN)) {
				if(!this.getDirection().equals(DIRECTION_DOWN)){
					if(!this.checkDownCollision()){
						this.setDirection(DIRECTION_DOWN);
						this.resetAppearance();
					}
				}
			} else if (deltaState.isKeyPressed(Key.LEFT) || deltaState.isKeyBeingHold(Key.LEFT)) {
				if(!this.getDirection().equals(DIRECTION_LEFT)){
					if(!this.checkLeftCollision()){
						this.setDirection(DIRECTION_LEFT);
						this.resetAppearance();
					}
				}
			} else if (deltaState.isKeyPressed(Key.RIGHT)|| deltaState.isKeyBeingHold(Key.RIGHT)) {
				if(!this.getDirection().equals(DIRECTION_RIGHT)){
					if(!this.checkRightCollision())
					{
						this.setDirection(DIRECTION_RIGHT);
						this.resetAppearance();
					}
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
		this.setWaitingTime(this.getWaitingTime()+delta*120);
		
	}

	protected double getWaitingTime() {
		return waitingTime;
	}

	protected void setWaitingTime(double waitingTime) {
		this.waitingTime = waitingTime;
	}

	public Vector2D getDirection() {
		return direction;
	}

	public void setDirection(Vector2D direction) {
		this.direction = direction;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

}
