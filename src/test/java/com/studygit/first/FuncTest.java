package com.studygit.first;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

/**
 * FileName: FuncTest.java Author: machao Date: 2019/06/05 09:56 Description:
 * History: <author> <time> <version> <desc>
 */

public class FuncTest {
	/**
	 * 快速判断是否为整数
	 * @param str 传入的字符串
	 * @return 是整数返回true,否则返回false
	 */
	public static boolean isInteger(String str) {
		str = str.trim();
		Pattern pattern = Pattern.compile("^\\d*$");
		return pattern.matcher(str).matches();
	}
	
	public void testLimitAndSkip() {
		 List<Person> persons = new ArrayList<Person>();
		 for (int i = 1; i <= 5; i++) {
		 Person person = new Person(i, "name" + i);
		 person.getNo();
		 persons.add(person);
		 }
		List<String> personList2 = persons.stream().
		map(Person::getName).limit(10).skip(3).collect(Collectors.toList());
		 System.out.println(personList2);
		}
		private class Person {
		 public int no;
		 private String name;
		 public Person (int no, String name) {
		 this.no = no;
		 this.name = name;
		 }
		 public String getName() {
		 System.out.println(name);
		 return name;
		 }
		 public int getNo() {
			 System.out.println(no);
			 return no;
			 }
		}
		
		/**
	     * 判断一个月的第几周
	     * @param datetime
	     * @return
	     * @throws java.text.ParseException
	     */
	    public static Integer whatWeek(String datetime) throws java.text.ParseException {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(datetime);
			Calendar calendar = Calendar.getInstance();
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
			calendar.setTime(date);
			Integer weekNumbe = calendar.get(Calendar.WEEK_OF_MONTH);
			return weekNumbe;
	    }
	public static void main(String[] args) throws Exception {
		List<String> groups = new ArrayList<>();
		//,101168,101170,101171,101174,101175,101176,101177,101167,101179,,101172
		groups.add("");
		groups.add("101168");
		groups.add("101170");
		groups.add("101171");
		groups.add("101174");
		groups.add("101175");
		groups.add("");
		groups.add("101172");
		groups.add("");
		groups.add("101177");
		groups.add("");
		StringBuilder strBuilder = new StringBuilder("");
		for(String uids : groups) {
			if(StringUtils.isNotBlank(uids)) {strBuilder.append(uids).append(",");}
		}
		String subStr = strBuilder.toString();
		String uids = subStr.substring(0, subStr.length()-1);
		System.out.println(uids);
		
//		String subStr = ",,123,456,";
//		String uids = subStr.substring(0, subStr.length()-1);
//		System.out.println(uids);
//		uids="123,456,789,012,345,678,901,123,456";
//		String[] aids = uids.split(",");
//		List<String> test = new ArrayList<>();
		//List<String> list = 
//		Arrays.asList(aids).stream().distinct().forEach((aid)->{test.add(aid);});//.collect(Collectors.toList());
//		System.out.println(test);
//		String dateline = "2019-06-03~2019-06-09";
//		System.out.println(Integer.parseInt(dateline.substring(dateline.length()-2)));
//		System.out.println(whatWeek(dateline.substring(0,dateline.indexOf("~"))));
		
//		计算书币
		//BigDecimal words = new BigDecimal(1000L);
				
		//long coin = words.divide(new BigDecimal(1000)).multiply(new BigDecimal(20)).longValue();
		//System.out.println(coin);
		//System.out.println(isInteger("-123"));
//		FuncTest funcTest = new FuncTest();
//		funcTest.testLimitAndSkip();
		
		//PasswordEncoder pass = new BCryptPasswordEncoder();
		//System.out.println(pass.encode("123456"));
		//String oldpass = "$2a$10$V3decgdlfJ0dCPhRvXHAwuSf444tWiVpJKJoUqX4lYu6yTRUFDqBa";
		//System.out.println(pass.matches("123456", oldpass));
		
		//funcTest.excptionCatch();
		//System.out.println(new Date());
//		List<Integer> a = new ArrayList<Integer>();
//		a.add(1);
//		a.add(2);
//		a.add(3);
//		a.add(4);
//		List<Integer> b = new ArrayList<Integer>();
//		b.add(5);
//		b.add(6);
//		b.add(5);
//		b.add(6);
//		System.out.println(a);
//		System.out.println(a.removeAll(b));
//		System.out.println(a);
		
//		List<TestClass> list = new ArrayList<TestClass>();
//		list.add(new TestClass(5,"40%"));
//		list.add(new TestClass(4,"1.67%"));
//		list.add(new TestClass(8,"80.5%"));
//		list.add(new TestClass(1,"0"));
//		list.add(new TestClass(7,"77.32%"));
//		list.add(new TestClass(3,"0.04%"));
//		System.out.println(list);
//		System.out.println(list.stream().filter(testClass -> testClass.getI()>5).collect(Collectors.toList()));

//		Collections.sort(list, (t1, t2)->{
//			return t2.getStr().compareTo(t1.getStr());
//		});
//		System.out.println(list);
		
		
		
//		NumberFormat nt = NumberFormat.getPercentInstance(); 
//		nt.setMaximumFractionDigits(2);
//		double d = 0;
//		System.out.println(nt.format(5d/300));
//		System.out.println(nt.format(2d / 5));
//		System.out.println(nt.format(0d / 5));
//		System.out.println(nt.format(1d / 9999));
//		System.out.println((float)(2/3));
//		System.out.println((float)2/3);
//		DecimalFormat df = new DecimalFormat("0.0000");
//		System.out.println(Float.parseFloat(df.format((float)2/3)));
		
		
		// 排序测试
		/*
		 * List<TestClass> testSortList = new ArrayList<>(); testSortList.add(new
		 * TestClass(5,"5")); testSortList.add(new TestClass(12,"12"));
		 * testSortList.add(new TestClass(33,"33")); testSortList.add(new
		 * TestClass(21,"21")); testSortList.add(new TestClass(48,"48"));
		 * testSortList.add(new TestClass(71,"71")); testSortList.add(0,new
		 * TestClass(55,"55")); //testSortList.sort((o1, o2) -> {return
		 * -o1.getI()+o2.getI();}); testSortList =
		 * testSortList.stream().limit(3).collect(Collectors.toList());
		 * System.out.println(testSortList);
		 */
//		Float f = 2.6f;
//		int a = f.intValue();
//		System.out.println(a);
	}
	
	public int excptionCatch() {
		int result = 0;
		try{
			result = exceptionTest();
		}catch(Exception e) {
			System.out.println("外部catch");
			e.printStackTrace();
		}
		return result;
	}
	
	public int exceptionTest(){
		int a = 0;
		try{
			a = 2/0;
		}catch(Exception e) {
			System.out.println("内部catch");
			throw new RuntimeException(e);
		}
		return a;
	}
}

class TestClass{
	int i;
	String str;
	
	public TestClass() {}
	public TestClass(int i, String str) {
		this.i = i;
		this.str = str;
	}
	@Override
	public String toString() {
		return "TestClass [i=" + i + ", str=" + str + "]";
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
}

//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//@Component
//@ConfigurationProperties(prefix = "oss")
//class AliyunAuto {    
//	private String appKey;    
//	private String appSecret;    
//	private String bucket;    
//	private String endPoint;    
//	public String getAppKey() {return appKey;}    
//	public void setAppKey(String appKey) {this.appKey = appKey;}    
//	public String getAppSecret() {return appSecret;}    
//	public void setAppSecret(String appSecret) {this.appSecret = appSecret;}    
//	public String getBucket() {return bucket;}    
//	public void setBucket(String bucket) {this.bucket = bucket;}    
//	public String getEndPoint() {return endPoint;}    
//	public void setEndPoint(String endPoint) {this.endPoint = endPoint;}    
//	@Override    
//	public String toString() {
//		return "AliyunAuto{"+"appKey='"+appKey+'\''+
//				", appSecret='" + appSecret + '\'' +
//				", bucket='" + bucket + '\'' + 
//				", endPoint='" + endPoint + '\''+'}';
//	}
//}

