package controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;

import java.io.IOException;

import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;

/**
 * Este Servlet maneja las solicitudes a la URL "/login" y realiza las siguientes acciones:
 * 
 * **doGet (Solicitudes GET):**
 * 1. Reenvía la solicitud a la página "login.jsp" para mostrar el formulario de inicio de sesión.
 * 
 * **doPost (Solicitudes POST):**
 * 1. Recupera el correo electrónico y la contraseña de los parámetros de la solicitud.
 * 2. Intenta recuperar un objeto Usuario del DAO utilizando el correo electrónico y la contraseña proporcionados.
 * 3. Si se encuentra un objeto Usuario coincidente:
 *     - Crea una nueva HttpSession (o recupera una existente).
 *     - Almacena la ID del usuario en la sesión.
 *     - Redirecciona al usuario a la página "inicio".
 * 4. Si no se encuentra ningún objeto Usuario coincidente:
 *     - Establece un atributo de estado "fallido" en el ámbito de la solicitud.
 *     - Reenvía la solicitud a "login.jsp" para mostrar un mensaje de error.
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;  
	UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
	
	 /**
     * Maneja las solicitudes GET a la URL "/login".
     *
     * @param request  El objeto HttpServletRequest que contiene los datos de la solicitud.
     * @param response El objeto HttpServletResponse para enviar la respuesta.
     * @throws ServletException Si ocurre un error al procesar la solicitud.
     * @throws IOException      Si ocurre un error al enviar la respuesta.
     */
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// con requestdispatcher se hace una redireccion interna
				//si lo enviamos con la ruta /registarse seguira mostrando /registrase pero con la vista cambiada
				dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
	}
	
	/**
     * Maneja las solicitudes POST a la URL "/login".
     *
     * @param request  El objeto HttpServletRequest que contiene los datos de la solicitud.
     * @param response El objeto HttpServletResponse para enviar la respuesta.
     * @throws ServletException Si ocurre un error al procesar la solicitud.
     * @throws IOException      Si ocurre un error al enviar la respuesta.
     */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// traernos los parametros
		String correo = request.getParameter("correo");
		String contrasena = request.getParameter("contrasena");
		
		
		//nos traemos el usuario
		Usuario usuario = usuarioDAO.obtenerUsuario(correo, contrasena);
		
		if(usuario != null) {
			// creamos sesion para el usuario, colocamos true para que se cree una si es que no encuentra una
			HttpSession session = request.getSession(true);
			// nos guardamos el id del usuario
			session.setAttribute("id", usuario.getId());
			response.sendRedirect("home");
		} else {	
			request.setAttribute("status", "failed");
			dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		

}
}