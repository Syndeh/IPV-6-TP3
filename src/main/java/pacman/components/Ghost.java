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
		super(SpriteManager.getCroppedPacmanSprite(2, 122, 15, 15),10,10);
	}
	
	@Override
	public void render(Graphics2D graphics) {
		super.render(graphics);
	}
	
	@Override
	public void update(DeltaState deltaState) {
		
		MapGraph<Valuable> mapGraph = this.getScene().getMapGraph();
		deltaState.getDelta();
		if(this.canMove())
		{
			Node<Valuable> source = mapGraph.obtainNode((int)this.getY(),(int)this.getX());
			Node<Valuable> destination = mapGraph.obtainNode(41,36);
			List<Node<Valuable>> path = mapGraph.getShortestPath(source, destination);
			if(path!=null && path.size()>0)
			{
				this.setX(path.get(0).getColumn());
				this.setY(path.get(0).getRow());
			}
		}else{
			this.increaseWaitingTime(deltaState.getDelta());
		}
	    
		super.update(deltaState);
	}

	private void increaseWaitingTime(double delta) {
		this.setWaitingTime(this.getWaitingTime()+delta);
		
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
