package pacman.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.utils.ClassLoaderResourcesProvider;
import com.uqbar.vainilla.utils.ResourceProvider;

public class PacmanTerrainGenerator {

	
	private Color[][] terrainGrid;
	private int cellSize = 40;
	private int width;
	private int height;
	private int rows;
	private int cols;
	
	private ArrayList<Color> terrainAllowedColors = new ArrayList<Color>();
	
	
	public static ResourceProvider defaultResourceProvider = new ClassLoaderResourcesProvider();

	public PacmanTerrainGenerator(PacmanImageMapParser mapParser, int height, int width, int cols, int rows) {
		this.terrainGrid = mapParser.getTerrainGrid();
		this.cols = cols;
		this.rows = rows;
		this.width = width;
		this.height = height;
		
		this.terrainAllowedColors.add(Color.BLACK);
		this.terrainAllowedColors.add(new Color(-16767233));
		//this.cellSize = this.width / this.rows;
	}
	
	public Sprite getSprite() {
		return new Sprite(this.getImage());
	}

	public BufferedImage getImage() {
		BufferedImage image = null;
		image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_BGR);
		try {
			//image = ImageIO.read(defaultResourceProvider.getResource("images/imagenVacia.jpg"));
			Graphics graphics = image.getGraphics();
			
			Color terrainColor;
			
			for (int i = 0; i < terrainGrid.length; i++) {
				int x = i * this.cellSize;
				for (int j = 0; j < terrainGrid[0].length; j++) {
					int y = j * cellSize;
					terrainColor = terrainGrid[i][j];
					System.out.println(terrainColor.getRGB());
					if( terrainAllowedColors.contains(terrainColor)) {
					
						graphics.setColor(terrainColor);
						graphics.fillRect(y, x, this.cellSize, this.cellSize);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}

	
}
