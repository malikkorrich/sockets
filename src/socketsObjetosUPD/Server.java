package socketsObjetosUPD;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class Server {
	private DatagramPacket datagramPacket;
	private DatagramSocket datagramSocket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	//Utilizaremos las clases ByteArrayOutputStream y ByteArrayInputStream. Se necesita convertir el objeto a un array de bytes.
	private	ByteArrayOutputStream byteArrayOs;
	private ByteArrayInputStream byteArrayIs;
	private Message mensaje;
	private InetAddress ip;
	private InetSocketAddress addrss;
	byte[] buffer ;
	private final int port = 5555;
	private String info;
	
	Server(){
		buffer = new byte[150];
		 
	}
	
	
	public void startServer() throws SocketException {
		datagramSocket  = new DatagramSocket(port);
		System.out.println("Server Started at :"+ datagramSocket.getPort());
		
	}
	
	/**
	 * Metodo para leer mensaje de tipo objeto enviado por el cliente usando la clase ByteArrayInputStream para convertir array of byte
	 * to objeto 
	 * @return Object Mensaje
	 * @throws IOException  throw IOException
	 * @throws ClassNotFoundException throw ClassNotFoundException
	 */
	
	public Message readMsg() throws IOException, ClassNotFoundException {
		
		// Recibo datagrama
		datagramPacket = new DatagramPacket(buffer,buffer.length);
		//infinit loop to wait for client 
		 
			 datagramSocket.receive(datagramPacket);
				// Convertirmos bytes a objetos
				byteArrayIs = new ByteArrayInputStream(buffer);
				ois = new ObjectInputStream(byteArrayIs);
				mensaje = (Message) ois.readObject();
				
				ois.close();
				 info = datagramPacket.getAddress() +"("+datagramPacket.getPort()+"):";
				return mensaje;
				
			
			
	}
	
	/**
	 * Metodo return String es informacion de conexion (Ip Adress , puerto)  del cliente
	 * @return info
	 */
	public String getInfo() {
		  return info;
	}
	
	
	/**
	 * Metodo para enviar o devlever Mensaje (tipo objeto )recibido al cliente   sobre cliente 
	 * @throws IOException throw ClassNotFoundException
	 */
	public void sendMsg() throws IOException {
		
	 	byteArrayOs = new ByteArrayOutputStream();
		oos = new ObjectOutputStream(byteArrayOs);
		oos.writeObject(mensaje);
		oos.flush();
		oos.close();
		
		buffer = byteArrayOs.toByteArray();
		
		DatagramPacket datagramPacket2 = new DatagramPacket(buffer,buffer.length,datagramPacket.getAddress(),datagramPacket.getPort());
		datagramSocket.send(datagramPacket2);
		
		
		
		
	}
		 
	
	
	
}
