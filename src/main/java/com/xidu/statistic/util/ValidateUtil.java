package com.xidu.statistic.util;  
  
import java.util.regex.Pattern;

public class ValidateUtil {
	
	 
	    /**
	     * 正则表达式：验证密码
	     */
	    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";
	 
	    /**
	     * 正则表达式：验证手机号
	     */
	    public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
	 
	    /**
	     * 正则表达式：验证邮箱
	     */
	    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	 
	    /**
	     * 正则表达式：验证汉字
	     */
	    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";
	 
	    /**
	     * 正则表达式：验证身份证
	     */
	    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";
	 
	    /**
	     * 正则表达式：验证URL
	     */
	    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
	 
	 
	    /**
	     * 校验密码
	     * 
	     * @param password
	     * @return 校验通过返回true，否则返回false
	     */
	    public static boolean isPassword(String password) {
	        return Pattern.matches(REGEX_PASSWORD, password);
	    }
	 
	    /**
	     * 校验手机号
	     * 
	     * @param mobile
	     * @return 校验通过返回true，否则返回false
	     */
	    public static boolean isMobile(String mobile) {
	        return Pattern.matches(REGEX_MOBILE, mobile);
	    }
	 
	    /**
	     * 校验邮箱
	     * 
	     * @param email
	     * @return 校验通过返回true，否则返回false
	     */
	    public static boolean isEmail(String email) {
	        return Pattern.matches(REGEX_EMAIL, email);
	    }
	 
	    /**
	     * 校验汉字
	     * 
	     * @param chinese
	     * @return 校验通过返回true，否则返回false
	     */
	    public static boolean isChinese(String chinese) {
	        return Pattern.matches(REGEX_CHINESE, chinese);
	    }
	 
	    /**
	     * 校验身份证
	     * 
	     * @param idCard
	     * @return 校验通过返回true，否则返回false
	     */
	    public static boolean isIDCard(String idCard) {
	        return Pattern.matches(REGEX_ID_CARD, idCard);
	    }
	 
	    /**
	     * 校验URL
	     * 
	     * @param url
	     * @return 校验通过返回true，否则返回false
	     */
	    public static boolean isUrl(String url) {
	        return Pattern.matches(REGEX_URL, url);
	    }

	    /**
	     * 校验字符串空值 不为空，不为null
	     * 
	     * @param val
	     * @return 校验通过返回true，否则返回false
	     */
	    public static boolean isEmpty(String val){
	    	return !"".equals(val)&&val!=null;
	    }
	    
	    public static boolean isEmpty(Long val){
	    	return !"".equals(val)&&val!=null;
	    }
	    
	 
	    public static void main(String[] args) {
	        String username = "fdsdfsdj";
	    }
}
