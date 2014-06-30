package pacman.utils;

import com.uqbar.vainilla.appearances.Animation;
import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Sprite;

public class SpriteManager {

	public static final SpriteManager INSTANCE = new SpriteManager();

	public Animation getAnimation(String spriteRef) {
		return new Animation(0.08, this.getSprite(spriteRef));
	}

	private Sprite[] getSprite(String spriteRef) {
		try {
			return (Sprite[]) this.getClass().getMethod("get" + spriteRef).invoke(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Sprite[] cropSprites(int[][] array, int width, int height) {
		Sprite[] result = new Sprite[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = this.getCroppedPacmanSprite(array[i][0], array[i][1],width, height).scaleTo(28, 28);
		}
		return result;
	}
	
	public Sprite getCroppedPacmanSprite(int initX, int initY, int width,int height) {
		return Sprite.fromImage("images/generalsprite.png").crop(initX, initY,width, height);
	}

	public Sprite[] getPacmanUP() {
		int[][] cuts = { { 489, 1 }, { 473, 33 }, { 457, 33 }, { 473, 33 } };
		return this.cropSprites(cuts, 13, 13);
	}

	public Sprite[] getPacmanDOWN() {
		int[][] cuts = { { 489, 1 }, { 473, 49 }, { 457, 49 }, { 473, 49 } };
		return this.cropSprites(cuts, 13, 13);
	}

	public Sprite[] getPacmanLEFT() {
		int[][] cuts = { { 489, 1 }, { 473, 17 }, { 457, 17 }, { 473, 17 } };
		return this.cropSprites(cuts, 13, 13);
	}

	public Sprite[] getPacmanRIGHT() {
		int[][] cuts = { { 489, 1 }, { 473, 1 }, { 457, 1 }, { 473, 1 } };
		return this.cropSprites(cuts, 13, 13);
	}

	public Sprite[] getGhostPinkyUP() {
		int[][] cuts = { { 521, 81 }, { 537, 81 }, { 521, 81 }, { 537, 81 } };
		return this.cropSprites(cuts, 14, 14);
	}

	public Sprite[] getGhostPinkyDOWN() {
		int[][] cuts = { { 553, 81 }, { 569, 81 }, { 553, 81 }, { 569, 81 } };
		return this.cropSprites(cuts, 14, 14);
	}

	public Sprite[] getGhostPinkyLEFT() {
		int[][] cuts = { { 489, 81 }, { 505, 81 }, { 489, 81 }, { 505, 81 } };
		return this.cropSprites(cuts, 14, 14);
	}

	public Sprite[] getGhostPinkyRIGHT() {
		int[][] cuts = { { 457, 81 }, { 473, 81 }, { 457, 81 }, { 473, 81 } };
		return this.cropSprites(cuts, 14, 14);
	}

	public Sprite[] getGhostInkyUP() {
		int[][] cuts = { { 521, 97 }, { 537, 97 }, { 521, 97 }, { 537, 97 } };
		return this.cropSprites(cuts, 14, 14);
	}

	public Sprite[] getGhostInkyDOWN() {
		int[][] cuts = { { 553, 97 }, { 569, 97 }, { 553, 97 }, { 569, 97 } };
		return this.cropSprites(cuts, 14, 14);
	}

	public Sprite[] getGhostInkyLEFT() {
		int[][] cuts = { { 489, 97 }, { 505, 97 }, { 489, 97 }, { 505, 97 } };
		return this.cropSprites(cuts, 14, 14);
	}

	public Sprite[] getGhostInkyRIGHT() {
		int[][] cuts = { { 457, 97 }, { 473, 97 }, { 457, 97 }, { 473, 97 } };
		return this.cropSprites(cuts, 14, 14);
	}
	
	public Sprite[] getGhostClydeUP() {
		int[][] cuts = { { 521, 113 }, { 537, 113 }, { 521, 113 }, { 537, 113 } };
		return this.cropSprites(cuts, 14, 14);
	}

	public Sprite[] getGhostClydeDOWN() {
		int[][] cuts = { { 553, 113 }, { 569, 113 }, { 553, 113 }, { 569, 113 } };
		return this.cropSprites(cuts, 14, 14);
	}

	public Sprite[] getGhostClydeLEFT() {
		int[][] cuts = { { 489, 113 }, { 505, 113 }, { 489, 113 }, { 505, 113 } };
		return this.cropSprites(cuts, 14, 14);
	}

	public Sprite[] getGhostClydeRIGHT() {
		int[][] cuts = { { 457, 113 }, { 473, 113 }, { 457, 113 }, { 473, 113 } };
		return this.cropSprites(cuts, 14, 14);
	}
	
	public Sprite[] getGhostBlinkyUP() {
		int[][] cuts = { { 521, 65 }, { 537, 65 }, { 521, 65 }, { 537, 65 } };
		return this.cropSprites(cuts, 14, 14);
	}

	public Sprite[] getGhostBlinkyDOWN() {
		int[][] cuts = { { 553, 65 }, { 569, 65 }, { 553, 65 }, { 569, 65 } };
		return this.cropSprites(cuts, 14, 14);
	}

	public Sprite[] getGhostBlinkyLEFT() {
		int[][] cuts = { { 489, 65 }, { 505, 65 }, { 489, 65 }, { 505, 65 } };
		return this.cropSprites(cuts, 14, 14);
	}

	public Sprite[] getGhostBlinkyRIGHT() {
		int[][] cuts = { { 457, 65 }, { 473, 65 }, { 457, 65 }, { 473, 65 } };
		return this.cropSprites(cuts, 14, 14);
	}

	public Appearance getCounterImage() {
		return this.getCroppedPacmanSprite(473, 17, 13, 13);
	}
}
