
package Logica;

import Persistencia.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Paciente extends Persona {

    //Atributos
    
    private String tipoIdentificacion,identificacion; 
    
    //Constructor//Dar valor a las variables--------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public Paciente(String tipoIdentificacion, String identificacion, String nombre, String apellido, String genero, String telefono, String direccion, String tipoPersona) {
        super(nombre, apellido, genero, telefono, direccion, tipoPersona);
        this.tipoIdentificacion = tipoIdentificacion;
        this.identificacion = identificacion;
    }

     //Funciones getter an setters------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public String gettipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void settipoIdentificacion(String tipo_identificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }
    
    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
 
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
      
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccin(String direccin) {
        this.direccion = direccin;
    }

    public String gettipoPersona() {
        return tipoPersona;
    }

    public void settipoPersona(String correo) {
        this.tipoPersona = correo;
    }
    
    //Métodos CRUD--------------------------------------------------------------------------------------------------------------------------------------------
  
    public boolean guardarPersona() {
        ConexionBD conexion= new ConexionBD ();//sentencia SQL
        String sentencia = "INSERT INTO paciente (identificacion_paciente,"
                + "nombres, apellidos, genero, tipo_identificacion, telefono, direccion)VALUES ('"+this.identificacion+"',"
                + "'"+this.nombre+"','"+this.apellido+"', '"+this.genero+"', '"+this.tipoIdentificacion +"','"+this.telefono+"',"
                + "'"+this.direccion+"')";//Si identificacion es autoincrementable en la base de datos no se coloca aqui.
        
        if (conexion.setAutoCommitBD(false)){
            if (conexion.insertarBD(sentencia)){
                conexion.commitBD();
                conexion.cerrarConexion();
                return true; 
            }else{
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }
        }else{
            conexion.cerrarConexion();
            return false;
        }
    }
    
    public boolean borrarPersona() {
          ConexionBD conexion= new ConexionBD ();
            String sentencia = "DELETE FROM paciente WHERE identificacion_paciente = '"+this.identificacion+"'";
        
          if (conexion.setAutoCommitBD(false)){
            if (conexion.acualizarBD(sentencia)){
                conexion.commitBD();
                conexion.cerrarConexion();
                return true; 
            }else{
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
                }
        }else{
            conexion.cerrarConexion();
            return false;
        }
    }

    public boolean actualizarPersona() {
        ConexionBD conexion= new ConexionBD ();
        String sentencia = "UPDATE paciente SET nombre='"+this.nombre+"',apellido='"+this.apellido+"', genero= '"+this.genero+"',"
                + "tipo_identificacion= '"+this.tipoIdentificacion +"',telefono='"+this.telefono+"', direccion='"+this.direccion+"')";     
        
          if (conexion.setAutoCommitBD(false)){
            if (conexion.acualizarBD(sentencia)){
                conexion.commitBD();
                conexion.cerrarConexion();
                return true; 
            }else{
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
                }
        }else{
            conexion.cerrarConexion();
            return false;
        }
    }

    public List<Persona> listarPersona()  {
         ConexionBD conexion= new ConexionBD ();
        List<Persona> listarPersona= new ArrayList <>();
        String sql = "SELECT * FROM paciente ORDER BY identificacion_paciente asc";
        ResultSet rs= conexion.consultarBD(sql);   
        Paciente p;
        
        try {
            while (rs.next ()){
                p = new Paciente (tipoIdentificacion,identificacion,nombre,apellido,genero, telefono, direccion, tipoPersona);
                p.settipoIdentificacion(rs.getString("tipoIdentificacion"));
                p.setIdentificacion(rs.getString("identificacion"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setGenero(rs.getString("genero"));
                p.setTelefono(rs.getString("telefono"));
                p.setDireccin(rs.getString("direccion"));
                listarPersona.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        conexion.cerrarConexion();
        return listarPersona;
    }

    public Persona getPersona() {
        ConexionBD conexion= new ConexionBD ();
        String sql = "SELECT * FROM paciente where identificacion='" + this.identificacion+"'";
        ResultSet rs= conexion.consultarBD(sql);   
        try {
            if (rs.next()){
                this.identificacion =rs.getString("identificacion");
                this.tipoIdentificacion= rs.getString("tipoIdentificacion");
                this.nombre = rs.getString("nombre");
                this.apellido = rs.getString("apellido");
                this.genero= rs.getString("genero");
                this.telefono =rs.getString("telefono");
                this.direccion=rs.getString("direccion");
                conexion.cerrarConexion();
                return this ;
            }else{
                conexion.cerrarConexion();
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Paciente{" + "tipoIdentificacion=" + tipoIdentificacion + ", identificacion=" + identificacion + '}';
    }
    
    
    

   
    
}
