/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import modelo.Compra;
import modelo.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompraDAOImpl implements CompraDAO {

    private final Connection connection;

    // Constructor que usa el singleton
    public CompraDAOImpl() throws SQLException {
        this.connection = DatabaseConnector.getInstance().getConnection();
    }

    @Override
    public void agregarCompra(Compra compra) throws SQLException {
        String query = "INSERT INTO compra (fechaCompra, ticket, cantidad, precioXticket, totalInversion, idUsuario) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(compra.getFechaCompra().getTime()));
            stmt.setString(2, compra.getTicket());
            stmt.setInt(3, compra.getCantidad());
            stmt.setDouble(4, compra.getPrecioTicket());
            stmt.setDouble(5, compra.getTotalInversion());
            stmt.setInt(6, compra.getUsuarioId());
            stmt.executeUpdate();
        }
    }


    @Override
    public Compra obtenerCompraPorId(int idCompra) throws SQLException {
        String query = "SELECT * FROM compra WHERE idCompra = ?";
        Compra compra = null;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCompra);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                compra = mapearCompra(rs);
            }
        }
        return compra;
    }

    @Override
    public List<Compra> listarCompras() throws SQLException {
        String query = "SELECT * FROM compra";
        List<Compra> compras = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                compras.add(mapearCompra(rs));
            }
        }
        return compras;
    }

    @Override
    public void actualizarCompra(Compra compra) throws SQLException {
        String query = "UPDATE compra SET fechaCompra = ?, ticket = ?, cantidad = ?, precioXticket = ?, totalInversion = ?, idUsuario = ? WHERE idCompra = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(compra.getFechaCompra().getTime()));
            stmt.setString(2, compra.getTicket());
            stmt.setInt(3, compra.getCantidad());
            stmt.setDouble(4, compra.getPrecioTicket());
            stmt.setDouble(5, compra.getTotalInversion());
            stmt.setInt(6, compra.getUsuarioId());
            stmt.setInt(7, compra.getIdCompra());
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminarCompra(int idCompra) throws SQLException {
        String query = "DELETE FROM compra WHERE idCompra = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCompra);
            stmt.executeUpdate();
        }
    }

    // Método privado para mapear un ResultSet a un objeto Compra
    private Compra mapearCompra(ResultSet rs) throws SQLException {
        Compra compra = new Compra();
        compra.setIdCompra(rs.getInt("idCompra"));
        compra.setFechaCompra(rs.getDate("fechaCompra"));
        compra.setTicket(rs.getString("ticket"));
        compra.setCantidad(rs.getInt("cantidad"));
        compra.setPrecioTicket(rs.getDouble("precioXticket"));
        compra.setTotalInversion(rs.getDouble("totalInversion"));
        // La relación con Usuario y Venta puede ser agregada aquí si es necesario
        return compra;
    }
}