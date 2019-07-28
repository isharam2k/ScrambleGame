package com.pelatro.training.finalproject;

import java.io.*;
import java.net.*;
import java.util.*;

public class WordBuilder extends Thread{
//	Scanner sc = new Scanner(System.in);
	String takeUserInput;
//	String fromUser() {
//	System.out.println("Enter the required word: ");
//	takeUserInput = sc.next();
//	return takeUserInput;
//	}
	
	public WordBuilder(String takeUserInput) {
		this.takeUserInput = takeUserInput;
	}
	public void run() {
	//fromUser();
	Socket soc = null  ;
	PrintWriter pw = null ;
	try {
		soc = new Socket("LOCALHOST",4883);
		pw = new PrintWriter(new OutputStreamWriter(soc.getOutputStream()),true);
	    pw.println(takeUserInput); 
	    pw.close();
		soc.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

	

//public static void main(String[] args) {
//	WordBuilder w = new WordBuilder();
//	System.out.println("hello");
//	w.fromUser();
//	w.run();
//	
//}

}


