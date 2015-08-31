package com.sn.ddsgame.level;

public class Level {
	
	public static final int NONE = -2;
	public static final int RANDOM = -1;
	
	public LevelData data = null;
	private int stepIndex = 0;
	private float stepTime = 0;
	private boolean over = false;
	
	public Level(LevelData data) {
		this.data = data;
	}
	
	public void start() {
		over = false;
		stepIndex = 0;
		stepTime = data.roundTime;
	}
	
	public int step(float dt) {
		stepTime -= dt;
		if (stepTime > 0) {
			return NONE;
		}
		PosTime pt = data.postimes.get(stepIndex++);
		stepTime = pt.time;
		
		if (stepIndex >= data.postimes.size()) {
			over = true;
		}
		return pt.pos;
	}

	public boolean isOver() {
		return over;
	}

//	public int getNext() {
//		if (stepIndex >= data.postimes.size()) {
//			return NONE;
//		}
//		PosTime pt = data.postimes.get(stepIndex);
//		return pt.pos;
//	}
}
