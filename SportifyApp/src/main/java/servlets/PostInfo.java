/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.google.gson.Gson;
import control.Fachada;
import dominio.Comentario;
import dominio.Post;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marlon
 */
public class PostInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("ESTOY DENTRO DEL SERVLET POST INFO");

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

            Gson gson = new Gson();
            String postJson = gson.toJson(post);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Forward to the JSP
            PrintWriter out = response.getWriter();
            out.print(postJson);
            out.flush();

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/views/NotFound.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading post");
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
