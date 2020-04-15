package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Server {
	public static void main(String[] args) {
		try {
			DatagramSocket serverSocket=new DatagramSocket(4000);
			
			System.out.println("server is booted up and ready");
			
			byte[] requestByte =new byte[4096];
			byte[] responseByte =null;
			Scanner scanner=new Scanner(System.in);
			
			while(true) {
				System.out.println("server is connected");
				DatagramPacket clientPacket =new DatagramPacket(requestByte,requestByte.length);
				serverSocket.receive(clientPacket);	
				
				String request=new String(clientPacket.getData());
				System.out.println("client: "+ request.trim());
				
				String input=scanner.nextLine();
				responseByte=input.getBytes();
				
				InetAddress ip =clientPacket.getAddress();
				int port=clientPacket.getPort();
				
				DatagramPacket serverPacket=new DatagramPacket(responseByte,responseByte.length,ip,port);
				serverSocket.send(serverPacket);
				
				
				
			}	}catch(IOException e) {
				e.printStackTrace();
			}
	}

}
