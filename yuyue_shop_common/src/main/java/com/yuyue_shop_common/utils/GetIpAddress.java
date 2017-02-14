package com.yuyue_shop_common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

/**
 * 获取客户机所在的网络地址
 * 
 * @author 李明
 * @tel 17310545652
 * @createtime 2017年1月16日 下午4:42:35
 */
public class GetIpAddress {
	public static void main(String[] args) {
		GetIpAddress ip = new GetIpAddress();
		try {
			System.out.println(ip.getLocaIp());
			System.out.println(ip.GetAddressIp().get("country"));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	public JSONObject GetAddressIp() throws UnknownHostException {
		String IP = getV4IP();
		JSONObject obj2 = null;
		try {
			String str = getJsonContent("http://ip.taobao.com/service/getIpInfo.php?ip=" + IP);
			System.out.println(str);
			JSONObject obj = JSONObject.fromObject(str);
			obj2 = (JSONObject) obj.get("data");
			String code = String.valueOf(obj.get("code"));
			if (!code.equals("0")) {
				System.out.println("IP地址有误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取IP地址异常：" + e.getMessage());
		}
		return obj2;
	}

	public String getJsonContent(String urlStr) {
		try {// 获取HttpURLConnection连接对象
			URL url = new URL(urlStr);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			// 设置连接属性
			httpConn.setConnectTimeout(3000);
			httpConn.setDoInput(true);
			httpConn.setRequestMethod("GET");
			// 获取相应码
			int respCode = httpConn.getResponseCode();
			if (respCode == 200) {
				return ConvertStream2Json(httpConn.getInputStream());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	private String ConvertStream2Json(InputStream inputStream) {
		String jsonStr = "";
		// ByteArrayOutputStream相当于内存输出流
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		// 将输入流转移到内存输出流中
		try {
			while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
				out.write(buffer, 0, len);
			}
			// 将内存流转换为字符串
			jsonStr = new String(out.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

	public String getV4IP(){
		String ip = "";
		String chinaz = "http://ip.chinaz.com";

		StringBuilder inputLine = new StringBuilder();
		String read = "";
		URL url = null;
		HttpURLConnection urlConnection = null;
		BufferedReader in = null;
		try {
			url = new URL(chinaz);
			urlConnection = (HttpURLConnection) url.openConnection();
			in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
			while ((read = in.readLine()) != null) {
				inputLine.append(read + "\r\n");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
		Matcher m = p.matcher(inputLine.toString());
		if (m.find()) {
			String ipstr = m.group(1);
			ip = ipstr;
		}
		return ip;
	}
	/**
	 * 获取本地ip
	 * @return
	 * @throws UnknownHostException
	 */
	public String getLocaIp() throws UnknownHostException{
		String locaIp = InetAddress.getLocalHost().getHostAddress();
		return getV4IP()+":"+locaIp;
	}
}
