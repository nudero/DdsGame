package com.sn.ddsgame.level;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.sn.ddsgame.Log;

public class LevelManager {

	private static LevelManager instance = new LevelManager();
	
	private int maxLevel = 0;
	private int curLevel = 1;
	
	private LevelManager() {
		
	}
	
	public static LevelManager getInstance() {
		return instance;
	}
	
//	private HashMap<String, JsonValue> allLevelDatas = new HashMap<String, JsonValue>();
	private HashMap<Integer, LevelData> allLevelDatas = new HashMap<Integer, LevelData>();
	
	public boolean loadLevelData() {
		Preferences pref = Gdx.app.getPreferences("dds");
		curLevel = pref.getInteger("curLevel", 1);
		
		JsonReader jr = new JsonReader();
		
		while(true) {
			String path = "level/"+(maxLevel+1)+".json";
			FileHandle fh = Gdx.files.internal(path);
			if (!fh.exists()) {
				break;
			}
			else {				
				JsonValue leveldata = jr.parse(fh);
				LevelData ld = new LevelData();
				ld.level = ++maxLevel;
				ld.n = leveldata.getInt("n");
				
				float roundTime = leveldata.getFloat("roundTime");
				if (roundTime <= 0) {
					Log.error("level"+ld.level+" roundTime is error");
					return false;
				}
				
				ld.music = leveldata.getString("music");
				ld.bg = leveldata.getString("bg");
				ld.actor = leveldata.getString("actor");
				
				JsonValue data = leveldata.get("data");
				for (int i = 0; i < data.size; i++) {
					JsonValue jv = data.get(i);
					float stepTime = jv.getFloat("stepTime");
					
					int num = jv.getInt("num");
					if (num <= 0) {
						Log.error("level"+ld.level+" num is error");
						return false;
					}
					
					for (int j = 0; j < num; j++) {
						JsonValue jpos = jv.get("pos");
						if (jpos.size <= 0) {
							Log.error("level"+ld.level+" pos num is error");
							return false;
						}
						
						for (int n = 0; n < jpos.size; n++) {
							PosTime pt = new PosTime();
							pt.pos = jpos.getInt(n);
							if (pt.pos < -1) {
								Log.error("level"+ld.level+" pos is error");
								return false;
							}
							pt.time = stepTime;
							ld.postimes.add(pt);
						}
					}
					
					ld.postimes.get(ld.postimes.size()-1).time = roundTime;
				}
				
				allLevelDatas.put(ld.level, ld);
				
//				allLevelDatas.put(fh.name(), leveldata);
//				System.out.println(fh.readString());
			}
		}
		
		if (maxLevel == 0) {
			Log.error("no valide level data file found");
			return false;
		}
		
		System.out.println("max_level is "+maxLevel);
		return true;
	}
	
	public LevelData getLevelData(int level) {
		if (allLevelDatas.containsKey(level)) {
			return allLevelDatas.get(level);
		}
		return null;
	}

	public int getCurLevel() {
		return curLevel;
	}

	public void setCurLevel(int curLevel) {
		this.curLevel = curLevel;
	}
	
	
}
