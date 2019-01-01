package java_webservice;


import java.util.List;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.jws.WebService;

import shuju.Db;
import shuju.Menu;
import shuju.Rol;
import shuju.Sexshu;
import shuju.Zhiwu;

@WebService(portName="userservice",serviceName="UserServiceImpl",
targetNamespace="http://thzm.com/wsdl",
endpointInterface="java_webservice.IUserService")
public class UserServiceImpl implements IUserService
{

	@Override
	public String querole()//查角色
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl queryRoleData is start... ");
		
		Db d=new Db();
		List<Rol> l=d.querole();
		System.out.println(l.size());
		
		JSONArray a=new JSONArray();
		for(Rol r:l)
		{
			JSONObject obj=new JSONObject();
			System.out.println(r.getRid()+r.getRname());
			obj.put("id", r.getRid());
			obj.put("name", r.getRname());
			a.add(obj);
		}
		return a.toString();
	}

	@Override
	public String quesexshu()// 男女比
	{
		// TODO Auto-generated method stub
		System.out.println("quesexshu is start... ");
		
		Db d=new Db();
		List<Sexshu> l=d.quesexshu();
		//System.out.println(l.size());
		
		JSONArray a=new JSONArray();
		for(Sexshu r:l)
		{
			JSONObject obj=new JSONObject();
			//System.out.println(r.getCount()+r.getSex());
			obj.put("scount", r.getCount());
			obj.put("sname", r.getSex());
			a.add(obj);
		}
		//System.out.println(a);
		return a.toString();
		
	}

	@Override
	public String quebansexshu(String name)//-- **学生所在班级男女比
	{
		// TODO Auto-generated method stub
		System.out.println("quebansexshu is start... ");
		
		Db d=new Db();
		String data=d.quebansexshu(name);
		System.out.println(data);
		return data;
	}

	@Override
	public String quejianchadenglu(String name, int pwd)//--检查登录
	{
		// TODO Auto-generated method stub
		System.out.println("quejianchadenglu 开始");
		
		Db d=new Db();
		int c=d.quejianchadenglu(name, pwd);
		System.out.println(c);
		if(c>0)
		{
			return "登陆成功";
		}
		return "登录失败";
	}

	@Override
	public String yanma()//--验证码
	{
		// TODO Auto-generated method stub
		
		Random ra=new Random();
		String a="";
		for(int i=0;i<6;i++)
		{
			a+=ra.nextInt(10);
		}
		System.out.println(a);
		return a;
	}

	@Override
	public void zhuce(String name,int pwd)//--注册
	{
		// TODO Auto-generated method stub
		Db d=new Db();
		d.zhuce(name, pwd);
	}
	
	@Override
	public String jianzhuce(String name, int pwd)//检查注册
	{
		// TODO Auto-generated method stub
		System.out.println("jianzhuce 开始");
		Db d=new Db();
		int a=d.jzhuce(name, pwd);
		if(a==0)
		{
			return "可注册";
		}
		
		
	 return "已有账号";
		
		
	}

	@Override
	public String queryMenu()//菜单
	{
		// TODO Auto-generated method stub
		System.out.println("queryMenu is start...  ");
		
		Db d=new Db();
		List<Menu> a=d.quemenu();
		// alibaba的json
		String strJson = com.alibaba.fastjson.JSONArray.toJSONString(a);

		System.out.println("strJson-->" + strJson);
		return strJson;
	}

	@Override
	public String nzhiwu()//报表 职务数分组
	{
		// TODO Auto-generated method stub
		System.out.println("nzhiwu is start...  ");
		
		Db d=new Db();
		List<Zhiwu> l=d.zhiwu();
		//System.out.println(l.size());
		
		JSONArray a=new JSONArray();
		for(Zhiwu r:l)
		{
			JSONObject obj=new JSONObject();
			System.out.println(r.getCount()+r.getName());
			obj.put("scount", r.getCount());
			obj.put("sname", r.getName());
			a.add(obj);
		}
		//System.out.println(a);
		return a.toString();
		
	}

	
	public static void main(String[] args)
	{
		UserServiceImpl u=new UserServiceImpl();
	    System.out.println(u.quesexshu());
		//System.out.println(u.quejianchadenglu("香香",000));
	}

	@Override
	public String jiaoshi()
	{
		// TODO Auto-generated method stub
		System.out.println("jiaoshi is start...  ");
		
		Db d=new Db();
		List<Zhiwu> l=d.jiaoshi();
		String sjson=com.alibaba.fastjson.JSONArray.toJSONString(l);
		System.out.println(sjson);
		return sjson;
	}

	@Override
	public String xuanke()//报表 选课
	{
		// TODO Auto-generated method stub
        System.out.println("jiaoshi is start...  ");
		
		Db d=new Db();
		List<Zhiwu> l=d.xuanke();
		String sjson=com.alibaba.fastjson.JSONArray.toJSONString(l);
		System.out.println(sjson);
		return sjson; 
	}

	@Override
	public String javanannv()//报表 JAVA男女
	{
		// TODO Auto-generated method stub
        System.out.println("jiaoshi is start...  ");
		
		Db d=new Db();
		List<Zhiwu> l=d.javanannv();
		String sjson=com.alibaba.fastjson.JSONArray.toJSONString(l);
		System.out.println(sjson);
		return sjson; 
	}

	@Override
	public String pythonnannv()//报表 python男女
	{
		// TODO Auto-generated method stub
        System.out.println("jiaoshi is start...  ");
		
		Db d=new Db();
		List<Zhiwu> l=d.pythonnannv();
		String sjson=com.alibaba.fastjson.JSONArray.toJSONString(l);
		System.out.println(sjson);
		return sjson; 
	}

}
