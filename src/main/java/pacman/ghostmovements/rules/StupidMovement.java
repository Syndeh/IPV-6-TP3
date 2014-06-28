package pacman.ghostmovements.rules;

import com.uqbar.vainilla.graphs.MapGraph;
import com.uqbar.vainilla.graphs.Node;
import com.uqbar.vainilla.graphs.Valuable;
import com.uqbar.vainilla.utils.Vector2D;

import pacman.components.Ghost;

public class StupidMovement implements Movement {

	private static final Vector2D DIRECTION_DOWN = new Vector2D(0, 1);
	private static final Vector2D DIRECTION_LEFT = new Vector2D(-1, 0);
	private static final Vector2D DIRECTION_RIGHT = new Vector2D(1, 0);
	@Override
	public void move(Ghost ghost) {
		MapGraph<Valuable> mapGraph = ghost.getScene().getMapGraph();
		Node<Valuable> currentNode = mapGraph.obtainNode((int)ghost.getY()/2,(int)ghost.getX()/2);
		if(ghost.getDirection().equals(DIRECTION_RIGHT)){
			if(currentNode.isHasRightAdjacency() && currentNode.getAdjancencies().size()<=2){
				ghost.setX(currentNode.getRightAdjacency().getColumn()* 2);
				ghost.setY(currentNode.getRightAdjacency().getRow()* 2);
			}else{
				Node<Valuable> nextAdjacency = this.getNextAdjacency(currentNode, currentNode.getRightAdjacency());
				ghost.setX(nextAdjacency.getColumn() * 2);
				ghost.setY(nextAdjacency.getRow()* 2);
			}
		}else if(ghost.getDirection().equals(DIRECTION_DOWN) && currentNode.getAdjancencies().size()<=2){
			if(currentNode.isHasDownAdjacency()){
				ghost.setX(currentNode.getDownAdjacency().getColumn()* 2);
				ghost.setY(currentNode.getDownAdjacency().getRow()* 2);
			}else{
				Node<Valuable> nextAdjacency = this.getNextAdjacency(currentNode, currentNode.getDownAdjacency());
				ghost.setX(nextAdjacency.getColumn() * 2);
				ghost.setY(nextAdjacency.getRow()* 2);
			}
		}else if(ghost.getDirection().equals(DIRECTION_LEFT) && currentNode.getAdjancencies().size()<=2){
			if(currentNode.isHasLeftAdjacency()){
				ghost.setX(currentNode.getLeftAdjacency().getColumn()* 2);
				ghost.setY(currentNode.getLeftAdjacency().getRow()* 2);
			}else {
				Node<Valuable> nextAdjacency = this.getNextAdjacency(currentNode, currentNode.getLeftAdjacency());
				ghost.setX(nextAdjacency.getColumn() * 2);
				ghost.setY(nextAdjacency.getRow()* 2);
			}
		}else{
			if(currentNode.isHasUpAdjacency() && currentNode.getAdjancencies().size()<=2){
				ghost.setX(currentNode.getUpAdjacency().getColumn() * 2);
				ghost.setY(currentNode.getUpAdjacency().getRow()* 2);
			}else{
				Node<Valuable> nextAdjacency = this.getNextAdjacency(currentNode, currentNode.getUpAdjacency());
				ghost.setX(nextAdjacency.getColumn() * 2);
				ghost.setY(nextAdjacency.getRow()* 2);
			}
			
		}
		
	}
	private Node<Valuable> getNextAdjacency(Node<Valuable> currentNode,
			Node<Valuable> currentAdjacency) {
		int index = (int) (Math.random() * (currentNode.getAdjancencies().size()));
		Node<Valuable> nextAdjacency = currentNode.getAdjancencies().get(index).getDestination();
		return nextAdjacency;
		
	}
}
