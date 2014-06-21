package pacman.utils;
import com.uqbar.vainilla.appearances.Animation;
import com.uqbar.vainilla.appearances.Sprite;

public class SpriteManager {


	public static final SpriteManager INSTANCE = new SpriteManager();

	public Animation getAnimation(String spriteRef) {
		return new Animation(0.5, this.getSprite(spriteRef));
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
			result[i] = Sprite.fromImage("images/generalsprite.png").crop(array[i][0], array[i][1], 13, 13).scale(2);
		}
		return result;
	}
	
	public Sprite[] getPacmanUP() {
		int[][] cuts = {{489,2},{473,34},{457,34}};
		return this.cropSprites(cuts);
	}
	
	public Sprite[] getPacmanDOWN() {
		int[][] cuts = {{489,2},{473,49},{457,49}};
		return this.cropSprites(cuts);
	}
	
	public Sprite[] getPacmanLEFT() {
		int[][] cuts = {{489,2},{473,17},{457,17}};
		return this.cropSprites(cuts);
	}
	
	public Sprite[] getPacmanRIGHT() {
		int[][] cuts = {{489,2},{473,1},{457,1}};
		return this.cropSprites(cuts);
	}

	public Sprite getPacmanSprite(){
		return Sprite.fromImage("images/generalsprite.png");
	}
	
	public Sprite getCroppedPacmanSprite(int initX, int initY, int width, int height){
		return SpriteManager.INSTANCE.getPacmanSprite().crop(initX, initY, width, height);
	}

}
