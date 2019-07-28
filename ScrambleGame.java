package com.pelatro.training.finalproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScrambleGame {
	public static boolean fileExist(String filePath) {
		File f = new File(filePath);
		if(!f.exists()) {
			//]System.out.println("Invalid FileLocation");
			return false;
			
		}
		return true;
		}
	
	public static AlphabetFactory[] initialiseAlphabetFactory() {
		String read;
		int wordLength;
		int rateOfFactory;
		List <Character> newList;
		FileReader fr = null;
		int actualWordLength;
		AlphabetFactory[] alphabetFactory = null;
		String filePath = "/home/anishar/eclipse-workspace/MiniProject/src/com/pelatro/training/finalproject/configFile.txt";
		try {
			if(!fileExist(filePath))
				System.exit(0);
		fr = new FileReader(filePath);
		BufferedReader br=new BufferedReader(fr);	
		String line[]=new String[50];
		String data[]=new String[2];
		read = br.readLine();
		
		if(read==null) {
			System.out.println("Your file content is empty");
			System.exit(0);
		}
		data=read.split(":");
		int factories = Integer.parseInt(data[1]);
		alphabetFactory= new AlphabetFactory[factories];
		int j=0;
		String part1[] = new String[2];
		String part2[] = new String[2];
			while((line[j]=br.readLine())!=null) {	
//				System.out.println(line[j]);
				data=line[j].split(",");
				part1=data[0].split(":");
				
				part2=data[1].split(":");
				wordLength = part1[1].length();
				actualWordLength=wordLength-(wordLength/2);
				String[] word = new String[actualWordLength];
				word=part1[1].split(" ");
				rateOfFactory=Integer.parseInt(part2[1]);
				newList = new ArrayList<Character>();
				for(int x = 0; x<actualWordLength;x++) {
				newList.add(word[x].charAt(0));
				}
				//int rate = Integer.parseInt(data[1]);
			System.out.println(newList);
				alphabetFactory[j]=new AlphabetFactory(newList,rateOfFactory);
				//System.out.println(alphabetFactory[j]);
				j++;
				newList=null;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alphabetFactory;
	}
	  
	
	
	  public static void main(String[] args) { 
		 
		  AlphabetFactory[] alphabetFactory = null;
		  alphabetFactory = initialiseAlphabetFactory(); 
		  //System.out.println(sg.alphabetFactory[0].rate);
		  AlphabetRepository alphabetRepository = new AlphabetRepository();
		  Thread tr = new Thread(alphabetRepository,"alr");
		  tr.start();
		  
		  for ( int r = 0; r< alphabetFactory.length;r++) {
		  Thread tf = new Thread(alphabetFactory[r],"alf");
		  tf.start();}
		
		 Scanner sc = new Scanner(System.in);
		 String takeUserInput ="";
		 while(!takeUserInput.equalsIgnoreCase("exit")) {
			 System.out.println("Enter the requied word");
			 takeUserInput = sc.next();
			 WordBuilder wb = new WordBuilder(takeUserInput);
			 Thread tw = new Thread(wb,"alw");
			 tw.start();
			
		 }
		 System.exit(0);
		  }
	 
	 

}
