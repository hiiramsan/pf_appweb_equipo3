/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import DAOs.PostDAO;
import control.Fachada;
import control.IFachada;
import dominio.Post;
import dominio.Usuario;

import java.io.File;
import java.io.FileOutputStream;

import dominio.Usuario.Rol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import utils.CloudinaryConfig;

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
        Fachada fachada = new Fachada();
        try {
            List<Post> posts = fachada.obtenerTodosLosPosts();
            request.setAttribute("posts", posts);
            request.getRequestDispatcher("/WEB-INF/posts.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println("error while fetching postssss");
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

        Part filePart = request.getPart("image");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            File tempFile = File.createTempFile("upload-", fileName);
            try (InputStream fileContent = filePart.getInputStream(); FileOutputStream outputStream = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileContent.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
            try {
                Cloudinary cloudinary = CloudinaryConfig.getCloudinaryInstance();
                Map uploadResult = cloudinary.uploader().upload(tempFile,
                        ObjectUtils.asMap("public_id", "post_images/" + fileName));
                String imageUrl = (String) uploadResult.get("url");
                post.setFoto(imageUrl);
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("problemas con la imagen ");
                return;
            }
        }

        Post.Categoria categoria = Post.Categoria.valueOf(sport);
        post.setCategoria(categoria);

        IFachada fachada = new Fachada();

        try {
            fachada.agregarPost(post);
            response.sendRedirect(request.getContextPath() + "/home");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while creating the post");
            request.getRequestDispatcher("/create-Post.jsp").forward(request, response);
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
