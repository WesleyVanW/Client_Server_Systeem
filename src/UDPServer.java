import java.io.*;
import java.net.*;

public class UDPServer {
	
	public static void main(String args[]) throws SocketException, UnknownHostException, IOException {
		
		File myFile = new File("Ocean.mp3");
		DatagramSocket dataSocket = null;
		BufferedInputStream inputStream = null;
		
		try {
			while (true) {
				dataSocket = new DatagramSocket();
				DatagramPacket dataPacket;
				int packetSize = 4096;
				double nbOfPackets;
				nbOfPackets = Math.ceil(((int) myFile.length()) / packetSize);
				inputStream = new BufferedInputStream(new FileInputStream(myFile));
				
				for (double i = 0; i < nbOfPackets + 1; i++) {
					byte[] myByteArray = new byte[packetSize];
					inputStream.read(myByteArray, 0, myByteArray.length);
					System.out.println("Packet:" + (i + 1));
					dataPacket = new DatagramPacket(myByteArray, myByteArray.length, InetAddress.getByName("127.0.0.1"), 4000);
					dataSocket.send(dataPacket);                    
				} 
			}
		}finally { 
		}
	}
}
