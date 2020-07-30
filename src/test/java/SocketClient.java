import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    // 搭建客户端
    public static void main(String[] args) throws IOException {
        try {
            // 1、创建客户端Socket，指定服务器地址和端口
        	Socket socket=new Socket("127.0.0.1",5200);
        	// Socket socket = new Socket("172.17.44.89", 20002);
            socket.setTcpNoDelay(true);
            socket.setReuseAddress(true);
            socket.setSoTimeout(30000);
            System.out.println("客户端启动成功");
            // 2、获取输出流，向服务器端发送信息
            // 向本机的52000端口发出客户请求
            //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // 由系统标准输入设备构造BufferedReader对象
            PrintWriter write = new PrintWriter(socket.getOutputStream());
            // 由Socket对象得到输出流，并构造PrintWriter对象
            //3、获取输入流，并读取服务器端的响应信息
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 由Socket对象得到输入流，并构造相应的BufferedReader对象
            String readline="{\"jsonrpc\":\"2.0\",\"method\":\"1.0::App\\\\Rpc\\\\Lib\\\\CoinInterface::get\",\"params\":[{\"uid\":10001563}],\"traceId\":\"433333375-d370-4641-a0ae-a4117b3e3bc7\",\"id\":\"\",\"ext\":{\"deviceId\":\"4ae9cd529204efc5\",\"ip\":\"127.0.0.1\",\"latitude\":\"\",\"longitude\":\"\",\"mobileModel\":\"\",\"void-risk\":\"true\"}}\\r\\n\\r\\n\n";
            // 若从标准输入读入的字符串为 "end"则停止循环
            write.println(readline);
            // 将从系统标准输入读入的字符串输出到Server
            write.flush();
            // 刷新输出流，使Server马上收到该字符串
            System.out.println("Client:" + readline);
            // 在系统标准输出上打印读入的字符串
            
            System.out.println("Server:" + in.readLine());
            System.out.println("接受服务端完毕");

            //4、关闭资源
            write.close(); // 关闭Socket输出流
            //          in.close(); // 关闭Socket输入流
            socket.close(); // 关闭Socket
            //socket.shutdownOutput();
        } catch (Exception e) {
            System.out.println("can not listen to:" + e);// 出错，打印出错信息
        }
    }
}
