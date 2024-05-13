package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Esta clase LogoutServlet maneja las solicitudes de cierre de sesión enviadas
 * a la URL "/logout". Realiza las siguientes acciones: 1. Recupera la sesión
 * del usuario usando request.getSession(). 2. Si existe una sesión, la invalida
 * usando session.invalidate(). Esto elimina todos los atributos de sesión
 * asociados con el usuario. 3. Redirecciona al usuario a la página de "login"
 * usando response.sendRedirect("login").
 */

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Maneja las solicitudes GET a la URL "/logout".
	 * 
	 * @param request  El objeto HttpServletRequest que contiene los datos de la
	 *                 solicitud.
	 * @param response El objeto HttpServletResponse para enviar la respuesta.
	 * @throws ServletException Si ocurre un error al procesar la solicitud.
	 * @throws IOException      Si ocurre un error al enviar la respuesta.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("login");

	}

}
