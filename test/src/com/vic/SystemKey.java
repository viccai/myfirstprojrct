package com.vic;

import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SystemKey extends JPanel {
	private JFrame frame;
	private Container contentPane;
	private JButton button;

	public SystemKey() {
		frame = new JFrame();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = frame.getContentPane();
		frame.setVisible(true);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		contentPane.setLayout(null);
		button = new JButton("按钮");
		button.setBounds(50, 50, 80, 30);
		contentPane.add(button);
		frame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				System.out.println("实现监听");
				if (e.getKeyCode() == e.VK_UP) {
					System.out.println("up");
				}
				System.out.println("实现监听");
				if (e.getKeyCode() == e.VK_DOWN) {
					System.out.println("down");
				}
			}
		});
	}

	public static void main(String[] args) {
		new SystemKey();
	}

}
