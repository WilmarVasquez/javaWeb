/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import modelo.Usuario;
import modelo.Compra;
import modelo.Venta;
import java.util.List;
import java.sql.*;


/**
 * Define las operaciones de acceso a datos para la entidad Usuario.
 */
public interface UsuarioDAO {
    void registrarUsuario(Usuario usuario);
    Usuario iniciarSesion(String email, String contrasena) throws SQLException;
    List<Usuario> listarUsuarios();
    void actualizarUsuario(int idUsuario, String nombre, String email, String contrasena);
    void eliminarUsuario(int idUsuario);
    List<Compra> getComprasPorUsuario(int idUsuario);
    List<Venta> getVentasPorUsuario(int idUsuario);
    // Elimina closeConnection() si no lo usas
}

