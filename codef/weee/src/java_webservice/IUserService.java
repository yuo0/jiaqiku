package java_webservice;

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
	
	@WebMethod
	public String  quejianchadenglu(String name,int pwd);//--检查登录
	
	@WebMethod
	public String  yanma();//--验证码
	
	@WebMethod
	public void  zhuce(String name,int pwd);//--注册
	
	@WebMethod
	public String  jianzhuce(String name,int pwd);//--检查注册
	
	
	@WebMethod
	public String   queryMenu();//菜单
	
	@WebMethod
	public String   nzhiwu();//报表 职务数分组
    
}
