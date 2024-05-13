package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Esta clase sirve para establecer una conexión con la base de datos MySQL y
 * ejecutar consultas básicas.
 */
public class DBConexion {
	
	private static final String URL = "jdbc:mysql://localhost:3306/alkewalletm5";
	private static final String USER = "root";
	private static final String PASSWORD = "12345";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static Connection connection = null;
	
	/**
     * Establece una conexión con la base de datos MySQL utilizando las credenciales proporcionadas.
     *
     * @return Un objeto Connection que representa la conexión a la base de datos, o null si se produce un error.
     * @throws SQLException Si se produce un error al establecer la conexión.
     * @throws ClassNotFoundException Si no se encuentra la clase del controlador JDBC de MySQL.
     */
	
	public static Connection getConexion() {
		try {
			// Registrar driver
			Class.forName(DRIVER);
			 // Establecer conexión con la BD utilizando la URL, el nombre de usuario y la contraseña proporcionados
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Conectado");
		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println("No conectado");
			ex.printStackTrace();
		}
		
		return connection;
	}
	
	public static void main(String[] args) {
		getConexion();
		Statement stmt;
		try {
			// le indicamos que estamos creando una sentencia sql
			stmt = connection.createStatement();
			String consultaSQL = "select * from usuarios";
			// Ejecutar la consulta y almacenar los resultados en un objeto ResultSet
			ResultSet rs = stmt.executeQuery(consultaSQL);
			while(rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String correo = rs.getString("correo");
				String contrasena = rs.getString("contrasena");
				Double saldo = rs.getDouble("saldo");
				System.out.println(id + nombre + apellido + correo + contrasena + saldo);
			}
			// Se  realiza el cierre de conexiones
			rs.close();
			stmt.close();
			connection.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

}
