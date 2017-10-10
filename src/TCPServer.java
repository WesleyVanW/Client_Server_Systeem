import java.io.*;
import java.net.*;

class TCPServer {

    public static void main(String args[]) {

    	try {
			int serverPort = 3248;
			ServerSocket listenSocket = new ServerSocket(serverPort);
			System.out.println("server start listening... ... ...");
			while(true) {
				Socket clientSocket = listenSocket.accept(); //accept all incoming connections
				new Connection(clientSocket); //send to server -> threaded server
			}
		} catch(IOException e) {
			System.out.println("Listen :"+e.getMessage());}
    }
}