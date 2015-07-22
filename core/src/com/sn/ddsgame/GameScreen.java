package com.sn.ddsgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen implements Screen {

	DdsGame game;
	Stage stage;
	Skin skin;
	
	public GameScreen(DdsGame game) {
		this.game = game;
		
		
		
		
//		stage = new Stage(new FitViewport(Const.DESIGN_WIDTH, Const.DESIGN_HEIGHT));
//		Gdx.input.setInputProcessor(stage);
//		skin = new Skin(Gdx.files.internal("uiskin.json"));
//		
//		Image img = new Image(new Texture("bg.jpg"));
//		stage.addActor(img);
//		
//		Table table = new Table();
////		table.debug();
//		stage.addActor(table);
//		table.setFillParent(true);
//		
//		int n = 4;
//		int w = (Const.DESIGN_HEIGHT - n*Const.PAD_DIST - 2*Const.EDGE_DIST) / n;
//		table.defaults().width(w).height(w).pad(Const.PAD_DIST);
//		
//		for (int i = 0; i < n; i++) {
//			table.row();
//			for (int j = 0; j < n; j++) {
//				Image img1 = new Image(new Texture("hole.jpg"));
//				table.add(img1);
//				img1.addListener(new ClickListener() {
//					@Override
//					public void clicked(InputEvent event, float x, float y) {
//						System.out.println("11111");
//					}
//				});
//				RepeatAction act = Actions.repeat(RepeatAction.FOREVER, Actions.sequence(Actions.scaleTo(0.8f, 0.8f, 0.5f), Actions.scaleTo(1.0f, 1.0f, 0.5f)));
//				img1.addAction(act);
//			}
//		}
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
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
