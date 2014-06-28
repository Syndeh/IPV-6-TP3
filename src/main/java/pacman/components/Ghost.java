package pacman.components;

import java.awt.Point;

import pacman.ghostmovements.rules.Movement;
import pacman.scene.PacmanLevelScene;
import pacman.utils.SpriteManager;

import com.uqbar.vainilla.AIComponent;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.utils.Vector2D;

public class Ghost extends AIComponent<PacmanLevelScene> {
	
	private double waitingTime = 0;
	private Movement movementRule;
	private int velocity = 50;
	private String name;
	private Point previousPosition = new Point();
	private Vector2D direction = new Vector2D(0, -1);
	
	public Ghost(){}
	
	public Ghost(String name) {
		super(SpriteManager.INSTANCE.getCroppedPacmanSprite(457, 65, 14, 14).scale(2),108*2,112*2);
		this.name = name;
	}
	
	public void changeDirection(int column, int row){
		int x = (int)this.getX() - column;
		int y = (int)this.getY() - row;
		this.setDirection(new Vector2D(x/2, y/2));
	}

	@Override
	public void update(DeltaState deltaState) {
		super.update(deltaState);
		if(this.canMove())
		{
			int row = (int)this.getY();
			int col = (int)this.getX();
			
			this.getMovementRule().move(this);
			this.getPreviousPosition().setLocation(row, col);
			this.changeDirection(col, row);
			this.setWaitingTime(0);
			
		}else{
			this.increaseWaitingTime(deltaState.getDelta());
		}
	}

	private void increaseWaitingTime(double delta) {
		this.setWaitingTime(this.getWaitingTime()+delta*this.getVelocity());
	}

	private boolean canMove() {
		return this.getWaitingTime()>1;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	protected double getWaitingTime() {
		return this.waitingTime;
	}

	protected void setWaitingTime(double waitingTime) {
		this.waitingTime = waitingTime;
	}
	public Movement getMovementRule() {
		return movementRule;
	}

	public void setMovementRule(Movement movementRule) {
		this.movementRule = movementRule;
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public Point getPreviousPosition() {
		return previousPosition;
	}

	public void setPreviousPosition(Point previousPosition) {
		this.previousPosition = previousPosition;
	}

	public Vector2D getDirection() {
		return direction;
	}

	public void setDirection(Vector2D direction) {
		this.direction = direction;
	}
	
	
	
}
