package servicethree;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerThree {

    //�������˵�socket
    private Socket socket;


    //��������
    private ServerSocket serverSocket;

    public   ServerThree()
    {

        System.out.println("ͳһ���ݷ��ʷ�������������..........");

        try {
            //���ֶԿͻ����ļ����������߳�main
            serverSocket = new ServerSocket(8789);


            
            ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

            while(true)
            {
                //ȫ����,����ͻ��������󣬻Ὠ�����ӣ�������socket����
                socket=serverSocket.accept();

                System.out.println("�ͻ����ͷ������ѽ������ӣ�����ͨ��");

                
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
