/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import modelo.Venta;
import modelo.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaDAOImpl implements VentaDAO {

    private final Connection connection;

    // Constructor que usa el singleton
    public VentaDAOImpl() throws SQLException {
        this.connection = DatabaseConnector.getInstance().getConnection();
    }

    @Override
    public void agregarVenta(Venta venta) throws SQLException {
        String query = "INSERT INTO venta (fechaVenta, ticket, cantidad, precioXticket, totalInversion, idUsuario, idCompra) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(venta.getFechaVenta().getTime()));
            stmt.setString(2, venta.getTicket());
            stmt.setInt(3, venta.getCantidad());
            stmt.setDouble(4, venta.getPrecioTicket());
            stmt.setDouble(5, venta.getTotalVenta());
            stmt.setInt(6, venta.getUsuarioId());
            stmt.setInt(7, venta.getIdCompra());
            stmt.executeUpdate();
        }
    }

    @Override
    public Venta obtenerVentaPorId(int idVenta) throws SQLException {
        String query = "SELECT * FROM venta WHERE idVenta = ?";
        Venta venta = null;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idVenta);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                venta = new Venta();
                venta.setIdVenta(rs.getInt("idVenta"));
                venta.setFechaVenta(rs.getDate("fechaVenta"));
                venta.setTicket(rs.getString("ticket"));
                venta.setCantidad(rs.getInt("cantidad"));
                venta.setPrecioTicket(rs.getDouble("precioXticket"));
                venta.setTotalVenta(rs.getDouble("totalInversion"));
                venta.setIdCompra(rs.getInt("idCompra"));
            }
        }
        return venta;
    }

    @Override
    public List<Venta> listarVentas() throws SQLException {
        String query = "SELECT * FROM venta";
        List<Venta> ventas = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(rs.getInt("idVenta"));
                venta.setFechaVenta(rs.getDate("fechaVenta"));
                venta.setTicket(rs.getString("ticket"));
                venta.setCantidad(rs.getInt("cantidad"));
                venta.setPrecioTicket(rs.getDouble("precioXticket"));
                venta.setTotalVenta(rs.getDouble("totalInversion"));
                venta.setIdCompra(rs.getInt("idCompra"));
                ventas.add(venta);
            }
        }
        return ventas;
    }

    @Override
    public void actualizarVenta(Venta venta) throws SQLException {
        String query = "UPDATE venta SET fechaVenta = ?, ticket = ?, cantidad = ?, precioXticket = ?, totalInversion = ?, idUsuario = ?, idCompra = ? WHERE idVenta = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(venta.getFechaVenta().getTime()));
            stmt.setString(2, venta.getTicket());
            stmt.setInt(3, venta.getCantidad());
            stmt.setDouble(4, venta.getPrecioTicket());
            stmt.setDouble(5, venta.getTotalVenta());
            stmt.setInt(6, venta.getUsuarioId());
            stmt.setInt(7, venta.getIdCompra());
            stmt.setInt(8, venta.getIdVenta());
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminarVenta(int idVenta) throws SQLException {
        String query = "DELETE FROM venta WHERE idVenta = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idVenta);
            stmt.executeUpdate();
        }
    }
}
