package servicethree;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerThree {

    //服务器端的socket
    private Socket socket;


    //服务器端
    private ServerSocket serverSocket;

    public   ServerThree()
    {

        System.out.println("统一数据访问服务器启动服务..........");

        try {
            //保持对客户机的监听，是主线程main
            serverSocket = new ServerSocket(8789);


            
            ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

            while(true)
            {
                //全阻塞,如果客户端有请求，会建立连接，并返回socket对象
                socket=serverSocket.accept();

                System.out.println("客户机和服务器已建立连接，可以通信");

                
                fixedThreadPool.execute(new ServerThreadThree(socket));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public  static void  main(String[]  args)
    {
        ServerThree serverthree = new ServerThree();

    }
}
