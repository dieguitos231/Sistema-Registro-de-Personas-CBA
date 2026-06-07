package com.sena;
import java.sql.*;
import java.util.Locale;

public class Usuario {
    public String iniciarSesion(String correo_electronico, char[] password){
        String consulta="SELECT rol FROM usuario WHERE correo_electronico=? AND password= crypt(?,password);  ";
        try{
            Connection con = ConexionDB.getConnection();
            PreparedStatement query = con.prepareStatement(consulta);
            query.setString(1, correo_electronico);
            query.setString(2,new String(password));
            try(ResultSet rs = query.executeQuery()){
                if(rs.next()){
                    System.out.println("Bienvenido usuario");
                    return rs.getString("rol").toLowerCase();
                }
            }
        }catch (SQLException e){
            System.out.println("Error:"+e.getMessage());
        }
        return null;
    }
}
