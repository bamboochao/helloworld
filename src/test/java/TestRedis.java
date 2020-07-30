import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * FileName: TestRedis.java
 * Author:   machao
 * Date:     2020/05/26 21:29
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */

public class TestRedis {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("r-2zeue4x271t186lmzn.redis.rds.aliyuncs.com", 6379);
		jedis.auth("fHDyh1Yf=MM");
		String time = "20200601";
		Set<String> uids = jedis.zrangeByScore("STAT:ZSET:WATCHTIME:"+time, 1200, 86400);
		time = "20200602";
		Set<String> uids2 = jedis.zrangeByScore("STAT:ZSET:WATCHTIME:"+time, 1200, 86400);
		time = "20200603";
		Set<String> uids3 = jedis.zrangeByScore("STAT:ZSET:WATCHTIME:"+time, 1200, 86400);
		time = "20200604";
		Set<String> uids4 = jedis.zrangeByScore("STAT:ZSET:WATCHTIME:"+time, 1200, 86400);
		time = "20200605";
		Set<String> uids5 = jedis.zrangeByScore("STAT:ZSET:WATCHTIME:"+time, 1200, 86400);
		time = "20200606";
		Set<String> uids6 = jedis.zrangeByScore("STAT:ZSET:WATCHTIME:"+time, 1200, 86400);
		time = "20200607";
		Set<String> uids7 = jedis.zrangeByScore("STAT:ZSET:WATCHTIME:"+time, 1200, 86400);
		uids.addAll(uids2);
		uids.addAll(uids3);
		uids.addAll(uids4);
		uids.addAll(uids5);
		uids.addAll(uids6);
		uids.addAll(uids7);
		StringBuilder sb = new StringBuilder();
		uids.forEach(obj -> sb.append(obj.toString()).append(","));
    	File f = new File("uidAll.txt"); 
    	FileWriter fw;
    	try {
    		fw = new FileWriter(f);
    		fw.write(sb.toString());
    		fw.close();
    		jedis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
