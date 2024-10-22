/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package factory;

import interfaces.IComentarioDAO;
import interfaces.IEstadoDAO;
import interfaces.IMunicipioDAO;
import interfaces.IPostDAO;
import interfaces.IUsuarioDAO;

/**
 *
 * @author jl4ma
 */
public interface IFactoryObjects {
    
    public IUsuarioDAO objectoUsuario();
    public IPostDAO obejctoPost();
    public IComentarioDAO objectoComentario();
    public IEstadoDAO objectoEstado();
    public IMunicipioDAO objectoMunicipio();
    
}
