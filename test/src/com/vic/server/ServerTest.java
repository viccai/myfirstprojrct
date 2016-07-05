package com.vic.server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {
    int port = 8821;

    void start() {
        Socket s = null;
        try {
            ServerSocket ss = new ServerSocket(port);
            while (true) {
                // ѡ����д�����ļ�
                String filePath = "D:\\ba_padweixun.rar";
                File fi = new File(filePath);

                System.out.println("�ļ�����:" + (int) fi.length());

                // public Socket accept() throws
                // IOException���������ܵ����׽��ֵ����ӡ��˷����ڽ�������֮ǰһֱ������

                s = ss.accept();
                System.out.println("����socket����");
                DataInputStream dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));
                dis.readByte();

                DataInputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)));
                DataOutputStream ps = new DataOutputStream(s.getOutputStream());
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
                s.close();                
                System.out.println("�ļ��������");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String arg[]) {
        new ServerTest().start();
    }
}
