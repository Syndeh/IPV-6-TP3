package pacman.utils;

import com.uqbar.vainilla.utils.ResourceUtil;
import com.uqbar.vainilla.utils.Vector2D;

public class GlobalResources {
	public static final Vector2D DIRECTION_UP = new Vector2D(0, -1);
	public static final Vector2D DIRECTION_DOWN = new Vector2D(0, 1);
	public static final Vector2D DIRECTION_RIGHT = new Vector2D(1, 0);
	public static final Vector2D DIRECTION_LEFT = new Vector2D(-1, 0);
	public static final int SCALEFACTOR = ResourceUtil.getResourceInt("Pacman.SCALEFACTOR");
}
