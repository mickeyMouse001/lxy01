package com.lxy.utils;


import java.util.Random;
import java.util.UUID;

public class RandomUtil {

	
	private static final String[] NONCE_CODES={"0", "1", "2", "3", "4",
			"5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z"};
	
	private static final String[] RANDOM_CODES={"9","C", "D", "E","8","F", "G", "H",
			"3","Z","K", "2", "X","M", "N","S","P", "Q","6","R",  "T", "U",
			"V","5", "W","B","7", "Y","J","A", "4","a"};//31个字符
	
	private static final String[] NUMBERS={"0", "1", "2", "3", "4",
			"5", "6", "7", "8", "9"};
	

	
	/**
	 * 小于两个字节的随机数
	 * @return
	 */
	public static int getLessThan2ByteRandomNum(){
		int ran=(int)(Math.random()*100000);
		return ran&0xffff;	
	}
	/**
	 * 生成8位不重复的字符串
	 * @return
	 */
	public static String generateShortUuid() {  
	    StringBuffer shortBuffer = new StringBuffer();  
	    String uuid = UUID.randomUUID().toString().replace("-", "");  
	    for (int i = 0; i < 8; i++) {  
	        String str = uuid.substring(i * 4, i * 4 + 4);  
	        int x = Integer.parseInt(str, 16);  
	        shortBuffer.append(NONCE_CODES[x % 0x24]);//"0x24"=36
	    }  
	    return shortBuffer.toString();  
	  
	} 
	/**
	 * 根据macid生成唯一对应码(设备编号)
	 * @param macId
	 * @return
	 */
	public static String generateDeviceCodeByMacid(String macId) {
		Long num=assembleMacId(macId);
		System.out.println("long------"+get64BitBinString(num));
		StringBuilder stringBuffer = new StringBuilder();  
		for(int i=0;i<10;i++){
			int j=(int)(num>>(i*5))&0x1f;
			System.out.println("j-----"+j);
			stringBuffer.append(RANDOM_CODES[j%0x1f]);//31取模
		}
		return stringBuffer.reverse().toString();
	} 
//	public static void main(String[] args) {
//		String deviceCode=generateDeviceCodeByMacid("0a:95:a6:e3:A6:57");
//		System.out.println("deviceCode---------"+deviceCode);
//		String macId=parseDeviceCode2Macid(deviceCode);
//		System.out.println("macId---------"+macId);
//	}
	
	
	
	public static void main(String[] args) {
		Long uid=593845679869403136L;
		String a=generateInvicateCodeByUid(uid);//PSNESaP93399
		
		Long uid1=reverseAssemble(a);
		
		System.out.println("uid---"+generateSalt(uid));
	}
	public static String generateInvicateCodeByUid(Long uid) {
		StringBuilder stringBuffer = new StringBuilder();  
		for(int i=0;i<12;i++){//
			int j=(int)(uid>>(i*5))&0x1f;
			stringBuffer.append(RANDOM_CODES[j]);//31取模,因为只有31个字符
		}
		return stringBuffer.reverse().toString();
	} 
	
	
	public static String generateSalt(Long uid){
		return MD5Util.encryptString(String.valueOf(uid));
	}

	
	
	
	
	
	/**
	 * 解密-将设备编号解析还原为macId
	 * @param deviceCode
	 * @return
	 */
	public static String parseDeviceCode2Macid(String deviceCode) {//"0a:95:a6:e3:56:57"---C8QCOMT6
		Long num=reverseAssemble(deviceCode);

		String str=parseLong(num);

		return str;
	}
	private static String parseLong(Long num) {
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<12;i++){
			Long tmp=num>>i*4;
			int k=(int)(tmp&0x0f);
			if(i%2==1&&i!=11){
				sb.append(Integer.toHexString(k)).append(":");
			}else{
				sb.append(Integer.toHexString(k));
			}	
		}
		return sb.reverse().toString();
	}
	private static Long reverseAssemble(String deviceCode){
		Long newNum=0l;
		for(int i=0;i<deviceCode.length();i++){
			String str=deviceCode.substring(i,i+1);
			int index=getIndex(str);
			if(i==deviceCode.length()-1){
				newNum=(newNum|(index&0x1f));
				break;
			}
			newNum=(newNum|(index&0x1f))<<5;
		}
		return newNum;
	}


	private static String get64BitBinString(Long number) {
	    StringBuilder sBuilder = new StringBuilder();
	    for (int i = 0; i < 64; i++){
	        sBuilder.append(number & 1);
	        number = number >>> 1;
	    }
	    return sBuilder.reverse().toString();
	}

	/**
	 * 获取数组指定值得下标
	 * @param str
	 * @return
	 */
	private static int getIndex(String str) {
		int index=-1;
		for(int i=0;i<RANDOM_CODES.length;i++){
			if(str.equals(RANDOM_CODES[i])){
				index= i;
				break;
			}
		}
		return index;
	}

	/**
	 * 将macId遍历取低四位，重新组装成一个新的长整数
	 * @param macId
	 * @return
	 */
	private static Long assembleMacId(String macId){
		macId=macId.replace(":", "");
		Long newNum=0l;
		for (int i = 0; i <macId.length(); i++) {
			String str=macId.substring(i,i+1);
			Short x=Short.parseShort(str, 16);
			if(i==(macId.length()-1)){
				newNum=(newNum|(x&0x0f));
				break;
			}
			newNum=(newNum|(x&0x0f))<<4;
		}		
		return newNum;
	}
	/**
	 * 生成指定长度的随机数（含字母）
	 * @param length
	 * @return
	 */
   public static String getRandomByLength(int length) {
    	String[] tmp=new String[NONCE_CODES.length];
    	StringBuilder sb=new StringBuilder();
    	Random random=new Random();
    	int i=0;
    	do{
        	int index=random.nextInt(NONCE_CODES.length);
        	if(tmp[index]!=null){
        		continue;
        	}
        	tmp[index]=NONCE_CODES[index];
        	sb.append(tmp[index]);
        	i++;
    	}while(i<length);
    	tmp=null;
		return sb.toString();
	}
   
   /**
	 * 生成指定长度的随机数字
	 * @param length
	 * @return
	 */
  public static String getRandomNumsByLength(int length) {
   	String[] tmp=new String[NUMBERS.length];
   	StringBuilder sb=new StringBuilder();
   	Random random=new Random();
   	int i=0;
   	do{
       	int index=random.nextInt(NUMBERS.length);
       	if(tmp[index]!=null){
       		continue;
       	}
       	tmp[index]=NUMBERS[index];
       	sb.append(tmp[index]);
       	i++;
   	}while(i<length);
   	tmp=null;
		return sb.toString();
	}

}
