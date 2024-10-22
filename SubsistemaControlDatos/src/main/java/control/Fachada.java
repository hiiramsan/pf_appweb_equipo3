/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import factory.FactoryObjects;
import factory.IFactoryObjects;

/**
 *
 * @author jl4ma
 */
public class Fachada implements IFachada{
    IFactoryObjects factory;
    
    public Fachada(){
        factory = new FactoryObjects();
    }
    
    
    
}
