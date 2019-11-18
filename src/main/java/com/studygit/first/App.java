package com.studygit.first;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws UnsupportedEncodingException
    {
    	String dateline = "2019-10-21 22:00:00";
    	int dateType = 0;
    	if(dateType==0) { //小时 2019-10-21 01:00:00
    		System.out.println( "Hello World!"+(1 ));
		}
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
}
