package com.sn.ddsgame;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sn.ddsgame.level.Level;

public class HoleManager {

	private static HoleManager instance = new HoleManager();
	public static HoleManager getInstance() {
		return instance;
	}
	
	private ArrayList<Hole> holes = new ArrayList<Hole>();
	private ArrayList<Hole> freeHoles = new ArrayList<Hole>();
	
	public void init(Table table, int n) {
		for (int i = 0; i < n; i++) {
			table.row();
			for (int j = 0; j < n; j++) {
				Image mouse = new Image(new Texture("hole.jpg"));
				table.add(mouse);
				mouse.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						int idx = (Integer) event.getTarget().getUserObject();
						if (idx < 0 || idx >= holes.size()) {
							Log.error("click index is invalide");
							return;
						}
						Hole hole = holes.get(idx);
						hole.hit();
//						if (idx == curShowN) {
//							Image img = images.get(curShowN);
//							img.setColor(origColor);
//							curShowN = -1;
//							System.out.println(++score);
//							label.setText(score+"");
//						}
					}
				});
				
				int index = holes.size();
				Hole hole = new Hole(mouse, index);
				mouse.setUserObject(index);
				holes.add(hole);
				
//				RepeatAction act = Actions.repeat(RepeatAction.FOREVER, Actions.sequence(Actions.scaleTo(0.8f, 0.8f, 0.5f), Actions.scaleTo(1.0f, 1.0f, 0.5f)));
//				img1.addAction(act);
			}
		}
	}
	
	private int randomHole() {
		freeHoles.clear();
		for (int i = 0; i < holes.size(); i++) {
			Hole hole = holes.get(i);
			if (hole.isFree()) {
				freeHoles.add(hole);
			}
		}
		if (freeHoles.isEmpty()) {
			return -1;
		}
		return Utils.random(freeHoles.size());
	}
	
	public boolean duuoo(int index, boolean over) {
		if (index >= holes.size()) {
			return false;
		}

		if (index == Level.RANDOM) {
			index = randomHole();
			if (index == -1) {
				return false;
			}
		}
		
		Hole hole = holes.get(index);
		if (!hole.isFree()) {
			return false;
		}
		
		hole.duuoo(over);
		return true;
	}
	
	public void step(float dt) {
		for (int i = 0; i < holes.size(); i++) {
			Hole hole = holes.get(i);
			hole.step(dt);
		}
	}
}
