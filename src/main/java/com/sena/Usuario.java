package com.sena;
import java.sql.*;
import java.util.Locale;

public class Usuario {
    public String iniciarSesion(String correo_electronico, String password){
        String consulta="SELECT rol FROM usuario WHERE correo_electronico=? AND password= crypt(?,password);  ";
        try{
            Connection con = ConexionDB.getConnection();
            PreparedStatement query = con.prepareStatement(consulta);
            query.setString(1, correo_electronico);
            query.setString(2,password);
            query.executeQuery();

            try(ResultSet rs = query.executeQuery()){
                if(rs.next()){
                    System.out.println("Se ha encontrado el usuario");
                    return rs.getString("rol").toLowerCase();
                }
            }
        }catch (SQLException e){
            System.out.println("Error al consultar datos :"+e.getMessage());
        }
        System.out.println("No se ha encontrado el usuario");
        return null;
    }
}
