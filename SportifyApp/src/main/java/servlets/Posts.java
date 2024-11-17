/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import control.Fachada;
import dominio.Post;
import dominio.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author carlo
 */
@WebServlet(name = "posts", urlPatterns = {"/posts"})
public class Posts extends HttpServlet {

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

//        Fachada fachada = new Fachada();
//        try {
//            List<Post> posts = fachada.obtenerTodosLosPosts();
//            request.setAttribute("posts", posts);
//            request.getRequestDispatcher("/WEB-INF/posts.jsp").forward(request, response);
//            
//        } catch(Exception e) {
//            System.out.println("error while fetching postssss");
//            e.printStackTrace();
//        }
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

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String sport = request.getParameter("sport");
        
        Post post = new Post();
        post.setTitulo(title);
        post.setContenido(content);
        Usuario author = (Usuario) request.getSession().getAttribute("usuario");
        Calendar createdAt = Calendar.getInstance();
        post.setFechaHoraCreacion(createdAt);

        post.setAutor(author);
        
        Fachada fachada = new Fachada();
        
        try {
            fachada.agregarPost(post);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "errorocurrido creando post");
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
