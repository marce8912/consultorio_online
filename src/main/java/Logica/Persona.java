package Logica;

import java.util.*;

public abstract class Persona {
    //Atributos 
    protected String nombre, apellido,genero, telefono, direccion, tipoPersona;
    
    //Constructor

    public Persona(String nombre, String apellido, String genero, String telefono, String direccion, String tipoPersona) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.telefono = telefono;
        this.direccion = direccion;
        this.tipoPersona = tipoPersona;
    }


    //Metodos 
    public abstract String toString();
 
    public abstract boolean guardarPersona();
    
    public abstract boolean borrarPersona ();
    
    public abstract boolean actualizarPersona ();
    
    public abstract List<Persona> listarPersona ();

    public abstract Persona getPersona ();
    
    
    
}

