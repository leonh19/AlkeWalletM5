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
 * Este Servlet maneja las solicitudes a la URL "/home" y realiza las siguientes acciones:
 * 1. Verifica si existe una sesión de usuario.
 * 2. Si no existe sesión, redirige a la página de login.
 * 3. Si existe una sesión, recupera el ID de usuario de la sesión.
 * 4. Si no se encuentra ningún ID de usuario, redirige a la página de login.
 * 5. Si se encuentra un ID de usuario, recupera el objeto Usuario correspondiente del DAO.
 * 6. Almacena el objeto Usuario recuperado en la sesión.
 * 7. Reenvía la solicitud a la página "home.jsp".
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
	
	/**
     * Maneja las solicitudes GET a la URL "/home".
     *
     * @param request  El objeto HttpServletRequest que contiene los datos de la solicitud.
     * @param response El objeto HttpServletResponse para enviar la respuesta.
     * @throws ServletException Si ocurre un error al procesar la solicitud.
     * @throws IOException      Si ocurre un error al enviar la respuesta.
     */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// nos traemos al usuario guardado en la sesion
		HttpSession session = request.getSession(false);
		// colocamos false para que no nos cree una si es que no existe
		// si no existe sesion
		if(session == null) {
			//que nos redireccione al login
			response.sendRedirect("login");
		} else {
			// nos traemos el id del usuario
			Object id = session.getAttribute("id");
			
			// si no existe id
			if(id == null) {
				//que nos redireccione al login
				response.sendRedirect("login");
			} else {
				// y si es que existe al home		
				int id2 = (int) session.getAttribute("id");
				Usuario usuario = usuarioDAO.obtenerUsuarioPorID(id2);
				session.setAttribute("usuario", usuario);
				dispatcher = request.getRequestDispatcher("home.jsp");
				dispatcher.forward(request, response);
			}
		}
		
	}


	
       

}
