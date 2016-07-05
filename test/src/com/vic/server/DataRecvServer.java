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
                System.out.println("建立socket链接");
                
                sendMessage();
                getMessage();
                
                s.close();
                System.out.println("文件接收完成");
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
            System.out.print("发送消息失败!" + "\n");
        }
    }
    
    private void getMessage() {
        if (s == null)
            return;
        DataInputStream inputStream = null;
        try {
            inputStream = new DataInputStream(new BufferedInputStream(s.getInputStream()));
        } catch (Exception e) {
            System.out.print("接收消息缓存错误\n");
            return;
        }

        try {
            //本地保存路径，文件名会自动从服务器端继承而来。
            String savePath = "E:\\";
            int bufferSize = 8192;
            byte[] buf = new byte[bufferSize];
            int passedlen = 0;
            long len=0;
            
            savePath += inputStream.readUTF();
            DataOutputStream fileOut = new DataOutputStream(new BufferedOutputStream(new BufferedOutputStream(new FileOutputStream(savePath))));
            len = inputStream.readLong();
            
            System.out.println("文件的长度为:" + len + "\n");
            System.out.println("开始接收文件!" + "\n");
                    
            while (true) {
                int read = 0;
                if (inputStream != null) {
                    read = inputStream.read(buf);
                }
                passedlen += read;
                if (read == -1) {
                    break;
                }
                //下面进度条本为图形界面的prograssBar做的，这里如果是打文件，可能会重复打印出一些相同的百分比
                System.out.println("文件接收了" +  (passedlen * 100/ len) + "%\n");
                fileOut.write(buf, 0, read);
            }
            System.out.println("接收完成，文件存为" + savePath + "\n");

            fileOut.close();
        } catch (Exception e) {
            System.out.println("接收消息错误" + "\n");
            return;
        }
    }
    
    public static void main(String arg[]) {
        new DataRecvServer().start();
    }
}
