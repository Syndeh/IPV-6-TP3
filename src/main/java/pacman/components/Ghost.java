package pacman.components;

import java.awt.Point;

import pacman.ghostmovements.rules.Movement;
import pacman.scene.PacmanLevelScene;
import pacman.utils.GlobalResources;
import pacman.utils.SpriteManager;

import com.uqbar.vainilla.AIComponent;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.colissions.CollisionDetector;
import com.uqbar.vainilla.utils.ResourceUtil;
import com.uqbar.vainilla.utils.Vector2D;

public class Ghost extends AIComponent<PacmanLevelScene> {


	private double waitingTime = 0;
	private Movement movementRule;
	private int velocity = ResourceUtil.getResourceInt("Ghost.Velocity");
	private String name;
	private Point previousPosition = new Point();
	private Vector2D direction = new Vector2D(0, -1);
	
	public Ghost(){}
	
	public Ghost(String name, Point position) {
		super(SpriteManager.INSTANCE.getAnimation("Ghost"+name+"UP")
				,position.getX() * GlobalResources.SCALEFACTOR,
				(position.getY()+1) * GlobalResources.SCALEFACTOR);
		this.name = name;
	}
	
	public void changeDirection(int column, int row){
		int x = (int)this.getX() - column;
		int y = (int)this.getY() - row;
		Vector2D newDirection = new Vector2D(x/GlobalResources.SCALEFACTOR, y/GlobalResources.SCALEFACTOR);
		if(!newDirection.equals(this.getDirection())){
			this.setDirection(newDirection);
			this.setNewAppearence();
		}
	}

	private void setNewAppearence() {
		if(this.getDirection().equals(GlobalResources.DIRECTION_UP)){
			this.setAppearance(SpriteManager.INSTANCE.getAnimation("Ghost"+this.getName()+"UP"));
		}else if(this.getDirection().equals(GlobalResources.DIRECTION_RIGHT)){
			this.setAppearance(SpriteManager.INSTANCE.getAnimation("Ghost"+this.getName()+"RIGHT"));
		}else if(this.getDirection().equals(GlobalResources.DIRECTION_DOWN)){
			this.setAppearance(SpriteManager.INSTANCE.getAnimation("Ghost"+this.getName()+"DOWN"));
		}else if(this.getDirection().equals(GlobalResources.DIRECTION_LEFT)){
			this.setAppearance(SpriteManager.INSTANCE.getAnimation("Ghost"+this.getName()+"LEFT"));
		}
	}

	@Override
	public void update(DeltaState deltaState) {
		super.update(deltaState);
		this.checkPacmanCollision();
		if(this.canMove()){
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

	private void checkPacmanCollision() {
		if(CollisionDetector.INSTANCE.collidesCircleAgainstCircle(this.getScene().getPacman().getX(),this.getScene().getPacman().getY(),12,this.getX(),this.getY(),12)){
//			this.getScene().getPacman().setAlive(false);
			this.getScene().killPacman();
		}
	}

	private void increaseWaitingTime(double delta) {
		this.setWaitingTime(this.getWaitingTime() + delta * this.getVelocity());
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
		return this.movementRule;
	}

	public void setMovementRule(Movement movementRule) {
		this.movementRule = movementRule;
	}

	public int getVelocity() {
		return this.velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public Point getPreviousPosition() {
		return this.previousPosition;
	}

	public void setPreviousPosition(Point previousPosition) {
		this.previousPosition = previousPosition;
	}

	public Vector2D getDirection() {
		return this.direction;
	}

	public void setDirection(Vector2D direction) {
		this.direction = direction;
	}
	
	
	
}
