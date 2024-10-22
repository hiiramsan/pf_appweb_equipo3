/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package control;

import dominio.Comentario;
import dominio.Estado;
import dominio.Municipio;
import dominio.Post;
import dominio.Usuario;
import java.util.List;

/**
 *
 * @author jl4ma
 */
public interface IFachada {
    
    
    public void agregarComentario(Comentario comentario);
    public void agregarComentarioAComentario(Comentario comentario, Comentario comentarioNuevo);
    public void actualizarComentario(Comentario comentario);
    public void eliminarComentario(Comentario comentario);
    public Comentario consultarComentario(int id);
     
    public void agregarEstado (Estado estado);
    public void agregarMunicipio(Estado estado, Municipio municipio);
    public void actualizarEstado(Estado estado);
    public void eliminarEstado(Estado estado);
    public Estado consultarEstado(int id);
    
    public void agregarMunicipio(Municipio municipio);
    public void actualizarMunicipio(Municipio municipio);
    public void eliminarMunicipio(Municipio municipio);
    public Municipio consultarMunicipio(int id);
    
    public void agregarPost(Post post);
    public void actualizarPost(Post post);
    public void eliminarPost(Post post);
    public Post consultarPost(int id);
    public void anclarPost(Post post);
    
    public void agregarUsuario(Usuario usuario);
    public void actualizarUsuario(Usuario usuario);
    public void eliminarUsuario(Usuario usuario);
    public Usuario consultarUsuario(int id);
    public void agregarEstado(Estado estado, Usuario usuario);
    public List<Usuario> consultarUsuarios();
}
