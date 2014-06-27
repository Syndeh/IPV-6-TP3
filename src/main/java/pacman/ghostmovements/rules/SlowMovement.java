package pacman.ghostmovements.rules;

import java.awt.Point;
import java.util.List;

import com.uqbar.vainilla.graphs.MapGraph;
import com.uqbar.vainilla.graphs.Node;
import com.uqbar.vainilla.graphs.Valuable;

import pacman.components.Ghost;

public class SlowMovement implements Movement {

	
	private Point target;
	private List<Node<Valuable>> path;
	@Override
	public void move(Ghost ghost) {
		MapGraph<Valuable> mapGraph = new MapGraph<Valuable>("mapa1.png");
		Node<Valuable> source = mapGraph.obtainNode((int)ghost.getY()/2,(int)ghost.getX()/2);
		Point targetPoint = this.obtainTarget(mapGraph);
		Node<Valuable> destination = mapGraph.obtainNode((int)targetPoint.getY(), (int)targetPoint.getX());
		if(path!=null && path.size()>0)
		{
			source.setMinDistance(Double.POSITIVE_INFINITY);
			source.setPrevious(null);
			ghost.setX(path.get(0).getColumn() * 2);
			ghost.setY(path.get(0).getRow()*2);
			path.get(0).setMinDistance(Double.POSITIVE_INFINITY);
			path.get(0).setPrevious(null);
			path.remove(0);
		}else{
			this.path = mapGraph.getShortestPath(source, destination);
			this.setTarget(null);
		}

	}

	private Point obtainTarget(MapGraph<Valuable> mapGraph) {
		
		if(this.getTarget()==null){
			List<Point> points = mapGraph.getColorsMap().get(1);
			int index = (int) (Math.random() * (points.size() +1 ));
			this.setTarget(points.get(index));
		}
		return this.getTarget();
	}

	public Point getTarget() {
		return target;
	}

	public void setTarget(Point target) {
		this.target = target;
	}


}
