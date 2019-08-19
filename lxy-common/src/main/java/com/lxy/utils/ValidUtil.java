package com.lxy.utils;

import org.apache.commons.lang3.StringUtils;

public class ValidUtil {
	private static final Integer CONTENT_LENGTH_LIMIT = 255;
	/**
	 * 验证是否为空
	 * 
	 * @param o
	 * @return
	 */
	public static boolean isNull(Object... o) {
		for (Object object : o) {
			if (object instanceof String) {
				String str = (String) object;
				if (StringUtils.isBlank(str)) {
					return true;
				}
			} else {
				if (object == null) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 * 验证内容长度不超过255
	 * 
	 * @param content
	 * @return
	 */
	public static boolean validContentLength(String content) {
		if (!isNull(content)) {
			if (content.length() > CONTENT_LENGTH_LIMIT) {
				return false;
			}
		}

		return true;
	}

	public static boolean validContentLength(String content, Integer length) {
		if (length == null) {
			return validContentLength(content);
		}
		if (!isNull(content)) {
			if (content.length() > length) {
				return false;
			}
		}

		return true;
	}
}
