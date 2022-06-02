package socketsObjetosUPD;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class UDPClient {

	private JFrame frmUdpClient;
	private JTextField tfIP;
	private JTextField tfPuerto;
	private JLabel lblTipo;
	private JTextField tfTipo;
	private JLabel labelTexto;
	private JTextField tfTexto;
	private JLabel lblTipoRecib;
	private JTextField tfTipoRecib;
	private JLabel labelTextoRecib;
	private JTextField tfTextoRecib;
	private JLabel lbaleRemitente;
	private JTextField tfRemitente;

 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UDPClient window = new UDPClient();
					window.frmUdpClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UDPClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUdpClient = new JFrame();
		frmUdpClient.setTitle("UDP Client");
		frmUdpClient.setResizable(false);
		frmUdpClient.setBounds(100, 100, 406, 468);
		frmUdpClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUdpClient.getContentPane().setLayout(null);
		
		JLabel labelIP = new JLabel("IP");
		labelIP.setBounds(61, 35, 46, 14);
		frmUdpClient.getContentPane().add(labelIP);
		
		tfIP = new JTextField();
		tfIP.setText("127.0.0.1");
		tfIP.setBounds(105, 32, 124, 20);
		frmUdpClient.getContentPane().add(tfIP);
		tfIP.setColumns(10);
		
		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setBounds(61, 63, 46, 14);
		frmUdpClient.getContentPane().add(lblPuerto);
		
		tfPuerto = new JTextField();
		tfPuerto.setText("5554");
		tfPuerto.setColumns(10);
		tfPuerto.setBounds(105, 60, 124, 20);
		frmUdpClient.getContentPane().add(tfPuerto);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "A enviar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(61, 111, 261, 94);
		frmUdpClient.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(10, 21, 32, 14);
		panel.add(lblTipo);
		
		tfTipo = new JTextField();
		tfTipo.setText("1");
		tfTipo.setColumns(10);
		tfTipo.setBounds(47, 18, 47, 20);
		panel.add(tfTipo);
		
		labelTexto = new JLabel("Texto");
		labelTexto.setBounds(10, 52, 52, 14);
		panel.add(labelTexto);
		
		tfTexto = new JTextField();
		tfTexto.setText("Hola mundo");
		tfTexto.setColumns(10);
		tfTexto.setBounds(47, 49, 153, 20);
		panel.add(tfTexto);
		
		JButton btnEnviar = new JButton("Enviar");
		
		
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String host = tfIP.getText();
				int port = Integer.parseInt(tfPuerto.getText());
				
				Client client = new Client(host,port);
				
				int tipo = Integer.parseInt(tfTipo.getText());
				String texto = tfTexto.getText();
				 
				try {
					client.connect();
					client.sendMsg(tipo, texto);
					
					//get information from server
						Message mensaje = client.readMsg();
						  tipo = mensaje.getTipo()+100;
						tfTipoRecib.setText(String.valueOf(tipo));
						tfTextoRecib.setText(mensaje.getTexto());
						tfRemitente.setText(client.getInfo());
						
					
				} catch (IOException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
		
		
		btnEnviar.setBounds(138, 216, 75, 34);
		frmUdpClient.getContentPane().add(btnEnviar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Recibido", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(61, 264, 261, 119);
		frmUdpClient.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		lblTipoRecib = new JLabel("Tipo");
		lblTipoRecib.setBounds(10, 25, 32, 14);
		panel_1.add(lblTipoRecib);
		
		tfTipoRecib = new JTextField();
		tfTipoRecib.setEditable(false);
		tfTipoRecib.setColumns(10);
		tfTipoRecib.setBounds(67, 22, 47, 20);
		panel_1.add(tfTipoRecib);
		
		labelTextoRecib = new JLabel("Texto");
		labelTextoRecib.setBounds(10, 56, 52, 14);
		panel_1.add(labelTextoRecib);
		
		tfTextoRecib = new JTextField();
		tfTextoRecib.setEditable(false);
		tfTextoRecib.setColumns(10);
		tfTextoRecib.setBounds(67, 53, 153, 20);
		panel_1.add(tfTextoRecib);
		
		lbaleRemitente = new JLabel("Remitente");
		lbaleRemitente.setBounds(10, 91, 52, 14);
		panel_1.add(lbaleRemitente);
		
		tfRemitente = new JTextField();
		tfRemitente.setEditable(false);
		tfRemitente.setColumns(10);
		tfRemitente.setBounds(67, 88, 153, 20);
		panel_1.add(tfRemitente);
	}
}
