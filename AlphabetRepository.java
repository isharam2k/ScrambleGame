package com.pelatro.training.finalproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class AlphabetRepository extends Thread {
	
	static ConcurrentHashMap<Character, Integer> chm = new ConcurrentHashMap<>();
	static AlphabetRepository a=new AlphabetRepository();
	public AlphabetRepository(ConcurrentHashMap<Character,Integer> chm) {
		this.chm=chm;
	}
	public void initialiseAlphabetRepository() {
		
		for(char y='a';y<='z';y++) {
			chm.put(y,0);
		}
		
	}
	public AlphabetRepository() {}
	
	//add message from clienthandler to rep
	public void addToRepository(String messageFromClientHandler) {
		//System.out.println("Adding");
		for(char c : messageFromClientHandler.toCharArray()) {
			chm.put(c, chm.get(c)+1);
		}
		//System.out.println(chm);
	}
	
synchronized public boolean toCheckAvailability(String msgFromUser) {
	
		String store = "";

		//System.out.println(msgFromUser);
		for(char c : msgFromUser.toCharArray()) {
			if(chm.get(c)==0) {
					//System.out.println("dont wait");
					System.out.println(c);
					try {
						wait(2000);
					//sleep(2000);
					}catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(chm.get(c)==0) {
						if(store == null) {
							System.out.println("Inside saved is null");
						}
						for(Character a :store.toCharArray()) {
							chm.put(a,chm.get(a)+1);
							System.out.println("Stored back");
						}
						return false;
							
				}}
			
			chm.put(c,chm.get(c)-1);
			store+=c;}
		
		return true;
	}
		
	public void run(){
		ServerSocket ss;
		Socket clientsoc ;
		BufferedReader br;
		String msgClient;
		a.initialiseAlphabetRepository();
		try {
			ss = new ServerSocket(4883);
			for(;;) {
			clientsoc = ss.accept();
			br = new BufferedReader(new InputStreamReader(clientsoc.getInputStream()));
			msgClient = br.readLine();
			//System.out.println("Client msg: "+ msgClient);
			Thread t = new ClientHandler(a,msgClient);
			t.start();
			clientsoc.close();
		} }catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
//	public static void main(String[] args) {
//	
//	System.out.println(chm);
//	a.run();
//	}

}
