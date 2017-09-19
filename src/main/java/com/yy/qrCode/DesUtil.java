package com.yy.qrCode;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * 
* @ClassName: DesUtil 
* @Description: DES加密解密工具 
* @author 猿说教育-Jame
* @date 2017年9月16日 下午9:52:13 
*
 */
public class DesUtil {
	//密钥 用来加密的通过这个密钥来加密 解密的时候同样通过这个密钥
	private static String key ="jameisSo3hand3s32om1e1234";
	//加密工具
	private Cipher encryptCipher = null;
	//解密工具
	private Cipher descryptCipher = null;
	public DesUtil() throws Exception{
		this(key);
	}
	public DesUtil(String keyStr) throws Exception{
		Key key = getKey(keyStr.getBytes());
		encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);
		descryptCipher = Cipher.getInstance("DES");
		descryptCipher.init(Cipher.DECRYPT_MODE, key);
		
	}
	private Key getKey(byte[] keyArray){
		byte[] tmp = new byte[8];
		for(int i = 0 ; i < tmp.length&&i<keyArray.length;i++){
			tmp[i] = keyArray[i];
		}
		Key key = new SecretKeySpec(tmp, "DES");
		return key;
	}
	public byte[] encrypt(byte[] source)throws Exception{
		return encryptCipher.doFinal(source);
	}
	public String encrypt(String str) throws Exception{
		return byteArrayToHexStr(encrypt(str.getBytes()));
	}
	public byte[] descrypt(byte[] source)throws Exception{
		return descryptCipher.doFinal(source);
	}
	public String descrypt(String str)throws Exception{
		return new String(descrypt(hexStrToByteArray(str)));
	}
	public String en(String en) throws Exception{
		return new String(encryptCipher.doFinal(en.getBytes()));
	}
	public String de(String de) throws Exception{
		return new String(descryptCipher.doFinal(de.getBytes()));
	}
	public static void main(String [] args) throws Exception{
		DesUtil desc = new DesUtil();
		String str= "jame老师太帅了！jame老师爱死你们了！！！";
		//加密操作
		String jiami = desc.encrypt(str);
		System.out.println("加密后："+jiami);
		//揭秘操作
		String jiemi = desc.descrypt(jiami);
		System.out.println("解密后："+jiemi);
		
	}
	public static String byteArrayToHexStr(byte[] array) throws Exception{
		int length = array.length;
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < length ; i++){
			//byte short int
			int tmpByte = array[i];
			while(tmpByte<0){
				tmpByte+=256;
			}
			if(tmpByte<16){
				sb.append("0");
			}
			sb.append(Integer.toString(tmpByte,16));
		}
		System.out.println("字节数组转换成16进制的字符串!"+sb.toString());
		return sb.toString();
	}
	public static byte[] hexStrToByteArray(String hexStr) throws Exception{
		byte[] tmp = hexStr.getBytes();
		int length = tmp.length;
		byte[] result = new byte[length/2];
		for(int i = 0 ; i < length ; i = i+2){
			String strTmp = new String(tmp,i,2);
			result[i/2] = (byte)Integer.parseInt(strTmp,16);
		}
		System.out.println("16进制字符串转成byte数组!"+hexStr+":"+result);
		return result;
	}
}
