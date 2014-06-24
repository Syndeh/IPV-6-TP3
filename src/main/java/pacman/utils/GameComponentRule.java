package pacman.utils;

import pacman.scene.PacmanLevelScene;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;

public abstract interface GameComponentRule<ComponentType extends GameComponent<PacmanLevelScene>> {

	public boolean mustApply(ComponentType component, DeltaState deltaState);

	public void apply(ComponentType component, DeltaState deltaState);
		
}
