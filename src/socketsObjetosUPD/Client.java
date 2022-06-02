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
import java.net.UnknownHostException;

public class Client {
	
	private DatagramPacket datgramPacket;
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
	private int port;
	private String host;
	private String info;
	
	/**
	 * @author malik
	 * Constructor
	 * @param host de tipo String
	 * @param port de tipo Int
	 */
	Client(String host, int port){
		this.host = host;
		this.port= port;
		buffer = new byte[150];
		
		try {
			ip = InetAddress.getByName("localhost");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @author malik
	 * Metodo para crear socket y establezer la conexion
	 * @throws SocketException throw SocketException
	 */
	
	public void connect() throws SocketException {
		addrss = new InetSocketAddress(host,port);
		datagramSocket = new DatagramSocket(addrss);
		 
		
		
	}
	
	/**
	 * @author malik
	 * Metodo para mandar mensaje  de tipo objeto serializado desde cliente al servdior usando la clase ByteArrayOutputStream
	 * que nos permite convertir objeto en array of bytes
	 * @param tipo de tipo Int
	 * @param texto de tipo String 
	 * @throws IOException thorw IOException
	 */
	public void sendMsg(int tipo, String texto) throws IOException {
		mensaje = new Message(tipo,texto);
		
		 
		byteArrayOs = new ByteArrayOutputStream();
		oos = new ObjectOutputStream(byteArrayOs);
		oos.writeObject(mensaje);
		oos.flush();
		oos.close();
		
		buffer = byteArrayOs.toByteArray();
		
		datgramPacket = new DatagramPacket(buffer,buffer.length,ip,5555);
		datagramSocket.send(datgramPacket);
		
		System.out.println(mensaje.getTipo() + "-" + mensaje.getTexto() + " - Enviado ... !");
		
		
		
	}
	
	/**
	 * @author malik
	 * Metodo para leer mensaje serializado enviado por el servidor usando la clase ByteArrayInputStream
	 * que nos permite hacer deserializacion o bien convertir array of byte a objeto 
	 * @return Object Mensahe
	 * @throws IOException thorw IOException
	 * @throws ClassNotFoundException throw ClassNotFoundException
	 */
	
	public Message readMsg() throws IOException, ClassNotFoundException {
		 
		DatagramPacket datagramPacket2 = new DatagramPacket(buffer,buffer.length);
		datagramSocket.receive(datagramPacket2);
		
		byteArrayIs = new ByteArrayInputStream(buffer);
		ois = new ObjectInputStream(byteArrayIs);
		Message mensaje = (Message) ois.readObject();
		info = datagramPacket2.getAddress() +"("+datagramPacket2.getPort()+")";
		return mensaje;
		 
	}
	
	
	
	
	
	/**
	 * Metodo return String es informacion de conexion (Adress ip, puerto)  del cliente
	 * @return info 
	 */
	public String getInfo() {
		  return info;
	}
	
	
	
	
	
}
