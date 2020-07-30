package com.studygit.first;

import java.io.UnsupportedEncodingException;

/**
 * FileName: TestUnicode.java
 * Author:   machao
 * Date:     2020/01/10 17:12
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */

public class TestUnicode {
	public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "\u7533\u8bf7\u79c0\u573a\u8fd0\u8425\u8d26\u53f7\u4e0d\u7ed3\u7b97\u94bb\u77f3:2019-10-08 20:32";
        System.out.println("转换成中文："+decodeUnicode(s));
        //System.out.println(s+" --的unicode编码是："+gbEncoding(s));
        //System.out.println(gbEncoding(s) + " --转换成中文是："+decodeUnicode(gbEncoding(s)));
        
        //System.out.println(gbEncoding(s) + " --转换成中文是："+decodeUnicode("\\u7b80\\u4ecb"));
    }
    
    /*
     * 中文转unicode编码
     */
    public static String gbEncoding(final String gbString) {
        char[] utfBytes = gbString.toCharArray();
        String unicodeBytes = "";
        for (int i = 0; i < utfBytes.length; i++) {
            String hexB = Integer.toHexString(utfBytes[i]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        return unicodeBytes;
    }
    /*
     * unicode编码转中文
     */
    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }
}
