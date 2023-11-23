
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import modelo.Alumno;


public class AlumnoIMPDAO implements AlumnoDAO {

    Connection conexion;
    PreparedStatement stInsertar;
    PreparedStatement stConsultar;
    PreparedStatement stActualizar;
    PreparedStatement stEliminar;
   
    
    public AlumnoIMPDAO(){
          
       try{
               
           Class.forName("com.mysql.cj.jdbc.Driver");
       
       }catch(ClassNotFoundException ex){
           System.out.println("Error al cargar el Driver");
           System.out.println(ex.getMessage());
       }
     
    }
 
       
    
    @Override
    public void abrirConexion() {
        try { 
            conexion = DriverManager.getConnection(
                 "jdbc:mysql://127.0.0.1/idsescuela?serverTimezone=GMT-5",
                 "root",
                 "");
            
            
             stInsertar = conexion.prepareStatement(
                    "INSERT INTO Alumno VALUES (?,?,?,?,?,?,?,?,?)");
            
             stConsultar = conexion.prepareStatement(
                    "SELECT * FROM Alumno WHERE codigo=?");
             
             stActualizar = conexion.prepareStatement(
                            "UPDATE Alumno set calMate=?, calFisica=?,"
                                    + " calProgra=?, promedio=? WHERE"
                                    + " codigo=?");
           
             stEliminar = conexion.prepareStatement(
                            "DELETE FROM Alumno WHERE codigo=?");
             
             
        }catch(SQLException ex) {
            System.out.println("Error, no se logró la conexión con la BD");
            System.out.println(ex.getMessage());
       } 
    }
    
    
    @Override
    public void cerrarConexion() {
        try{
            conexion.close();
        }catch(SQLException ex) {
            System.out.println("Error, no se logró cerrar la conexión con la BD");
            System.out.println(ex.getMessage());
       }
    }

    
    
    
    
    @Override
    public void insertarAlumno(Alumno objAl) {
    
        try{
            stInsertar.setString(1, objAl.getNombre());
            stInsertar.setInt(2, objAl.getEdad());
            stInsertar.setString(3, objAl.getEmail());
            stInsertar.setString(4, objAl.getCodigo());
            stInsertar.setString(5, objAl.getCarrera());
            stInsertar.setDouble(6, objAl.getCalMate());
            stInsertar.setDouble(7, objAl.getCalFisica());
            stInsertar.setDouble(8, objAl.getCalProgra());
            stInsertar.setDouble(9, objAl.getPromedio());
            
            stInsertar.executeUpdate();
            
        }catch(SQLException ex) {
            System.out.println("Error al registrar un Alumno en la BD");    
            System.out.println(ex.getMessage());
        }
        
        
    }
  
   
    @Override
    public Alumno consultarAlumno(String codigo) {
    
        Alumno objAl = null;
        ResultSet rs = null;
        
        try{
            stConsultar.setString(1, codigo);
            rs = stConsultar.executeQuery();
            
            if( rs.next() ){
                objAl = new Alumno();
                objAl.setNombre(  rs.getString("nombre") );
                objAl.setEdad(rs.getInt("edad"));
                objAl.setCodigo( rs.getString("codigo"));
                objAl.setEmail( rs.getString("email"));
                objAl.setCarrera( rs.getString("carrera"));
                objAl.setCalMate( rs.getDouble("calMate"));
                objAl.setCalFisica( rs.getDouble("calFisica"));
                objAl.setCalProgra( rs.getDouble("calProgra"));
                objAl.setPromedio( rs.getDouble("promedio"));
             }
            
            
        }catch(SQLException ex) {
            System.out.println("Errro al Consultar un Alumno en la BD");
            System.out.println(ex.getMessage());
        }
        return objAl;
    }
    
    
    
    

    @Override
    public void eliminarAlumno(String codigo) { 
       
       try{ 
        
           stEliminar.setString(1, codigo);
           stEliminar.executeUpdate();
       }catch(SQLException ex){
           System.out.println("Error al Eliminar un Alumno");
           System.out.println(ex.getMessage());
       }
             
    }

    @Override
    public void actualizarAlumno(Alumno objAl) {
         
        try{
            stActualizar.setDouble(1, objAl.getCalMate());
            stActualizar.setDouble(2, objAl.getCalFisica());
            stActualizar.setDouble(3, objAl.getCalProgra());
            stActualizar.setDouble(4, objAl.getPromedio());
            stActualizar.setString(5, objAl.getCodigo());
            stActualizar.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println("Error al Actualizar un Alumno en la BD");
            System.out.println(ex.getMessage());
        }
       
    }
   
}
