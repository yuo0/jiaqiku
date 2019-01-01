package shuju;




import java.io.FilePermission;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import file.Fileproutils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Db
{
	Connection lian;
	
	static String urlimg="";
	static
	{
		urlimg=Fileproutils.getImageUtilPath();
	}
	
	public Db()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			lian=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/thzm", "root", "123456");
//			System.out.println(lian);
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List querole()
	{
		List<Rol> a=new ArrayList<Rol>();
		
		try
		{
			PreparedStatement biao=lian.prepareStatement("SELECT * FROM t_juese");
			ResultSet jie=biao.executeQuery();
			while(jie.next())
			{
				Rol r=new Rol();
				r.setRid(jie.getInt(1));
				r.setRname(jie.getString(2));
				a.add(r);
			}
			System.out.println(a);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally
		{
			if(null!=lian)
			{
				try
				{
					lian.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return a;
	}
	
	public  List   quesexshu()
	{
		
		List<Sexshu>  lists  = new ArrayList<Sexshu>();
		
		try {
			PreparedStatement  pstmt=lian.prepareStatement("SELECT ssex,COUNT(*) FROM t_students GROUP BY ssex");
		 
			ResultSet  rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				Sexshu  crole  = new Sexshu();
				crole.setSex(rs.getString(1));
				crole.setCount(rs.getInt(2));
				
				lists.add(crole);
			}
		   System.out.println(lists);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(null!=lian)
			{
				try {
					lian.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return lists;
	}
	
	public  String   quebansexshu(String  add)
	{
		
       String data="";
		
		try {
			PreparedStatement  pstmt=lian.prepareStatement("SELECT COUNT(*),ssex FROM t_stu GROUP BY sbid=(SELECT sbid FROM t_stu WHERE sname=?)");
			pstmt.setString(1,add);
			ResultSet  rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				data=rs.getInt(1)+","+rs.getString(2);
			}
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(null!=lian)
			{
				try {
					lian.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return data;
	}
	
	public  int quejianchadenglu(String name,int  pwd)//登录
	{
		
		try
		{
			PreparedStatement biao=lian.prepareStatement("SELECT COUNT(*) FROM t_students WHERE sname=? AND spsd=?");
			biao.setString(1,name);
			biao.setInt(2, pwd);
			ResultSet jie=biao.executeQuery();
			while(jie.next())
			{
				System.out.println("------------------>"+jie.getInt(1));
			 return jie.getInt(1);
			}
			//System.out.println(a);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 00;
	}
	
	public void zhuce(String name,int pwd)
	{
		try
		{
			PreparedStatement biao=lian.prepareStatement("INSERT INTO t_epl(ename,epwd) VALUES(?,?)");
			biao.setString(1, name);
			biao.setInt(2, pwd);
			biao.executeUpdate();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int jzhuce(String name,int pwd)
	{
		int a =0;
		try
		{
			PreparedStatement biao=lian.prepareStatement("select count(*) from t_epl where ename=? and epwd=?");
			biao.setString(1, name);
			biao.setInt(2, pwd);
			ResultSet jie=biao.executeQuery();
			while(jie.next())
			{
				a=jie.getInt(1);
			}
			System.out.println(a);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	
	public List<Menu> quemenu()
	{
		List<Menu> a=new ArrayList<Menu>();
		try
		{
			PreparedStatement biao=lian.prepareStatement("SELECT * FROM t_kemu where buff=1");
			ResultSet jie=biao.executeQuery();
			while(jie.next())
			{
				Menu m=new Menu();
				m.setKid(jie.getInt(1));
				m.setKname(jie.getString(2));
				m.setUrl(jie.getString(3));
				m.setIurl(urlimg+jie.getString(4));
				a.add(m);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally
		{
			if(null!=lian)
			{
				try
				{
					lian.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return a;
	}
	
	public List zhiwu()//职务数分组
	{
		List<Zhiwu> a=new ArrayList<Zhiwu>();
		try
		{
			PreparedStatement biao=lian.prepareStatement("SELECT   COUNT(jname),jname FROM t_stu INNER JOIN t_juese GROUP BY jname");
			ResultSet jie=biao.executeQuery();
			while(jie.next())
			{
				Zhiwu z=new Zhiwu();
				z.setCount(jie.getInt(1));
				z.setName(jie.getString(2));
				a.add(z);
			}
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	
	public List jiaoshi()//报表教室
	{
		List<Zhiwu> a=new ArrayList<Zhiwu>();
		try
		{
			PreparedStatement biao=lian.prepareStatement("SELECT COUNT(baddress),baddress FROM t_stu INNER JOIN t_banji GROUP BY baddress");
			ResultSet jie=biao.executeQuery();
			while(jie.next())
			{
				Zhiwu z=new Zhiwu();
				z.setCount(jie.getInt(1));
				z.setName(jie.getString(2));
				a.add(z);
			}
			//System.out.println(a);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally
		{
			if(null!=lian)
			{
				try
				{
					lian.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return a;
		
	}
	
	public List xuanke()//报表 选课
	{
		List<Zhiwu> a=new ArrayList<Zhiwu>();
		try
		{
			PreparedStatement biao=lian.prepareStatement("SELECT COUNT(kname),kname FROM t_stu INNER JOIN t_kemu ON sbid=kid GROUP BY kname");
			ResultSet jie=biao.executeQuery();
			while(jie.next())
			{
				Zhiwu z=new Zhiwu();
				z.setCount(jie.getInt(1));
				z.setName(jie.getString(2));
				a.add(z);
			}
			//System.out.println(a);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally
		{
			if(null!=lian)
			{
				try
				{
					lian.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return a;
		
	}
	
	public List javanannv()//报表 JAVA男女
	{
		List<Zhiwu> a=new ArrayList<Zhiwu>();
		try
		{
			PreparedStatement biao=lian.prepareStatement("SELECT COUNT(ssex),ssex FROM (SELECT * FROM t_stu INNER JOIN t_kemu ON sbid=kid WHERE kname='JAVA')temp GROUP BY ssex\r\n" + 
					"");
			ResultSet jie=biao.executeQuery();
			while(jie.next())
			{
				Zhiwu z=new Zhiwu();
				z.setCount(jie.getInt(1));
				z.setName(jie.getString(2));
				a.add(z);
			}
			//System.out.println(a);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally
		{
			if(null!=lian)
			{
				try
				{
					lian.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return a;
		
	}
	
	public List pythonnannv()//报表 python男女
	{
		List<Zhiwu> a=new ArrayList<Zhiwu>();
		try
		{
			PreparedStatement biao=lian.prepareStatement("SELECT COUNT(ssex),ssex FROM (SELECT * FROM t_stu INNER JOIN t_kemu ON sbid=kid WHERE kname='Python')temp GROUP BY ssex\r\n" + 
					"");
			ResultSet jie=biao.executeQuery();
			while(jie.next())
			{
				Zhiwu z=new Zhiwu();
				z.setCount(jie.getInt(1));
				z.setName(jie.getString(2));
				a.add(z);
			}
			//System.out.println(a);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally
		{
			if(null!=lian)
			{
				try
				{
					lian.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return a;
		
	}
	
	public static void main(String[] args)
	{
		Db d=new Db();
		d.jiaoshi();
		
//		List<Sexshu> l=d.quesexshu();
//		List<Zhiwu> l=d.zhiwu();
//		JSONArray a=new JSONArray();
//		for(Zhiwu r:l)
//		{
//			JSONObject obj=new JSONObject();
//			System.out.println(r.getCount()+r.getName());
//			obj.put("scount", r.getCount());
//			obj.put("sname", r.getName());
//			a.add(obj);
//		}
//		System.out.println(a);
		
//		int a=d.quejianchadenglu("刘冬孝",111111);
//		System.out.println(a);//0
		//d.jzhuce("香香", 000000);
		//System.out.println(d.quemenu());
	}

}
