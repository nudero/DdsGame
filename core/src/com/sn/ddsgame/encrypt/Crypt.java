package com.sn.ddsgame.encrypt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.compression.Lzma;

public class Crypt {
	
	private static final String PASSWORD = "c8a9229820ffa315bc6a17a9e43d01a9";
	
	public static byte[] decrypt(FileHandle fh) {
		byte[] encryptData = fh.readBytes();
		byte[] originData = AESHelper.decrypt(encryptData, PASSWORD);

		ByteArrayInputStream is = new ByteArrayInputStream(originData);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			Lzma.decompress(is, os);
			byte[] decryptData = os.toByteArray();
			is.close();
			os.close();
			return decryptData;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
