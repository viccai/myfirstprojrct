import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Test {
	public static void main(String[] args) {
		/*List list = new ArrayList();
		list.add(21101);
		list.add(21102);
		list.add(21103);
		list.add(21104);
		list.add(21105);
		list.add(21104);
		list.add(21104);
		list.add(21102);
		list.add(21106);

		System.out.println("����ǰ��");
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		System.out.println("������...");
		
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).equals(list.get(i))) {
					list.remove(j);
				}
			}
		}

		System.out.println("�����");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}*/
		/*
		 * String[] requiredField = { "��Ա���"}; List<Map> dataBand_ct_main =
		 * (List<Map>) mainDataMap.get("DataBand_CT_Ⱥ���Ա"); boolean needRemove =
		 * true; Iterator it = dataBand_ct_main.iterator(); Map dataMap; while
		 * (it.hasNext()) { dataMap = (Map) it.next(); for (int i = 0; i <
		 * requiredField.length; i++) { if (dataMap.get(requiredField[i]) !=
		 * null) { needRemove = false; break; } } if (needRemove) { it.remove();
		 * } needRemove = true; }
		 */
		
		String key = MD5MD("dbxx78Rew232N5_2dlocsw!@ertanglaoshi");
		
		System.out.println(key);
		
	}
	
	public static String MD5MD(String s) {  
        char hexDigits[] = { '0', '1', '2', '3', '4',  
                             '5', '6', '7', '8', '9',  
                             'A', 'B', 'C', 'D', 'E', 'F' };  
        try {  
            byte[] btInput = s.getBytes("utf-8");  
     //���MD5ժҪ�㷨�� MessageDigest ����  
            MessageDigest mdInst = MessageDigest.getInstance("MD5");  
     //ʹ��ָ�����ֽڸ���ժҪ  
            mdInst.update(btInput);  
     //�������  
            byte[] md = mdInst.digest();  
     //������ת����ʮ�����Ƶ��ַ�����ʽ  
            int j = md.length;  
            char str[] = new char[j * 2];  
            int k = 0;  
            for (int i = 0; i < j; i++) {  
                byte byte0 = md[i];  
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];  
                str[k++] = hexDigits[byte0 & 0xf];  
            }  
            return new String(str);  
        }  
        catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
}  
}
