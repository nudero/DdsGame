package com.sn.ddsgame;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen implements Screen {

	DdsGame game;
	Stage stage;
	Skin skin;
	
	private static final int NUM = 4;
	private float SHOW_SEC = 1.0f;
	private float GAP_SEC = 1.0f;
	private float passtime = GAP_SEC;
	private int LEVEL_NUM = 10;
	private int level_count = 0;
	private boolean showing = false;
	private ArrayList<Image> images = new ArrayList<Image>();
	private int curShowN = -1;
	private Color origColor = null;
	
	
	
//	3 2 1 GO
	public int step = 0;
	public int posindex = 0;
	
	private int score = 0;
	private Label label;
	
	public GameScreen(DdsGame game) {
		this.game = game;
		
		stage = new Stage(new FitViewport(Const.DESIGN_WIDTH, Const.DESIGN_HEIGHT));
		Gdx.input.setInputProcessor(stage);
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		
		Image img = new Image(new Texture("bg.jpg"));
		stage.addActor(img);
		
		Table table = new Table();
//		table.debug();
		stage.addActor(table);
		table.setFillParent(true);
		
		int n = NUM;
		int w = (Const.DESIGN_HEIGHT - n*Const.PAD_DIST - 2*Const.EDGE_DIST) / n;
		table.defaults().width(w).height(w).pad(Const.PAD_DIST);
		
		for (int i = 0; i < n; i++) {
			table.row();
			for (int j = 0; j < n; j++) {
				Image img1 = new Image(new Texture("hole.jpg"));
				table.add(img1);
//				img1.setColor(Color.GREEN);
				img1.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						int idx = (Integer) event.getTarget().getUserObject();
						if (idx == curShowN) {
							Image img = images.get(curShowN);
							img.setColor(origColor);
							curShowN = -1;
							System.out.println(++score);
							label.setText(score+"");
						}
					}
				});
				images.add(img1);
				img1.setUserObject(images.size()-1);
				origColor = img1.getColor();
//				RepeatAction act = Actions.repeat(RepeatAction.FOREVER, Actions.sequence(Actions.scaleTo(0.8f, 0.8f, 0.5f), Actions.scaleTo(1.0f, 1.0f, 0.5f)));
//				img1.addAction(act);
			}
		}
		
		label = new Label("0", skin);
		label.setAlignment(Align.right);
		stage.addActor(label);
//		table.row();
//		table.add(lbl);
	}
	
	@Override
	public void show() {
		
	}

	private void timee(float delta) {
		if (level_count >= LEVEL_NUM) {
			return;
		}
		
		passtime -= delta;
		if (passtime > 0) {
			return;
		}
		
		if (showing) {
			showing = false;
			passtime = GAP_SEC;
			
			if (curShowN >= 0) {
				Image img = images.get(curShowN);
				img.setColor(origColor);
				curShowN = -1;
			}
		}
		else {
			level_count++;
			showing = true;
			passtime = SHOW_SEC;
			curShowN = Utils.random(images.size());
			Image img = images.get(curShowN);
			img.setColor(Color.GREEN);
		}
	}
	
	private void hit() {
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		timee(delta);
		
		stage.act(Math.min(delta, 1/30f));
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}

}
