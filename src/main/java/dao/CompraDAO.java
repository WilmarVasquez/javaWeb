/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import modelo.Compra;
import java.sql.SQLException;
import java.util.List;

public interface CompraDAO {
    void agregarCompra(Compra compra) throws SQLException;
    Compra obtenerCompraPorId(int idCompra) throws SQLException;
    List<Compra> listarCompras() throws SQLException;
    void actualizarCompra(Compra compra) throws SQLException;
    void eliminarCompra(int idCompra) throws SQLException;
}