package java_webservice;

import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

@SOAPBinding(style=SOAPBinding.Style.RPC)//使用Webservice统一发布数据访问接口中间键
public class Userdataservice
{
	public static void main(String[] args)
	{
		System.out.println("webservice service is start...");
		Endpoint.publish("http://127.0.0.1:8009/user", new UserServiceImpl());
		
		System.out.println("Userdataservice服务发布");
	}  

}
