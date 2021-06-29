package server;

import java.io.IOException;
import java.net.Socket;

public class WorkerssThread implements Runnable {
	private Socket clientSocket;
	private int i;
	public WorkerssThread(Socket s,int a) {
		clientSocket = s;
		i=a;
	}
	
	public void run() {
		try {
			ServerThread.clientSockets.add(ServerThread.serverSockets.get(i).accept());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
s