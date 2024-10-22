/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dominio.Comentario;
import dominio.Estado;
import dominio.Municipio;
import dominio.Post;
import dominio.Usuario;
import factory.FactoryObjects;
import factory.IFactoryObjects;
import java.util.List;

/**
 *
 * @author jl4ma
 */
public class Fachada implements IFachada{
    IFactoryObjects factory;
    
    public Fachada(){
        factory = new FactoryObjects();
    }

    @Override
    public void agregarComentario(Comentario comentario) {
        factory.objectoComentario().agregarComentario(comentario);
    }

    @Override
    public void agregarComentarioAComentario(Comentario comentario, Comentario comentarioNuevo) {
        factory.objectoComentario().agregarComentarioAComentario(comentario, comentarioNuevo);
    }

    @Override
    public void actualizarComentario(Comentario comentario) {
        factory.objectoComentario().actualizarComentario(comentario);
    }

    @Override
    public void eliminarComentario(Comentario comentario) {
        factory.objectoComentario().eliminarComentario(comentario);
    }

    @Override
    public Comentario consultarComentario(int id) {
            return factory.objectoComentario().consultarComentario(id);
    }

    @Override
    public void agregarEstado(Estado estado) {
            factory.objectoEstado().agregarEstado(estado);
    }

    @Override
    public void agregarMunicipio(Estado estado, Municipio municipio) {
            factory.objectoEstado().agregarMunicipio(estado, municipio);
    }

    @Override
    public void actualizarEstado(Estado estado) {
            factory.objectoEstado().actualizarEstado(estado);
    }

    @Override
    public void eliminarEstado(Estado estado) {
        factory.objectoEstado().eliminarEstado(estado);
    }

    @Override
    public Estado consultarEstado(int id) {
        return factory.objectoEstado().consultarEstado(id);
    }

    @Override
    public void agregarMunicipio(Municipio municipio) {
        factory.objectoMunicipio().agregarMunicipio(municipio);
    }

    @Override
    public void actualizarMunicipio(Municipio municipio) {
        factory.objectoMunicipio().actualizarMunicipio(municipio);
    }

    @Override
    public void eliminarMunicipio(Municipio municipio) {
        factory.objectoMunicipio().eliminarMunicipio(municipio);
    }

    @Override
    public Municipio consultarMunicipio(int id) {
       return factory.objectoMunicipio().consultarMunicipio(id);
    }

    @Override
    public void agregarPost(Post post) {
        factory.obejctoPost().agregarPost(post);
    }

    @Override
    public void actualizarPost(Post post) {
        factory.obejctoPost().actualizarPost(post);
    }

    @Override
    public void eliminarPost(Post post) {
        factory.obejctoPost().eliminarPost(post);
    }

    @Override
    public Post consultarPost(int id) {
        return factory.obejctoPost().consultarPost(id);
    }

    @Override
    public void anclarPost(Post post) {
        factory.obejctoPost().anclarPost(post);
    }

    @Override
    public void agregarUsuario(Usuario usuario) {
        factory.objectoUsuario().agregarUsuario(usuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        factory.objectoUsuario().actualizarUsuario(usuario);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        factory.objectoUsuario().eliminarUsuario(usuario);
    }

    @Override
    public Usuario consultarUsuario(int id) {
        return factory.objectoUsuario().consultarUsuario(id);
    }

    @Override
    public void agregarEstado(Estado estado, Usuario usuario) {
        factory.objectoUsuario().agregarEstado(estado, usuario);
    }

    @Override
    public List<Usuario> consultarUsuarios() {
        return factory.objectoUsuario().consultarUsuarios();
    }
    
    
    
}
