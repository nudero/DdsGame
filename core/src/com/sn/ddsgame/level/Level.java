package com.sn.ddsgame.level;

public class Level {
	public static final int END = -3;
	public static final int NO_OUTPUT = -2;
	public static final int RANDOM = -1;
	
	private LevelData data;
	private int round;
	private int index;
	private 
	
	public Level(LevelData data) {
		this.data = data;
		round = -1;
		index = -1;
		toRound();
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
