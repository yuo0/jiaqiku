package jiekou;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace="http://thzm.com/wsdl")
public interface IUserService
{
	@WebMethod
	public String querole();//���ɫ
	
	
	@WebMethod
	public String  quesexshu();// ��Ů��
	
	
	@WebMethod
	public String  quebansexshu(String name);//-- **ѧ�����ڰ༶��Ů��
    
}
