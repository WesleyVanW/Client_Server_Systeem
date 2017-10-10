import java.io.*;
import java.net.*;

public class UDPClient {
	
	private final static File newFile = new File("udp_test.mp3"); 
	private final static File ogFile = new File("Ocean.mp3");
	static DatagramSocket serverSocket;
    static int packetSize=4096;
    static FileOutputStream outputStream = null; 
    
    public static void main(String args[]) throws SocketException, UnknownHostException, IOException {
    	serverSocket = new DatagramSocket(4000);
    	try {
    		outputStream = new FileOutputStream(newFile);
    		BufferedOutputStream bufferedOutput = new BufferedOutputStream(outputStream);
    		double nbOfPackets=Math.ceil(((int) (ogFile.length()/packetSize)));
    		byte[] myByteArray = new byte[packetSize];
    		DatagramPacket receivePacket = new DatagramPacket(myByteArray,myByteArray.length);

    		System.out.println("NbOfPackets: " + nbOfPackets + " PacketSize: " + packetSize);

    		for(double i=0;i<nbOfPackets+1;i++) {
    			serverSocket.receive(receivePacket); 
    			byte audioData[] = receivePacket.getData();
    			System.out.println("Packet:"+(i+1));
    			bufferedOutput.write(audioData, 0,audioData.length);
    		} 
    	}
    	finally { 
    	}
    }
}
    

