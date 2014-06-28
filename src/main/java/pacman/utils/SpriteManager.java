package pacman.utils;

import com.uqbar.vainilla.appearances.Animation;
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
			result[i] = this.getCroppedPacmanSprite(array[i][0], array[i][1],width, height).scaleTo(26, 26);
		}
		return result;
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

	public Sprite getCroppedPacmanSprite(int initX, int initY, int width,int height) {
		return Sprite.fromImage("images/generalsprite.png").crop(initX, initY,width, height);
	}

	public Sprite[] getGhostUP() {
		int[][] cuts = { { 521, 65 }, { 537, 65 }, { 521, 65 }, { 537, 65 } };
		return this.cropSprites(cuts, 14, 14);
	}

	public Sprite[] getGhostDOWN() {
		int[][] cuts = { { 553, 65 }, { 63, 65 }, { 553, 65 }, { 569, 65 } };
		return this.cropSprites(cuts, 14, 14);
	}

	public Sprite[] getGhostLEFT() {
		int[][] cuts = { { 489, 65 }, { 505, 65 }, { 489, 65 }, { 505, 65 } };
		return this.cropSprites(cuts, 14, 14);
	}

	public Sprite[] getGhostRIGHT() {
		int[][] cuts = { { 457, 65 }, { 473, 65 }, { 457, 65 }, { 473, 65 } };
		return this.cropSprites(cuts, 14, 14);
	}

}
