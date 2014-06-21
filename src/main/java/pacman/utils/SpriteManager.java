package pacman.utils;

import com.uqbar.vainilla.appearances.Sprite;

public class SpriteManager {

	public static Sprite getPacmanSprite(){
		return Sprite.fromImage("images/pacman10-hp-sprite.png");
	}
	
	public static Sprite getCroppedPacmanSprite(int initX, int initY, int width, int height){
		return SpriteManager.getPacmanSprite().crop(initX, initY, width, height);
	}
	
}
