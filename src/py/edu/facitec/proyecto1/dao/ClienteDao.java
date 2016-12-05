package py.edu.facitec.proyecto1.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import py.edu.facitec.proyecto1.entidad.Cliente;
import py.edu.facitec.proyecto1.util.ConexionManager;

/**
 * 
 * @author rorogarcete
 * DAO => Data Acces Object
 * Encapsular acceso a la base de datos mediante esta clase 
 * para el objeto Cliente
 */
public class ClienteDao {

	public static void guardarCliente(Cliente cliente) {
		String sql = "insert into tb_clientes(cli_documento, cli_nombre, cli_direccion, cli_telefono, "
				+ "cli_sexo, cli_activo) values ( '"+cliente.getDocumento()+"', '"+cliente.getNombre()+"', "
						+ " '"+cliente.getDireccion()+"', '"+cliente.getTelefono()+"', "
								+ " "+cliente.getSexo()+", "+cliente.isActivo()+" )";
		
		System.out.println("SQL : " + sql);
		
		ConexionManager.abrirConexion(); //abro la conexion con la BD
		
		try {
			ConexionManager.stm.executeUpdate(sql); //mandamos ejecutar el SQL
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		ConexionManager.cerrarConexion(); //cerramos la conexion
	}
	
	public static Cliente recuperarClientePorCodigo(int codigo) {
		String sql = "select * from tb_clientes where cli_codigo = "+codigo+" ";
		
		ConexionManager.abrirConexion();

		Cliente cliente = null;
		
		try {
			ResultSet rs = ConexionManager.stm.executeQuery(sql);
			
			if ( rs.next() ) {
				cliente = new Cliente(); //creamos un objeto
				
				cliente.setNombre(rs.getString("cli_nombre"));
				cliente.setDocumento(rs.getString("cli_documento"));
				cliente.setDireccion(rs.getString("cli_direccion"));
				cliente.setTelefono(rs.getString("cli_telefono"));
				
				cliente.setSexo(rs.getInt("cli_sexo"));
				
				cliente.setActivo(rs.getBoolean("cli_activo"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ConexionManager.cerrarConexion(); //cerramos la conexion
		
		return cliente; //retorna el objeto cliente
	}
	
	public static void modificarCliente(Cliente cliente) {
		String sql = "update tb_clientes set "
				+ "cli_nombre = '"+cliente.getNombre()+"',"
				+ "cli_documento = '"+cliente.getDocumento()+"',"
				+ "cli_direccion = '"+cliente.getDireccion()+"',"
				+ "cli_telefono = '"+cliente.getTelefono()+"',"
				+ "cli_activo = "+cliente.isActivo()+","
				+ "cli_sexo = "+cliente.getSexo()+" where "
				+ "cli_codigo = "+cliente.getId()+"  ";
		
		ConexionManager.abrirConexion(); //abro la conexion
		
		try {
			ConexionManager.stm.executeUpdate(sql); //mando ejecutar el sql
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		ConexionManager.cerrarConexion(); //cierro la conexion
	}
	
	public static void eliminarClientePorCodigo(int codigo) {
		String sql = "delete from tb_clientes where cli_codigo = "+codigo+" ";
	
		ConexionManager.abrirConexion();
		
		try {
			ConexionManager.stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ConexionManager.cerrarConexion();
	}
	
	public static List<Cliente> recuperarTodo(){
		String sql = "select * from tb_clientes order by cli_codigo";
		
		ConexionManager.abrirConexion();

		List<Cliente> listaClientes = new ArrayList<Cliente>();
		Cliente cliente = null;
		
		try {
			ResultSet rs = ConexionManager.stm.executeQuery(sql);
			
			while ( rs.next() ) {
				
				cliente = new Cliente(); //creamos un objeto
				cliente.setId(rs.getInt("cli_codigo"));
				cliente.setNombre(rs.getString("cli_nombre"));
				cliente.setDocumento(rs.getString("cli_documento"));
				cliente.setDireccion(rs.getString("cli_direccion"));
				cliente.setTelefono(rs.getString("cli_telefono"));
				
				cliente.setSexo(rs.getInt("cli_sexo"));
				
				cliente.setActivo(rs.getBoolean("cli_activo"));
				
				//se agrega el objeto al la lista
				listaClientes.add(cliente);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ConexionManager.cerrarConexion(); //cerramos la conexion
		
		return listaClientes; //retorna la lista de clientes
	}
	
	public static List<Cliente> recuperarClientesPorFiltros(String filtro){
		
		//si se necesita filtrar un valor entero
		/*int id = 0;
		try {
			id = Integer.parseInt(filtro);
		} catch (NumberFormatException e1) {
		}*/
		
		String sql = "select * from tb_clientes "
				+ "where cli_nombre like '%"+filtro+"%' "
				+ "or cli_documento = '"+filtro+"' "
				+ "order by cli_codigo";
		
		ConexionManager.abrirConexion();

		List<Cliente> listaClientes = new ArrayList<Cliente>();
		Cliente cliente = null;
		
		try {
			ResultSet rs = ConexionManager.stm.executeQuery(sql);
			
			while ( rs.next() ) {
				
				cliente = new Cliente(); //creamos un objeto
				cliente.setId(rs.getInt("cli_codigo"));
				cliente.setNombre(rs.getString("cli_nombre"));
				cliente.setDocumento(rs.getString("cli_documento"));
				cliente.setDireccion(rs.getString("cli_direccion"));
				cliente.setTelefono(rs.getString("cli_telefono"));
				
				cliente.setSexo(rs.getInt("cli_sexo"));
				
				cliente.setActivo(rs.getBoolean("cli_activo"));
				
				//se agrega el objeto al la lista
				listaClientes.add(cliente);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ConexionManager.cerrarConexion(); //cerramos la conexion
		
		return listaClientes; //retorna la lista de clientes
	}
	
}
