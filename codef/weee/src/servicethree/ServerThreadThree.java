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
            System.out.println("产生一个子线程对客户机的应答,名字:-->"+Thread.currentThread().getName());

            //服务器接受到客户机的消息
            BufferedReader br =  new BufferedReader(new InputStreamReader(socket.getInputStream(),"gbk"));
            String  serverReceiverMsg=br.readLine();

            System.out.println("服务器得到的消息:"+serverReceiverMsg);
            String[]  msgs=null;
            //学生所选课程男女比|香香
            if(serverReceiverMsg.contains("|"))
            {
                  msgs= serverReceiverMsg.split("\\|");
                System.out.println(msgs[0]+","+msgs[1]);

            }

           
            String   re="";
            
            
            if(serverReceiverMsg.contains("角色"))
            {
            	URL url=new URL("http://127.0.0.1:8100/user");//构建访问的URL
    			QName qname=new QName("http://thzm.com/wsdl", "UserServiceImpl");//构建访问的名称
    			Service service=Service.create(url,qname);//创建服务对象
    			IUserService user=(IUserService)service.getPort(IUserService.class);//得到服务对象接口对象
    			
    			re=user.querole();
    			System.out.println("java客户端访问querole结果："+re);

                

            }
            
            else  if(serverReceiverMsg.contains("男女比"))
            {
            	URL url=new URL("http://127.0.0.1:8100/user");
    			QName qname=new QName("http://thzm.com/wsdl", "UserServiceImpl");
    			Service service=Service.create(url,qname);
    			IUserService user=(IUserService)service.getPort(IUserService.class);
    			
    			re=user.quesexshu();
    			System.out.println("java客户端访问quesexshu结果："+re);
            }
            
            else  if(msgs[0].contains("学生所在班级男女"))
            {

            	URL url=new URL("http://127.0.0.1:8100/user");
    			QName qname=new QName("http://thzm.com/wsdl", "UserServiceImpl");
    			Service service=Service.create(url,qname);
    			IUserService user=(IUserService)service.getPort(IUserService.class);
    			
    			re=user.quebansexshu(msgs[1]);
    			System.out.println("java客户端访问quebansexshu结果："+re);

            }
           
            Thread.sleep(10*1000);

            //发送给客户端端
            //PrintWriter可以构建字符流和字节流，高级流
            PrintWriter pw=  new PrintWriter(socket.getOutputStream());

            pw.println(re);
            pw.flush();//立即发送到目标
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }



    }
}

