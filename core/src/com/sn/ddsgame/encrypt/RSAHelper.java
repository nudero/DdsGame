package com.sn.ddsgame.encrypt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

import javax.crypto.Cipher;



/**
 * RSA�ǶԳƼ����㷨���÷���1��Կ����(C)��˽Կ����(S)��2˽Կ����(S)����Կ����(C)��
 */
public class RSAHelper {
	
	/** ָ�������㷨ΪRSA */
	private static String ALGORITHM = "RSA";
	
	/** ָ��key�Ĵ�С */
	private static int KEYSIZE = 1024;
	
	/** ָ����Կ����ļ� */
	private static String PUBLIC_KEY_FILE = "PublicKey";
	
	/** ָ��˽Կ����ļ� */
	private static String PRIVATE_KEY_FILE = "PrivateKey";

	public static void main(String[] args) throws Exception {
		System.out.println("RSA�ӽ��ܲ��ԣ�");

		generateKeyPair();

		final String source = "73C58BAFE578C59366D8C995CD0B9";// Ҫ���ܵ��ַ���
		System.out.println("����ǰ:" + source);

		String cryptograph = encrypt(source);// ���ɵ�����
		System.out.println("Base64 format:" + cryptograph);

		String target = decrypt(cryptograph);// ��������
		System.out.println("���ܺ�:" + target);
	}

	/**
	 * ������Կ��
	 */
	private static void generateKeyPair() throws Exception {
		/** RSA�㷨Ҫ����һ�������ε������Դ */
		SecureRandom sr = new SecureRandom();
		/** ΪRSA�㷨����һ��KeyPairGenerator���� */
		KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
		/** ����������������Դ��ʼ�����KeyPairGenerator���� */
		kpg.initialize(KEYSIZE, sr);
		/** �����ܳ׶� */
		KeyPair kp = kpg.generateKeyPair();
		/** �õ���Կ */
		Key publicKey = kp.getPublic();
		/** �õ�˽Կ */
		Key privateKey = kp.getPrivate();
		/** �ö����������ɵ���Կд���ļ� */
		ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY_FILE));
		ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(PRIVATE_KEY_FILE));
		oos1.writeObject(publicKey);
		oos2.writeObject(privateKey);
		/** ��ջ��棬�ر��ļ������ */
		oos1.close();
		oos2.close();
	}

	/**
	 * ���ܷ��� source�� Դ����
	 */
	public static String encrypt(String source) throws Exception {
		/** ���ļ��еĹ�Կ������� */
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
		Key key = (Key) ois.readObject();
		ois.close();
		/** �õ�Cipher������ʵ�ֶ�Դ���ݵ�RSA���� */
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] b = source.getBytes();
		/** ִ�м��ܲ��� */
		byte[] b1 = cipher.doFinal(b);
		return Base64.encode(b1);
	}

	/**
	 * �����㷨 cryptograph:����
	 */
	public static String decrypt(String cryptograph) throws Exception {
		/** ���ļ��е�˽Կ������� */
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
		Key key = (Key) ois.readObject();
		ois.close();
		/** �õ�Cipher��������ù�Կ���ܵ����ݽ���RSA���� */
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] b1 = Base64.decode(cryptograph);
		/** ִ�н��ܲ��� */
		byte[] b = cipher.doFinal(b1);
		return new String(b);
	}

}