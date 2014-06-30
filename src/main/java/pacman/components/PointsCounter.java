package pacman.components;

import java.awt.Color;
import java.awt.Font;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Label;
import com.uqbar.vainilla.components.AbstractCounter;
import com.uqbar.vainilla.utils.ResourceUtil;

public class PointsCounter extends AbstractCounter<GameScene> {
	
	private int points=0;
	private static int FONTSIZE = ResourceUtil.getResourceInt("PointsCounter.FONT.SIZE");
	private static int Y = ResourceUtil.getResourceInt("PointsCounter.Y");
	
	public PointsCounter() {
		this.setAppearance(new Label(new Font(Font.SANS_SERIF, Font.BOLD, FONTSIZE), Color.YELLOW, "Puntos: " + this.getPoints()));
		this.setY(Y);
	}

	@Override
	public void update(DeltaState deltaState) {
		Label label = (Label) this.getAppearance();
		label.setText("Puntos: " + this.getPoints());
	}

	public int getPoints() {
		return this.points;
	}
	
	public void addPoints(int points) {
		this.points += points;
	}
	public void addPoints(String points) {
		this.points += this.getIntPropertyFromConfig(points);
	}
}
