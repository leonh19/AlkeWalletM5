package controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;

import java.io.IOException;

import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;

/**
 * **RegistrarServlet** maneja las solicitudes a la URL "/registrarse". Se
 * utiliza para procesar el registro de nuevos usuarios en el sistema.
 */

@WebServlet("/registrarse")
public class RegistrarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// con response.sendredirect nos hace redireccionar de forma visual al usuario
		// si lo enviamos con la ruta /registrarse se cambiará a /registrarse.jsp
		// response.sendRedirect("index.jsp");

		// con requestdispatcher se hace una redireccion interna
		// si lo enviamos con la ruta /registarse seguira mostrando /registrase pero con
		// la vista cambiada
		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario usuario = null;
		UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
		RequestDispatcher dispatcher = null;

		// obtener parametros
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String correo = request.getParameter("correo");
		String contrasena = request.getParameter("contrasena");
		int operacion;

		try {
			usuario = new Usuario(nombre, apellido, correo, contrasena, 0.0);
			operacion = usuarioDAO.guardar(usuario);
			// Preparamos el mensaje de estado según el resultado (éxito o fracaso)
			if (operacion > 0) {
				request.setAttribute("status", "success");
				// Redireccionamos internamente a la vista "index.jsp" con el mensaje de estado
				dispatcher = request.getRequestDispatcher("index.jsp");
			} else {
				request.setAttribute("status", "failed");
			}
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
