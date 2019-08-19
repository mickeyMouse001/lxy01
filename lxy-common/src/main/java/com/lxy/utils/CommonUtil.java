package com.lxy.utils;

import java.util.HashMap;
import java.util.Map;
import org.springframework.cglib.beans.BeanMap;

import com.lxy.entity.Header;

//import org.apache.commons.beanutils.BeanMap;

public class CommonUtil {


    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
            	map.put(key + "", beanMap.get(key)+"");
            	
            }
        }
        return map;
    }
    
	/**
	 * 生成token
	 * @param header
	 * @param uid
	 * @return
	 */
    public static String generateToken(Header header, long uid) {
		return MD5Util.encryptString(uid+header.getIp()+header.getClientId());
	}

}
