package com.sn.ddsgame;

import com.badlogic.gdx.Game;

public class DdsGame extends Game {
	@Override
	public void create () {
		setScreen(new GameScreen(this));
	}
}
