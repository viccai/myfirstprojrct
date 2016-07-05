package com.vic;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

public class SystemKey01 implements HotkeyListener {
	static final int KEY_1 = 88;
	static final int KEY_2 = 89;
	static final int KEY_3 = 90;

	/**
	 * �÷����������ע���ϵͳ�ȼ��¼�
	 * 
	 * @param key
	 *            :�������ȼ���ʶ
	 */
	public void onHotKey(int key) {
		switch (key) {
		case KEY_1:
			System.out.println("ctrl+alt+I ����1.........");
			break;
		case KEY_2:
			System.out.println("ctrl+alt+O ����1.........");
			break;
		case KEY_3:
			System.out.println("ϵͳ�˳�..........");
			destroy();
		}

	}

	/**
	 * ���ע�Ტ�˳�
	 */
	void destroy() {
		JIntellitype.getInstance().unregisterHotKey(KEY_1);
		JIntellitype.getInstance().unregisterHotKey(KEY_2);
		JIntellitype.getInstance().unregisterHotKey(KEY_3);
		System.exit(0);
	}

	/**
	 * ��ʼ���ȼ���ע������¼�
	 */
	void initHotkey() {
		// ����KEY_1��ʾ�����ȼ���ϵı�ʶ���ڶ���������ʾ��ϼ������û����Ϊ0�����ȼ���Ӧctrl+alt+I
		JIntellitype.getInstance().registerHotKey(KEY_1,JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT, (int) 'I');
		JIntellitype.getInstance().registerHotKey(KEY_2,JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT, (int) 'O');
		JIntellitype.getInstance().registerHotKey(KEY_3,JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT, (int) 'X');

		JIntellitype.getInstance().addHotKeyListener(this);
	}

	public static void main(String[] args) {
		SystemKey01 key = new SystemKey01();
		key.initHotkey();

		// ����ģ�ⳤʱ��ִ�е�����
		while (true) {
			try {
				Thread.sleep(10000);
			} catch (Exception ex) {
				break;
			}
		}
	}
}