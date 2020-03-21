package cuarentena;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import cuarentena.Conector_cuarentena;


public class Conector_cuarentena {
	
	private static Connection con;
	private static Conector_cuarentena INSTANCE = null;

	private Conector_cuarentena(){ 
		
	}
	
	
	private synchronized static void crearInstancia() {
		if(INSTANCE == null) {
			INSTANCE = new Conector_cuarentena();
			crearConexion();
		}
	}
	
	public static Conector_cuarentena getInstancia() {
		if (INSTANCE == null ) {
			crearInstancia(); 
		}
		return INSTANCE;	
	}


	private static void crearConexion() {
		String host = "127.0.0.1";
		String user = "Griscel Lazo";
		String password = "Contrasena1";
		String dataBase = "cuarentena";
		try {
			// Importando la libreria de conexion de mysql
			Class.forName("com.mysql.jdbc.Driver");
			// Url de conexion
			String urlConexion = "jdbc:mysql://"+host+"/"+dataBase+"?user="+user+"&password="+password;
			con = DriverManager.getConnection(urlConexion);
			System.out.println("Lo lograste!!");
			
		}catch (Exception e) {
			System.out.println("Error al conectar a la base de datos");
		}
	}
	
	public ArrayList<String> getPaciente() throws SQLException {
		ArrayList<String> listaPaciente = new ArrayList<String>(); 
		PreparedStatement ps = con.prepareStatement("Select * from persona");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			listaPaciente.add(rs.getString("Nombre de Pacientes"));
		}
		rs.close();
		return listaPaciente;
	}
	public ArrayList<String> getConsultorioYDoctores() throws SQLException {
		ArrayList<String> listaConsultorioYDoctores = new ArrayList<String>(); 
		PreparedStatement ps = con.prepareStatement("select persona.nombres, persona.apellidos,consultorio.nro,consultorio.piso from persona inner join doctor on persona.CI=doctor.personaID inner join consultorio on doctor.consultorioID=consultorio.ID;");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			listaConsultorioYDoctores.add(rs.getString("Nombre Doctores"));
			listaConsultorioYDoctores.add(rs.getString(" Numero Consultorio"));
		}
		rs.close();
		return listaConsultorioYDoctores;
	}
}
