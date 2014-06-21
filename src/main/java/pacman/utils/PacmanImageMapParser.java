package pacman.utils;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

//import com.uqbar.vainilla.maps.AbstractImageMapParser;
//
//public class PacmanImageMapParser extends AbstractImageMapParser{
//
//	public PacmanImageMapParser(String file) {
//		super(file);
//	}
//
//	@Override
//	public ArrayList<Double> getEnemySpawnPoints() {
//		ArrayList<Double> spawnPoints = new ArrayList<Double>();
//		Color[][] terrainGrid = this.getTerrainGrid();
//		for (int i = 0; i < terrainGrid.length; i++) {
//			for (int j = 0; j < terrainGrid[0].length; j++) {
//				if(terrainGrid[i][j].equals(Color.RED)) {
//					spawnPoints.add(new Double(i, j));
//				}
//			}
//		}
//		return spawnPoints;
//	}
//	
//	public ArrayList<Double> getPillsSpawnPoints() {
//		ArrayList<Double> spawnPoints = new ArrayList<Double>();
//		Color[][] terrainGrid = this.getTerrainGrid();
//		for (int i = 0; i < terrainGrid.length; i++) {
//			for (int j = 0; j < terrainGrid[0].length; j++) {
////				System.out.println(terrainGrid[i][j].getRGB());
//				if(terrainGrid[i][j].getRGB() == -10240) {
//					spawnPoints.add(new Double(i, j));
//				}
//			}
//		}
//		return spawnPoints;
//	}
//
//	public Double getPacmanSpawnPosition() {
//		Color[][] terrainGrid = this.getTerrainGrid();
//		for (int i = 0; i < terrainGrid.length; i++) {
//			for (int j = 0; j < terrainGrid[0].length; j++) {
//				if(terrainGrid[i][j].equals(Color.WHITE)) {
//					return new Double(i, j);
//				}
//			}
//		}
//		return null;
//	}
//
//}
