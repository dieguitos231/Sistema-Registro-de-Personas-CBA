package com.sena;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {
    public List<Object[]> mostrarAprendices(){
        List<Object[]> listaAprendices = new ArrayList<>();
        String consulta ="SELECT detalle_usuario.tipo_documento, usuario.n_documento, detalle_usuario.nombres, detalle_usuario.apellidos, detalle_usuario.fecha_creacion FROM usuario INNER JOIN detalle_usuario ON usuario.n_documento = detalle_usuario.n_documento WHERE usuario.rol = 'aprendiz'";
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

                listaAprendices.add(arreglo);
                //System.out.println(arreglo[0] + " | " + " | "+ arreglo[1] + " | " + arreglo[2] + " | " + arreglo[3] + " | " + arreglo[4]);
            }
        }
        catch (SQLException e){
            System.out.println("Error al obtener el detalle de Aprendiz" +  e.getMessage() );
        }
        return listaAprendices;
    }
    public List<Object[]> mostrarFuncionarios(){
        List<Object[]> listaFuncionarios = new ArrayList<>();
        String consulta ="SELECT detalle_usuario.tipo_documento, usuario.n_documento, detalle_usuario.nombres, detalle_usuario.apellidos, detalle_usuario.fecha_creacion FROM usuario INNER JOIN detalle_usuario ON usuario.n_documento = detalle_usuario.n_documento WHERE usuario.rol = 'funcionario'";
        try {
            Connection con = ConexionDB.getConnection();
            PreparedStatement ps2 = con.prepareStatement(consulta);
            ResultSet rs = ps2.executeQuery();
            while (rs.next()) {
                Object[] arreglo = new Object[5];
                arreglo[0] = rs.getString("tipo_documento");
                arreglo[1] = rs.getInt("n_documento");
                arreglo[2] = rs.getString("nombres");
                arreglo[3] = rs.getString("apellidos");
                arreglo[4] = rs.getString("fecha_creacion");

                listaFuncionarios.add(arreglo);
            }
        }
        catch (SQLException e){
            System.out.println("Error: " +  e.getMessage() );
        }
        return listaFuncionarios;
    }
    public List<Object[]> mostrarUsuario( int numero){
        List<Object[]> listaUsuario = new ArrayList<>();
        String consulta ="SELECT detalle_usuario.tipo_documento, usuario.n_documento,detalle_usuario.nombres,detalle_usuario.apellidos,usuario.correo_electronico,usuario.rol FROM usuario INNER JOIN detalle_usuario ON usuario.n_documento = detalle_usuario.n_documento WHERE usuario.n_documento= ?";
        try{
            Connection con = ConexionDB.getConnection();
            try(PreparedStatement query= con.prepareStatement(consulta)){
                query.setInt(1, numero);
                ResultSet rs = query.executeQuery();
                while(rs.next()) {
                    Object[] arreglo = new Object[5];
                    arreglo[0] = rs.getString("tipo_documento");
                    arreglo[1] = rs.getInt("n_documento");
                    arreglo[2] = rs.getString("nombres");
                    arreglo[3] = rs.getString("apellidos");
                    arreglo[4] = rs.getString("rol");
                    listaUsuario.add(arreglo);
                }
            }
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        return listaUsuario;
    }

    public void crearUsuarioAprendiz(String tipo_documento,int n_documento,String nombres,String apellidos, String correo_electronico, String password, int ficha){
        String query1 = "INSERT INTO usuario(n_documento,correo_electronico,password,rol) VALUES(?,?,crypt(?,gen_salt('bf')),'aprendiz')";
        String query2 = "INSERT INTO detalle_usuario(tipo_documento,n_documento,nombres,apellidos) VALUES(?,?,?,?)";
        String query3 = "INSERT INTO aprendiz(n_documento,ficha) VALUES(?,?)";

        try(Connection con = ConexionDB.getConnection();){
            try(PreparedStatement ps1 = con.prepareStatement(query1)){
                ps1.setInt(1,n_documento);
                ps1.setString(2,correo_electronico);
                ps1.setString(3,password);
                ps1.executeUpdate();

            }
            try(PreparedStatement ps2 = con.prepareStatement(query2)){
                ps2.setString(1,tipo_documento);
                ps2.setInt(2,n_documento);
                ps2.setString(3,nombres);
                ps2.setString(4,apellidos);
                ps2.executeUpdate();
            }
            try(PreparedStatement ps3 = con.prepareStatement(query3)){
                ps3.setInt(1,n_documento);
                ps3.setInt(2,ficha);
                ps3.executeUpdate();
            }
        } catch(SQLException ex){
            System.out.println("Error al insetar el registro");
            ex.printStackTrace();
        }
    }
    public void crearUsuarioFuncionario(String tipo_documento,int n_documento,String nombres,String apellidos, String correo_electronico, String password, String cargo){
        String query1 = "INSERT INTO usuario(n_documento,correo_electronico,password,rol) VALUES(?,?,crypt(?,gen_salt('bf')),'funcionario')";
        String query2 = "INSERT INTO detalle_usuario(tipo_documento,n_documento,nombres,apellidos) VALUES(?,?,?,?)";
        String query3 = "INSERT INTO funcionario(n_documento,cargo) VALUES(?,?)";

        try(Connection con = ConexionDB.getConnection();){
            try(PreparedStatement ps1 = con.prepareStatement(query1)){
                ps1.setInt(1,n_documento);
                ps1.setString(2,correo_electronico);
                ps1.setString(3,password);
                ps1.executeUpdate();

            }
            try(PreparedStatement ps2 = con.prepareStatement(query2)){
                ps2.setString(1,tipo_documento);
                ps2.setInt(2,n_documento);
                ps2.setString(3,nombres);
                ps2.setString(4,apellidos);
                ps2.executeUpdate();
            }
            try(PreparedStatement ps3 = con.prepareStatement(query3)){
                ps3.setInt(1,n_documento);
                ps3.setString(2,cargo);
                ps3.executeUpdate();
            }
        } catch(SQLException ex){
            System.out.println("Error al insetar el registro");
            ex.printStackTrace();
        }
    }
    public void modificarUsuarios(){

    }
    public void eliminarUsuarios(){

    }

}
