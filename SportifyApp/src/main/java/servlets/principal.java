/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import control.Fachada;
import dominio.Post;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cloudinary.json.JSONObject;

/**
 *
 * @author carlo
 */
@WebServlet(name = "principal", urlPatterns = {"/principal"})
public class principal extends HttpServlet {

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
        try {
            System.out.println("INSIDE PRINCIPAL GET");
            Fachada fachada = new Fachada();
            List<Post> posts = fachada.obtenerTodosLosPosts();
            for (Post p : posts) {
                System.out.println(p.getTitulo());
            }
            request.setAttribute("posts", posts);
            request.getRequestDispatcher(request.getContextPath() + "/views/index.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("ocurrio un error");
        }
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
        response.setCharacterEncoding("UTF-8");

        try {
            // Obtener el postId del parámetro de la solicitud
            int postId = Integer.parseInt(request.getParameter("postId"));

            Fachada fachada = new Fachada();
            Post post = fachada.consultarPost(postId);
            JSONObject jsonResponse = new JSONObject();

            if (post != null) {
                // Eliminar el post
                fachada.eliminarPost(post);
                jsonResponse.put("status", "success");
                jsonResponse.put("message", "Post eliminado correctamente.");
            } else {
                jsonResponse.put("status", "error");
                jsonResponse.put("message", "Post no encontrado.");
            }

            // Enviar la respuesta JSON al cliente
            response.getWriter().write(jsonResponse.toString());

        } catch (Exception e) {
            e.printStackTrace();
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "Error al eliminar el post.");
            response.getWriter().write(jsonResponse.toString());
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
