package com.sena;
import javax.swing.*;
import java.sql.*;

public class Usuario {
    public String iniciarSesion(String correo_electronico, char[] password){
        String consulta="SELECT rol FROM usuario WHERE correo_electronico=? AND password= crypt(?,password);  ";
        try{
            Connection con = ConexionDB.getConnection();
            PreparedStatement query = con.prepareStatement(consulta);
            query.setString(1, correo_electronico);
            query.setString(2,new String(password));
            try(ResultSet rs = query.executeQuery()){
                if(rs.next()){JOptionPane.showMessageDialog(null,"Bienvenido");return rs.getString("rol");}
            }
        }catch (SQLException e){
            System.out.println("Error");
        }
        return null;
    }
}
