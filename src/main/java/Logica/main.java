package Logica;

import Persistencia.ConexionBD;

public class main {
    public static void main (String [] args){
        ConexionBD b = new ConexionBD ();
        b.cerrarConexion();
        
        Paciente p = new Paciente ("Cedula","1012359897","Liceth Marcela" ,"Castaño Rodriguez","Femenino","3133725396","Calle 54F No.94-21","Paciente");
        p.guardarPersona();
        System.out.println(p);
    }
}

