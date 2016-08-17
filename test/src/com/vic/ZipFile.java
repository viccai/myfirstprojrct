package com.vic;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFile {

	public static void main(String[] args) {
		ZipFile zf = new ZipFile();
		zf.downloadFile();

	}
	
	public void downloadFile(){

		byte[] buf=new byte[1024];
		
		try{
			//读取文件
			File file = new File("D:\\wifi_pw.txt");
			//打包文件
			//ByteArrayOutputStream baos=new ByteArrayOutputStream();
			
			File zipFile = new File("D:\\wifi_pw.zip");
			@SuppressWarnings("resource")
			ZipOutputStream zos=new ZipOutputStream(new FileOutputStream(zipFile));

			
	
				BufferedInputStream bis = null;
				bis = new BufferedInputStream(new FileInputStream(file));
				
				ZipEntry entry = new ZipEntry("wifi.txt");  
				
				zos.putNextEntry(entry);
	            int len;
	            while((len=bis.read(buf))>0){
	            	zos.write(buf,0,len);
	            }
	            zos.closeEntry();
	            zos.close();
	            bis.close();

			
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
