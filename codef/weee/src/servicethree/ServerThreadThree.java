package servicethree;





import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import jiekou.IUserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

public class ServerThreadThree   extends  Thread {

    Socket socket;
    public ServerThreadThree( Socket socket)
    {
        this.socket=socket;
    }
    public void  run()
    {
        try
        {
            System.out.println("����һ�����̶߳Կͻ�����Ӧ��,����:-->"+Thread.currentThread().getName());

            //���������ܵ��ͻ�������Ϣ
            BufferedReader br =  new BufferedReader(new InputStreamReader(socket.getInputStream(),"gbk"));
            String  serverReceiverMsg=br.readLine();

            System.out.println("�������õ�����Ϣ:"+serverReceiverMsg);
            String[]  msgs=null;
            //ѧ����ѡ�γ���Ů��|����
            if(serverReceiverMsg.contains("|"))
            {
                  msgs= serverReceiverMsg.split("\\|");
                System.out.println(msgs[0]+","+msgs[1]);

            }

           
            String   re="";
            
            
            if(serverReceiverMsg.contains("��ɫ"))
            {
            	URL url=new URL("http://127.0.0.1:8100/user");//�������ʵ�URL
    			QName qname=new QName("http://thzm.com/wsdl", "UserServiceImpl");//�������ʵ�����
    			Service service=Service.create(url,qname);//�����������
    			IUserService user=(IUserService)service.getPort(IUserService.class);//�õ��������ӿڶ���
    			
    			re=user.querole();
    			System.out.println("java�ͻ��˷���querole�����"+re);

                

            }
            
            else  if(serverReceiverMsg.contains("��Ů��"))
            {
            	URL url=new URL("http://127.0.0.1:8100/user");
    			QName qname=new QName("http://thzm.com/wsdl", "UserServiceImpl");
    			Service service=Service.create(url,qname);
    			IUserService user=(IUserService)service.getPort(IUserService.class);
    			
    			re=user.quesexshu();
    			System.out.println("java�ͻ��˷���quesexshu�����"+re);
            }
            
            else  if(msgs[0].contains("ѧ�����ڰ༶��Ů"))
            {

            	URL url=new URL("http://127.0.0.1:8100/user");
    			QName qname=new QName("http://thzm.com/wsdl", "UserServiceImpl");
    			Service service=Service.create(url,qname);
    			IUserService user=(IUserService)service.getPort(IUserService.class);
    			
    			re=user.quebansexshu(msgs[1]);
    			System.out.println("java�ͻ��˷���quebansexshu�����"+re);

            }
           
            Thread.sleep(10*1000);

            //���͸��ͻ��˶�
            //PrintWriter���Թ����ַ������ֽ������߼���
            PrintWriter pw=  new PrintWriter(socket.getOutputStream());

            pw.println(re);
            pw.flush();//�������͵�Ŀ��
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }



    }
}

