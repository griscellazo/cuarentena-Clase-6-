package cuarentena;
import java.sql.SQLException;
import java.util.ArrayList;


public class Main_cuarentena {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Conector_cuarentena instancia = Conector_cuarentena.getInstancia();
		try{
		ArrayList<String>listaPaciente = instancia.getPaciente();
		System.out.println("Nombres de Pacientes");
		
		for (String nombrePaciente:listaPaciente) {
			System.out.println(nombrePaciente);
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		try{
			ArrayList<String>listaConsultorioYDoctores = instancia.getConsultorioYDoctores();
			System.out.println("Consultorio y Nombre de los Doctores");
			System.out.println("Nro // Piso // Nombres // Apellidos");
			
			int i=0;
			for (String ConsultorioYDoctores:listaConsultorioYDoctores) {
				System.out.println(ConsultorioYDoctores+ " // ");
				i++;
				if (i==2) {
					System.out.println();
					i=0;
				}
			}
			
			}catch(SQLException e) {
				e.printStackTrace();
			}

	}

}
