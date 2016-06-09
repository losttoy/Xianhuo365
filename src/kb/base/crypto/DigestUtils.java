package kb.base.crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import kb.base.radix.Hex;

/**
 * reference apache commons <a
 * href="http://commons.apache.org/codec/">http://commons.apache.org/codec/</a>
 * 
 * support MD2/MD5/SHA/SHA256/SHA384/SHA512
 * @author Aub
 * 
 */
public class DigestUtils {
	
 /**
 * @Fields BYTE_SIZE_FILE : 计算文件消息摘要时每次读入的字节数
 *                          1000KB
 */
 private static final int BYTES_SIZE = 1000 * 1024;
 
 /**
  * 根据给定摘要算法创建一个消息摘要实例
  * 
  * @param algorithm
  *            摘要算法名
  * @return 消息摘要实例
  * @see MessageDigest#getInstance(String)
  * @throws RuntimeException
  *             当 {@link java.security.NoSuchAlgorithmException} 发生时
  */
 static MessageDigest getDigest(String algorithm) {
  try {
   return MessageDigest.getInstance(algorithm);
  } catch (NoSuchAlgorithmException e) {
   throw new RuntimeException(e.getMessage());
  }
 }
 
 /**
  * 获取 MD5 消息摘要实例
  * 
  * @return MD5 消息摘要实例
  * @throws RuntimeException
  *             当 {@link java.security.NoSuchAlgorithmException} 发生时
  */
 private static MessageDigest getMd5Digest() {
  return getDigest("MD5");
 }
 
 /**
  * 获取 SHA-1 消息摘要实例
  * 
  * @return SHA-1 消息摘要实例
  * @throws RuntimeException
  *             当 {@link java.security.NoSuchAlgorithmException} 发生时
  */
 private static MessageDigest getShaDigest() {
  return getDigest("SHA");
 }
 
 /**
  * 获取 SHA-256 消息摘要实例
  * 
  * @return SHA-256 消息摘要实例
  * @throws RuntimeException
  *             当 {@link java.security.NoSuchAlgorithmException} 发生时
  */
 private static MessageDigest getSha256Digest() {
  return getDigest("SHA-256");
 }
 
 /**
  * 获取 SHA-384 消息摘要实例
  * 
  * @return SHA-384 消息摘要实例
  * @throws RuntimeException
  *             当 {@link java.security.NoSuchAlgorithmException} 发生时
  */
 private static MessageDigest getSha384Digest() {
  return getDigest("SHA-384");
 }
 
 /**
  * 获取 SHA-512 消息摘要实例
  * 
  * @return SHA-512 消息摘要实例
  * @throws RuntimeException
  *             当 {@link java.security.NoSuchAlgorithmException} 发生时
  */
 private static MessageDigest getSha512Digest() {
  return getDigest("SHA-512");
 }
 
 /**
  * 使用MD5消息摘要算法计算消息摘要
  * 
  * @param data
  *            做消息摘要的数据
  * @return 消息摘要（长度为16的字节数组）
  */
 public static byte[] encodeMD5(byte[] data) {
  return getMd5Digest().digest(data);
 }
 
 /**
  * 使用MD5消息摘要算法计算消息摘要
  * 
  * @param data
  *            做消息摘要的数据
  * @return 消息摘要（长度为32的十六进制字符串）
  */
 public static String encodeMD5Hex(byte[] data) {
  return Hex.encodeHexStr(encodeMD5(data));
 }
 
 /**
  * 使用SHA-1消息摘要算法计算消息摘要
  * 
  * @param data
  *            做消息摘要的数据
  * @return SHA-1消息摘要（长度为20的字节数组）
  */
 public static byte[] encodeSHA(byte[] data) {
  return getShaDigest().digest(data);
 }
 
 /**
  * 使用SHA-1消息摘要算法计算消息摘要
  * 
  * @param data
  *            做消息摘要的数据
  * @return SHA-1消息摘要（长度为40的十六进制字符串）
  */
 public static String encodeSHAHex(byte[] data) {
  return Hex.encodeHexStr(getShaDigest().digest(data));
 }
 
 /**
  * 使用SHA-256消息摘要算法计算消息摘要
  * 
  * @param data
  *            做消息摘要的数据
  * @return SHA-256消息摘要（长度为32的字节数组）
  */
 public static byte[] encodeSHA256(byte[] data) {
  return getSha256Digest().digest(data);
 }
 
 /**
  * 使用SHA-256消息摘要算法计算消息摘要
  * 
  * @param data
  *            做消息摘要的数据
  * @return SHA-256消息摘要（长度为64的十六进制字符串）
  */
 public static String encodeSHA256Hex(byte[] data) {
  return Hex.encodeHexStr(encodeSHA256(data));
 }
 
 /**
  * 使用SHA-384消息摘要算法计算消息摘要
  * 
  * @param data
  *            做消息摘要的数据
  * @return SHA-384消息摘要（长度为48的字节数组）
  */
 public static byte[] encodeSHA384(byte[] data) {
  return getSha384Digest().digest(data);
 }
 
 /**
  * 使用SHA-384消息摘要算法计算消息摘要
  * 
  * @param data
  *            做消息摘要的数据
  * @return SHA-384消息摘要（长度为96的十六进制字符串）
  */
 public static String encodeSHA384Hex(byte[] data) {
  return Hex.encodeHexStr(encodeSHA384(data));
 }
 
 /**
  * 使用SHA-512消息摘要算法计算消息摘要
  * 
  * @param data
  *            做消息摘要的数据
  * @return SHA-512消息摘要（长度为64的字节数组）
  */
 public static byte[] encodeSHA512(byte[] data) {
  return getSha512Digest().digest(data);
 }
 
 /**
  * 使用SHA-512消息摘要算法计算消息摘要
  * 
  * @param data
  *            做消息摘要的数据
  * @return SHA-512消息摘要（长度为128的十六进制字符串）
  */
 public static String encodeSHA512Hex(byte[] data) {
  return Hex.encodeHexStr(encodeSHA512(data));
 }
 
 private static byte[] encodeFile (MessageDigest md, String dir) {
	 File f = new File(dir);
	 byte[] b = new byte[BYTES_SIZE];
	 InputStream is = null;
	 try {
		 is = new FileInputStream(f);
		 int readed;
		 while (-1 != (readed = is.read(b))) {
			 md.update(b, 0, readed);
		 }
		 return md.digest();
	 } catch (FileNotFoundException e) {
		e.printStackTrace();
	 } catch (IOException e) {
		e.printStackTrace();
	 } finally {
		 if (null != is) {
			 try {
				is.close();
				is = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		 }
		 if (null != f) {
			 f = null;
		 }
	 }
	 
	 return null;
 }
 
 /**
 * @Title:       encodeMD5File
 * @Description: 计算文件的MD5值
 * @param dir
 * @return       byte[] 16
 * @throws
 */
public static byte[] encodeMD5File(String dir) {
	 MessageDigest md = getMd5Digest();
	 return encodeFile(md, dir);
 }
 
 /**
 * @Title:       encodeSHAFile
 * @Description: 计算文件的SHA1值
 * @param dir
 * @return       byte[] 20
 * @throws
 */
public static byte[] encodeSHAFile(String dir) {
	 MessageDigest md = getShaDigest();
	 return encodeFile(md, dir);
 }
 
 /**
 * @Title:       encodeSHA256File
 * @Description: 计算文件的SHA-256值
 * @param dir
 * @return       byte[] 32
 * @throws
 */
public static byte[] encodeSHA256File(String dir) {
	 MessageDigest md = getSha256Digest();
	 return encodeFile(md, dir);
 }
 
 /**
 * @Title:       encodeSHA384File
 * @Description: 计算文件的SHA-384值
 * @param dir
 * @return       byte[] 48
 * @throws
 */
public static byte[] encodeSHA384File(String dir) {
	 MessageDigest md = getSha384Digest();
	 return encodeFile(md, dir);
 }
 
 /**
 * @Title:       encodeSHA512File
 * @Description: 计算文件的SHA-512值
 * @param dir
 * @return       byte[] 64
 * @throws
 */
public static byte[] encodeSHA512File(String dir) {
	 MessageDigest md = getSha512Digest();
	 return encodeFile(md, dir);
 }
 
 /**
 * @Title:       encodeMD5FileStr
 * @Description: 计算文件的MD5值
 * @param dir
 * @return       String 32
 * @throws
 */
public static String encodeMD5FileStr(String dir) {
	 return Hex.encodeHexStr(encodeMD5File(dir));
 }
 
 /**
 * @Title:       encodeSHAFileStr
 * @Description: 计算文件的SHA1值
 * @param dir
 * @return       String 40
 * @throws
 */
public static String encodeSHAFileStr(String dir) {
	 return Hex.encodeHexStr(encodeSHAFile(dir));
 }
 
 /**
 * @Title:       encodeSHA256FileStr
 * @Description: 计算文件的SHA-256值
 * @param dir
 * @return       String 64
 * @throws
 */
public static String encodeSHA256FileStr(String dir) {
	 return Hex.encodeHexStr(encodeSHA256File(dir));
 }
 
 /**
 * @Title:       encodeSHA384FileStr
 * @Description: 计算文件的SHA-384值
 * @param dir
 * @return       String 96
 * @throws
 */
public static String encodeSHA384FileStr(String dir) {
	 return Hex.encodeHexStr(encodeSHA384File(dir));
 }
 
 /**
 * @Title:       encodeSHA512FileStr
 * @Description: 计算文件的SHA-512值
 * @param dir
 * @return       String 128
 * @throws
 */
public static String encodeSHA512FileStr(String dir) {
	 return Hex.encodeHexStr(encodeSHA512File(dir));
 }
 
 public static void main(String[] args) throws UnsupportedEncodingException {
	System.out.println(encodeSHAHex("zhuwei123".getBytes()));
	
	//计算文件的SHA1值
	/*
	 * 100K 44b73423a7bbce38d06ba55ecd821946630bea4d
	 * 计算耗费80.786s
	 * 1000K 44b73423a7bbce38d06ba55ecd821946630bea4d
     * 计算耗费56.843s
	 * 10M 44b73423a7bbce38d06ba55ecd821946630bea4d
     * 计算耗费94.465s
	*/
	for (int i = 0; i < 10; ++i) {
		long t1 = System.currentTimeMillis();
		System.out.println(encodeSHAFileStr
				 ("C:\\Users\\Will\\Desktop\\cn_visual_studio_2010_ultimate_x86_dvd_532347.iso"));
		long t2 = System.currentTimeMillis();
		System.out.println("计算耗费" + (t2 - t1) / 1000.0 + "s");
	}
 }
}
