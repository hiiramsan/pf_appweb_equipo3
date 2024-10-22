/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dominio.Estado;
import dominio.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jl4ma
 */
public interface IUsuarioDAO {
    public void agregarUsuario(Usuario usuario);
    public void actualizarUsuario(Usuario usuario);
    public void eliminarUsuario(Usuario usuario);
    public Usuario consultarUsuario(int id);
    public void agregarEstado(Estado estado, Usuario usuario);
    public List<Usuario> consultarUsuarios();
}
