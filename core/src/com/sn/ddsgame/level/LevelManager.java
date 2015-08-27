package com.sn.ddsgame.level;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class LevelManager {

	private static LevelManager instance = new LevelManager();
	
	private LevelManager() {
		
	}
	
	public static LevelManager getInstance() {
		return instance;
	}
	
	private HashMap<String, JsonValue> allLevelDatas = new HashMap<String, JsonValue>();
	
	public void loadLevelData() {
		JsonReader jr = new JsonReader();
		FileHandle[] files = Gdx.files.local("levels").list();
		for (FileHandle file : files) {
			JsonValue leveldata = jr.parse(file);
			allLevelDatas.put(file.name(), leveldata);
			System.out.println(file.readString());
		}
	}
}
