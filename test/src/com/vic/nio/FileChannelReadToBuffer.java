package com.vic.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelReadToBuffer {

	public static void main(String[] args) throws Exception {
		RandomAccessFile aFile = new RandomAccessFile("D:\\wifi_pw.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		
		ByteBuffer buf = ByteBuffer.allocate(20);
		
		int bytesRead = inChannel.read(buf);
		while(bytesRead != -1){
			System.out.println("Read " + bytesRead);
			buf.flip();
			
			while(buf.hasRemaining()){
				System.out.println((char)buf.get());
			}
			
			buf.clear();
			bytesRead = inChannel.read(buf);
		}
		
		aFile.close();
	}

}
