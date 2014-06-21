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
		super(SpriteManager.getCroppedPacmanSprite(2, 122, 15, 15),60,10);
	}
	
	@Override
	public void render(Graphics2D graphics) {
		super.render(graphics);
	}
	
	@Override
	public void update(DeltaState deltaState) {
		
		MapGraph<Valuable> mapGraph = this.getScene().getMapGraph();
		if(this.canMove())
		{
			Node<Valuable> source = mapGraph.obtainNode((int)this.getY(),(int)this.getX());
			
			if(this.getY()==179 && this.getX()==360){
				source.setMinDistance(Double.POSITIVE_INFINITY);
				source.setPrevious(null);
				this.getScene().changePacmanPosition();
			}
			Node<Valuable> destination = this.getScene().getPacmanPosition();
			List<Node<Valuable>> path = mapGraph.getShortestPath(source, destination);
			if(path!=null && path.size()>0)
			{
				source.setMinDistance(Double.POSITIVE_INFINITY);
				source.setPrevious(null);
				this.setX(path.get(0).getColumn());
				this.setY(path.get(0).getRow());
				path.get(0).setMinDistance(Double.POSITIVE_INFINITY);
				path.get(0).setPrevious(null);
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
