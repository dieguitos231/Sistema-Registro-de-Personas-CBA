package com.sena;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {
    public List<Object[]> mostrarAprendices(){
        List<Object[]> listaAprendizes = new ArrayList<>();
        String consulta ="SELECT detalle_usuario.tipo_documento, usuario.n_documento, detalle_usuario.nombres, detalle_usuario.apellidos, detalle_usuario.fecha_creacion FROM usuario INNER JOIN detalle_usuario ON usuario.n_documento = detalle_usuario.n_documento WHERE usuario.rol = 'Aprendiz'";
        try{
            Connection con = ConexionDB.getConnection();
            PreparedStatement ps1 = con.prepareStatement(consulta);
            ResultSet rs = ps1.executeQuery();
            while(rs.next()){
                Object[] arreglo = new Object[5];

                arreglo[0] = rs.getString("tipo_documento");
                arreglo[1] = rs.getInt("n_documento");
                arreglo[2] = rs.getString("nombres");
                arreglo[3] = rs.getString("apellidos");
                arreglo[4] = rs.getString("fecha_creacion");

                listaAprendizes.add(arreglo);
                //System.out.println(arreglo[0] + " | " + " | "+ arreglo[1] + " | " + arreglo[2] + " | " + arreglo[3] + " | " + arreglo[4]);
            }
        }
        catch (SQLException e){
            System.out.println("Error al obtener el detalle de Aprendiz" +  e.getMessage() );
        }
        return listaAprendizes;
    }
    public List<Object[]> mostrarFuncionarios(){
        List<Object[]> listaFuncionarios = new ArrayList<>();
        String consulta ="SELECT detalle_usuario.tipo_documento, usuario.n_documento, detalle_usuario.nombres, detalle_usuario.apellidos, detalle_usuario.fecha_creacion FROM usuario INNER JOIN detalle_usuario ON usuario.n_documento = detalle_usuario.n_documento WHERE usuario.rol = 'Funcionario'";

        try {
            Connection con = ConexionDB.getConnection();
            PreparedStatement ps2 = con.prepareStatement(consulta);
            ResultSet rs = ps2.executeQuery();
            System.out.println("Lista de Funcionarios");
            while (rs.next()) {
                Object[] arreglo = new Object[5];

                arreglo[0] = rs.getString("tipo_documento");
                arreglo[1] = rs.getInt("n_documento");
                arreglo[2] = rs.getString("nombres");
                arreglo[3] = rs.getString("apellidos");
                arreglo[4] = rs.getString("fecha_creacion");

                listaFuncionarios.add(arreglo);
                //System.out.println(arreglo[0] + " | " + " | "+ arreglo[1] + " | " + arreglo[2] + " | " + arreglo[3] + " | " + arreglo[4]);
            }
        }
        catch (SQLException e){
            System.out.println("Error al obtener el detalle de Funcionario" +  e.getMessage() );
        }
        return listaFuncionarios;
    }
    public void crearUsuario(String tipo_documento,int n_documento,String nombres,String apellidos,String rol, String correo_electronico, String password){
        String query ="INSERT INTO usuario(n_documento,correo_electronico, password, rol) VALUES (?,?,crypt(?,gen_salt('bf')),?)";
        String query2 ="INSERT INTO detalle_usuario(tipo_documento,n_documento,nombres,apellidos) VALUES (?,?,?,?)";
        try (Connection con = ConexionDB.getConnection();){
            try(PreparedStatement ps1 = con.prepareStatement(query);){
                ps1.setInt(1, n_documento);
                ps1.setString(2, correo_electronico);
                ps1.setString(3, password);
                ps1.setString(4, rol);
                ps1.executeUpdate();
                System.out.println("Datos Insertados de usuario");
            }
            try( PreparedStatement ps2 = con.prepareStatement(query2)){
                ps2.setString(1,tipo_documento);
                ps2.setInt(2, n_documento);
                ps2.setString(3, nombres);
                ps2.setString(4, apellidos);
                ps2.executeUpdate();
                System.out.println("Datos Insertados de usuario");

            }
        }
        catch (SQLException ex){
            System.out.println("Error al insertar el registro");
            ex.printStackTrace();
        }
    }
    public void modificarUsuarios(){

    }
    public void eliminarUsuarios(){

    }

}
