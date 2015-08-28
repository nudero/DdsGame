package com.sn.ddsgame.level;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class LevelManager {

	private static LevelManager instance = new LevelManager();
	
	private int max_level = 0;
	
	private LevelManager() {
		
	}
	
	public static LevelManager getInstance() {
		return instance;
	}
	
	private HashMap<String, JsonValue> allLevelDatas = new HashMap<String, JsonValue>();
	
	public boolean loadLevelData() {
		JsonReader jr = new JsonReader();
		
		while(true) {
			String path = "level/"+(max_level+1)+".json";
			FileHandle fh = Gdx.files.internal(path);
			if (!fh.exists()) {
				break;
			}
			else {
				max_level++;
				
				JsonValue leveldata = jr.parse(fh);
				LevelData ld = new LevelData();
				ld.level = max_level;
				ld.n = leveldata.getInt("n");
				ld.time = leveldata.getFloat("time");
				ld.music = leveldata.getString("music");
				ld.bg = leveldata.getString("bg");
				ld.actor = leveldata.getString("actor");
				JsonValue data = leveldata.get("data");
				for (int i = 0; i < data.size; i++) {
					JsonValue jv = data.get(i);
					ld.times.add(jv.getFloat("time"));
					int num = jv.getInt("num");
					for (int j = 0; j < num; j++) {
						JsonValue jpos = jv.get("pos");
						for (int n = 0; n < jpos.size; n++) {
							ld.poses.add(jpos.getInt(n));
						}
					}
				}
				
				allLevelDatas.put(fh.name(), leveldata);
				System.out.println(fh.readString());
			}
		}
		
		if (max_level == 0) {
			return false;
		}
		System.out.println("max_level is "+max_level);
		return true;
	}
}
