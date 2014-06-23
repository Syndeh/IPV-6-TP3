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
			return (Sprite[]) this.getClass().getMethod("get" + spriteRef)
					.invoke(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Sprite[] cropSprites(int[][] array){
		Sprite[] result = new Sprite[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = Sprite.fromImage("images/pacmansprite.png").crop(array[i][0], array[i][1], 13, 13).scaleTo(100, 100);
		}
		return result;
	}
	
	public Sprite[] getPacmanUP() {
		int[][] cuts = {{3,43},{23,43},{3,43},{43,3}};
		return this.cropSprites(cuts);
	}
	public Sprite[] getPacmanDOWN() {
		int[][] cuts = {{3,63},{23,62},{3,63},{43,3}};
		return this.cropSprites(cuts);
	}
	public Sprite[] getPacmanLEFT() {
		int[][] cuts = {{3,3},{23,3},{3,3},{43,3}};
		return this.cropSprites(cuts);
	}
	public Sprite[] getPacmanRIGHT() {
		int[][] cuts = {{3,23},{23,23},{3,23},{43,3}};
		return this.cropSprites(cuts);
	}
}
