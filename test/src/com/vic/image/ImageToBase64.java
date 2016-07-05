package com.vic.image;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.omg.CORBA_2_3.portable.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ImageToBase64 {

	public static void main(String[] args) {
		
		//测试从Base64编码转换成图片文件
		String strImg = "vzSNePJnT6VxIxgGiXU9gKJYknd2XWHWlyKLsQdekPMebwubC Hml42KcWNwRc6sRT3glUYAaR1DuslEgIVlOjIxbg9oR4t7ap6s21j2nyKTPv AuS2JrTvpWSj0DdXhqzc1/OlWGMAj5zh9uuJUK7s4K1ZuTnl6pl0m/WX8AEv/S3YKt3JsZynhhGOkk4K gzxHdke5EkMyQ3ECgBBcu4XV3SblFsoLlceOL0MJwQeksDhUjpCy3PMR6qiWWV8apAWDTZMOfwKVffPi8zSocZKLmqKO64xfZQmwpYSVOe5aa8rmU7cMRLiPdk8cQWrFa6 Na1otfYyMZTAQ3WO8S1tM0rWp7hzmxEtEfPNfp5E71RSjzp2dR20odOTlHCDqYq2qTmpd0L2A5he4lcsQk9JcGPJob3gATxNfvURM8KeBdQp77TJX1SoAiZDX1GWoywzvEHSuP8gdkSKMMZzRn5l4vdWoS9tVYE5foLhhTaD JkvP6cAM5MMy0CFl/z3PJn XZddA0wYzVsKJf7qoj7wvY 4y7mRyQXLWrMFhAVY84Zqvday07q3wfqWcYeivsy8lME3rLVhiM6dW8G0og IAvVxj6r9220TeDpFF3oE7dIrSCQbrIJMYItC3LXpXDscqaygCdhwOGs92coroiGhF123n80KwHr1PDsH4LQP5InzckxYTk FTX6wNXacuFyMTiavIt/jL2BBlpYOt9gsmz7EPX4NJBORyqu/mSeRlnbMb4TKVS2S6vLVunt3t3N2IWT0k8ff5D1lRdt92RrQv2dHr hdO2bwghovXs/eItAKNE5aCW8Ey3NWteQCksO5bBf1ZyD/2p6otsU7Hd6yXzJSxTFnqPB2nNRAP0oz1dUmkSOyKEGZbYXPQmm F4wElBV3 lCSmQD0QQrWR5wPMqwnQ6DcuBybsgeqjajPBmNqDG76KoR8s4TWAGJfPnfzWK8YWdcXnnCxojN2FbhPVrT/OvzpwVrAQ4xjinpBym0WBrSx32TQnKTUrK34jhYvxkL1koa/BP6imNazql/hQ8Q9Fz0zrnjvM3 jUfi4WjqVvbSul7vW1oXrT/oPeoWDw 3Vo1Uu36mPaicT5IJB3qwtJoIRrPgncn71KPaBu9zvmKvCKpaWHVRRsL3z31fbAh526b74xZ2Ipq9iTIOT/j3gEat4AlQ9TWuBKzYWBJeXCvgckvCI0TKPjKZGuO/TWmRg6OwZM/xqiZwkkQQaiSgsoSQdgQR0k7AIwwNw3zo8UT5FDZi8fPnOteApEmMVM3FcAmsiJl7r0ZDxA479rzrn71PuPQVuutl7Lg8uDBY1qQYM6tJYQ7BaHu Oh92LnzkeBSjU=";
		GenerateImage(strImg, "D:\\demo\\test11.jpg");
		
		//System.out.println(GetImageStr("D:\\demo\\touxiang1.jpg"));
	}

	public static String GetImageStr(String imgFilePath){
		byte[] data = null;
		try{
			InputStream in = new FileInputStream(imgFilePath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch(IOException e){
			e.printStackTrace();
		}
		
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}
	
	public static boolean GenerateImage(String imgStr,String imgFilePath){
		if(imgStr == null)
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try{
			//Base64解码
			byte[] bytes = decoder.decodeBuffer(imgStr);
			for(int i = 0; i < bytes.length; ++i){
				if(bytes[i] < 0){//调整异常数据
					bytes[i] += 256;
				}
			}
			//生成jpeg图片
			FileOutputStream out = new FileOutputStream(imgFilePath);
			out.write(bytes);
			out.flush();
			out.close();
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
