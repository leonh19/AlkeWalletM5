package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexion.DBConexion;
import modelo.Usuario;

/**
 * **UsuarioDAOImpl** es la implementación concreta de la interfaz UsuarioDAO.
 * Proporciona la lógica para interactuar con la base de datos y realizar las
 * operaciones CRUD sobre los usuarios.
 */
public class UsuarioDAOImpl implements UsuarioDAO {

	private String sql = "";
	private Connection con = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;
	private Usuario usuario = null;

	/**
	 * Guarda un nuevo usuario en la base de datos.
	 *
	 * @param usuario El objeto Usuario que representa al usuario a guardar.
	 * @return Un entero mayor a 0 si el usuario se guarda exitosamente, 0 en caso
	 *         contrario.
	 */

	@Override
	public int guardar(Usuario usuario) {
		int row = 0;

		sql = "insert into usuarios(nombre,apellido,correo,contrasena,saldo) values (?,?,?,?,?)";

		// obtenemos la conexion
		con = DBConexion.getConexion();
		try {
			// vamos a indicar nuestra sentencia sql
			pstm = con.prepareStatement(sql);
			// setear los parametros
			pstm.setString(1, usuario.getNombre());
			pstm.setString(2, usuario.getApellido());
			pstm.setString(3, usuario.getCorreo());
			pstm.setString(4, usuario.getContrasena());
			pstm.setDouble(5, usuario.getSaldo());
			// le indicamos que ejecute la query en la base de datos
			row = pstm.executeUpdate();
			pstm.close();
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return row;
	}

	/**
	 * Obtiene una lista con todos los usuarios de la base de datos. (Uso general no
	 * recomendado en producción)
	 *
	 * @return Una lista de objetos Usuario.
	 */

	@Override
	public List<Usuario> obtenerTodos() {
		List<Usuario> lista = new ArrayList<>();
		Statement stm;
		sql = "select * from usuarios";
		con = DBConexion.getConexion();
		try {
			stm = con.createStatement();
			// resultset nos trae los datos (se utiliza con select)
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setCorreo(rs.getString("correo"));
				usuario.setContrasena(rs.getString("contrasena"));
				usuario.setSaldo(rs.getDouble("saldo"));
				lista.add(usuario);
			}
			stm.close();
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return lista;
	}

	/**
	 * Realiza un depósito en la cuenta del usuario identificado por su ID.
	 *
	 * @param monto     El monto a depositar.
	 * @param usuarioID El identificador del usuario.
	 * @return Un entero mayor a 0 si el depósito se realiza exitosamente, 0 en caso
	 *         contrario.
	 */
	@Override
	public int depositar(Double monto, int usuarioID) {
		int row = 0;
		sql = "UPDATE usuarios SET saldo = saldo + ? WHERE id = ?";
		con = DBConexion.getConexion();

		try {
			// statement -> consultas estaticas
			// preparedstatement -> consulta dinamica, le debemos pasar los parametros
			// estos son representados con un ?
			PreparedStatement pstm = con.prepareStatement(sql);
			// setear parametros en la query
			pstm.setDouble(1, monto);
			pstm.setInt(2, usuarioID);
			row = pstm.executeUpdate();
			pstm.close();
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return row;
	}

	/**
	 * Realiza un retiro en la cuenta del usuario identificado por su ID.
	 *
	 * @param monto     El monto a retirar.
	 * @param usuarioID El identificador del usuario.
	 * @return Un entero mayor a 0 si el retiro se realiza exitosamente, 0 en caso
	 *         contrario.
	 */
	@Override
	public int retirar(Double monto, int usuarioID) {
		int row = 0;
		sql = "UPDATE usuarios SET saldo = saldo - ? WHERE id = ?";
		con = DBConexion.getConexion();

		try {
			// statement -> consultas estaticas
			// preparedstatement -> consulta dinamica, le debemos pasar los parametros
			// estos son representados con un ?
			PreparedStatement pstm = con.prepareStatement(sql);
			// setear parametros en la query
			pstm.setDouble(1, monto);
			pstm.setInt(2, usuarioID);
			row = pstm.executeUpdate();
			pstm.close();
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return row;
	}

	/**
	 * Obtenemos un usuario mediante el correo y contraseña
	 */
	@Override
	public Usuario obtenerUsuario(String correo, String contrasena) {
		sql = "select * from usuarios where correo = ? and contrasena = ?";
		con = DBConexion.getConexion();

		try {
			// le pasamos nuestra sentencia sql
			pstm = con.prepareStatement(sql);
			// seteamos parametros
			pstm.setString(1, correo);
			pstm.setString(2, contrasena);

			// guardamos datos y ejecutmos query
			rs = pstm.executeQuery();

			// si nos trae al usuario
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setCorreo(rs.getString("correo"));
				usuario.setContrasena(rs.getString("contrasena"));
				usuario.setSaldo(rs.getDouble("saldo"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return usuario;
	}

	/**
	 * Obtenemos a un usuario mediante su identificador ID
	 */
	@Override
	public Usuario obtenerUsuarioPorID(int usuarioID) {
		sql = "select * from usuarios where id = ?";
		con = DBConexion.getConexion();

		try {
			// le pasamos nuestra sentencia sql
			pstm = con.prepareStatement(sql);
			// seteamos parametros
			pstm.setInt(1, usuarioID);

			// guardamos datos y ejecutmos query
			rs = pstm.executeQuery();

			// si nos trae al usuario
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setCorreo(rs.getString("correo"));
				usuario.setContrasena(rs.getString("contrasena"));
				usuario.setSaldo(rs.getDouble("saldo"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return usuario;
	}

}
