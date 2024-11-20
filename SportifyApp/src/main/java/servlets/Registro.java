/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import control.Fachada;
import control.IFachada;
import dominio.Usuario;
import dominio.Usuario.Rol;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author carlo
 */
@WebServlet(name = "registro", urlPatterns = {"/registro"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 5, // 5 MB
        maxRequestSize = 1024 * 1024 * 10 // 10 MB
)
public class Registro extends HttpServlet {

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

            System.out.println("ESTOY EN REGISTRO");

            String nombre = request.getParameter("first-name");
            String apellido = request.getParameter("last-name");
            String correo = request.getParameter("email");
            String contrasenia = request.getParameter("password");
            String telefono = request.getParameter("phone");
            String ciudad = request.getParameter("city");
            String fechaNacimientoStr = request.getParameter("dob");
            String generoStr = request.getParameter("gender");

            Calendar fechaNacimiento = Calendar.getInstance();
            fechaNacimiento.setTime(java.sql.Date.valueOf(fechaNacimientoStr));
            Usuario.Genero genero = Usuario.Genero.valueOf(generoStr.toUpperCase());

            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setCorreo(correo);
            usuario.setContrasenia(contrasenia);
            usuario.setTelefono(telefono);
            usuario.setCiudad(ciudad);
            usuario.setFechaNacimiento(fechaNacimiento);
            usuario.setGenero(genero);
            usuario.setRol(Rol.NORMAL);

            IFachada fachada = new Fachada();
            try {
                fachada.agregarUsuario(usuario);
                Usuario newUser = fachada.consultarUsuarioPorEmail(correo);
                HttpSession session = request.getSession();
                session.setAttribute("usuario", newUser);
                response.sendRedirect(request.getContextPath()+"/views/uploadProfilePicture.jsp");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
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
