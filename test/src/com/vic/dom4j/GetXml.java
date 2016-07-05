package com.vic.dom4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class GetXml {

	// filename D:\\SWTdemo\\eg.xml
	private Map<String, String> readXml(String filename) {
		File file = new File(filename);
		SAXReader reader = new SAXReader();
		Map<String, String> map = new HashMap<String, String>();
		try {
			Document doc = reader.read(file);
			Element root = doc.getRootElement();
			List<Element> listmsg = root.elements();
			if (listmsg.size() == 0)
				return null;
			for (Element element : listmsg) {
				map.put(element.getName(), element.getText());
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	public static void main(String[] args) {
		/*
		 * GetXml xml = new GetXml(); Map<String, String> map = xml.readXml(
		 * "http://127.0.0.1:1936/conf.xml?sr=www.nnv1.com&id=test1&room=1&img=1&ls=test1&nick=test1"
		 * ); System.out.println("服务器:" + map.get("server"));
		 */

		/*String str = "";
		String s = "";
		try {
			URL url = new URL("http://127.0.0.1:1936/conf.xml?sr=www.nnv1.com&id=test1&room=1&img=1&ls=test1&nick=test1");
			InputStream is = url.openStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			while ((str = br.readLine()) != null){
				s = str;
				//System.out.println(str);
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}

		//System.out.println(str);
		System.out.println(s);*/
		
		String str = "房估字(2014)第YPQD0006号";
		String jieguo = str.substring(str.indexOf("第")+1,str.indexOf("号"));
		System.out.println(jieguo);
		
	}

}
