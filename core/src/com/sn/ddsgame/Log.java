package com.sn.ddsgame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Log {
	
	private static final String LOG_FILE_NAME = "log";
	private static FileHandle logFile = null;
	private static boolean append = false;
	
	private static final String LEVEL_STR_INFO = "[i]";
	private static final String LEVEL_STR_ERROR = "[e]";
	private static final String LEVEL_STR_DEBUG = "[d]";
	
	private static void log(String level, String msg) {
		if (logFile == null) {
			String logPath = Gdx.files.getLocalStoragePath() + LOG_FILE_NAME;
			logFile = Gdx.files.local(logPath);
		}
		logFile.writeString(level+msg, append);
	}

	public static void error(String msg) {
		if (Gdx.app.getLogLevel() >= Application.LOG_ERROR) {
			log(LEVEL_STR_ERROR, msg);
		}
	}
	
	public static void info(String msg) {
		if (Gdx.app.getLogLevel() >= Application.LOG_INFO) {
			log(LEVEL_STR_INFO, msg);
		}
	}
	
	public static void debug(String msg) {
		if (Gdx.app.getLogLevel() >= Application.LOG_DEBUG) {
			log(LEVEL_STR_DEBUG, msg);
		}
	}
	
	public static void setAppend(boolean pend) {
		append = pend;
	}
}
