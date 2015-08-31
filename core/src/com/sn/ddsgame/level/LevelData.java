package com.sn.ddsgame.level;

import java.util.ArrayList;

public class LevelData {
	public int level;
	public int n;
	public float roundTime;
	public String music;
	public String bg;
	public String actor;
	public ArrayList<PosTime> postimes = new ArrayList<PosTime>();
	
	public ArrayList<Float> times = new ArrayList<Float>();
	public ArrayList<Integer> poses = new ArrayList<Integer>();
}
