package py.edu.facitec.proyecto1.util;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.facitec.proyecto1.entidad.Cliente;

public class ModeloTbClientes extends AbstractTableModel {

	private String[] columnas = 
		{"Código", "Nombre", "Documento"};
	
	private Object[][] filas = new Object[0][columnas.length];
	
	//Metodo que recibe la lista y carga los datos necesarios a la matriz
	public void setLista(List<Cliente> listaClientes){
		filas = new Object[listaClientes.size()][columnas.length];
		
		for (int i = 0; i < listaClientes.size(); i++) {
			filas[i][0] = listaClientes.get(i).getId();
			filas[i][1] = listaClientes.get(i).getNombre();
			filas[i][2] = listaClientes.get(i).getDocumento();
		}
	}
	
	public int getColumnCount() {
		return columnas.length;
	}
	
	public String getColumnName(int i) {
		return columnas[i];
	}

	public int getRowCount() {
		return filas.length;
	}

	public Object getValueAt(int f, int c) {
		return filas[f][c];
	}

}
