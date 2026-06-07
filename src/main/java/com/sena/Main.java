package com.sena;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

public class Main {
    //Colores
    Color colorSena = new Color(57, 169, 0);
    Color colorSecundario = new Color(136, 231, 136);
    Color azulclaro = new Color(87, 185, 255);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new Main();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Main() {
        // Comprobamos la conexión a la base de datos
        try (Connection conexion = ConexionDB.getConnection()) {
            if (conexion != null) {
                System.out.println("¡Conexión establecida con éxito usando ConexionDB!");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        panelLogin();
    }

    private void panelLogin() {
        //Labels
        JLabel titulo;
        JLabel correo;
        JLabel password;
        //Inputs
        JTextField textEmail;
        JPasswordField textPassword;
        JButton btnIngresar;

        //Configuracion ventana
        JFrame Login = new JFrame();
        Login.setTitle("Sistema de Ingreso SENA");
        Login.setSize(500, 400);
        Login.getContentPane().setBackground(colorSena);
        Login.setLocationRelativeTo(null);
        Login.setLayout(null);
        Login.setResizable(false);
        Login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Titulo Principal
        titulo = new JLabel("Inicio de Sesion ");
        titulo.setForeground(Color.white);
        titulo.setFont(new Font("Serif", Font.BOLD, 24));
        titulo.setBounds(150, 20, 200, 100); //Posicion
        Login.add(titulo);

        //Correo
        correo = new JLabel("Correo electronico");
        correo.setForeground(Color.white);
        correo.setHorizontalAlignment(JLabel.CENTER);
        correo.setBounds(50, 120, 150, 30);
        Login.add(correo);

        textEmail = new JTextField();
        textEmail.setBounds(200, 120, 200, 30);
        Login.add(textEmail);

        //Password
        password = new JLabel("Password:");
        password.setHorizontalAlignment(JLabel.CENTER);
        password.setForeground(Color.WHITE);
        password.setBounds(50, 180, 200, 30);
        Login.add(password);

        textPassword = new JPasswordField();
        textPassword.setBounds(200, 180, 200, 30);
        Login.add(textPassword);

        //Boton ingresar
        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBackground(colorSecundario);
        btnIngresar.setBounds(150, 220, 150, 40);
        Login.add(btnIngresar);

        btnIngresar.addActionListener(event -> {
            String txtEmail = textEmail.getText();
            char[] txtPassword = textPassword.getPassword();
            Usuario usuario = new Usuario();
            String rol = usuario.iniciarSesion(txtEmail, txtPassword);
            switch (rol) {
                case "aprendiz":
                    //Panel aprendiz
                    break;
                case "funcionario":
                    //Panel funcionario
                    break;
                case "administrador":
                    panelAdmin();
                    break;
            }
            //Vacio de valores al iniciar sesion
            textEmail.setText("");
            textPassword.setText("");
        });
        Login.setVisible(true);
    }

    private void panelAdmin() {
        //Labels
        JLabel titulo;
        //Botones
        JButton btnPanelUsuario;
        JButton btnPanelRegistros;
        JButton btnCloseSession;

        //Configuracion Ventana
        JFrame PanelAdmin = new JFrame();
        PanelAdmin.setTitle("Panel Admin");
        PanelAdmin.setSize(400, 400);
        PanelAdmin.setLocationRelativeTo(null);
        PanelAdmin.setLayout(null);
        PanelAdmin.setResizable(false);
        PanelAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Titulo
        titulo = new JLabel("Panel Administrador");
        titulo.setForeground(Color.black);
        titulo.setFont(new Font("Serif", Font.BOLD, 24));
        titulo.setBounds(80, 20, 400, 100);
        PanelAdmin.add(titulo);

        // Boton panel usuarios
        btnPanelUsuario = new JButton("Panel Usuario");
        btnPanelUsuario.setBackground(Color.ORANGE);
        btnPanelUsuario.setBounds(80, 120, 200, 40);
        PanelAdmin.add(btnPanelUsuario);

        //Boton panel registros
        btnPanelRegistros = new JButton("Historial Ingresos");
        btnPanelRegistros.setBackground(azulclaro);
        btnPanelRegistros.setBounds(80, 180, 200, 40);
        PanelAdmin.add(btnPanelRegistros);

        //Boton Cerrar Sesion
        btnCloseSession = new JButton("Cerrar Sesion");
        btnCloseSession.setBackground(Color.RED);
        btnCloseSession.setForeground(Color.white);
        btnCloseSession.setBounds(100, 300, 150, 35);
        PanelAdmin.add(btnCloseSession);

        btnPanelUsuario.addActionListener(event -> panelUsuarios());
//      btnPanelRegistros.addActionListener(event -> panelIngresos()) PENDIENTE
        btnCloseSession.addActionListener(event -> PanelAdmin.dispose());
        PanelAdmin.setVisible(true);
    }

    private void panelUsuarios() {
        //labels
        JLabel titulo;
        //Botones
        JButton btnReturn;
        JButton btnGestionUsuario;
        JButton btnMostrarUsuario;
        JButton btnBuscarUsuario;

        //Configuracion ventana
        JFrame PanelUsuarios = new JFrame();
        PanelUsuarios.setTitle("Panel Usuarios");
        PanelUsuarios.setSize(400, 400);
        PanelUsuarios.setLocationRelativeTo(null);
        PanelUsuarios.setLayout(null);
        PanelUsuarios.setResizable(false);
        PanelUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Boton Volver
        btnReturn = new JButton("Volver");
        btnReturn.setBackground(Color.RED);
        btnReturn.setForeground(Color.WHITE);
        btnReturn.setBounds(40, 20, 100, 30);
        PanelUsuarios.add(btnReturn);

        //Titulo principal
        titulo = new JLabel("Panel Usuarios");
        titulo.setFont(new Font("Serif", Font.BOLD, 24));
        titulo.setBounds(80, 40, 200, 100);
        PanelUsuarios.add(titulo);

        //Boton gestion de usuario
        btnGestionUsuario = new JButton("Gestion Usuario");
        btnGestionUsuario.setBackground(colorSecundario);
        btnGestionUsuario.setBounds(80, 120, 200, 40);
        PanelUsuarios.add(btnGestionUsuario);

        //Boton mostrar Usuarios
        btnMostrarUsuario = new JButton("Mostrar Usuario");
        btnMostrarUsuario.setBackground(Color.YELLOW);
        btnMostrarUsuario.setBounds(80, 180, 200, 40);
        PanelUsuarios.add(btnMostrarUsuario);

        //Boton mostrar info de un usuario
        btnBuscarUsuario = new JButton("Buscar Usuario");
        btnBuscarUsuario.setBackground(Color.RED);
        btnBuscarUsuario.setBounds(80, 240, 200, 40);
        PanelUsuarios.add(btnBuscarUsuario);

        btnReturn.addActionListener(event -> PanelUsuarios.dispose());
        btnGestionUsuario.addActionListener(event -> {
            gestionUsuarios();
            PanelUsuarios.dispose();
        });
        btnMostrarUsuario.addActionListener(event -> mostrarUsuarios());
        btnBuscarUsuario.addActionListener(event -> buscarUsuario("mostrarInfo"));
        PanelUsuarios.setVisible(true);
    }

    private void gestionUsuarios() {
        //labels
        JLabel titulo;
        //Botones
        JButton btnReturn;
        JButton btnCreateUser;
        JButton btnUpdateUser;
        JButton btnDeleteUser;

        //Configuracion ventana
        JFrame GestionUsuarios = new JFrame();
        GestionUsuarios.setTitle("Panel Gestion de usuarios");
        GestionUsuarios.setSize(500, 400);
        GestionUsuarios.setLocationRelativeTo(null);
        GestionUsuarios.setLayout(null);
        GestionUsuarios.setResizable(false);
        GestionUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Boton Volver
        btnReturn = new JButton("VOLVER");
        btnReturn.setBounds(40, 20, 100, 30);
        btnReturn.setBackground(Color.RED);
        btnReturn.setForeground(Color.WHITE);
        GestionUsuarios.add(btnReturn);

        //Titulo Principal
        titulo = new JLabel("Gestion de Usuarios");
        titulo.setFont(new Font("Serif", Font.BOLD, 24));
        titulo.setBounds(150, 40, 400, 100);
        GestionUsuarios.add(titulo);

        //Boton Crear Usuario
        btnCreateUser = new JButton("Crear Usuario");
        btnCreateUser.setBackground(colorSecundario);
        btnCreateUser.setForeground(Color.black);
        btnCreateUser.setBounds(150, 120, 200, 40);
        GestionUsuarios.add(btnCreateUser);

        //Boton Modificar Usuario
        btnUpdateUser = new JButton("Modificar Usuario");
        btnUpdateUser.setBounds(150, 180, 200, 40);
        btnUpdateUser.setBackground(Color.YELLOW);
        btnUpdateUser.setForeground(Color.black);
        GestionUsuarios.add(btnUpdateUser);

        //Boton Eliminar Usuario
        btnDeleteUser = new JButton("Eliminar Usuario");
        btnDeleteUser.setBackground(Color.RED);
        btnDeleteUser.setForeground(Color.black);
        btnDeleteUser.setBounds(150, 240, 200, 40);
        GestionUsuarios.add(btnDeleteUser);

        btnReturn.addActionListener(event -> {
            GestionUsuarios.dispose();
            panelUsuarios();
        });
        btnCreateUser.addActionListener(event ->crearUsuario());
        btnUpdateUser.addActionListener(event -> buscarUsuario("update"));
        btnDeleteUser.addActionListener(event -> buscarUsuario("delete"));
        GestionUsuarios.setVisible(true);
    }

    private void crearUsuario() {
        //Labels
        JLabel titulo;
        JLabel lblNumeroId;
        JLabel lblNombre;
        JLabel lblApellidos;
        JLabel lblEmail;
        JLabel lblRol;
        //Inputs
        JComboBox<String> tipoId;
        JTextField numeroId;
        JTextField nombre;
        JTextField apellido;
        JTextField correo_electronico;
        JComboBox<String> rol;
        //Botones
        JButton create;
        JButton cancel;

        //Configuracion Ventana
        JFrame CrearUsuario = new JFrame();
        CrearUsuario.setTitle("Crear usuario");
        CrearUsuario.setSize(500, 400);
        CrearUsuario.setLocationRelativeTo(null);
        CrearUsuario.setLayout(null);
        CrearUsuario.setResizable(false);
        CrearUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Titulo
        titulo = new JLabel("Crear Nuevo Usuario");
        titulo.setForeground(colorSena);
        titulo.setFont(new Font("Serif", Font.BOLD, 24));
        titulo.setBounds(80, 20, 300, 30);
        CrearUsuario.add(titulo);

        //Tipo ID
        JLabel lblTipoId = new JLabel("T.I");
        lblTipoId.setBounds(80, 60, 50, 30);
        CrearUsuario.add(lblTipoId);

        String[] tipos = {
                "CC",
                "TI",
                "Pasaporte",
                "CE",
                "PPT"
        };
        tipoId = new JComboBox<>(tipos);
        tipoId.setBackground(Color.WHITE);
        tipoId.setBounds(80, 100, 50, 30);
        CrearUsuario.add(tipoId);

        //NUMERO DE IDENTIFICACION
        lblNumeroId = new JLabel("Numero de identificación");
        lblNumeroId.setBounds(140, 60, 150, 30);
        CrearUsuario.add(lblNumeroId);

        numeroId = new JTextField();
        numeroId.setBounds(140, 100, 150, 30);
        CrearUsuario.add(numeroId);

        //NOMBRE
        lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(80, 140, 150, 30);
        CrearUsuario.add(lblNombre);

        nombre = new JTextField();
        nombre.setBounds(80, 180, 150, 30);
        CrearUsuario.add(nombre);

        //Apellidos
        lblApellidos = new JLabel("Apellidos");
        lblApellidos.setBounds(250, 140, 150, 30);
        CrearUsuario.add(lblApellidos);

        apellido = new JTextField();
        apellido.setBounds(250, 180, 150, 30);
        CrearUsuario.add(apellido);

        //Email
        lblEmail = new JLabel("Correo electronico");
        lblEmail.setBounds(80, 220, 200, 30);
        CrearUsuario.add(lblEmail);

        correo_electronico = new JTextField();
        correo_electronico.setBounds(80, 250, 150, 30);
        CrearUsuario.add(correo_electronico);

        //ROL
        lblRol = new JLabel("Rol");
        lblRol.setBounds(250, 220, 50, 30);
        CrearUsuario.add(lblRol);

        String[] opcionesRol = {
                "aprendiz",
                "funcionario",
                "administrador"
        };
        rol = new JComboBox<>(opcionesRol);
        rol.setBackground(Color.WHITE);
        rol.setBounds(250, 250, 100, 30);
        CrearUsuario.add(rol);

        //CREAR
        create = new JButton("CREAR");
        create.setBackground(azulclaro);
        create.setBounds(330, 300, 100, 30);
        CrearUsuario.add(create);

        //CANCELAR
        cancel = new JButton("CANCELAR");
        cancel.setBackground(Color.WHITE);
        cancel.setBounds(200, 300, 100, 30);
        CrearUsuario.add(cancel);

        create.addActionListener(event -> {
            String tipo= tipoId.getSelectedItem().toString();
            String numero = numeroId.getText();
            int numeroConvertido = 0;
            try {
                numeroConvertido = Integer.parseInt(numero);
            } catch (NumberFormatException e) {
                javax.swing.JOptionPane.showMessageDialog(null, "Por favor ingrese un numero valido",
                        "Error en el formato", javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
            String nombres = nombre.getText();
            String apellidos = apellido.getText();
            String email = correo_electronico.getText();
            String rolAsignado = rol.getSelectedItem().toString();
            Admin admin = new Admin();
            admin.crearUsuario(tipo, numeroConvertido, nombres, apellidos, rolAsignado, email, numero);
            tipoId.setSelectedIndex(0);
            numeroId.setText("");
            nombre.setText("");
            apellido.setText("");
            correo_electronico.setText("");
            rol.setSelectedIndex(0);
        });
        CrearUsuario.setVisible(true);
    }

    private void buscarUsuario(String panel) {
        //Labels
        JLabel titulo;
        JLabel lblTipo;
        JLabel lblNumero;
        //Inputs
        JComboBox<String> tipoId;
        JTextField n_documento;
        //Botones
        JButton btnBuscar;
        JButton btnCancelar;

        //Configuracion Ventana
        JFrame BuscarUsuario = new JFrame();
        BuscarUsuario.setTitle("Buscar Usuario");
        BuscarUsuario.setSize(400, 300);
        BuscarUsuario.setLocationRelativeTo(null);
        BuscarUsuario.setLayout(null);
        BuscarUsuario.setResizable(false);
        BuscarUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Titulo
        titulo = new JLabel("Buscar Usuario");
        titulo.setForeground(colorSena);
        titulo.setFont(new Font("Serif", Font.BOLD, 24));
        titulo.setBounds(80, 20, 400, 40);
        BuscarUsuario.add(titulo);
        //Tipo
        lblTipo = new JLabel("Tipo");
        lblTipo.setBounds(80,80,100,40);
        BuscarUsuario.add(lblTipo);

        String[] tipo = {
                "CC",
                "TI",
                "Pasaporte",
                "CE",
                "PPT"
        };
        tipoId = new JComboBox<>(tipo);
        tipoId.setBackground(Color.WHITE);
        tipoId.setBounds(80,120, 50, 30);
        BuscarUsuario.add(tipoId);

        //Numero
        lblNumero = new JLabel("Numero de identificacion");
        lblNumero.setBounds(180,80,200,40);
        BuscarUsuario.add(lblNumero);

        n_documento = new JTextField();
        n_documento.setBounds(180,120,150,30);
        BuscarUsuario.add(n_documento);

        //Boton buscar
        btnBuscar=new JButton("BUSCAR");
        btnBuscar.setBackground(azulclaro);
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setBounds(230,180,100,30);
        BuscarUsuario.add(btnBuscar);

        //Boton Cancelar
        btnCancelar=new JButton("CANCELAR");
        btnCancelar.setBackground(Color.white);
        btnCancelar.setBounds(120,180,100,30);
        BuscarUsuario.add(btnCancelar);
        switch (panel) {
            case "actualizar":
                System.out.println("Panel Actulizar informacion");
                break;
            case "eliminar":
                System.out.println("Panel Eliminar informacion");
                break;
            case "mostrarInfo":
                System.out.println("Panel mostrar informacion");
                break;
        }
        BuscarUsuario.setVisible(true);
    }

    private void actualizarInfo(String tipoId, String n_documento) {
        JFrame ActualizarInfo = new JFrame();
    }

    private void eliminarUsuario(String tipoId, String n_documento) {
        JFrame EliminarUsuario = new JFrame();
        EliminarUsuario.setTitle("Eliminar Usuario");
    }

    private void mostrarUsuarios() {
        //labels
        JLabel titulo;
        //Botones
        JButton btnReturn;
        JButton btnFuncionarios;
        JButton btnAprendices;

        //Configuracion Ventana
        JFrame MostrarUsuario = new JFrame();
        MostrarUsuario.setTitle("Mostrar Usuario");
        MostrarUsuario.setSize(400, 300);
        MostrarUsuario.setLocationRelativeTo(null);
        MostrarUsuario.setLayout(null);
        MostrarUsuario.setResizable(false);
        MostrarUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Volver
        btnReturn=new JButton("VOLVER");
        btnReturn.setBackground(Color.RED);
        btnReturn.setForeground(Color.WHITE);
        btnReturn.setBounds(40,20,100,30);
        MostrarUsuario.add(btnReturn);

        //Titulo
        titulo = new JLabel("Mostrar Usuarios");
        titulo.setFont(new Font("Serif", Font.BOLD, 24));
        titulo.setBounds(100, 40, 400, 100);
        MostrarUsuario.add(titulo);

        //Boton funcionarios
        btnFuncionarios=new JButton("Funcionarios");
        btnFuncionarios.setBackground(colorSecundario);
        btnFuncionarios.setBounds(100,120,150,40);
        MostrarUsuario.add(btnFuncionarios);

        //Boton aprendices
        btnAprendices=new JButton("Aprendices");
        btnAprendices.setBackground(Color.YELLOW);
        btnAprendices.setBounds(100,180,150,40);
        MostrarUsuario.add(btnAprendices);

        Admin admin = new Admin();
        btnFuncionarios.addActionListener(event -> admin.mostrarFuncionarios());
        btnAprendices.addActionListener(event -> admin.mostrarAprendices());
        MostrarUsuario.setVisible(true);
    }
}
