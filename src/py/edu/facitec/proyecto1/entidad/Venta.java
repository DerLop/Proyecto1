package py.edu.facitec.proyecto1.entidad;

import java.util.Date;

public class Venta {
	private int numero;
	private Cliente cliente;
	private Date fecha;
	private Boolean estado;
	
	public Venta() {
		numero = 0;
		fecha = new Date();
		estado = true;
		cliente = new Cliente();
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
