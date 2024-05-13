package modelo;

/**
 * **Usuario** representa a un usuario del sistema. Almacena información como el
 * nombre, apellido, correo electrónico, contraseña y saldo.
 */
public class Usuario {

	private int id;
	private String nombre;
	private String apellido;
	private String correo;
	private String contrasena;
	private Double saldo;

	/**
	 * Constructor vacío para inicializar un objeto Usuario.
	 */
	public Usuario() {
	}

	/**
	 * Constructor para crear un nuevo registro de usuario.
	 *
	 * @param nombre     El nombre del usuario.
	 * @param apellido   El apellido del usuario.
	 * @param correo     El correo electrónico del usuario.
	 * @param contrasena La contraseña del usuario.
	 * @param saldo      El saldo inicial del usuario.
	 */
	public Usuario(String nombre, String apellido, String correo, String contrasena, Double saldo) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.contrasena = contrasena;
		this.saldo = saldo;
	}

	/**
	 * Constructor para actualizar o buscar un usuario existente.
	 *
	 * @param id         El identificador único del usuario.
	 * @param nombre     El nombre del usuario.
	 * @param apellido   El apellido del usuario.
	 * @param correo     El correo electrónico del usuario.
	 * @param contrasena La contraseña del usuario.
	 * @param saldo      El saldo del usuario.
	 */
	public Usuario(int id, String nombre, String apellido, String correo, String contrasena, Double saldo) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.contrasena = contrasena;
		this.saldo = saldo;
	}

	/**
	 * Obtiene el identificador único del usuario.
	 *
	 * @return El identificador del usuario (int).
	 */
	public int getId() {
		return id;
	}

	/**
	 * Establece el identificador único del usuario.
	 *
	 * @param id El nuevo identificador del usuario (int).
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Obtiene el nombre del usuario.
	 *
	 * @return El nombre del usuario (String).
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del usuario.
	 *
	 * @param nombre El nuevo nombre del usuario (String).
	 */

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el apellido del usuario.
	 *
	 * @return El apellido del usuario (String).
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Establece el apellido del usuario.
	 *
	 * @param apellido El nuevo apellido del usuario (String).
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Obtiene el correo electrónico del usuario.
	 *
	 * @return El correo electrónico del usuario (String).
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * Establece el correo electrónico del usuario.
	 *
	 * @param correo El nuevo correo electrónico del usuario (String).
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * Obtiene la contraseña del usuario.
	 *
	 * @return La contraseña del usuario (String).
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Establece la contraseña del usuario.
	 *
	 * @param contrasena La nueva contraseña del usuario (String).
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * Obtiene el saldo de la cuenta del usuario.
	 *
	 * @return El saldo de la cuenta del usuario (Double).
	 */
	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo
				+ ", contrasena=" + contrasena + ", saldo=" + saldo + "]";
	}

}
