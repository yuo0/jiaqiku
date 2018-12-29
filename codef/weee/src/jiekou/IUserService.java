package jiekou;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace="http://thzm.com/wsdl")
public interface IUserService
{
	@WebMethod
	public String querole();//查角色
	
	
	@WebMethod
	public String  quesexshu();// 男女比
	
	
	@WebMethod
	public String  quebansexshu(String name);//-- **学生所在班级男女比
    
}
