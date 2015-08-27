package com.sn.ddsgame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.compression.Lzma;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sn.ddsgame.encrypt.AESHelper;
import com.sn.ddsgame.encrypt.Base64;
import com.sn.ddsgame.encrypt.Crypt;
import com.sn.ddsgame.encrypt.RSAHelper;

public class TestScreen implements Screen {

	final String password = "c8a9229820ffa315bc6a17a9e43d01a9";
	
	Stage stage;
	Skin skin;
	Label label;

	int allNum = 0;
	int curNum = 0;
	int lastAllNum = 0;
	int lastCurNum = 0;
	
	boolean crypting = false;
	
	TestScreen(DdsGame game) {
		stage = new Stage(new FitViewport(Const.DESIGN_WIDTH, Const.DESIGN_HEIGHT));
		Gdx.input.setInputProcessor(stage);
		skin = new Skin(Gdx.files.internal("uiskin.json"));

		Table table = new Table();
		stage.addActor(table);
		table.setFillParent(true);
		table.defaults().width(200).height(60).pad(30);
		
		TextButton bt = new TextButton("encrypt", skin);
		table.add(bt);
		bt.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (!crypting) {
					crypting = true;
					new Thread() {
						public void run() {
							encrypt();
						}
					}.start();
				}
			}
		});
		
		table.row();
		bt = new TextButton("decrypt", skin);
		table.add(bt);
		bt.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (!crypting) {
					crypting = true;
					new Thread() {
						public void run() {
							decrypt();
						}
					}.start();
				}
			}
		});
		
		table.row();
		label = new Label("", skin);
		label.setColor(Color.GREEN);
		label.setAlignment(Align.left);
		table.add(label);
		
		
	}
	
	private void decrypt() {
		FileHandle[] files = Gdx.files.local("encrypt/").list();
		allNum = files.length;
		curNum = lastCurNum = lastAllNum = 0;
		for (FileHandle fi : files) {
			byte[] data = Crypt.decrypt(fi);
			System.out.println(new String(data, Charset.forName("UTF8")));
//			try {
//				byte[] encryptData = fi.readBytes();
//				byte[] originData = AESHelper.decrypt(encryptData, password);
//
//				ByteArrayInputStream is = new ByteArrayInputStream(originData);
//				ByteArrayOutputStream os = new ByteArrayOutputStream();
//				Lzma.decompress(is, os);
//				
//				FileHandle fo = Gdx.files.local("origin/"+fi.name());
//				fo.writeBytes(os.toByteArray(), false);
//				is.close();
//				os.close();
//				
				curNum++;
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//			}
		}
		crypting = false;
		System.out.println("test compress done");
	}
	
	private void encrypt() {
		FileHandle[] files = Gdx.files.local("origin/").list();
		allNum = files.length;
		curNum = lastCurNum = lastAllNum = 0;

		for (FileHandle fi : files) {
			try {
				byte[] originData = fi.readBytes();
				ByteArrayInputStream is = new ByteArrayInputStream(originData);
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				Lzma.compress(is, os);
				byte[] encryptData = AESHelper.encrypt(os.toByteArray(), password);
				is.close();
				os.close();
				
				FileHandle fo = Gdx.files.local("encrypt/"+fi.name());
				fo.writeBytes(encryptData, false);
				
				curNum++;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		crypting = false;
		System.out.println("test compress done");
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		updatePercent();
		stage.act(Math.min(delta, 1/30f));
		stage.draw();
	}
	
	private void updatePercent() {
		if (lastCurNum == curNum && lastAllNum == allNum) {
			return;
		}
		lastCurNum = curNum;
		lastAllNum = allNum;
		label.setText("allNum: "+allNum+"   curNum: "+curNum);
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}

}
