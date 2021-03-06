package pacman.components;

import java.awt.Graphics2D;
import java.util.List;
import pacman.scene.PacmanLevelScene;
import pacman.utils.SpriteManager;
import com.uqbar.vainilla.AIComponent;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.graphs.MapGraph;
import com.uqbar.vainilla.graphs.Node;
import com.uqbar.vainilla.graphs.Valuable;

public class Ghost extends AIComponent<PacmanLevelScene> {
	
	private double waitingTime = 0;
	
	public Ghost() {
		super(SpriteManager.INSTANCE.getCroppedPacmanSprite(457, 65, 14, 14).scale(2),108*2,112*2);
	}
	
	@Override
	public void render(Graphics2D graphics) {
		super.render(graphics);
	}
	
	@Override
	public void update(DeltaState deltaState) {
		super.update(deltaState);
		MapGraph<Valuable> mapGraph = this.getScene().getMapGraph();
		if(this.canMove())
		{
			Node<Valuable> source = mapGraph.obtainNode((int)this.getY()/40,(int)this.getX()/40);
			Node<Valuable> destination = this.getScene().getPacmanPosition();
			List<Node<Valuable>> path = mapGraph.getShortestPath(source, destination);
			if(path!=null && path.size()>0)
			{
				source.setMinDistance(Double.POSITIVE_INFINITY);
				source.setPrevious(null);
				this.setX(path.get(0).getColumn() * 40);
				this.setY(path.get(0).getRow() * 40);
				path.get(0).setMinDistance(Double.POSITIVE_INFINITY);
				path.get(0).setPrevious(null);
			}
			this.setWaitingTime(0);
		}else{
			this.increaseWaitingTime(deltaState.getDelta());
		}
	    
	}

	private void increaseWaitingTime(double delta) {
		this.setWaitingTime(this.getWaitingTime()+delta*50);
		
	}

	private boolean canMove() {
		return this.getWaitingTime()>1;
	}

	protected double getWaitingTime() {
		return waitingTime;
	}

	protected void setWaitingTime(double waitingTime) {
		this.waitingTime = waitingTime;
	}
	
	
	
}
