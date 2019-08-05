package com.cxy.spring.boot.module.quartz.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 *
 * @author : xy.chen
 * @time : 2019/7/31
 * @desc : ip获取工具类
 */
public class IpUtil {
	
	public static final Logger logger = LoggerFactory.getLogger(IpUtil.class);
	
	/*private static final int IPV6Length = 8; // IPV6地址的分段  
    private static final int IPV4Length = 4; // IPV6地址分段  
    private static final int IPV4ParmLength = 2; // 一个IPV4分段占的长度  
    private static final int IPV6ParmLength = 4; // 一个IPV6分段占的长 
	 */
	
    @Resource  
    private WebServiceContext wsContext;
    
	/**
	 * 获取请求IP
	 * @param request
	 * @return
	 */
	public static String getRemortIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		
		 //这里主要是获取本机的ip,可有可无  
	    if ("127.0.0.1".equals(ip) || ip.endsWith("0:0:0:0:0:0:1")) {  
	        // 根据网卡取本机配置的IP  
	        InetAddress inet = null;
	        try {  
	            inet = InetAddress.getLocalHost();  
	        } catch (UnknownHostException e) {  
	            logger.error(e.getMessage(), e);
	        }
	        if(inet != null){
	        	ip = inet.getHostAddress();
	        }
	        return ip;
	    } 
		if(ip.length() > 0){
			String[] ipArray = ip.split(",");
			if (ipArray != null && ipArray.length > 1) {
				return ipArray[0];
			}
			return ip;
		}
		
		return "";
	}

	public static String getLocalIP() throws UnknownHostException, SocketException {
		if (isWindowsOS()) {
			return InetAddress.getLocalHost().getHostAddress();
		} else {
			return getLinuxLocalIp();
		}
	}

	public static boolean isWindowsOS() {
		String os = System.getProperty("os.name");
		if(os.toLowerCase().startsWith("win")){
			return true;
		}
		return false;
	}

	private static String getLinuxLocalIp() throws SocketException {
		String ip = "";
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				String name = intf.getName();
				if (!name.contains("docker") && !name.contains("lo")) {
					for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
						InetAddress inetAddress = enumIpAddr.nextElement();
						if (!inetAddress.isLoopbackAddress()) {
							String ipaddress = inetAddress.getHostAddress().toString();
							if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
								ip = ipaddress;
							}
						}
					}
				}
			}
		} catch (SocketException ex) {
			logger.error("获取ip地址异常");
			ip = "127.0.0.1";
			ex.printStackTrace();
		}
		return ip;
	}
}
