package egovframework.com.utl.sim.service;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.Globals;

import javax.servlet.http.HttpServletRequest;

public class EgovClntInfo {
	
	public static String getClntIP(HttpServletRequest request) throws Exception {
		
		// IP주소
		String ipAddr = request.getRemoteAddr();
		return ipAddr;
	}
	
	public static String getClntOsInfo(HttpServletRequest request) throws Exception {
	
		String user_agent = request.getHeader("user-agent");
		String os_info = user_agent.toUpperCase().split(";")[2].split("\\)")[0];
		String os_conf = EgovProperties.getProperty(Globals.CLIENT_CONF_PATH, os_info.replaceAll(" ", ""));
		String osInfo = "";
		if (os_conf != null && !"".equals(os_conf)) {
			osInfo = os_conf;
		} else {
			osInfo = os_info;
		}
		return osInfo;
	}
	
	public static String getClntWebKind(HttpServletRequest request) throws Exception {
		
		String user_agent = request.getHeader("user-agent");
		
		// 웹브라우저 종류 조회
		String webKind = "";
		if (user_agent.toUpperCase().indexOf("GECKO") != -1) {
			if (user_agent.toUpperCase().indexOf("NESCAPE") != -1) {
				webKind = "Netscape (Gecko/Netscape)";
			} else if (user_agent.toUpperCase().indexOf("FIREFOX") != -1) {
				webKind = "Mozilla Firefox (Gecko/Firefox)";
			} else {
				webKind = "Mozilla (Gecko/Mozilla)";
			}
		} else if (user_agent.toUpperCase().indexOf("MSIE") != -1) {
			if (user_agent.toUpperCase().indexOf("OPERA") != -1) {
				webKind = "Opera (MSIE/Opera/Compatible)";
			} else {
				webKind = "Internet Explorer (MSIE/Compatible)";
			}
		} else if (user_agent.toUpperCase().indexOf("SAFARI") != -1) {
			if (user_agent.toUpperCase().indexOf("CHROME") != -1) {
				webKind = "Google Chrome";
			} else {
				webKind = "Safari";
			}
		} else if (user_agent.toUpperCase().indexOf("THUNDERBIRD") != -1) {
			webKind = "Thunderbird";
		} else {
			webKind = "Other Web Browsers";
		}
		return webKind;
	}
	
	public static String getClntWebVer(HttpServletRequest request) throws Exception {
		
		String user_agent = request.getHeader("user-agent");
		
		// 웹브라우저 버전 조회
		String webVer = "";
		String [] arr = {"MSIE", "OPERA", "NETSCAPE", "FIREFOX", "SAFARI"};
		for (int i = 0; i < arr.length; i++) {
			int s_loc = user_agent.toUpperCase().indexOf(arr[i]);
			if (s_loc != -1) {
				int f_loc = s_loc + arr[i].length();
				webVer = user_agent.toUpperCase().substring(f_loc, f_loc+5);
				webVer = webVer.replaceAll("/", "").replaceAll(";", "").replaceAll("^", "").replaceAll(",", "").replaceAll("//.", "");
			}
		}
		return webVer;
	}
}
