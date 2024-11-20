/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import control.Fachada;
import control.IFachada;
import dominio.Usuario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import utils.CloudinaryConfig;

/**
 *
 * @author carlo
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class UploadProfilePicture extends HttpServlet {

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

        Part filePart = request.getPart("profilePicture");

        if (filePart != null && filePart.getSize() > 0) {
            
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            
            
            File tempFile = File.createTempFile("upload-", fileName);
            try (InputStream inputStream = filePart.getInputStream();
                 FileOutputStream outputStream = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            
            try {
                Cloudinary cloudinary = CloudinaryConfig.getCloudinaryInstance();
                Map uploadResult = cloudinary.uploader().upload(tempFile, 
                    ObjectUtils.asMap("public_id", "profile_pictures/" + fileName));

                
                String url = (String) uploadResult.get("url");
                Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
                usuario.setUrlAvatar(url);
                IFachada fachada = new Fachada();
                fachada.actualizarUsuario(usuario);
                tempFile.delete();
                request.getSession().setAttribute("usuario", usuario);
                response.sendRedirect(request.getContextPath() + "/home");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "Error  subiendo Cloudinary");
            }
        } else {
            System.out.println("manejar error");
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
