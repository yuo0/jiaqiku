package java_webservice;

import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

@SOAPBinding(style=SOAPBinding.Style.RPC)//ʹ��Webserviceͳһ�������ݷ��ʽӿ��м��
public class Userdataservice
{
	public static void main(String[] args)
	{
		System.out.println("webservice service is start...");
		Endpoint.publish("http://127.0.0.1:8009/user", new UserServiceImpl());
		
		System.out.println("Userdataservice���񷢲�");
	}  

}
