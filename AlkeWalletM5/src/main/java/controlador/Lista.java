package controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;

/**
 * Este Servlet maneja las solicitudes a la URL "/lista" y realiza las siguientes acciones:
 * 1. Recupera una lista de todos los objetos Usuario del UsuarioDAO.
 * 2. Almacena la lista recuperada en el atributo de solicitud llamado "lista".
 * 3. Reenvía la solicitud a la página "lista.jsp".
 **/

@WebServlet("/lista")
public class Lista extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
	RequestDispatcher dispatcher = null;

	 /**
     * Maneja las solicitudes GET a la URL "/lista".
     *
     * @param request  El objeto HttpServletRequest que contiene los datos de la solicitud.
     * @param response El objeto HttpServletResponse para enviar la respuesta.
     * @throws ServletException Si ocurre un error al procesar la solicitud.
     * @throws IOException      Si ocurre un error al enviar la respuesta.
     */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recupera una lista de todos los objetos Usuario del DAO
		List<Usuario> lista = new ArrayList<>();
		lista = usuarioDAO.obtenerTodos();
		// Almacena la lista recuperada en el atributo de solicitud llamado "lista"
		request.setAttribute("lista", lista);
		// Reenvía la solicitud a la página "lista.jsp"
		dispatcher = request.getRequestDispatcher("lista.jsp");
		dispatcher.forward(request, response);
		}



}
