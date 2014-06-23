package pacman.components;

import java.awt.Color;
import java.awt.geom.Point2D.Double;

import pacman.scene.PacmanLevelScene;
import pacman.utils.PacmanTerrainGenerator;

import com.uqbar.vainilla.GameComponent;

public class Scenary extends GameComponent<PacmanLevelScene> {

	private double height;
	private double width;
	private int rows;
	private int cols;

	public Scenary(double height, double width, int rows, int cols) {
		this.height = height;
		this.width = width;
		this.rows = rows;
		this.cols = cols;
		// this.setAppearance(new Rectangle(Color.BLACK, (int)width,
		// (int)height));
	}

	@Override
	public void onSceneActivated() {
		super.onSceneActivated();
		PacmanTerrainGenerator terrainGenerator = new PacmanTerrainGenerator(
				this.getScene().getMapParser()
				, this.getGame().getDisplayHeight()
				, this.getGame().getDisplayWidth()
				, this.getScene().getMapParser().getHeight()
				, this.getScene().getMapParser().getWidth()
		);
		this.setAppearance(terrainGenerator.getSprite());
	}

	public double getTileHeight() {
		return this.height / this.rows;
	}

	public double getTileWidth() {
		return this.width / this.cols;
	}

	public Double getScenaryPosition(Double position) {
		return new Double(position.getY() * getTileWidth() + getTileWidth() / 2, position.getX()
				* getTileHeight() + getTileHeight() / 2);
	}

}
