package java_webservice;

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
	
	@WebMethod
	public String  quejianchadenglu(String name,int pwd);//--����¼
	
	@WebMethod
	public String  yanma();//--��֤��
	
	@WebMethod
	public void  zhuce(String name,int pwd);//--ע��
	
	@WebMethod
	public String  jianzhuce(String name,int pwd);//--���ע��
	
	
	@WebMethod
	public String   queryMenu();//�˵�
	
	@WebMethod
	public String   nzhiwu();//���� ְ��
	
	@WebMethod
	public String   jiaoshi();//���� ����
	
	@WebMethod
	public String   xuanke();//���� ѡ��
	
	@WebMethod
	public String   javanannv();//���� JAVA��Ů
	
	@WebMethod
	public String   pythonnannv();//���� python��Ů
    
}
