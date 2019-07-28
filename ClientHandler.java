package com.pelatro.training.finalproject;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ClientHandler extends Thread{
		AlphabetRepository ar = null;
		String msg = null; 
		//WordBuilder wb = null;
	      
	 public ClientHandler(AlphabetRepository ar,String msg)  
	    { 
	        this.ar = ar; 
	        this.msg = msg; 
	    } 
	 @Override
	 public void run() {
		
		 if(msg.startsWith("$"))
			 {
			 	msg = msg.substring(1);
			 	connectRepositoryToStore(msg);
			 }
		 
		 else 
		 { System.out.println(msg);
			 System.out.println("Message from wordbuilder, connecting to repository");
		 	 connectRepositoryToCheck(msg);}
		 
	 }
	 
	 public void connectRepositoryToStore(String message) {
		 ar.addToRepository(message);
	 }
	 
	 public void connectRepositoryToCheck(String message) {
		 String status;
		 boolean result = ar.toCheckAvailability(message);
		 if(result == true) {
			 status = "Success";
			 System.out.println("Word Built");
		 }
		 else {
			 status = "Falied";
			 System.out.println("Word cannot be formed");
		 }
		 updateInDatabase(message,status); 
			 
	 }
	 
	public void updateInDatabase(String word,String status)
		{
	    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
			session.getTransaction().begin();
			
			ScrambleStorage  ss = new ScrambleStorage ();
			Date date= new Date();
			long time = date. getTime();
			
			Timestamp ts = new Timestamp(time);
			
			ss.setStatus(status);
			ss.setTimeStamp(ts);
			ss.setWord(word);
			session.save(ss);
			
			session.getTransaction().commit();
			session.close();
		}
	}
	 
