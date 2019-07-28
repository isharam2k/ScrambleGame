package com.pelatro.training.finalproject;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class AlphabetFactory extends Thread{
	List <Character> list;
	int rate;
	int i;
	char valueGenerated;

	public AlphabetFactory(List<Character> list, int rate ) {
		this.list=list;
		this.rate=rate;
		
			
		}
	
	public AlphabetFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return "list : "+list+" rate : "+rate;
	}
	@Override
	public void run() {
//		list = new ArrayList<Character>();
//		list.add('s');
//		list.add('a');
//		list.add('t');
//		list.add('u');
//		list.add('r');
//		list.add('n');
//		
		Socket soc  ;
		PrintWriter pw ;
		for(i=0;;i++) {
			
		try {
			
			soc = new Socket("LOCALHOST",4883);
			pw = new PrintWriter(new OutputStreamWriter(soc.getOutputStream()),true);
			Thread.sleep(rate*1000);
			//System.out.println("jjjj");
			valueGenerated = list.get(i%list.size());
			String finalMsg = "$"+valueGenerated;
			pw.println(finalMsg);
			soc.close();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		//socket
		//pick up rate
		//
	}
//	public void create() {
//		
//		
//	}
		
//public static void main(String[] args) {
//	AlphabetFactory af = new AlphabetFactory();
//	af.run();
//	
//	
//}

}
