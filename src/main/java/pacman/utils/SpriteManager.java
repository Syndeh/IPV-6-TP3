package pacman.utils;
import com.uqbar.vainilla.appearances.Animation;
import com.uqbar.vainilla.appearances.Sprite;

public class SpriteManager {


	public static final SpriteManager INSTANCE = new SpriteManager();

	public Animation getAnimation(String spriteRef) {
		return new Animation(5, this.getPacmanLEFT());
//		return new Animation(0.5, this.getSprite(spriteRef));
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

	private Sprite[] cropSprites(){
		return null;
	}
	
	public Sprite[] getPacmanUP() {
		Sprite[] result = new Sprite[4];
		result[0] = Sprite.fromImage("images/pacmansprite.png").crop(3, 43, 13, 13);
		result[1] = Sprite.fromImage("images/pacmansprite.png").crop(23, 43, 13, 13);
		result[2] = Sprite.fromImage("images/pacmansprite.png").crop(3, 43, 13, 13);
		result[3] = Sprite.fromImage("images/pacmansprite.png").crop(43, 3, 13, 13);
		return result;
	}
	public Sprite[] getPacmanDOWN() {
		Sprite[] result = new Sprite[4];
		result[0] = Sprite.fromImage("images/pacmansprite.png").crop(3, 63, 13, 13);
		result[1] = Sprite.fromImage("images/pacmansprite.png").crop(23, 62, 13, 13);
		result[2] = Sprite.fromImage("images/pacmansprite.png").crop(3, 63, 13, 13);
		result[3] = Sprite.fromImage("images/pacmansprite.png").crop(43, 3, 13, 13);
		return result;
	}
	public Sprite[] getPacmanLEFT() {
		Sprite[] result = new Sprite[4];
		result[0] = Sprite.fromImage("images/pacmansprite.png").crop(3, 3, 13, 13).scaleTo(100, 100);
		result[1] = Sprite.fromImage("images/pacmansprite.png").crop(23, 3, 13, 13).scaleTo(100, 100);
		result[2] = Sprite.fromImage("images/pacmansprite.png").crop(3, 3, 13, 13).scaleTo(100, 100);
		result[3] = Sprite.fromImage("images/pacmansprite.png").crop(43, 3, 13, 13).scaleTo(100, 100);
		return result;
	}
	public Sprite[] getPacmanRIGHT() {
		Sprite[] result = new Sprite[4];
		result[0] = Sprite.fromImage("images/pacmansprite.png").crop(23, 3, 13, 13);
		result[1] = Sprite.fromImage("images/pacmansprite.png").crop(23, 23, 13, 13);
		result[2] = Sprite.fromImage("images/pacmansprite.png").crop(23, 3, 13, 13);
		result[3] = Sprite.fromImage("images/pacmansprite.png").crop(43, 3, 13, 13);
		return result;
	}

	public Sprite getPacmanSprite(){
		return Sprite.fromImage("images/generalsprite.png");
	}
	
	public Sprite getCroppedPacmanSprite(int initX, int initY, int width, int height){
		return SpriteManager.INSTANCE.getPacmanSprite().crop(initX, initY, width, height);
	}

}
