import java.io.*;
import java.util.Scanner;
import java.io.ByteArrayOutputStream;
import java.net.*;

class TCPClient {

    private final static String serverIP = "127.0.0.1"; //Local System
    private final static int serverPort = 3248;
    private final static String fileOutput = "tcp_test.mp3";
    static DataOutputStream dataOutput;
    
    public static void main(String args[]) {
        byte[] aPacket = new byte[4096];
        int bytesRead;

        Socket clientSocket = null;
        InputStream input = null;

        try {
            clientSocket = new Socket( serverIP , serverPort );
            input = clientSocket.getInputStream();
            
        } catch (IOException ex) {
            // Do exception handling
        }

        ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();

        if (input != null) {
        	System.out.println("Starting Download ...");
            FileOutputStream output = null;
            BufferedOutputStream bufferedOutput = null;
            try {
                output = new FileOutputStream( fileOutput );
                bufferedOutput = new BufferedOutputStream(output);
                bytesRead = input.read(aPacket, 0, aPacket.length); //Lees de input in in aByte

                do { //ontvang bestand
                        byteArrayOutput.write(aPacket);
                        bytesRead = input.read(aPacket);
                } while (bytesRead != -1);

                bufferedOutput.write(byteArrayOutput.toByteArray()); //Verschillende bytes omvormen tot bestand
                bufferedOutput.flush();
                bufferedOutput.close();
                clientSocket.close();
                System.out.println("File received");
            } catch (IOException ex) {
                // Do exception handling
            }
        }
    }
}