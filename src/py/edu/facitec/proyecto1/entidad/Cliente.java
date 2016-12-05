package py.edu.facitec.proyecto1.entidad;

/**
 * 
 * Clase que representa al objeto Cliente
 * @author Nazario Luis
 *
 */
public class Cliente extends Persona {
	private boolean activo;

	//Constructor
	public Cliente() {
		super();
		this.activo = true;
	}

	//Getter y Setter
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}
