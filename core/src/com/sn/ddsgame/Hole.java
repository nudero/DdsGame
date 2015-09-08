package com.sn.ddsgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Hole {
	private enum HoleState {
		FREE, BUSY
	}
	
	private Color origColor;
	private Image mouse;
	private boolean last = false;
	private int index;
	private HoleState state = HoleState.FREE;
	private static final float BUSY_TIME = 1.0f;
	private float busyTime = 0;
	
	public Hole(Image mouse, int index) {
		this.mouse = mouse;
		this.index = index;
		origColor = new Color(mouse.getColor());
	}
	
	public boolean isFree() {
		return state == HoleState.FREE;
	}
	
	public boolean hit() {
		if (isFree()) {
			return false;
		}
		
		state = HoleState.FREE;
		mouse.setColor(origColor);
		Dds.gameScreen.addHitNum();
		
		if (last) {
			Dds.gameScreen.setGameOver(true);
		}
		return true;
	}
	
	public void miss() {
		state = HoleState.FREE;
		mouse.setColor(origColor);
		
		Dds.gameScreen.addMissNum();
		if (last) {
			Dds.gameScreen.setGameOver(true);
		}
	}
	
	public void step(float dt) {
		if (isFree()) {
			return;
		}
		
		busyTime -= dt;
		if (busyTime <= 0) {
			miss();
		}
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public int getIndex() {
		return index;
	}
	
	public void duuoo(boolean over) {
		this.last = over;
		mouse.setColor(Color.GREEN);
		state = HoleState.BUSY;
		busyTime = BUSY_TIME;
		
//		if (idx == curShowN) {
//		Image img = images.get(curShowN);
//		img.setColor(origColor);
//		curShowN = -1;
//		System.out.println(++score);
//		label.setText(score+"");

	}
	
}
