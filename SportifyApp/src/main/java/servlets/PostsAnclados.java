/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.google.gson.Gson;
import control.Fachada;
import dominio.Comentario;
import dominio.Post;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cloudinary.json.JSONObject;

/**
 *
 * @author marlon
 */
public class PostsAnclados extends HttpServlet {

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
        System.out.println("ESTOY DENTRO DEL SERVLET POSTS ANCLADOS (METODO GET)");

        Fachada fachada = new Fachada();

        // obtener todos los posts
        List<Post> posts = fachada.obtenerTodosLosPosts();

        // guardar posts anclados
        List<Post> postsAnclados = new ArrayList<>();

        for (Post post : posts) {
            if (post.isIsAnclado()) {
                postsAnclados.add(post);
            }
        }

        Gson gson = new Gson();
        String postsAncladosJson = gson.toJson(postsAnclados);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Forward to the JSP
        PrintWriter out = response.getWriter();
        out.print(postsAncladosJson);
        out.flush();
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
        
        System.out.println("ESTOY EN EL SERVLET DE POSTS ANCLADOS EN EL METODO POST");
        
        Fachada fachada = new Fachada();

        // Leer el JSON del cuerpo de la solicitud
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        
        // Convertir el JSON recibido a un objeto
        String json = sb.toString();
        JSONObject jsonObject = new JSONObject(json);
        
        // Extraer el id del post que se está por anclar
        int idPost = jsonObject.getInt("postPorAnclarId");

        // Procesar los datos (ejemplo: imprimir en consola)
        System.out.println("Id: " + idPost);
        
        // Actualizar el post a anclado o desanclado
        Post postPorActualizar = fachada.consultarPost(idPost);
        boolean estadoActualPost = postPorActualizar.isIsAnclado();
        postPorActualizar.setIsAnclado(!estadoActualPost);
        fachada.actualizarPost(postPorActualizar);
        System.out.println("Post Actualizado con éxito!");
        

        // Responder con un mensaje (opcional)
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "success");
        jsonResponse.put("message", "Datos recibidos correctamente");
        out.print(jsonResponse.toString());
        out.flush();

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
