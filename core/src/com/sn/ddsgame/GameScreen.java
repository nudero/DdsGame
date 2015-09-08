package com.sn.ddsgame;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sn.ddsgame.level.Level;
import com.sn.ddsgame.level.LevelData;
import com.sn.ddsgame.level.LevelManager;

public class GameScreen implements Screen {

	enum GameState {
		READY_GO, GOING, GAME_OVER
	}
	
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
	
	private GameState gameState = GameState.READY_GO;
	
	
	public int step = 0;
	public int posindex = 0;
	
	private Label labelHit;
	private Label labelMiss;
	
	private Level level;
	
	private int missNum = 0;
	private int hitNum = 0;
	private boolean gameOver = false;
	
	public GameScreen(DdsGame game) {
		Dds.gameScreen = this;
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
		
		labelHit = new Label("0", skin);
		labelHit.setAlignment(Align.right);
		stage.addActor(labelHit);
		
		labelMiss = new Label("0", skin);
		labelHit.setAlignment(Align.left);
		stage.addActor(labelHit);
		
		LevelData ld = LevelManager.getInstance().getLevelData(LevelManager.getInstance().getCurLevel());
		level = new Level(ld);
		HoleManager.getInstance().init(table, level.data.n);
		level.start();
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
	
	private void dologic(float dt) {
		int index = level.step(dt);
		if (index != Level.NONE) {
			boolean ret = HoleManager.getInstance().duuoo(index, level.isOver());
			if (!ret) {
				Log.error("level"+level.data.level+" duuoo failed, index="+index);
			}
		}
		
		HoleManager.getInstance().step(dt);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
//		timee(delta);
		dologic(delta);
		
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

	public int getMissNum() {
		return missNum;
	}

	public void addMissNum() {
		missNum++;
		labelMiss.setText(missNum+"");
	}
	
	public void addHitNum() {
		hitNum++;
		labelMiss.setText(hitNum+"");
	}
	
	public void setMissNum(int missNum) {
		this.missNum = missNum;
	}

	public int getHitNum() {
		return hitNum;
	}

	public void setHitNum(int hitNum) {
		this.hitNum = hitNum;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	
}
