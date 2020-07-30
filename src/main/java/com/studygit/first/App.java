package com.studygit.first;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws UnsupportedEncodingException
    {
    	System.out.println(getLevel(0));
    	
    	List<String> groups = new ArrayList<>();
    	groups.add("");
    	groups.add("458521,471890");
    	groups.add("1101487");
    	groups.add("1125788,1130411,1130440,1123430,1134224");
    	groups.add("1123399,1123431,1123383,1119759,1130856,1123342,1123408,1131687,1134169");
    	groups.add("1128635,1099792,1129926,1149740,1098552,1165044,1175306,1166811,1179449,1176751");
    	groups.add("1130923,1142829,1177241");
    	
    	
    	
    	
//		if(groups.isEmpty()) {groups.add("0");}
//		StringBuilder strBuilder = new StringBuilder("");
//		for(String uids : groups) {
//			if(StringUtils.isNotEmpty(uids)) {strBuilder.append(uids).append(",");}
//		}
//    	String dateline = "2019-10-21 22:00:00";
//    	int dateType = 0;
//    	if(dateType==0) { //小时 2019-10-21 01:00:00
//    		System.out.println( "Hello World!"+(1 ));
//		}
        //System.out.println( "Hello World!" );
        //System.out.println(urlDecode("%222345%E5%AF%BC%E8%88%AA%22%2C%22%E4%B8%9C%E6%96%B9%E6%96%B0%E9%97%BBh5%22%2C%22360%E6%89%8B%E5%8A%A9%22"));
    }
    static String urlDecode(String str) throws UnsupportedEncodingException {
		/*
		 * if (str == null) { return null; } if (str.length() % 3 != 0) { return null; }
		 * byte[] arr = new byte[str.length() / 3]; for (int i = 0; i <= str.length() -
		 * 3; i += 3) { //截取%后两位 String temp = null; if (i == str.length() - 3) { temp =
		 * str.substring(i + 1); } else { temp = str.substring(i + 1, i + 3); }
		 * System.out.println(temp); //转换成自字节 arr[i / 3] = (byte) Integer.parseInt(temp,
		 * 16); } System.out.println(Arrays.toString(arr));
		 */    //[-28, -72, -83, -27, -101, -67]
        //解码
    	String s = str;
    	s = URLDecoder.decode(s,"UTF-8");
        return s;
      }
    public static Integer getLevel(Integer exp){
        Integer allExp = 0;
        Integer i = 1;
        while (i < 500) {
            if (exp < allExp) {
                break;
            }
            i++;
            allExp += getLevelNeedExp(i);
        }
        Integer l = --i;
        l = l < 257 ? l : 256;
        return l;
    }
    public static Integer getLevel(BigDecimal exp){
    	BigDecimal allExp = new BigDecimal(0);
    	Integer i = 1;
        while (i < 500) {
            if (allExp.compareTo(exp)>0) {
                break;
            }
            i++;
            allExp = allExp.add(new BigDecimal(getLevelNeedExp(i)));
        }
        Integer l = --i;
        l = l < 257 ? l : 256;
        return l;
    }
    /**
     * 计算某个用户等级所需经验值
     * @param level 用户等级
     * @return
     */
	public static Integer getLevelNeedExp(Integer level){
        if (level > 1 && level < 500) {
            return 110 * level * level - 320 * level + 229;
        }
        return 0;
    }
}
