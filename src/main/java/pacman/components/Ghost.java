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
	private String name;
	
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
		if(this.canMove())
		{
			MapGraph<Valuable> mapGraph = this.getScene().getMapGraph();
			Node<Valuable> source = mapGraph.obtainNode((int)this.getY()/2,(int)this.getX()/2);
			Node<Valuable> destination = this.getScene().getPacmanPosition();
			List<Node<Valuable>> path = mapGraph.getShortestPath(source, destination);
			if(path!=null && path.size()>0)
			{
				source.setMinDistance(Double.POSITIVE_INFINITY);
				source.setPrevious(null);
				this.setX(path.get(0).getColumn() * 2);
				this.setY(path.get(0).getRow()*2);
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
		return this.waitingTime;
	}

	protected void setWaitingTime(double waitingTime) {
		this.waitingTime = waitingTime;
	}
}
