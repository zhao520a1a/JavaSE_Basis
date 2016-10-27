package com.foldercopy01;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame{

	public static JFrame frame = new JFrame("复制文件窗口");
	public static JLabel label = new JLabel("查找");;
	public static TextField copytf = new TextField();;
	public static TextField newtf = new TextField();
	public static JButton button = new JButton("开始复制");

	
	/*
	 * 复制文件主窗口
	 */
	public void lanuch() {
		frame.setTitle("复制文件窗口");
		frame.setBounds(300, 200, 500, 250);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		label.setBounds(400, 30, 40, 30);
		frame.add(label);
		copytf.setBounds(50, 30, 300, 25);
		frame.add(copytf);
		newtf.setBounds(50, 80, 300, 25);
		frame.add(newtf);
		button.setBounds(50, 120, 160, 30);
		frame.add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ProgressBar().doCopy(e);
			}
		});
		

		
	}

	public static void main(String[] args) {
		new MainFrame().lanuch();
	}

}