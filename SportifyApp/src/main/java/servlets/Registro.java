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
import java.util.HashMap;
import java.util.Map;
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

            Map<String, String> errores = new HashMap<>();
            String nombre = validarStrings(request.getParameter("first-name"));
            if (nombre == null || !nombre.matches("[a-zA-Z]+")) {
                errores.put("first-name", "El nombre solo debe contener letras.");
            }

            String apellido = validarStrings(request.getParameter("last-name"));
            if (apellido == null || !apellido.matches("[a-zA-Z]+")) {
                errores.put("last-name", "El apellido solo debe contener letras.");
            }

            String correo = validarStrings(request.getParameter("email"));
            if (correo == null || !correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                errores.put("email", "El correo electrónico no es válido.");
            }

            String contrasenia = validarStrings(request.getParameter("password"));
            String confirmContrasenia = validarStrings(request.getParameter("confirm-password"));
            if (contrasenia == null || contrasenia.length() < 8 || !contrasenia.matches("[a-zA-Z0-9]+")) {
                errores.put("password", "La contraseña debe tener al menos 8 caracteres alfanuméricos.");
            } else if (!contrasenia.equals(confirmContrasenia)) {
                errores.put("confirm-password", "Las contraseñas no coinciden.");
            }

            String telefono = validarStrings(request.getParameter("phone"));
            if (telefono == null || !telefono.matches("\\d+")) {
                errores.put("phone", "El teléfono debe contener solo números.");
            }

            String ciudad = validarStrings(request.getParameter("city"));
            if (ciudad == null || ciudad.trim().isEmpty()) {
                errores.put("city", "La ciudad es obligatoria.");
            }

            String fechaNacimientoStr = validarStrings(request.getParameter("dob"));
            Calendar fechaNacimiento = null;
            try {
                fechaNacimiento = Calendar.getInstance();
                fechaNacimiento.setTime(java.sql.Date.valueOf(fechaNacimientoStr));
            } catch (Exception e) {
                errores.put("dob", "La fecha de nacimiento no es válida.");
            }

            String generoStr = validarStrings(request.getParameter("gender"));
            Usuario.Genero genero = null;
            try {
                genero = Usuario.Genero.valueOf(generoStr.toUpperCase());
            } catch (Exception e) {
                errores.put("gender", "El género seleccionado no es válido.");
            }

//            if (!errores.isEmpty()) {
//                // Si hay errores, vuelve a la página de registro con mensajes
//                request.setAttribute("errores", errores);
//                request.getRequestDispatcher("/views/signup.jsp").forward(request, response);
//                return;
//            }

            // Crear usuario si no hay errores
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
                request.setAttribute("errorGeneral", "Ocurrió un error al registrar el usuario. Inténtalo de nuevo.");
                request.getRequestDispatcher("/views/signup.jsp").forward(request, response);
            }
        }
    
        // evitar xss atack 
        private String validarStrings(String input) {
        if (input == null) {
            return "";
        }
        return input.replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\"", "&quot;")
                .replaceAll("'", "&#x27;")
                .replaceAll("&", "&amp;");
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
