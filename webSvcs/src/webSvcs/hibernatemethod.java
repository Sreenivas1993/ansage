package webSvcs;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import java.util.*;
import org.hibernate.*;
import org.json.simple.JSONObject;
import org.json.simple.*;




public class hibernatemethod {

    private static SessionFactory sessionfactory;
    
    public static void main(String agr[]){
    	JSONObject regform = new JSONObject();
		regform.put("username","pm@gmail.com");
		regform.put("password","12345");
		hibernatemethod h = new hibernatemethod();
		hibernatepojo plojo = h.login(regform);
		System.out.println(plojo.getName());
    }
    
   
	public  hibernatepojo registration(JSONObject regdata)
	{
		
		try
		{
		 sessionfactory=new Configuration().configure().buildSessionFactory();
		}
		catch(Throwable ex)
		{
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		String name=regdata.get("name").toString();
		String email=regdata.get("emailid").toString();
		String tagline=regdata.get("tagline").toString();
		String bio=regdata.get("bio").toString();
		String password=regdata.get("password").toString();
		String skills=regdata.get("skills").toString();
		int coins=25;
		
		hibernatepojo reg=new hibernatepojo();
		login logdet=new login();
		logdet.setEmail(email);
		logdet.setPassword(password);
		
		reg.setName(name);
		reg.setEmail(email);
		reg.setTagline(tagline);
		reg.setBioinfo(bio);
	    reg.setCoins(coins);
	    reg.setSkills(skills);
	    Session session=sessionfactory.openSession();
		Transaction tx=null;
		try
		{
		tx = session.beginTransaction();
		session.save(reg);
		session.save(logdet);
		tx.commit();
		}
		catch(Exception e)
		{
			if(tx!=null)
			tx.rollback();
			e.printStackTrace();
			return null;
			
		}
		finally
		{
			session.close();
		}
		
		sessionfactory.close();
		return reg;
	}
	
	@SuppressWarnings("deprecation")
	public  hibernatepojo  login(JSONObject logindet)
	{
		 hibernatepojo reg=null;	
		 login prof=null;
		String email=logindet.get("username").toString();
		String password=logindet.get("password").toString();
		
		
		
		try
		{
		 sessionfactory=new Configuration().configure().buildSessionFactory();
		}
		catch(Throwable ex)
		{
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
		
		
		
		Session session=sessionfactory.openSession();
		Transaction tx=null;
		try
		{
			tx=session.beginTransaction();
		     @SuppressWarnings("rawtypes")
		     Query query=session.createQuery("from login where email=:email and password=:password");
		    query.setParameter("email",email);
		    query.setParameter("password",password);
		    
		     prof=(login)query.uniqueResult();
		     
		     tx.commit();
		     if(prof==null)
		    	 return null;
		     
		     
		}
		catch(HibernateException e)
		{
			
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}
		finally
		{
			
			session.close();
			
		}
		int profileid=prof.getProfileid();
		try
		{
		session=sessionfactory.openSession();
		tx=session.beginTransaction();
		@SuppressWarnings("rawtypes")
		Query query=session.createQuery("from hibernatepojo where profileid=:profileid");
		query.setParameter("profileid",profileid);
		reg=(hibernatepojo)query.uniqueResult();
		tx.commit();
		if(reg==null)
			return null;
		
		
		}
		catch(HibernateException e)
		{
			
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}
		finally
		{
			
			session.close();
			
		}
		
		
		sessionfactory.close();
		return reg;
	}
	
	
	public String usercheck(String email)
	{  
		hibernatepojo check=null;
		try
		{
		 sessionfactory=new Configuration().configure().buildSessionFactory();
		}
		catch(Throwable ex)
		{
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		Session session=sessionfactory.openSession();
		Transaction tx=null;
		try
		{
			tx=session.beginTransaction();
		     @SuppressWarnings("rawtypes")
		     Query query=session.createQuery("from hibernatepojo where email=:email");
		     query.setParameter("email",email);
		    check =(hibernatepojo)query.uniqueResult();
		    tx.commit();
		    if(check==null)
		    	return "false";
		    	
		    
		}
		catch(HibernateException e)
		{
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}
		finally
		{
		session.close();
			
		}
		
		return "true";
	}
	
	@SuppressWarnings("unchecked")
	public static  void retrieve()
	{
		JSONArray jsonobj=new JSONArray();
		
		try
		{
		 sessionfactory=new Configuration().configure().buildSessionFactory();
		}
		catch(Throwable ex)
		{
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		Session session=sessionfactory.openSession();
		Transaction tx=null;
		try
		{
			tx=session.beginTransaction();
		     @SuppressWarnings("rawtypes")
		     Query query=session.createQuery("from hibernatepojo");
		     List<hibernatepojo> reglist=query.list();
		     tx.commit();
		     for(hibernatepojo u:reglist)
		     {
		    	 JSONObject jobj=new JSONObject();
				String name=u.getName();
				String email=u.getEmail();
				String tagline=u.getTagline();
				String bio=u.getBioinfo();
				int con=u.getCoins();
				String skills=u.getSkills();
				jobj.put("name", name);
				jobj.put("email", email);
				jobj.put("tagline", tagline);
				jobj.put("bio", bio);
				jobj.put("con", con);
				jobj.put("skills", skills);
				jsonobj.add(jobj);
		     }
				
		}
		catch(Throwable ex)
		{
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		finally
		{
		session.close();
			
		}
		
		
		
	}
	
	
	
	
}

