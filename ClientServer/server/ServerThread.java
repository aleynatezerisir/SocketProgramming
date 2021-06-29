package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServerThread {
	//the port on which server will listen for connections 
	public static int portNumber = 4444;
	public static List<Socket> clientSockets = new ArrayList <Socket>();
	public static List<ServerSocket> serverSockets = new ArrayList<ServerSocket>();
	
	public static int sayac=0;
	
	public static void main(String[] args) {
		
		try {
			ReadFile.main(args);
			for (int i =0 ;i<ReadFile.ClientList.size();i++ ) {
				String[] arrOfStr = ReadFile.ClientList.get(i).split(",", 2);
				serverSockets.add(new ServerSocket(Integer.parseInt(arrOfStr[1])));
			}
			
			//initialize server socket
			//serverSocket = new ServerSocket(portNumber);
			System.out.println("Server socket initialized.\n");
		} catch (IOException e) { //if this port is busy, an IOException is fired
			System.out.println("Cannot listen on port " + portNumber);
			e.printStackTrace();
			System.exit(0);
		}
		
	    boolean exit=false;
	    do
	    {
	      System.out.println("1.Add String");
	      System.out.println("2.Delete String");
	      System.out.println("3.Find String");
	      System.out.println("4.exit");
	      System.out.println("choose one!");
	      Scanner sd=new Scanner(System.in);
	      System.out.println("enter your choice");
	      int num=sd.nextInt();
	      sd.close();
	      switch(num)
	     {
	       case 1:
		       System.out.println("Enter string:");
	    	   String gr = sd.nextLine();
	    	   HashTable.map.put(HashTable.HashFunction(gr, Integer.parseInt(ReadFile.numOfNodes)),gr);
		       break;

	       case 2:
	    	   System.out.println("Enter string:");
	    	   String gr2 = sd.nextLine();
	    	   Boolean sildiMi  = HashTable.map.remove(HashTable.HashFunction(gr2, Integer.parseInt(ReadFile.numOfNodes)),gr2);
	    	   if(sildiMi) {
	    		   System.out.println("Silindi");
	    	   }
	    	   else {
	    		   System.out.println("Silinemedi");
	    	   }
		       System.out.println("\n");
		       
		       break;

	       case 3:
	    	   String gr3 = sd.nextLine();
	    	   String varMi = HashTable.map.get(HashTable.HashFunction(gr3, Integer.parseInt(ReadFile.numOfNodes)));
	    	   if(varMi == null) {
	    		   System.out.println("Bulunamadý.");
	    	   }else {
	    		   System.out.println("Bulundu !");
	    	   }
		       System.out.println("\n");
		       break;

	       case 4:
		       exit=true;
		       break;
	      }
	    }while(!exit);
		
		
		
		try {
			while(true) { //infinite loop - terminate manually
				//wait for client connections
				for(int i=0;i<serverSockets.size();i++) {
					System.out.println("Waiting for a client connection.");
					
					WorkerssThread w2 = new WorkerssThread(clientSockets.get(i),i);
					new Thread(w2).start();
					
					//let us see who connected
					String clientName = clientSockets.get(i).getInetAddress().getHostName();
					System.out.println(clientName + " established a connection.");
					System.out.println();
					
					//assign a worker thread
					WorkerThread w = new WorkerThread(clientSockets.get(i));
					new Thread(w).start();
				}
				
			}
		} finally {
			//make sure that the socket is closed upon termination
			for(int i=0;i<clientSockets.size();i++) {
				try {
					clientSockets.get(i).close();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
			
			
			
		}
	}
}
