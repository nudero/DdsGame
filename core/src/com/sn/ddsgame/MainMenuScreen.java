package com.sn.ddsgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainMenuScreen implements Screen {

	Stage stage;
	DdsGame game;
	Skin skin;
	
	public MainMenuScreen(DdsGame game) {
		this.game = game;
		
		stage = new Stage(new FitViewport(Const.DESIGN_WIDTH, Const.DESIGN_HEIGHT));
		Gdx.input.setInputProcessor(stage);
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		
		
		FileHandle fh = Gdx.files.internal("levels");
		if (fh.exists()) {
			System.out.println(fh.name());
		}
		FileHandle[] files = fh.list();
		System.out.println(files.length+"");
		
		Table table = new Table();
		stage.addActor(table);
		table.setFillParent(true);
		table.defaults().width(150).height(40).pad(20);
		
		TextButton tb = new TextButton("start", skin);
		table.add(tb);
		tb.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				start();
			}
		});
		
		table.row();
		tb = new TextButton("help", skin);
		table.add(tb);
		tb.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				help();
			}
		});
		
		table.row();
		tb = new TextButton("about", skin);
		table.add(tb);
	}
	
	void start() {
		game.setScreen(new GameScreen(game));
	}
	
	void help() {
		game.setScreen(new TestScreen(game));
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
