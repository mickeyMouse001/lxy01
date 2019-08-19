package com.lxy.enums;


public enum MessageType {

	//系统容错提醒信息
	TYPE_PUBLIC(1,"public"),
	TYPE_PRIVATE(2,"private"),
	;
	private Integer type;
	private String cacheKey;
	
	
	private MessageType(Integer type, String cacheKey) {
		this.type = type;
		this.cacheKey = cacheKey;
	}
	public Integer getType() {
		return type;
	}
	public String getCacheKey() {
		return cacheKey;
	}

	public static String getType(String cacheKey) {
		
        for (MessageType wrapperEnumError : MessageType.values()) {
            if (Integer.valueOf(cacheKey).equals(wrapperEnumError.getType())) {
                return wrapperEnumError.getCacheKey();
            }
        }
        return "";
    }

}
