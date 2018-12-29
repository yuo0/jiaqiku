package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientApplication
{
	
	private  Socket  socket;
	
	public ClientApplication()
	{
		System.out.println("----�ͻ����������������������ķ��� ------");
		
		while(true)
		{
			try
			{
				//�����ͻ���������������׽��ֶ���socket�������˷�����ip,port
				socket = new Socket("127.0.0.1",8789); 
				System.out.println("����ͷ������������ӳɹ�"+socket);
				
				System.out.println("��ͻ���������Ϣ:");
				
				//�ڿ���̨������Ϣ  �ֽ���---ת����-------�ַ���
				BufferedReader  br =  new BufferedReader(new InputStreamReader(System.in));
				String  clientmsg=br.readLine();
				
				//���͸���������
				//PrintWriter���Թ����ַ������ֽ������߼���
				PrintWriter  pw=  new PrintWriter(socket.getOutputStream());
				pw.println(clientmsg);
				pw.flush();//�������͵�Ŀ��
				
				 //�ͻ������ܵ�����������Ϣ
                BufferedReader  cbr =  new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
                String  clientReceiverMsg=cbr.readLine();
                
                
                System.out.println("�ͻ��˽��յ��������˷��͵���ϢΪ:"+clientReceiverMsg);
			}
			catch (UnknownHostException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
	public static void main(String[] args)
	{
		ClientApplication  client = new ClientApplication();
	}
}
