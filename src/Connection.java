import java.net.*; 
import java.io.*;

class Connection extends Thread { 
	
	private final static String fileToSend = "Ocean.mp3";
	File myFile = new File( fileToSend ); 
    byte[] mybytearray = new byte[(int) myFile.length()]; //Create array of bytes out of file to send
    BufferedOutputStream bufferedOutput = null;
    FileInputStream input = null;
    Socket clientSocket;
    
    public Connection (Socket aClientSocket) throws IOException
	{
		clientSocket = aClientSocket;
		this.start(); 
	}

    public void run(){
    	try {
    		input = new FileInputStream(myFile); //Create new inputstream out of file
    	} catch (FileNotFoundException ex) {
    		// Do exception handling
    	}
    	
    	BufferedInputStream bufferedInput = new BufferedInputStream(input); //split input stream into bytes

    	try {
    		bufferedOutput = new BufferedOutputStream(clientSocket.getOutputStream()); //create outputstream
    		bufferedInput.read(mybytearray, 0, mybytearray.length);  //Lees de file in naar een buffered byteArray
    		bufferedOutput.write(mybytearray, 0, mybytearray.length); //Stuur de byteArray door
    		bufferedOutput.flush(); //output flushen en slu
    		bufferedOutput.close();
    		clientSocket.close();
    		// File sent, exit the main method
    		return;
    	} catch (IOException ex) {
    		// Do exception handling
    	}
    }
}
