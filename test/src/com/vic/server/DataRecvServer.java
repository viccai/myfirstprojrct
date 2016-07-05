package com.vic.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DataRecvServer {
    int port = 8888;
    
    private String sendMessage = "Windwos";

    Socket s = null;
    DataOutputStream out = null;
    
    void start() {
        
        try {
            ServerSocket ss = new ServerSocket(port);
            while (true) {
                s = ss.accept();
                System.out.println("����socket����");
                
                sendMessage();
                getMessage();
                
                s.close();
                System.out.println("�ļ��������");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        if (s == null)
            return;
        try {
        	out = new DataOutputStream(s.getOutputStream());
            if (sendMessage.equals("Windows")) {
                out.writeByte(0x1);
                out.flush();
                return;
            }
            if (sendMessage.equals("Unix")) {
                out.writeByte(0x2);
                out.flush();
                return;
            }
            if (sendMessage.equals("Linux")) {
                out.writeByte(0x3);
                out.flush();
            } else {
                out.writeUTF(sendMessage);
                out.flush();
            }
        } catch (Exception e) {
            System.out.print("������Ϣʧ��!" + "\n");
        }
    }
    
    private void getMessage() {
        if (s == null)
            return;
        DataInputStream inputStream = null;
        try {
            inputStream = new DataInputStream(new BufferedInputStream(s.getInputStream()));
        } catch (Exception e) {
            System.out.print("������Ϣ�������\n");
            return;
        }

        try {
            //���ر���·�����ļ������Զ��ӷ������˼̳ж�����
            String savePath = "E:\\";
            int bufferSize = 8192;
            byte[] buf = new byte[bufferSize];
            int passedlen = 0;
            long len=0;
            
            savePath += inputStream.readUTF();
            DataOutputStream fileOut = new DataOutputStream(new BufferedOutputStream(new BufferedOutputStream(new FileOutputStream(savePath))));
            len = inputStream.readLong();
            
            System.out.println("�ļ��ĳ���Ϊ:" + len + "\n");
            System.out.println("��ʼ�����ļ�!" + "\n");
                    
            while (true) {
                int read = 0;
                if (inputStream != null) {
                    read = inputStream.read(buf);
                }
                passedlen += read;
                if (read == -1) {
                    break;
                }
                //�����������Ϊͼ�ν����prograssBar���ģ���������Ǵ��ļ������ܻ��ظ���ӡ��һЩ��ͬ�İٷֱ�
                System.out.println("�ļ�������" +  (passedlen * 100/ len) + "%\n");
                fileOut.write(buf, 0, read);
            }
            System.out.println("������ɣ��ļ���Ϊ" + savePath + "\n");

            fileOut.close();
        } catch (Exception e) {
            System.out.println("������Ϣ����" + "\n");
            return;
        }
    }
    
    public static void main(String arg[]) {
        new DataRecvServer().start();
    }
}
