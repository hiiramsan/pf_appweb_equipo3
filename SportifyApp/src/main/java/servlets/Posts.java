/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import control.Fachada;
import control.IFachada;
import dominio.Post;
import dominio.Usuario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author carlo
 */

@MultipartConfig
@WebServlet(name = "posts", urlPatterns = {"/posts"})
public class Posts extends HttpServlet {

    private static final String UPLOAD_DIR = "post_images";

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
        System.out.println("INSIDE GET POSTS");
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

        request.setCharacterEncoding("UTF-8");

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String sport = request.getParameter("sport");

        if (sport != null && !sport.isEmpty()) {
            sport = sport.toUpperCase();
        } else {
            System.out.println("Sport is not selected");
        }
        Post post = new Post();
        post.setTitulo(title);
        post.setContenido(content);
        Usuario author = (Usuario) request.getSession().getAttribute("usuario");
        Calendar createdAt = Calendar.getInstance();
        post.setFechaHoraCreacion(createdAt);
        post.setAutor(author);

        // subida de iamgenn
        Part filePart = request.getPart("image");
        String fileName = null;
        if (filePart != null && filePart.getSize() > 0) {
            fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            File file = new File(uploadDir, fileName);
            try (InputStream fileContent = filePart.getInputStream(); OutputStream outStream = new FileOutputStream(file)) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileContent.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
            }
            post.setFoto(fileName);
        }

        Post.Categoria categoria = Post.Categoria.valueOf(sport);

        post.setCategoria(categoria);

        IFachada fachada = new Fachada();

        try {
            fachada.agregarPost(post);
            response.sendRedirect(request.getContextPath() + "/home");
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
