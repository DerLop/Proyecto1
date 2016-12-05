package py.edu.facitec.proyecto1.entidad;

/**
 * 
 * Clase que representa al objeto Persona
 * @author Nazario Luis
 *
 */
public class Persona {
	//Atributos
	private int id;
	private String nombre;
	private String documento;
	private String direccion;
	private String telefono;
	private int sexo;
	
	//Constructor
	public Persona() {
		this.id = 0;
		this.nombre = "";
		this.documento = "";
		this.direccion = "";
		this.telefono = "";
		this.sexo = 0;
	}

	//Getter y Setter
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDocumento() {
		return documento;
	}


	public void setDocumento(String documento) {
		this.documento = documento;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public int getSexo() {
		return sexo;
	}


	public void setSexo(int sexo) {
		this.sexo = sexo;
	}
	
	
}
