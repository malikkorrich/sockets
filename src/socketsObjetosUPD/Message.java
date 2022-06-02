package socketsObjetosUPD;
import java.io.Serializable;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6676510551454900106L;
	private int tipo;
	private String texto;
	
	/**
	 * Constructor
	 * @param tipo int
	 * @param texto String
	 */
	public Message(int tipo, String texto) {
		super();
		this.tipo = tipo;
		this.texto = texto;
	}


	/**
	 * Metodo get tipo
	 * @return tipo int
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * Metodo set Tipo
	 * @param tipo int
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * Metodo get Texto
	 * @return texto String
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * Metodo set Texto
	 * @param texto String
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
	
	
}
