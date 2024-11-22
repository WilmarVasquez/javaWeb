/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import modelo.Usuario;
import modelo.DatabaseConnector;
import modelo.Compra;
import modelo.Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    private final Connection connection;

    // Constructor que utiliza el Singleton
    public UsuarioDAOImpl() throws SQLException {
        this.connection = DatabaseConnector.getInstance().getConnection();
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        String query = "INSERT INTO usuario (nombre, email, contrasena) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getContrasena());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar usuario: " + e.getMessage(), e);
        }
    }

    @Override
    public Usuario iniciarSesion(String email, String contrasena) throws SQLException {
        Usuario usuario = null;
        String query = "SELECT * FROM usuario WHERE email = ? AND contrasena = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("idUsuario"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("contrasena")
                );
            }
        }
        return usuario;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM usuario";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("idUsuario"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("contrasena")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar usuarios: " + e.getMessage(), e);
        }
        return usuarios;
    }

    @Override
    public void actualizarUsuario(int idUsuario, String nombre, String email, String contrasena) {
        String query = "UPDATE usuario SET nombre = ?, email = ?, contrasena = ? WHERE idUsuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, email);
            stmt.setString(3, contrasena);
            stmt.setInt(4, idUsuario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar usuario: " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminarUsuario(int idUsuario) {
        String query = "DELETE FROM usuario WHERE idUsuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar usuario: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Compra> getComprasPorUsuario(int idUsuario) {
        List<Compra> compras = new ArrayList<>();
        String query = "SELECT idCompra, fechaCompra, ticket, cantidad, precioXticket FROM compra WHERE idUsuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Compra compra = new Compra(
                        rs.getInt("idCompra"),
                        rs.getDate("fechaCompra"),
                        rs.getString("ticket"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precioXticket")
                );
                compras.add(compra);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener compras por usuario: " + e.getMessage(), e);
        }
        return compras;
    }

    @Override
    public List<Venta> getVentasPorUsuario(int idUsuario) {
        List<Venta> ventas = new ArrayList<>();
        String query = "SELECT idVenta, fechaVenta, ticket, cantidad, precioXticket, idCompra FROM venta WHERE idUsuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(rs.getInt("idVenta"));
                venta.setFechaVenta(rs.getDate("fechaVenta"));
                venta.setTicket(rs.getString("ticket"));
                venta.setCantidad(rs.getInt("cantidad"));
                venta.setPrecioTicket(rs.getDouble("precioXticket"));

                Compra compra = new Compra();
                compra.setIdCompra(rs.getInt("idCompra"));
                venta.setCompra(compra);

                Usuario usuario = new Usuario();
                usuario.setIdUsuario(idUsuario);
                venta.setUsuario(usuario);

                ventas.add(venta);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener ventas por usuario: " + e.getMessage(), e);
        }
        return ventas;
    }
}
