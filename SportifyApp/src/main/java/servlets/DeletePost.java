/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import DAOs.PostDAO;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import control.Fachada;
import dominio.Post;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;

/**
 *
 * @author OMEN
 */
@WebServlet(name = "deletePost", urlPatterns = {"/deletePost"})
public class DeletePost extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet deletePost</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet deletePost at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            BufferedReader reader = request.getReader();
            Gson gson = new Gson();
            Post post = gson.fromJson(reader, Post.class);

            if (post == null || post.getIdPost() == 0) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"status\":\"error\", \"message\":\"Datos del post no válidos.\"}");
                return;
            }

            PostDAO postDAO = new PostDAO();
            postDAO.eliminarPost(post);

            response.setStatus(HttpServletResponse.SC_OK);
            out.print("{\"status\":\"success\", \"message\":\"Post eliminado correctamente.\"}");

        } catch (JsonSyntaxException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"status\":\"error\", \"message\":\"Formato de JSON inválido.\"}");
        } catch (RuntimeException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"status\":\"error\", \"message\":\"Error al eliminar el post.\"}");
            e.printStackTrace();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"status\":\"error\", \"message\":\"Ocurrió un error inesperado.\"}");
            e.printStackTrace();
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
