package dao;

import java.util.List;

import modelo.Usuario;

/**
 * **UsuarioDAO** define los métodos para interactuar con los datos de usuarios
 * en el sistema. Un DAO (Data Access Object) encapsula el acceso a la fuente de
 * datos y proporciona métodos para realizar operaciones CRUD (Crear, Leer,
 * Actualizar, Eliminar) sobre los usuarios.
 */

public interface UsuarioDAO {

	/**
	 * Guarda un nuevo usuario en el sistema.
	 *
	 * @param usuario El objeto Usuario que representa al usuario a guardar.
	 * @return Un entero mayor a 0 si el usuario se guarda exitosamente, 0 en caso
	 *         contrario.
	 */
	public int guardar(Usuario usuario);

	/**
	 * Realiza un depósito en la cuenta del usuario identificado por su ID.
	 *
	 * @param monto     El monto a depositar.
	 * @param usuarioID El identificador del usuario.
	 * @return Un entero mayor a 0 si el depósito se realiza exitosamente, 0 en caso
	 *         contrario.
	 */
	public int depositar(Double monto, int usuarioID);

	/**
	 * Realiza un retiro en la cuenta del usuario identificado por su ID.
	 *
	 * @param monto     El monto a retirar.
	 * @param usuarioID El identificador del usuario.
	 * @return Un entero mayor a 0 si el retiro se realiza exitosamente, 0 en caso
	 *         contrario.
	 */
	public int retirar(Double monto, int usuarioID);

	/**
	 * Obtiene el usuario en base a su correo electrónico y contraseña para el
	 * inicio de sesión.
	 *
	 * @param correo     El correo electrónico del usuario.
	 * @param contrasena La contraseña del usuario.
	 * @return Un objeto Usuario si el correo y contraseña son correctos, null en
	 *         caso contrario.
	 */
	public Usuario obtenerUsuario(String correo, String contrasena);

	/**
	 * Obtiene el usuario en base a su identificador (ID).
	 *
	 * @param usuarioID El identificador del usuario.
	 * @return Un objeto Usuario si el usuario existe, null en caso contrario.
	 */
	public Usuario obtenerUsuarioPorID(int usuarioID);

	/**
	 * Obtiene una lista con todos los usuarios del sistema. (Proceso no
	 * recomendado en producción)
	 *
	 * @return Una lista de objetos Usuario.
	 */
	public List<Usuario> obtenerTodos();

}
