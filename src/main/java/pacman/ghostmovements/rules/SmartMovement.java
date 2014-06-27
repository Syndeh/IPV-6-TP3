package pacman.ghostmovements.rules;

import java.util.List;

import com.uqbar.vainilla.graphs.MapGraph;
import com.uqbar.vainilla.graphs.Node;
import com.uqbar.vainilla.graphs.Valuable;

import pacman.components.Ghost;

public class SmartMovement implements Movement{

	@Override
	public void move(Ghost ghost) {
		MapGraph<Valuable> mapGraph = ghost.getScene().getMapGraph();
		Node<Valuable> source = mapGraph.obtainNode((int)ghost.getY()/2,(int)ghost.getX()/2);
		Node<Valuable> destination = ghost.getScene().getPacmanPosition();
		List<Node<Valuable>> path = mapGraph.getShortestPath(source, destination);
		if(path!=null && path.size()>0)
		{
			source.setMinDistance(Double.POSITIVE_INFINITY);
			source.setPrevious(null);
			ghost.setX(path.get(0).getColumn() * 2);
			ghost.setY(path.get(0).getRow()*2);
			path.get(0).setMinDistance(Double.POSITIVE_INFINITY);
			path.get(0).setPrevious(null);
		}
		
	}



}
