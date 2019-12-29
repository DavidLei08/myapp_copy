package jp.co.nttdata.myapp.utils.ip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;


public class IPAddrUtil {
	protected static final Logger logger = LoggerFactory.getLogger(IPAddrUtil.class);
	
	public static String getLocalIpAddr() {
		 
	    String clientIP = null;
	    Enumeration<NetworkInterface> networks = null;
	    try {
	        //获取所有网卡设备
	        networks = NetworkInterface.getNetworkInterfaces();
	        if (networks == null) {
	            //没有网卡设备 打印日志  返回null结束
	            logger.info("networks  is null");
	            return null;
	        }
	    } catch (SocketException e) {
	        System.out.println(e.getMessage());
	    }
	    InetAddress ip;
	    Enumeration<InetAddress> addrs;
	    // 遍历网卡设备
	    while (networks.hasMoreElements()) {
	        NetworkInterface ni = networks.nextElement();
	        try {
	            //过滤掉 loopback设备、虚拟网卡
	            if (!ni.isUp() || ni.isLoopback() || ni.isVirtual()) {
	                continue;
	            }
	        } catch (SocketException e) {
	            logger.info(e.getMessage());
	        }
	        addrs = ni.getInetAddresses();
	        if (addrs == null) {
	            logger.info("InetAddress is null");
	            continue;
	        }
	        // 遍历InetAddress信息
	        while (addrs.hasMoreElements()) {
	            ip = addrs.nextElement();
	            if (!ip.isLoopbackAddress() && ip.isSiteLocalAddress() && ip.getHostAddress().indexOf(":") == -1) {
	                try {
	                    clientIP = ip.toString().split("/")[1];
	                } catch (ArrayIndexOutOfBoundsException e) {
	                    clientIP = null;
	                }
	            }
	        }
	    }
	    return clientIP;
	}

	/**
	 * IP地址运算判断是否在分配的地址范围内.
	 * @param enterIp　起始ip地址.
	 * @param endIp 结束ip地址.
	 * @param nowIp 当前ip地址.
	 * @return 运算结果 true ：当前地址在范围内 false：当前地址不在范围内.
	 * @throws IpAddrFormatException ip地址转化异常。
	 */
	public  static boolean isSecurityIpAddr(String enterIp,String endIp,String nowIp) throws IpAddrFormatException {
		//传入的ip地址字符串，切成数组
		String [] enterIpArry = enterIp.split("\\.");
		String [] endIpArry = endIp.split("\\.");
		String [] nowIpArry = nowIp.split("\\.");
		//初始化接受转化数字的数组
		int [] enterIpNumArry = new int[4];
		int [] endIpNumArry = new int[4];
		int [] nowIpNumArry = new int[4];
		//判断数组格式是否正确
		if(enterIpArry.length==4&&endIpArry.length==4){
			try {
				//迭代起始ip地址数组，将String转成int
				for(int i=0 ; i < 4; i++){
					enterIpNumArry[i] = Integer.parseInt(enterIpArry[i]);
				}
				//迭代结束ip地址数组，将String转成int
				for(int i=0 ; i < 4; i++){
					endIpNumArry[i] = Integer.parseInt(endIpArry[i]);
				}
				//迭代当前ip地址数组，将String转成int
				for(int i=0 ; i < 4; i++){
					nowIpNumArry[i] = Integer.parseInt(nowIpArry[i]);
				}
			}catch (NumberFormatException ex){
				//捕获数值转化异常
				//抛出自定义异常
				throw  new IpAddrFormatException( ex.getCause());
			}
		}else {
			//抛出自定义异常
			throw new IpAddrFormatException("ipAddr format is not true");
		}
		//定义long型获取ip地址的二进制转十进制数
		long enterIpTenNum = getIpByteToTen(enterIpNumArry);
		long endIpTenNum = getIpByteToTen(endIpNumArry);
		long nowIpTenNum = getIpByteToTen(nowIpNumArry);
		//判断当前ip数值是否在范围内
		if(nowIpTenNum >= enterIpTenNum && nowIpTenNum <= endIpTenNum){
			return  true;
		}
		return false;
	}

	/**
	 * 将4位的ip地址数组转二进制对应的十进制数.
	 * @param ipArr 4位的ip地址int数组.
	 * @return  ip地址数组转二进制对应的十进制数.
	 */
	private static long getIpByteToTen(int[] ipArr){
		//定义临时值
		long temp = 0;
		//迭代数组
		for(int i : ipArr ){
			//左移4位后，加上当前数值
			temp =(temp << 8) + i;
		}
		return  temp;
	}
}
