package com.xin.stream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;


/**
 * ��һ�����ļ��ָ�ɶ��С�ļ�
 * ˼·����ȡÿ��С�ļ����������������е�����˳��д��Ŀ���ļ���������У�
 * 
 * @author С��Ŷ
 *
 */
public class SmallFile {
	public static final String SUFFIX = ".txt"; // �ָ����ļ�����׺
	// ��ָ�����ļ����Ÿ������ļ����ֽ������зָ��ļ�������nameָ������Ҫ���зָ���ļ�����sizeָ����ָ����С�ļ��Ĵ�С

	public static String[] divide(String name, long size) throws Exception {
		File file = new File(name);// ����һ���ļ�--
		if (!file.exists() || (!file.isFile())) {
			throw new Exception("ָ���ļ������ڣ�");
		}
		// ��ñ��ָ��ļ����ļ����������ָ�ɵ�С�ļ���������Ŀ¼��
		File parentFile = file.getParentFile();
		// ȡ���ļ��Ĵ�С
		long fileLength = file.length();
		if (size <= 0) {
			size = fileLength / 2;
		}
		// ȡ�ñ��ָ���С�ļ�����Ŀ
		int num = (fileLength % size != 0) ? (int) (fileLength / size + 1) : (int) (fileLength / size);
		// ��ű��ָ���С�ļ���
		String[] fileNames = new String[num];
		// �����ļ����������ָ���ļ�
		FileInputStream in = new FileInputStream(file);
		// �������ļ����Ŀ�ʼ�ͽ����±�
		long end = 0;
		int begin = 0;
		// ����Ҫ�ָ����Ŀ����ļ�
		for (int i = 0; i < num; i++) {
			// ����ǰnum - 1��С�ļ�����С��Ϊָ����size
			File outFile = new File(parentFile, file.getName() + i + SUFFIX);
			// ����С�ļ��������
			FileOutputStream out = new FileOutputStream(outFile);
			// �������±����size
			end += size;
			end = (end > fileLength) ? fileLength : end;
			// ���������ж�ȡ�ֽڴ洢���������
			for (; begin < end; begin++) {
				out.write(in.read());
			}
			out.close();
			fileNames[i] = outFile.getAbsolutePath();
		}
		in.close();
		return fileNames;
	}

	public static void readFileMessage(String fileName) {// ���ָ�ɵ�С�ļ��е����ݶ���
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String string = null;
			// ���ж�ȡ���ݣ�ֱ������null���ʾ��ȡ�ļ�����
			while ((string = reader.readLine()) != null) {
				System.out.println(string);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	public static void main(final String[] args) throws Exception {
		String name = "e:/1.txt";// ����Ǹ����ļ�
		long size = 1024 * 10;// ÿ���ļ��ķָ��С
		String[] fileNames = SmallFile.divide(name, size);
		System.out.println("�ļ�" + name + "�ָ�Ľ�����£�");
		for (int i = 0; i < fileNames.length; i++) {
			System.out.println(fileNames[i] + "���������£�");
			SmallFile.readFileMessage(fileNames[i]);
			System.out.println();
		}
	}
}
