package com.foldercopy01;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JProgressBar;
import javax.swing.Timer;


public class ProgressBar {

	// 创建一条进度条
	JProgressBar bar = new JProgressBar();
	JCheckBox indeterminate = new JCheckBox("不确定进度");
	JCheckBox noBorder = new JCheckBox("不绘制边框");
	Timer timer;
	
	public void doCopy(ActionEvent e) {
		Box box = new Box(BoxLayout.Y_AXIS);
		box.setBounds(320, 160, 160, 100);
		box.add(indeterminate);
		box.add(noBorder);
		MainFrame.frame.add(box);
		// 把进度条添加到JFrame窗口中
		bar.setBounds(50, 170, 250, 20);
		MainFrame.frame.add(bar);

		// 设置在进度条中绘制完成百分比
		bar.setStringPainted(true);
		noBorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// 根据该选择框决定是否绘制进度条的边框
				bar.setBorderPainted(!noBorder.isSelected());
			}
		});

		Thread t = new Thread(new CopyThread(MainFrame.copytf.getText(), MainFrame.newtf.getText()));
		t.start();
		// 设置进度条的最大值和最小值,
		bar.setMinimum(0);
		// 以总任务量作为进度条的最大值
		int sumSpace = (int) FolderCopy.getInstance().getSpace(MainFrame.copytf.getText());
		bar.setMaximum(sumSpace);
		System.out.println(bar.getMaximum()/1024);
		timer = new Timer(300, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 以任务的当前完成量设置进度条的value
				bar.setValue((int) FolderCopy.getInstance().getCurrentSpace());
				System.out.println(bar.getValue()/1024);
				if (bar.getValue() >= bar.getMaximum()) {
					System.out.println("进度条应结束！");
					MainFrame.frame.remove(bar);
					MainFrame.frame.remove(box);
					MainFrame.frame.repaint();
					timer.stop();
				}
			}
		});
		timer.start();
		
		indeterminate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// 设置该进度条的进度是否确定
				bar.setIndeterminate(indeterminate.isSelected());
				bar.setStringPainted(!indeterminate.isSelected());
			}
		});

	}

}

/*
 * 
 * public void doCopy(MouseEvent e) { Thread t = new Thread(new
 * CopyThread(CopyFileFrame.copytf.getText(), CopyFileFrame.newtf.getText()));
 * t.start();
 * 
 * // 创建进度对话框 int sumSpace = (int)
 * FolderCopy.getInstance().getSpace(CopyFileFrame.copytf.getText()); final
 * ProgressMonitor dialog = new ProgressMonitor(null, "等待任务完成", "已完成：", 0,
 * sumSpace);
 * 
 * // 创建一个计时器 timer = new Timer(300, new ActionListener() { public void
 * actionPerformed(ActionEvent e) { // 以任务的当前完成量设置进度对话框的完成比例
 * dialog.setProgress((int) FolderCopy.getInstance().getCurrentSpace()); if
 * (dialog.isCanceled()) { timer.stop(); // 停止计时器 t.interrupt();// 中断任务的执行线程
 * System.exit(0); // 系统退出 } } }); timer.start(); if((int)
 * FolderCopy.getInstance().getCurrentSpace() == dialog.getMaximum()){
 * timer.stop(); }
 * 
 * }
 */
