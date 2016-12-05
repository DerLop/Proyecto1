package py.edu.facitec.proyecto1.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import py.edu.facitec.proyecto1.entidad.Cliente;
import py.edu.facitec.proyecto1.entidad.Venta;
import py.edu.facitec.proyecto1.util.ConexionManager;

public class VentaDao {
	public static void guardarVenta(Venta venta) {
		String sql = "insert into tb_ventas(ven_cli_id, ven_fecha, ven_estado) "
				+ "values ( "+venta.getCliente().getId()+", '"+venta.getFecha()+"', "
						+ " "+venta.getEstado()+" )";
		
		System.out.println("SQL : " + sql);
		
		ConexionManager.abrirConexion(); //abro la conexion con la BD
		
		try {
			ConexionManager.stm.executeUpdate(sql); //mandamos ejecutar el SQL
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		ConexionManager.cerrarConexion(); //cerramos la conexion
	}
	
	public static void anularVenta(int codigo) {
		String sql = "update tb_ventas set ven_estado = false where ven_id = "+codigo+"";
		
		System.out.println("SQL : " + sql);
		
		ConexionManager.abrirConexion(); //abro la conexion con la BD
		
		try {
			ConexionManager.stm.executeUpdate(sql); //mandamos ejecutar el SQL
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		ConexionManager.cerrarConexion(); //cerramos la conexion
	}
	
	public static void modificarVenta(Venta venta) {
		String sql = "update tb_ventas set ven_cli_id = "+venta.getCliente().getId()+""
				+ " where ven_id = "+venta.getNumero()+"";
		
		System.out.println("SQL : " + sql);
		
		ConexionManager.abrirConexion(); //abro la conexion con la BD
		
		try {
			ConexionManager.stm.executeUpdate(sql); //mandamos ejecutar el SQL
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		ConexionManager.cerrarConexion(); //cerramos la conexion
	}
	
	public static Venta recuperarVentaPorCodigo(int codigo) {
		String sql = "select * from tb_ventas "
				+ " join tb_clientes on cli_codigo = ven_cli_id "
				+ " where ven_id = "+codigo+" ";
		
		ConexionManager.abrirConexion();

		Venta venta = null;
		
		try {
			ResultSet rs = ConexionManager.stm.executeQuery(sql);
			
			if ( rs.next() ) {
				venta = new Venta(); //creamos un objeto
				venta.getCliente().setId(rs.getInt("cli_codigo"));
				venta.getCliente().setNombre(rs.getString("cli_nombre"));
				venta.setEstado(rs.getBoolean("ven_estado"));
				venta.setFecha(rs.getDate("ven_fecha"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ConexionManager.cerrarConexion(); //cerramos la conexion
		
		return venta; //retorna el objeto cliente
	}
}
