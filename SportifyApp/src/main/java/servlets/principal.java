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

        try {
            // Paso 1: Recuperar el ID del post desde la solicitud
            int postId = Integer.parseInt(request.getParameter("postId"));

            // Paso 2: Instanciar la fachada
            Fachada fachada = new Fachada();

            // Paso 3: Eliminar el post
            Post post = fachada.consultarPost(postId); // Busca el objeto Post por ID
            if (post != null) {
                fachada.eliminarPost(post); // Elimina el post
                System.out.println("Post eliminado correctamente.");
            } else {
                System.out.println("No se encontró el post con ID: " + postId);
            }

            // Paso 4: Redirigir o actualizar la página
            response.sendRedirect(request.getContextPath() + "/index.jsp"); // Redirige al índice o página deseada
        } catch (Exception e) {
            System.out.println("Ocurrió un error al intentar eliminar el post.");
            e.printStackTrace();
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
