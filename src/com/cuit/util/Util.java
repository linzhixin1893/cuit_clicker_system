package com.cuit.util;

public class Util {
	public static String getDBParam(String src) {
		return "'" + src + "'";
	}
	
	public static boolean isEmptyOrNull(String src) {
		if (src == null || "".equals(src)) {
			return true;
		} else {
			return false;
		}
	}
}
