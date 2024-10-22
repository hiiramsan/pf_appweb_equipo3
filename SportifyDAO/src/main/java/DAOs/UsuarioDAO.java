/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import dominio.Estado;
import dominio.Usuario;
import interfaces.IUsuarioDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jl4ma
 */
public class UsuarioDAO implements IUsuarioDAO{
    
    
    @Override
    public void agregarUsuario(Usuario usuario){
        
    }
    
    @Override
    public void actualizarUsuario(Usuario usuario){
        
    }
    
    @Override
    public void eliminarUsuario(Usuario usuario){
        
    }
    
    @Override
    public Usuario consultarUsuario(int id){
        
        
        return new Usuario();
    }
    
    @Override
    public void agregarEstado(Estado estado, Usuario usuario){
        
    }
    
    @Override
    public List<Usuario> consultarUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();
        
        
        return usuarios;
    }
}
