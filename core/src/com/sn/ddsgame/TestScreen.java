package com.sn.ddsgame;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.compression.Lzma;

public class TestScreen implements Screen {

	TestScreen(DdsGame game) {
		try {
			InputStream fis = new FileInputStream("test");
			OutputStream fos = new FileOutputStream("text");
			Lzma.compress(fis, fos);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			InputStream fis = new FileInputStream("text");
			OutputStream fos = new FileOutputStream("txt");
			Lzma.decompress(fis, fos);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		
//		for (int i = 0; i < 1; i++) {
//			FileHandle file = Gdx.files.local("test");
//			String readstr = file.readString();
//			file.delete();
//
//			try {
//				InputStream is = new ByteArrayInputStream(readstr.getBytes());
//				OutputStream os = new ByteArrayOutputStream();
////				Lzma.compress(is, os);
//				Lzma.decompress(is, os);
//				FileHandle outfile = Gdx.files.local("test");
//				outfile.writeString(os.toString(), false);
//				
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
//			catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		System.out.println("test compress done");
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

}
