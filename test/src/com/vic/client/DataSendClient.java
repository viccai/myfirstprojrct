package com.vic.client;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

public class DataSendClient {
	private Socket socket = null;

    private String ip = "localhost";// ���óɷ�����IP

    private int port = 8881;

    public DataSendClient() {
        try {
        	socket = new Socket(ip, port);
            if (socket!=null) {
            	System.out.println("�����Ϸ�����");
            	// ѡ����д�����ļ�
                String filePath = "D:\\������Ƶ.mp4";
                //String filePath = "F:\\����ͼƬ\\2013��10��������ֽͼƬ\\1 (3).jpg";
                File fi = new File(filePath);

                System.out.println("�ļ�����:" + (int) fi.length());

                // public Socket accept() throws
                // IOException���������ܵ����׽��ֵ����ӡ��˷����ڽ�������֮ǰһֱ������

                
                DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                dis.readByte();

                DataInputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)));
                DataOutputStream ps = new DataOutputStream(socket.getOutputStream());
                //���ļ��������ȴ����ͻ��ˡ�����Ҫ������������ƽ̨�������������Ĵ�������Ҫ�ӹ���������Բμ�Think In Java 4th�����ֳɵĴ��롣
                ps.writeUTF(fi.getName());
                ps.flush();
                ps.writeLong((long) fi.length());
                ps.flush();

                int bufferSize = 8192;
                byte[] buf = new byte[bufferSize];

                while (true) {
                    int read = 0;
                    if (fis != null) {
                        read = fis.read(buf);
                    }

                    if (read == -1) {
                        break;
                    }
                    ps.write(buf, 0, read);
                }
                ps.flush();
                // ע��ر�socket����Ŷ����Ȼ�ͻ��˻�ȴ�server�����ݹ�����
                // ֱ��socket��ʱ���������ݲ�������                
                fis.close();
                socket.close();                
                System.out.println("�ļ��������");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String arg[]) {
        new DataSendClient();
    }
}
