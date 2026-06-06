package com.sena;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

public class Main {
    //Paneles
    private JFrame Login;
    private JFrame PanelAdmin;
    private JFrame MostrarUsuario;
    private JFrame PanelUsuarios;
    //Paneles Asociados a Gestion de usuarios
    private JFrame GestionUsuarios;
    private JFrame CrearUsuario;
    private JFrame ActualizarUsuario;
    private JFrame ModificarUsuario;
    //Complementos
    private JFrame BuscarUsuario;
    //Colores
    Color colorSena = new Color(57, 169, 0);
    Color colorSecundario = new Color(136,231,136);
    Color azulclaro=new Color(87,185,255);
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                    frame.Login.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public Main(){
        // Comprobamos la conexión a la base de datos
        try (Connection conexion = ConexionDB.getConnection()) {
            if (conexion != null) {
                System.out.println("¡Conexión establecida con éxito usando ConexionDB!");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        Login();
    }
    private void Login(){
        /**
         * Declaracion de variables
         **/
        //Labels
        JLabel titulo;
        JLabel correo;
        JLabel password;
        //Inputs
        JTextField textEmail;
        JPasswordField textPassword;
        JButton btnIngresar;
        Login = new JFrame();
        //Configuracion Ventana
        Login.setTitle("Sistema de Ingreso SENA");
        Login.setSize(500,400);
        Login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Login.setLayout(null);
        Login.setResizable(false);
        Login.getContentPane().setBackground(colorSena);

        //Titulo Principal
        titulo=new JLabel("Inicio de Sesion ");
        titulo.setForeground(Color.white);
        titulo.setFont(new Font("Serif",Font.BOLD,24));
        titulo.setBounds(150,20,200,100); //Posicion
        Login.add(titulo);

        //Correo
        correo=new JLabel("Correo electronico");
        correo.setForeground(Color.white);
        correo.setHorizontalAlignment(JLabel.CENTER);
        correo.setBounds(50,120,150,30);
        Login.add(correo);

        textEmail=new JTextField();
        textEmail.setBounds(200,120,200,30);
        Login.add(textEmail);

        //Password
        password=new JLabel("Password:");
        password.setHorizontalAlignment(JLabel.CENTER);
        password.setForeground(Color.WHITE);
        password.setBounds(50,180,200,30);
        Login.add(password);

        textPassword=new JPasswordField();
        textPassword.setBounds(200,180,200,30);
        Login.add(textPassword);

        //Boton ingresar
        btnIngresar=new JButton("Ingresar");
        btnIngresar.setBounds(150,220,150,40);
        btnIngresar.setBackground(colorSecundario);
        Login.add(btnIngresar);

        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                String email=textEmail.getText();
                String password=textPassword.getText();
                Usuario usuario=new Usuario();
                String rol=usuario.iniciarSesion(email,password);
                switch (rol){
                    case "aprendiz":
                        //Panel aprendiz
                        break;
                    case "funcionario":
                        //Panel funcionario
                        break;
                    case "administrador":
                        PanelAdmin();
                        break;
                }
            }
        });
    }
    private void PanelAdmin(){
        //Labels
        JLabel titulo;
        //Botones
        JButton btnPanelUsuario;
        JButton btnPanelRegistros;
        JButton btnCloseSession;
        //Titulo Ventana
        PanelAdmin=new JFrame();
        PanelAdmin.setTitle("Panel Admin");
        PanelAdmin.setSize(500,400);
        PanelAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PanelAdmin.setLayout(null);
        PanelAdmin.setResizable(false);

        //Titulo
        titulo=new JLabel("Panel Administrativo");
        titulo.setForeground(Color.black);
        titulo.setFont(new Font("Serif",Font.BOLD,24));
        titulo.setBounds(120,20,400,100);
        PanelAdmin.add(titulo);

        // Boton panel usuarios
        btnPanelUsuario=new JButton("Panel Usuario");
        btnPanelUsuario.setBackground(Color.ORANGE);
        btnPanelUsuario.setBounds(120,120,200,40);
        PanelAdmin.add(btnPanelUsuario);

        //Boton panel registros
        btnPanelRegistros=new JButton("Historial Ingresos");
        btnPanelRegistros.setBackground(azulclaro);
        btnPanelRegistros.setBounds(120,180,200,40);
        PanelAdmin.add(btnPanelRegistros);

        //Boton Cerrar Sesion
        btnCloseSession=new JButton("Cerrar Sesion");
        btnCloseSession.setBackground(Color.RED);
        btnCloseSession.setForeground(Color.white);
        btnCloseSession.setBounds(150,300,150,35);
        PanelAdmin.add(btnCloseSession);

        btnPanelUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                PanelUsuarios();
            }
        });
        btnCloseSession.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PanelAdmin.dispose();
            }
        });
        PanelAdmin.setVisible(true);
    }
    private void PanelUsuarios(){
        //labels
        JLabel titulo;
        //Botones
        JButton btnReturn;
        JButton btnGestionUsuario;
        JButton btnMostrarUsuario;
        JButton btnBuscarUsuario;
        //Titulo Ventana
        PanelUsuarios = new JFrame();
        PanelUsuarios.setTitle("Panel Usuarios");
        PanelUsuarios.setSize(500,400);
        PanelUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PanelUsuarios.setLayout(null);

        //Boton Volver
        btnReturn=new JButton("Volver");
        btnReturn.setBounds(40,20,100,30);
        btnReturn.setBackground(Color.RED);
        btnReturn.setForeground(Color.WHITE);
        PanelUsuarios.add(btnReturn);

        //Titulo
        titulo=new JLabel("Panel Usuarios");
        titulo.setFont(new Font("Serif",Font.BOLD,24));
        titulo.setBounds(150,40,200,100);
        PanelUsuarios.add(titulo);

        //Boton gestion de usuario
        btnGestionUsuario=new JButton("Gestion Usuario");
        btnGestionUsuario.setBackground(colorSecundario);
        btnGestionUsuario.setBounds(120,120,200,40);
        PanelUsuarios.add(btnGestionUsuario);

        //Boton mostrar Usuarios
        btnMostrarUsuario=new JButton("Mostrar Usuario");
        btnMostrarUsuario.setBackground(Color.YELLOW);
        btnMostrarUsuario.setBounds(120,180,200,40);
        PanelUsuarios.add(btnMostrarUsuario);

        //Boton mostrar info de un usuario
        btnBuscarUsuario=new JButton("Buscar Usuario");
        btnBuscarUsuario.setBackground(Color.RED);
        btnBuscarUsuario.setBounds(120,240,200,40);
        PanelUsuarios.add(btnBuscarUsuario);

        btnGestionUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                GestionUsuarios();
            }
        });
        btnMostrarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                MostrarUsuario();
            }
        });
        PanelUsuarios.setVisible(true);
    }
    private void GestionUsuarios(){
        //labels
        JLabel titulo;
        //Botones
        JButton btnReturn;
        JButton btnCreateUser;
        JButton btnUpdateUser;
        JButton btnDeleteUser;

        //Titulo Ventana
        GestionUsuarios=new JFrame();
        GestionUsuarios.setTitle("Panel Gestion de usuarios");
        GestionUsuarios.setSize(400,400);
        GestionUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GestionUsuarios.setLayout(null);

        //Boton Volver
        btnReturn=new JButton("VOLVER");
        btnReturn.setBounds(40,20,100,30);
        btnReturn.setBackground(Color.RED);
        btnReturn.setForeground(Color.WHITE);
        GestionUsuarios.add(btnReturn);

        //Titulo Principal
        titulo=new JLabel("Gestion de Usuarios");
        titulo.setBounds(150,40,400,100);
        titulo.setFont(new Font("Serif",Font.BOLD,24));
        GestionUsuarios.add(titulo);

        //Boton Crear Usuario
        btnCreateUser=new JButton("Crear Usuario");
        btnCreateUser.setBounds(150,120,200,40);
        btnCreateUser.setBackground(colorSecundario);
        btnCreateUser.setForeground(Color.black);
        GestionUsuarios.add(btnCreateUser);

        //Boton Modificar Usuario
        btnUpdateUser=new JButton("Modificar Usuario");
        btnUpdateUser.setBounds(150,180,200,40);
        btnUpdateUser.setBackground(Color.YELLOW);
        btnUpdateUser.setForeground(Color.black);
        GestionUsuarios.add(btnUpdateUser);

        //Boton Eliminar Usuario
        btnDeleteUser=new JButton("Eliminar Usuario");
        btnDeleteUser.setBounds(150,240,200,40);
        btnDeleteUser.setBackground(Color.RED);
        btnDeleteUser.setForeground(Color.black);
        GestionUsuarios.add(btnDeleteUser);

        btnCreateUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                CrearUsuario();
            }
        });
        btnUpdateUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                BuscarUsuario("actualizar");
            }
        });
        btnDeleteUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
              BuscarUsuario("eliminar");
            }
        });
        GestionUsuarios.setVisible(true);
    }
    private void CrearUsuario(){
        JComboBox<String> tipoId;
        JTextField numeroId;
        JTextField nombre;
        JTextField apellido;
        JTextField correo_electronico;
        JComboBox<String> rol;

        JButton create;
        JButton cancel;

        CrearUsuario=new JFrame();
        CrearUsuario.setTitle("Crear usuario");
        CrearUsuario.setSize(500,400);
        CrearUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CrearUsuario.setLayout(null);
        CrearUsuario.setResizable(false);

        //TITULO
        JLabel title=new JLabel("Crear Nuevo Usuario");
        title.setBounds(80,20,300,30);
        title.setForeground(colorSena);
        title.setFont(new Font("Serif",Font.BOLD,24));
        CrearUsuario.add(title);

        //TIPO ID
        //LABEL
        JLabel lblTipoId=new JLabel("T.I");
        lblTipoId.setBounds(80,60,50,30);
        CrearUsuario.add(lblTipoId);
        //Select
        String[] tipo={
                "CC",
                "TI",
                "Pasaporte",
                "CE",
                "PPT"
        };
        tipoId=new JComboBox<>(tipo);
        tipoId.setBounds(80,100,50,30);
        CrearUsuario.add(tipoId);

        //NUMERO DE IDENTIFICACION
        //LABEL
        JLabel lblNumeroId=new JLabel("Numero de identificación");
        lblNumeroId.setBounds(140,60,150,30);
        CrearUsuario.add(lblNumeroId);
        //INPUT
        numeroId=new JTextField();
        numeroId.setBounds(140,100,150,30);
        CrearUsuario.add(numeroId);

        //NOMBRE
        //LABEL
        JLabel lblNombre=new JLabel("Nombre");
        lblNombre.setBounds(80,140,150,30);
        CrearUsuario.add(lblNombre);
        //INPUT
        nombre=new JTextField();
        nombre.setBounds(80,180,150,30);
        CrearUsuario.add(nombre);
        //Apellidos
        //LABEL
        JLabel lblApellidos=new JLabel("Apellidos");
        lblApellidos.setBounds(250,140,150,30);
        CrearUsuario.add(lblApellidos);
        //INPUT
        apellido=new JTextField();
        apellido.setBounds(250,180,150,30);
        CrearUsuario.add(apellido);

        //Email
        //LABEL
        JLabel lblEmail=new JLabel("Correo electronico");
        lblEmail.setBounds(80,220,150,30);
        CrearUsuario.add(lblEmail);
        //INPUT
        correo_electronico=new JTextField();
        correo_electronico.setBounds(80,250,150,30);
        CrearUsuario.add(correo_electronico);

        //ROL
        JLabel lblRol=new JLabel("Rol");
        lblRol.setBounds(250,220,50,30);
        CrearUsuario.add(lblRol);

        String[] opcionesRol = {
                "aprendiz",
                "funcionario",
                "administrador"
        };
        rol=new JComboBox<>(opcionesRol);
        rol.setBounds(250,250,100,30);
        CrearUsuario.add(rol);

        //CREAR
        create=new JButton("CREAR");
        create.setBounds(330,300,100,30);
        create.setBackground(azulclaro);
        CrearUsuario.add(create);

        //CANCELAR
        cancel=new JButton("CANCELAR");
        cancel.setBounds(200,300,100,30);
        cancel.setBorder(null);
        CrearUsuario.add(cancel);

        create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                String tipo=tipoId.getSelectedItem().toString();
                String numero=numeroId.getText();
                int numeroConvertido = 0;
                try{
                    numeroConvertido = Integer.parseInt(numero);
                } catch (NumberFormatException e){
                    javax.swing.JOptionPane.showMessageDialog(null,"Por favor ingrese un numero valido",
                            "Error en el formato",javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String nombres=nombre.getText();
                String apellidos=apellido.getText();
                String email=correo_electronico.getText();
                String rolAsignado=rol.getSelectedItem().toString();
                Admin admin=new Admin();
                admin.crearUsuario(tipo,numeroConvertido,nombres,apellidos,rolAsignado,email,numero);
            }
        });
        //Borrar despues
        Admin admin = new Admin();
        admin.mostrarAprendices();
        admin.mostrarFuncionarios();
        CrearUsuario.setVisible(true);
    }
    private void BuscarUsuario(String panel){
        //Labels
        JLabel lblTipo;
        JLabel lblNumero;

        //Inputs
        JComboBox<String> tipoId;
        JTextField n_documento;

        //Botones
        JButton buscar;
        JButton cancelar;

        BuscarUsuario=new JFrame();
        BuscarUsuario.setTitle("Buscar Usuario");
        BuscarUsuario.setSize(200,200);
        BuscarUsuario.setLocationRelativeTo(null);
        BuscarUsuario.setResizable(false);

        //Tipo
        lblTipo=new JLabel("Tipo");
        lblTipo.setBounds(null);
        BuscarUsuario.add(lblTipo);

        String[] tipo={
                "CC",
                "TI",
                "Pasaporte",
                "CE",
                "PPT"
        };
        tipoId=new JComboBox<>(tipo);
        tipoId.setBounds(250,250,100,30);
        BuscarUsuario.add(tipoId);

        //Numero
        lblNumero=new JLabel("T.I");
        lblNumero.setBounds(null);
        BuscarUsuario.add(lblNumero);

        n_documento=new JTextField();
        n_documento.setBounds(null);
        BuscarUsuario.add(n_documento);

        if(panel.equals("actualizar")){
            ActualizarInfo(getTipo,getNumero);
        }
        else if(panel.equals("eliminar")){
            EliminarUsuario(getTipo,getNumero);
        }
        BuscarUsuario.setVisible(true);
    }
    private void ActualizarInfo(String tipoId,String n_documento){

    }
    private void EliminarUsuario(String tipoId,String n_documento){

    }
    private void MostrarUsuario(){

        MostrarUsuario=new JFrame();
        MostrarUsuario.setTitle("Mostrar Usuario");
        MostrarUsuario.setSize(200,200);
        MostrarUsuario.setLocationRelativeTo(null);
        MostrarUsuario.setResizable(false);


    }
}
