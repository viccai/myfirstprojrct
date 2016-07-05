import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;

public class Encryption {
	private int intPrivateKey = 0;
	private static final long MaxInt = (long) Math.pow(2.0D, 32.0D);

	private int[] intConstKey = { 1556696929, 1266038903, 1265722019,
			1265722531, 1265658509, 1265282947, 1263528397, 1263599759,
			1263487033, 1263648241, 1262235517, 1262210177, 1261371079,
			1261525493, 1261118363, 1260675071, 1260706169, 1260299731,
			1260230359, 1259026997, 1258887283, 1258865891, 1258626371,
			1258503781, 1258482227, 1258579643, 1258190971, 1258098757,
			1257611279, 1257724121, 1257273319, 1257361279, 1256913187,
			1256892151, 1256455111, 1256408429, 1060988843, 1028046497,
			1059548141, 1059702103, 1059282011, 1057910353, 1057261279,
			1056410731, 1027302959, 1056241757, 1053532217, 1053109769,
			1052145631, 1052156549, 1052311109, 1052138359, 1051307749,
			1051455023, 1030905121, 1030546703, 1030613851, 1030517641,
			1029296581, 1029412469, 1028968387, 1028847733, 1028118731,
			1028044769 };

	private int[] intUserKey = new int[64];

	private int[] intPosition = new int[64];

	private static String encryptionInfo = "";

	private static long readTimer = 0L;
	public static final long lngREMAINTIMER = 1200000L;
	public static final long abc = Long.parseLong("7776000000");

	public Encryption(String strKey) {
		int[] intData = getStringLong(strKey);
		this.intPrivateKey = 0;
		if (intData != null) {
			for (int i = 0; i < intData.length; i++) {
				this.intPrivateKey ^= intData[i];
			}
		}
		intData = getStringLong(String.valueOf(this.intPrivateKey));
		for (int i = 0; i < intData.length; i++) {
			this.intPrivateKey ^= intData[i];
		}
		processKeyGene();
	}

	public Encryption() {
	}

	public int[] getStringLong(String strString) {
		byte[] byteString = strString.getBytes();
		int intI = 0;
		int intIndex = -1;

		int intByte = 0;
		if (byteString.length == 0) {
			return null;
		}
		int[] intData = new int[(byteString.length - 1) / 4 + 1];
		while (intI < byteString.length) {
			if (intI % 4 == 0) {
				intData[(++intIndex)] = 0;
			}
			intByte = byteString[(intI++)];
			if (intByte < 0) {
				intByte += 256;
			}
			intData[intIndex] = ((intData[intIndex] << 8) + intByte);
		}
		return intData;
	}

	public String getLongString(int[] intData) {
		String strData = "";
		if (intData != null) {
			int[] intLocation = { -16777216, 16711680, 65280, 255 };
			int[] intMove = { 24, 16, 8 };
			int intIndex = 0;
			int intI = 0;
			int intLen = (intData.length - 1) * 4 + 1;
			int tmp = intData[(intData.length - 1)];
			if (tmp < 0)
				intLen += 3;
			else {
				while (tmp > 255) {
					intLen++;
					tmp >>= 8;
				}
			}
			byte[] bytString = new byte[intLen];
			intLen = 0;
			while (intI < intData.length - 1) {
				bytString[(intLen++)] = ((byte) ((intData[intI] & intLocation[intIndex]) >> intMove[intIndex]));
				intIndex++;
				if (intIndex > 3) {
					intI++;
					intIndex = 0;
				}
			}
			tmp = intData[(intData.length - 1)];
			for (intI = bytString.length - 1; intI >= intLen; intI--) {
				bytString[intI] = ((byte) (tmp & 0xFF));
				tmp >>= 8;
			}
			strData = new String(bytString);
		}
		return strData;
	}

	public int[] getHexLong(String strHex) {
		if (strHex.length() == 0) {
			return null;
		}
		int[] intData = new int[(strHex.length() - 1) / 8 + 1];
		String strSubHex = "";
		for (int i = 0; i < strHex.length(); i += 8) {
			if (i + 8 < strHex.length())
				strSubHex = strHex.substring(i, i + 8);
			else {
				strSubHex = strHex.substring(i);
			}
			intData[(i / 8)] = ((int) Long.parseLong(strSubHex, 16));
		}
		return intData;
	}

	public String getLongHex(int[] intData) {
		String strHex = "";
		String strSubHex = "";
		if (intData != null) {
			for (int i = 0; i < intData.length - 1; i++) {
				strSubHex = Integer.toHexString(intData[i]).toUpperCase();
				for (int j = strSubHex.length(); j < 8; j++) {
					strSubHex = "0" + strSubHex;
				}
				strHex = strHex + strSubHex;
			}
			strSubHex = Integer.toHexString(intData[(intData.length - 1)])
					.toUpperCase();
			if (strSubHex.length() % 2 != 0) {
				strSubHex = "0" + strSubHex;
			}
			strHex = strHex + strSubHex;
		}
		return strHex;
	}

	private void processKeyGene() {
		boolean[] blnInGene = new boolean[64];
		int intG = 0;
		int intGCount = -1;
		int intCount = 0;
		int[] intGene = new int[64];
		String strPrivateKey = String.valueOf(this.intPrivateKey);
		for (int i = 0; i < 64; i++) {
			this.intUserKey[i] = this.intConstKey[i];
			blnInGene[i] = false;
			this.intPosition[i] = i;
			intGene[i] = -1;
		}
		for (int i = 0; i < strPrivateKey.length(); i++) {
			if (strPrivateKey.substring(i, i + 1).equals("-"))
				intG = 0;
			else {
				intG = Integer.parseInt(strPrivateKey.substring(i, i + 1));
			}
			if (blnInGene[intG] == false) {
				blnInGene[intG] = true;
				this.intPrivateKey = (this.intUserKey[intG] ^ this.intPrivateKey);
				intGCount++;
				intGene[intGCount] = intG;
			}
		}
		intCount = intGCount;
		for (int i = 0; i <= intCount; i++) {
			if ((intGene[i] > 0) && (intGene[i] < 7)) {
				for (int j = 0; j <= intCount; j++) {
					intG = intGene[i] * 10 + intGene[j];
					if ((intG < 64) && (blnInGene[intG] == false)) {
						intGCount++;
						intGene[intGCount] = intG;
						this.intPrivateKey = (this.intUserKey[intG] ^ this.intPrivateKey);
					}
				}
			}
		}
		intGCount++;
		for (int i = 0; i < 64; i++) {
			int j = (int) (intGene[(i % intGCount)] * Math.pow(i, 2.0D)) & 0x1F;
			intG = this.intUserKey[i];
			this.intUserKey[i] = this.intUserKey[j];
			this.intUserKey[j] = intG;
			intG = this.intPosition[i];
			this.intPosition[i] = this.intPosition[j];
			this.intPosition[j] = intG;
		}
	}

	public String encryptString(String strString, boolean blnGetHex) {
		if (blnGetHex) {
			return getLongHex(encryptData(getStringLong(strString), true));
		}
		return getLongString(encryptData(getStringLong(strString), true));
	}

	public String encryptString(String strString) {
		return getLongString(encryptData(getStringLong(strString), true));
	}

	public String decryptionString(String strString, boolean blnIsHex) {
		if (blnIsHex) {
			return getLongString(encryptData(getHexLong(strString), false));
		}
		return getLongString(encryptData(getStringLong(strString), false));
	}

	public String decryptionString(String strString) {
		return getLongString(encryptData(getStringLong(strString), false));
	}

	public int[] encryptData(int[] intData, boolean blnEncryption) {
		if (intData == null) {
			return null;
		}
		int intLen = intData.length;
		int[] intEncryptData = new int[intLen];
		int[] intModPosition = (int[]) null;
		int intMod = intLen & 0x3F;
		if (intMod > 0) {
			intModPosition = new int[intMod];
			int index = -1;
			for (int i = 0; i < 64; i++) {
				if (this.intPosition[i] < intMod) {
					intModPosition[(++index)] = this.intPosition[i];
				}
			}
		}
		if (blnEncryption) {
			for (int i = 0; i < intLen; i++)
				if (intLen - i > 63) {
					for (int j = 0; j < 64; j++) {
						intEncryptData[(i + j)] = (intData[(i + this.intPosition[j])]
								^ this.intPrivateKey ^ this.intUserKey[j]);
					}
					i += 63;
				} else {
					for (int j = 0; j < intLen - i; j++) {
						intEncryptData[(i + j)] = (intData[(i + intModPosition[j])]
								^ this.intPrivateKey ^ this.intUserKey[j]);
					}
					i = intLen;
				}
		} else {
			for (int i = 0; i < intData.length; i++) {
				if (intLen - i > 63) {
					for (int j = 0; j < 64; j++) {
						intEncryptData[(i + this.intPosition[j])] = (intData[(i + j)]
								^ this.intPrivateKey ^ this.intUserKey[j]);
					}
					i += 63;
				} else {
					for (int j = 0; j < intLen - i; j++) {
						intEncryptData[(i + intModPosition[j])] = (intData[(i + j)]
								^ this.intPrivateKey ^ this.intUserKey[j]);
					}
					i = intLen;
				}
			}
		}
		return intEncryptData;
	}

	/*
	 * public String getEncryptionMachineInfo() throws SystemException { if
	 * ((readTimer == 0L) || (System.currentTimeMillis() - readTimer >=
	 * 1200000L)) { encryptionInfo = "1244948480"; readTimer =
	 * System.currentTimeMillis(); }
	 * 
	 * return encryptionInfo; }
	 */

	/*
	 * public boolean testTemp() { boolean blnResult = false; long dateTime =
	 * System.currentTimeMillis() - DBConfigure.DBTime; blnResult = dateTime <
	 * abc; return blnResult; }
	 */

	public byte[] decryptionFile(String strFileName) {
		byte[] bytData = (byte[]) null;
		File file = new File(strFileName);
		int fleLen = (int) file.length();
		if (fleLen > 8) {
			try {
				FileInputStream in = new FileInputStream(file);
				int srcLength = 0;
				bytData = new byte[4];
				for (int i = 0; i < 2; i++) {
					in.read(bytData);
					for (int j = 3; j >= 0; j--) {
						if (bytData[j] < 0)
							srcLength = (srcLength << 8) + 256 + bytData[j];
						else {
							srcLength = (srcLength << 8) + bytData[j];
						}
					}
				}
				fleLen -= 8;
				bytData = new byte[fleLen];
				in.read(bytData);
				in.close();
				if (fleLen % 4 == 0)
					fleLen /= 4;
				else {
					fleLen = fleLen / 4 + 1;
				}
				int[] intData = new int[fleLen];
				int intIndex = 0;
				for (int i = 0; i < fleLen; i++) {
					intIndex += 4;
					for (int j = 1; j < 5; j++) {
						int index = intIndex - j;
						if (index < bytData.length) {
							if (bytData[index] < 0)
								intData[i] = ((intData[i] << 8) + 256 + bytData[index]);
							else {
								intData[i] = ((intData[i] << 8) + bytData[index]);
							}
						}
					}
				}
				intData = encryptData(intData, false);
				bytData = new byte[srcLength];
				intIndex = 0;
				for (int i = 0; i < fleLen; i++) {
					long lngData = intData[i] < 0 ? MaxInt + intData[i]
							: intData[i];
					intIndex += 4;
					for (int j = 4; j > 0; j--) {
						int index = intIndex - j;
						if (index < srcLength) {
							bytData[(intIndex - j)] = ((byte) (int) (lngData & 0xFF));
							lngData >>= 8;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bytData;
	}

	public static String getEncryptString(String key, String data) {
		Encryption ept = new Encryption(key);
		return ept.encryptString(data, true);
	}

	public static void main(String[] args) {
		Encryption Encryp = new Encryption("www.zhufida.com");

		String sString = "{[%UD,false,869,577{#L=51;T=102;W=253;H=34;F=宋体|12|000|0;N=新口岸友誼巷28號國際中心AZ鋪#}{#L=535;T=75;W=85;H=34;F=宋体|12|000|0;N=PO號：#}{#L=51;T=59;W=274;H=34;F=宋体|12|000|0;N=富利來大藥房#}{#L=739;T=48;W=94;H=34;F=宋体|12|000|0;N=6A倉#}{#L=387;T=113;W=106;H=29;F=宋体|12|000|0;N=3-0028#}{#L=568;T=113;W=84;H=29;F=宋体|12|000|0;N=03#}{#L=735;T=75;W=108;H=29;F=宋体|12|000|0;N=I000000016#}{#L=735;T=113;W=108;H=29;F=宋体|12|000|0;TP=DYYYY-MM-DD;N=2008-02-01#}{#L=641;T=497;W=227;H=22;A=2;F=宋体|12|000|0;N=澳門幣        $1595.40#}{#L=2;T=179;W=120;H=18;B=1;BC=;F=宋体|11|000|0;N=20101020#}{#L=122;T=179;W=320;H=18;B=1;BC=;F=宋体|11|000|0;N=強生嬰兒特溫和沐浴露(藍色)12x1000ml#}{#L=442;T=179;W=60;H=18;B=1;BC=;A=2;F=宋体|11|000|0;N=1打#}{#L=502;T=179;W=100;H=18;B=1;BC=;A=2;F=宋体|11|000|0;N=310.200#}{#L=602;T=179;W=60;H=18;B=1;BC=#}{#L=662;T=179;W=60;H=18;B=1;BC=#}{#L=722;T=179;W=100;H=18;B=1;BC=;A=2;F=宋体|11|000|0;N=310.20#}{#L=2;T=197;W=120;H=18;B=1;BC=;F=宋体|11|000|0;N=20106423#}{#L=122;T=197;W=320;H=18;B=1;BC=;F=宋体|11|000|0;N=強生嬰兒清新香桃沐浴露(粉紅色)12x1000ml#}{#L=442;T=197;W=60;H=18;B=1;BC=;A=2;F=宋体|11|000|0;N=1打#}{#L=502;T=197;W=100;H=18;B=1;BC=;A=2;F=宋体|11|000|0;N=310.200#}{#L=602;T=197;W=60;H=18;B=1;BC=#}{#L=662;T=197;W=60;H=18;B=1;BC=#}{#L=722;T=197;W=100;H=18;B=1;BC=;A=2;F=宋体|11|000|0;N=310.20#}{#L=2;T=215;W=120;H=18;B=1;BC=;F=宋体|11|000|0;N=20111431#}{#L=122;T=215;W=320;H=18;B=1;BC=;F=宋体|11|000|0;N=強生嬰兒(牛奶)沐浴露(泵裝)12x1000ml#}{#L=442;T=215;W=60;H=18;B=1;BC=;A=2;F=宋体|11|000|0;N=1打#}{#L=502;T=215;W=100;H=18;B=1;BC=;A=2;F=宋体|11|000|0;N=334.200#}{#L=602;T=215;W=60;H=18;B=1;BC=#}{#L=662;T=215;W=60;H=18;B=1;BC=#}{#L=722;T=215;W=100;H=18;B=1;BC=;A=2;F=宋体|11|000|0;N=334.20#}{#L=2;T=233;W=120;H=18;B=1;BC=;F=宋体|11|000|0;N=20115439#}{#L=122;T=233;W=320;H=18;B=1;BC=;F=宋体|11|000|0;N=強生嬰兒甜睡沐浴露1x12x1000ml#}{#L=442;T=233;W=60;H=18;B=1;BC=;A=2;F=宋体|11|000|0;N=1打#}{#L=502;T=233;W=100;H=18;B=1;BC=;A=2;F=宋体|11|000|0;N=358.800#}{#L=602;T=233;W=60;H=18;B=1;BC=#}{#L=662;T=233;W=60;H=18;B=1;BC=#}{#L=722;T=233;W=100;H=18;B=1;BC=;A=2;F=宋体|11|000|0;N=358.80#}{#L=2;T=251;W=120;H=18;B=1;BC=;F=宋体|11|000|0;N=20103410#}{#L=122;T=251;W=320;H=18;B=1;BC=;F=宋体|11|000|0;N=強生嬰兒蜜糖沐浴露(橙黃色)12x800ml#}{#L=442;T=251;W=60;H=18;B=1;BC=;A=2;F=宋体|11|000|0;N=1打#}{#L=502;T=251;W=100;H=18;B=1;BC=;A=2;F=宋体|11|000|0;N=282.000#}{#L=602;T=251;W=60;H=18;B=1;BC=#}{#L=662;T=251;W=60;H=18;B=1;BC=#}{#L=722;T=251;W=100;H=18;B=1;BC=;A=2;F=宋体|11|000|0;N=282.00#}{#L=2;T=269;W=120;H=18;B=1;BC=#}{#L=122;T=269;W=320;H=18;B=1;BC=#}{#L=442;T=269;W=60;H=18;B=1;BC=#}{#L=502;T=269;W=100;H=18;B=1;BC=#}{#L=602;T=269;W=60;H=18;B=1;BC=#}{#L=662;T=269;W=60;H=18;B=1;BC=#}{#L=722;T=269;W=100;H=18;B=1;BC=#}{#L=2;T=287;W=120;H=18;B=1;BC=#}{#L=122;T=287;W=320;H=18;B=1;BC=#}{#L=442;T=287;W=60;H=18;B=1;BC=#}{#L=502;T=287;W=100;H=18;B=1;BC=#}{#L=602;T=287;W=60;H=18;B=1;BC=#}{#L=662;T=287;W=60;H=18;B=1;BC=#}{#L=722;T=287;W=100;H=18;B=1;BC=#}{#L=2;T=305;W=120;H=18;B=1;BC=#}{#L=122;T=305;W=320;H=18;B=1;BC=#}{#L=442;T=305;W=60;H=18;B=1;BC=#}{#L=502;T=305;W=100;H=18;B=1;BC=#}{#L=602;T=305;W=60;H=18;B=1;BC=#}{#L=662;T=305;W=60;H=18;B=1;BC=#}{#L=722;T=305;W=100;H=18;B=1;BC=#}{#L=2;T=323;W=120;H=18;B=1;BC=#}{#L=122;T=323;W=320;H=18;B=1;BC=#}{#L=442;T=323;W=60;H=18;B=1;BC=#}{#L=502;T=323;W=100;H=18;B=1;BC=#}{#L=602;T=323;W=60;H=18;B=1;BC=#}{#L=662;T=323;W=60;H=18;B=1;BC=#}{#L=722;T=323;W=100;H=18;B=1;BC=#}{#L=2;T=341;W=120;H=18;B=1;BC=#}{#L=122;T=341;W=320;H=18;B=1;BC=#}{#L=442;T=341;W=60;H=18;B=1;BC=#}{#L=502;T=341;W=100;H=18;B=1;BC=#}{#L=602;T=341;W=60;H=18;B=1;BC=#}{#L=662;T=341;W=60;H=18;B=1;BC=#}{#L=722;T=341;W=100;H=18;B=1;BC=#}{#L=2;T=359;W=120;H=18;B=1;BC=#}{#L=122;T=359;W=320;H=18;B=1;BC=#}{#L=442;T=359;W=60;H=18;B=1;BC=#}{#L=502;T=359;W=100;H=18;B=1;BC=#}{#L=602;T=359;W=60;H=18;B=1;BC=#}{#L=662;T=359;W=60;H=18;B=1;BC=#}{#L=722;T=359;W=100;H=18;B=1;BC=#}{#L=2;T=377;W=120;H=18;B=1;BC=#}{#L=122;T=377;W=320;H=18;B=1;BC=#}{#L=442;T=377;W=60;H=18;B=1;BC=#}{#L=502;T=377;W=100;H=18;B=1;BC=#}{#L=602;T=377;W=60;H=18;B=1;BC=#}{#L=662;T=377;W=60;H=18;B=1;BC=#}{#L=722;T=377;W=100;H=18;B=1;BC=#}{#L=2;T=395;W=120;H=18;B=1;BC=#}{#L=122;T=395;W=320;H=18;B=1;BC=#}{#L=442;T=395;W=60;H=18;B=1;BC=#}{#L=502;T=395;W=100;H=18;B=1;BC=#}{#L=602;T=395;W=60;H=18;B=1;BC=#}{#L=662;T=395;W=60;H=18;B=1;BC=#}{#L=722;T=395;W=100;H=18;B=1;BC=#}{#L=2;T=413;W=120;H=18;B=1;BC=#}{#L=122;T=413;W=320;H=18;B=1;BC=#}{#L=442;T=413;W=60;H=18;B=1;BC=#}{#L=502;T=413;W=100;H=18;B=1;BC=#}{#L=602;T=413;W=60;H=18;B=1;BC=#}{#L=662;T=413;W=60;H=18;B=1;BC=#}{#L=722;T=413;W=100;H=18;B=1;BC=#}{#L=2;T=431;W=120;H=18;B=1;BC=#}{#L=122;T=431;W=320;H=18;B=1;BC=#}{#L=442;T=431;W=60;H=18;B=1;BC=#}{#L=502;T=431;W=100;H=18;B=1;BC=#}{#L=602;T=431;W=60;H=18;B=1;BC=#}{#L=662;T=431;W=60;H=18;B=1;BC=#}{#L=722;T=431;W=100;H=18;B=1;BC=#}%]}";
		sString = Encryp.encryptString(sString, true);
		String dString = Encryp.decryptionString(sString, true);
		System.out.println(dString);
	}
}
