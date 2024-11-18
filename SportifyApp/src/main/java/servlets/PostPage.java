package servlets;
import control.Fachada;
import dominio.Post;
import dominio.Comentario;
import dominio.Usuario;
import java.io.IOException;
import java.util.List;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PostPage", urlPatterns = {"/postpage"})
public class PostPage extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get post ID from request parameter
            int postId = Integer.parseInt(request.getParameter("id"));
            System.out.println("retrieving post: " + postId);
            Fachada fachada = new Fachada();
            
            // Get post details
            Post post = fachada.consultarPost(postId);
            if (post == null) {
                response.sendRedirect(request.getContextPath() + "/views/NotFound.jsp");
                return;
            }
            System.out.println("post" + post);
            // Get comments for this post
            List<Comentario> comentarios = fachada.obtenerTodosLosComentariosDeUnPost(postId);
            System.out.println("comentarios: " + comentarios);
            // Set attributes for the JSP
            request.setAttribute("post", post);
            request.setAttribute("comentarios", comentarios);
            
            // Forward to the JSP
            request.getRequestDispatcher("/views/post-page.jsp").forward(request, response);
            
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/views/NotFound.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading post");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get the current user from session
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            System.out.println("inside postpage POST: " + usuario);
            if (usuario == null) {
                response.sendRedirect(request.getContextPath() + "/views/login.jsp");
                return;
            }
            
            // Get post ID and comment content from request
            int postId = Integer.parseInt(request.getParameter("postId"));
            String contenido = request.getParameter("comentario");
            System.out.println("contenido:  " + contenido + '.' + postId);
            if (contenido != null && !contenido.trim().isEmpty()) {
                Fachada fachada = new Fachada();
                
                // Create new comment
                Comentario comentario = new Comentario();
                comentario.setContenido(contenido);
                comentario.setUsuario(usuario);
                comentario.setComentado(postId);
                
                // Set current date and time
                Calendar fechaHora = Calendar.getInstance();
                comentario.setFechaHora(fechaHora);
                
                // Save comment
                fachada.agregarComentario(comentario);
            }
            
            // Redirect back to the post page
            response.sendRedirect(request.getContextPath() + "/postpage?id=" + postId);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving comment");
        }
    }
}