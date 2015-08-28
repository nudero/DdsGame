package com.sn.ddsgame;

import com.badlogic.gdx.Game;
import com.sn.ddsgame.level.LevelManager;

public class DdsGame extends Game {
	@Override
	public void create () {
		LevelManager.getInstance().loadLevelData();
		setScreen(new MainMenuScreen(this));
	}
}
