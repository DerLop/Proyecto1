package py.edu.facitec.proyecto1.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author rorogarcete
 * Sera el encargado de abrir y cerrar una conexion a la Base de Datos
 */
public class ConexionManager {
	
	private static Connection conexion; //abre una sesion con la BD
	public static Statement stm; //ejecuta sentencia sql en el BD
	
	//CONSTANTE
	private static final String URL = "jdbc:postgresql://localhost:5432/Proyecto1";
	private static final String USER = "postgres";
	private static final String PASSWORD = "12345";
	
	public static Connection abrirConexion() {
		
		try {
			Class.forName("org.postgresql.Driver");	
			
			System.out.println("conexion con sucesso");
			
		} catch (ClassNotFoundException e) {
			
			System.out.println("conexion con error");
			
			e.printStackTrace();
		}
		
		
		try {
			conexion = DriverManager.getConnection(URL, USER, PASSWORD);
			stm = conexion.createStatement(); //listo para recibir sentencia sql
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conexion;
		
	}
	
	public static void cerrarConexion() {
		
		if (conexion != null) {
			
			try {
				stm.close(); //cierro la sentencia
				conexion.close(); //cierro la conexion
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) {
		
		ConexionManager.abrirConexion();
		
	}
	

}
