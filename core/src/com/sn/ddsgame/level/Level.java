package com.sn.ddsgame.level;

import com.sn.ddsgame.Dds;

public class Level {
	public static final int END = -3;
	public static final int NO_OUTPUT = -2;
	public static final int RANDOM = -1;
	
	private LevelData data;
	private int round;
	private int index;
	
	public Level(LevelData data) {
		this.data = data;
		round = -1;
		index = -1;
		toRound();
	}
	
	public void start() {
		over = false;
		stepIndex = 0;
		stepTime = data.roundTime;
		
		Dds.gameScreen.setHitNum(0);
		Dds.gameScreen.setMissNum(0);
		Dds.gameScreen.setGameOver(false);
	}
	
	private boolean toRound() {
		round++;
		index = 0;
		if (round >= data.roundNum) {
			return true;
		}
		return false;
	}
	
	public int step(float delta) {
		
		
		return 0;
	}
}
