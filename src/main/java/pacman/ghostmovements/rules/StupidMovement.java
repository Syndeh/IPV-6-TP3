package pacman.ghostmovements.rules;

import com.uqbar.vainilla.graphs.MapGraph;
import com.uqbar.vainilla.graphs.Node;
import com.uqbar.vainilla.graphs.Valuable;
import com.uqbar.vainilla.utils.Vector2D;

import pacman.components.Ghost;
import pacman.utils.GlobalResources;

public class StupidMovement implements Movement {


	@Override
	public void move(Ghost ghost) {
		MapGraph<Valuable> mapGraph = ghost.getScene().getMapGraph();
		Node<Valuable> currentNode = mapGraph.obtainNode((int)ghost.getY()/GlobalResources.SCALEFACTOR,(int)ghost.getX()/GlobalResources.SCALEFACTOR);
		if(ghost.getDirection().equals(GlobalResources.DIRECTION_RIGHT)){
			if(currentNode.isHasRightAdjacency() && currentNode.getAdjancencies().size()<=2){
				ghost.setX(currentNode.getRightAdjacency().getColumn()* GlobalResources.SCALEFACTOR);
				ghost.setY(currentNode.getRightAdjacency().getRow()* GlobalResources.SCALEFACTOR);
			}else{
				Node<Valuable> nextAdjacency = this.getNextAdjacency(currentNode, currentNode.getRightAdjacency());
				ghost.setX(nextAdjacency.getColumn() * GlobalResources.SCALEFACTOR);
				ghost.setY(nextAdjacency.getRow()* GlobalResources.SCALEFACTOR);
			}
		}else if(ghost.getDirection().equals(GlobalResources.DIRECTION_DOWN) && currentNode.getAdjancencies().size()<=2){
			if(currentNode.isHasDownAdjacency()){
				ghost.setX(currentNode.getDownAdjacency().getColumn()* GlobalResources.SCALEFACTOR);
				ghost.setY(currentNode.getDownAdjacency().getRow()* GlobalResources.SCALEFACTOR);
			}else{
				Node<Valuable> nextAdjacency = this.getNextAdjacency(currentNode, currentNode.getDownAdjacency());
				ghost.setX(nextAdjacency.getColumn() * 2);
				ghost.setY(nextAdjacency.getRow()* 2);
			}
		}else if(ghost.getDirection().equals(GlobalResources.DIRECTION_LEFT) && currentNode.getAdjancencies().size()<=2){
			if(currentNode.isHasLeftAdjacency()){
				ghost.setX(currentNode.getLeftAdjacency().getColumn()* GlobalResources.SCALEFACTOR);
				ghost.setY(currentNode.getLeftAdjacency().getRow()* GlobalResources.SCALEFACTOR);
			}else {
				Node<Valuable> nextAdjacency = this.getNextAdjacency(currentNode, currentNode.getLeftAdjacency());
				ghost.setX(nextAdjacency.getColumn() * 2);
				ghost.setY(nextAdjacency.getRow()* 2);
			}
		}else{
			if(currentNode.isHasUpAdjacency() && currentNode.getAdjancencies().size()<=2){
				ghost.setX(currentNode.getUpAdjacency().getColumn() * GlobalResources.SCALEFACTOR);
				ghost.setY(currentNode.getUpAdjacency().getRow()* GlobalResources.SCALEFACTOR);
			}else{
				Node<Valuable> nextAdjacency = this.getNextAdjacency(currentNode, currentNode.getUpAdjacency());
				ghost.setX(nextAdjacency.getColumn() * GlobalResources.SCALEFACTOR);
				ghost.setY(nextAdjacency.getRow()* GlobalResources.SCALEFACTOR);
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
