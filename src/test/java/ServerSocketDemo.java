import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * FileName: ServerSocketDemo.java
 * Author:   machao
 * Date:     2019/11/21 18:38
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */

public class ServerSocketDemo {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(5200);
		Socket client = ss.accept();
		BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(client.getInputStream()));
		//读取一行数据
		String str = "";
		while ((str = bufferedReader.readLine())!=null){
			System.out.println(str);
			//输出打印
			PrintWriter write = new PrintWriter(client.getOutputStream());
			write.println("收到请求来,返回数据123");
			write.write("收到请求来,返回数据123");
			write.flush();
		}
		
		
	}
}
