package com.userreg;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Session;
import javax.persistence.TypedQuery;
import org.hibernate.cfg.Configuration;
import java.util.*;
public class Main {

	private static SessionFactory sessionfactory;
	
	public static void main(String[] args) {
		
		
		
		try
		{
		 sessionfactory=new Configuration().configure().buildSessionFactory();
		}
		catch(Throwable ex)
		{
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
		login();
	
		
		sessionfactory.close();
		
		

	}
	public static void registration()
	{
		Userreg reg=new Userreg();
		reg.setCountry("USA");
		reg.setPassword("usacoun");
		reg.setEmail("uaewef@gmail.com");
		reg.setName("John");
		reg.setProfileid(9);
		reg.setBirthdate(new Date());
		
		Session session=sessionfactory.openSession();
		Transaction tx=null;
		try
		{
		tx = session.beginTransaction();
		session.save(reg);
		tx.commit();
		}
		catch(Exception e)
		{
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
				
		}
		finally
		{
			session.close();
		}
		
		
	}
	
	public static void login()
	{
		Session session=sessionfactory.openSession();
		Transaction tx=null;
		try
		{
			tx=session.beginTransaction();
			Userreg reg=new Userreg();
			reg=(Userreg)session.get(Userreg.class,1);
			System.out.println(reg.getName());
			tx.commit();
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
		
	}
	
}
