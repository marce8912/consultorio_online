package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {
    //Variables de configuración de la BD
    private String BD_driver = "";
    private String url = "";
    private String bd = "";
    private String host = "";
    private String username = "";
    private String password = "";
    
    //Conexión 
    public Connection conn = null;
    //Ejecutar las sentencias de la BD
    private Statement stmt = null;
    //Preparar y conectar con la conexión y con sql
    private PreparedStatement pstmt = null;
    //Guarga una tabla, filas y columnas
    private ResultSet rs = null;
    
    //Constructor
    public ConexionBD(){
        //Está en internet
        BD_driver = "con.mysql.jdbc.Driver";
        //Puerto al final
        host = "localhost:3306";
        bd = "consultorio_online";
        //jdbc:mysql://...
        url = "jdbc:mysql://"+host +"/"+bd;
        username = "root";
        password = "";
        
        try{
            Class.forName(BD_driver);
        }catch(ClassNotFoundException ex){
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null,ex);    
        }
        try{
            conn = DriverManager.getConnection(url, username, password);
            conn.setTransactionIsolation(8);
        }catch (SQLException ex){
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null,ex);    
        
        }
    }
    
    public Connection getConnection(){
        return conn;
    } 
    public void closeConnection(){
        if (conn !=null){
            try{
                conn.close();
            }catch (SQLException ex){
                Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null,ex);
            }
        }
    }
    
    //Insertar ----------- SELECT
    public boolean insertarBD(String sentencia){
        try{
                stmt = conn.createStatement();
                stmt.execute(sentencia);
        }catch (SQLException | RuntimeException sqlex){
                System.out.println("Error rutina: "+sqlex);
                return false;
        }
        return true;
    }
    
    //consultar -------------
    public ResultSet consultarBD(String sentencia){
        try{
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sentencia);
        }catch(SQLException sqlex){
            System.out.println("Error en la consulta");
        } 
        return rs;
    }
    
    //Eliminar --------------
    public boolean borrarBD(String sentencia){
        try{
            stmt = conn.createStatement();
            stmt.execute(sentencia);
        }catch(SQLException | RuntimeException sqlex){
            System.out.println("Error al eliminar: "+sqlex);
            return false;
        }
        return true;
    }
    
    //Actualizar -----------
    public boolean acualizarBD(String sentencia){
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(sentencia);
            
        }catch(SQLException | RuntimeException sqlex){
            System.out.println("Error al actualizar: "+sqlex);
            return false;
        }
        return true;
    }
    
    //-------------------------------------------
    
    public boolean setAutoCommitBD (boolean parametro){
        try {
        conn.setAutoCommit (parametro);
        }catch (SQLException sqlex){
            System.out.println("Error al configurar el Autocommit: "+sqlex.getMessage());
            return false;
        }
        return true;
    }
   
    
    public boolean commitBD (){
         try {
            conn.commit ();
        }catch (SQLException sqlex){
            System.out.println("Error al hacer el commit: "+sqlex.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean rollbackBD (){
         try {
            conn.rollback ();
            return true;
        }catch (SQLException sqlex){
            System.out.println("Error al hacer rollback "+sqlex.getMessage());
            return false;
        }   
    }    
}
