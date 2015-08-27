package com.sn.ddsgame;

import java.util.Random;

public class Utils {

	private static Random rand = new Random(System.currentTimeMillis());
	
	public static int random(int n) {
		return rand.nextInt(n);
	}
}
