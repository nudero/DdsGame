package com.sn.ddsgame.level;

import com.badlogic.gdx.utils.JsonValue;

public class LevelData {
	public int levelId;
	public String bg;
	public String music;
	public int n;
	public int nextime;
	public int actime;
	public JsonValue steps;
	
//	{
//		"bg":"1111",
//		"music":"222",
//		"n":3,
//		"nextime":1000
//		"actime":3000
//		
//		"steps":[
//			{"num":5},
//			{"actime":2000, "num":5}
//			{"actime":1000, "num":5}
//			{"nextime":500, "num":10}
//		]
//	}
}
