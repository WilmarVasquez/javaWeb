/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import modelo.Venta;

import java.sql.SQLException;
import java.util.List;

public interface VentaDAO {

    /**
     * Agrega una nueva venta a la base de datos.
     * 
     * @param venta El objeto Venta a agregar.
     * @throws SQLException Si ocurre un error durante la operación.
     */
    void agregarVenta(Venta venta) throws SQLException;

    /**
     * Obtiene una venta específica por su ID.
     * 
     * @param idVenta El ID de la venta a obtener.
     * @return El objeto Venta correspondiente al ID, o null si no existe.
     * @throws SQLException Si ocurre un error durante la operación.
     */
    Venta obtenerVentaPorId(int idVenta) throws SQLException;

    /**
     * Obtiene todas las ventas registradas en la base de datos.
     * 
     * @return Una lista de objetos Venta.
     * @throws SQLException Si ocurre un error durante la operación.
     */
    List<Venta> listarVentas() throws SQLException;

    /**
     * Actualiza los datos de una venta existente.
     * 
     * @param venta El objeto Venta con los datos actualizados.
     * @throws SQLException Si ocurre un error durante la operación.
     */
    void actualizarVenta(Venta venta) throws SQLException;

    /**
     * Elimina una venta de la base de datos por su ID.
     * 
     * @param idVenta El ID de la venta a eliminar.
     * @throws SQLException Si ocurre un error durante la operación.
     */
    void eliminarVenta(int idVenta) throws SQLException;
}
