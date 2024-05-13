package controlador;

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
 * **OperacionServlet** maneja las solicitudes POST a la URL "/operacion". Se
 * utiliza para procesar operaciones bancarias de depósito y retiro para un
 * usuario.
 */
@WebServlet("/operacion")
public class OperacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
	Usuario usuario = null;
	int exitoso = 0;

	/**
	 * Maneja las solicitudes POST a la URL "/operacion".
	 *
	 * @param request  El objeto HttpServletRequest que contiene los datos de la
	 *                 solicitud.
	 * @param response El objeto HttpServletResponse para enviar la respuesta.
	 * @throws ServletException Si ocurre un error al procesar la solicitud.
	 * @throws IOException      Si ocurre un error al enviar la respuesta.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Obtenemos los parámetros de la solicitud (operación y monto)
		String operacion = request.getParameter("operacion");
		Double monto = Double.parseDouble(request.getParameter("monto"));

		// 2. Obtenemos la sesión del usuario (si existe)
		HttpSession session = request.getSession(false);
		int id = (int) session.getAttribute("id");

		// 3. Obtenemos el usuario de la sesión
		usuario = (Usuario) session.getAttribute("usuario");

		// 4. Procesamos la operación en base al tipo (depósito o retiro)
		if (operacion.equals("depositar")) {
			exitoso = usuarioDAO.depositar(monto, id);
			// Actualizamos el estado de la operación en la sesión (éxito o fracaso)
			if (exitoso > 0) {
				session.setAttribute("status", "success");
				response.sendRedirect("home");
			} else {
				session.setAttribute("status", "failed");
				response.sendRedirect("home");
			}
		} else if (operacion.equals("retirar")) {
			if (usuario.getSaldo() >= monto) {
				exitoso = usuarioDAO.retirar(monto, id);
				// Actualizamos el estado de la operación en la sesión (éxito o fracaso)
				if (exitoso > 0) {
					session.setAttribute("status", "success");
					response.sendRedirect("home");
				} else {
					session.setAttribute("status", "failed");
					response.sendRedirect("home");
				}
			} else {
				session.setAttribute("status", "failed");
				response.sendRedirect("home");
			}

		}

	}

}
