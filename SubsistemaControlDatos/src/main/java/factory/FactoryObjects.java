/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import DAOs.ComentarioDAO;
import DAOs.EstadoDAO;
import DAOs.MunicipioDAO;
import DAOs.PostDAO;
import DAOs.UsuarioDAO;
import interfaces.IComentarioDAO;
import interfaces.IEstadoDAO;
import interfaces.IMunicipioDAO;
import interfaces.IPostDAO;
import interfaces.IUsuarioDAO;

/**
 *
 * @author jl4ma
 */
public class FactoryObjects implements IFactoryObjects{
    
    public FactoryObjects(){
        
    }
    
    @Override
    public IUsuarioDAO objectoUsuario(){
        IUsuarioDAO usuario = new UsuarioDAO();
        return usuario;
    }
    
    @Override
    public IPostDAO obejctoPost(){
        IPostDAO post = new PostDAO();
        return post;
    }
    
    @Override
    public IComentarioDAO objectoComentario(){
        IComentarioDAO comentario = new ComentarioDAO();
        return comentario;
    }
    
    @Override
    public IEstadoDAO objectoEstado(){
        IEstadoDAO estado = new EstadoDAO();
        return estado;
    }
    
    @Override
    public IMunicipioDAO objectoMunicipio(){
        IMunicipioDAO municipio = new MunicipioDAO();
        return municipio;
    }
}
