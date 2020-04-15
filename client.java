package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class client {

	public static void main(String[] args) {
		try {
			DatagramSocket clientSocket=new DatagramSocket();
			InetAddress ip =InetAddress.getByName("localhost");
			
			byte[] requestByte =null;
			byte[] responseByte =new byte[4096];
			Scanner scanner =new Scanner(System.in);
			while(true) {
				System.out.println("client is connected with server");
				
				String input=scanner.nextLine();
				
				if(input.equalsIgnoreCase("close")) {
					System.out.println("server is closed");
					clientSocket.close();
					break;
				}
				requestByte=input.getBytes();
				
				DatagramPacket clientPacket =new DatagramPacket(requestByte,requestByte.length,ip,4000);
				clientSocket.send(clientPacket);
				
				DatagramPacket serverPacket =new DatagramPacket(responseByte,responseByte.length);
				clientSocket.receive(serverPacket);
				String response =new String(serverPacket.getData());
				
				System.out.println("server: "+response.trim());
				
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
