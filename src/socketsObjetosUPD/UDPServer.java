package socketsObjetosUPD;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JTextArea;

public class UDPServer {

	private JFrame frmUdpServer;
	private static JTextArea textAreaMensajes;
	  
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UDPServer window = new UDPServer();
					window.frmUdpServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		try {
			
			Server server = new Server();
			
			server.startServer();
			 
			Message mensaje = server.readMsg();
			
			textAreaMensajes.setText(server.getInfo() + mensaje.getTipo()+ "-" +mensaje.getTexto());
			
			server.sendMsg();
			
			
			
			
		} catch (  IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/**
	 * Create the application.
	 */
	public UDPServer() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUdpServer = new JFrame();
		frmUdpServer.setTitle("UDP Server");
		frmUdpServer.setBounds(100, 100, 563, 373);
		frmUdpServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUdpServer.getContentPane().setLayout(null);
		
		JLabel labelTitulo = new JLabel("Mensajes Recibidos");
		labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelTitulo.setBounds(206, 11, 128, 38);
		frmUdpServer.getContentPane().add(labelTitulo);
		
		  textAreaMensajes = new JTextArea();
		textAreaMensajes.setEditable(false);
		textAreaMensajes.setBounds(10, 52, 527, 271);
		frmUdpServer.getContentPane().add(textAreaMensajes);
		
		


		
		
	}
}
