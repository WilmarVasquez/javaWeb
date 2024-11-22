/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

public class Venta {
    private int idVenta;
    private Date fechaVenta;
    private String ticket;
    private int cantidad;
    private double precioTicket;
    private double totalVenta;
    private Usuario usuario; // Relación con Usuario
    private Compra compra;   // Relación con Compra
    private int idCompra; 
    
    // Constructor por defecto
    public Venta() {
    }

    // Constructor con parámetros
    public Venta(int idVenta, Date fechaVenta, String ticket, int cantidad, double precioTicket, int idCompra , Compra compra, Usuario usuario) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.ticket = ticket;
        this.cantidad = cantidad;
        this.precioTicket = precioTicket;
        this.idCompra = idCompra;
        this.compra = compra;
        this.usuario = usuario;
        this.totalVenta = calcularTotalVenta();
    }

    // Getters y Setters
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
    
    
    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }
    
    

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.totalVenta = calcularTotalVenta(); // Actualiza el total cada vez que se cambia la cantidad
    }

    public double getPrecioTicket() {
        return precioTicket;
    }

    public void setPrecioTicket(double precioTicket) {
        this.precioTicket = precioTicket;
        this.totalVenta = calcularTotalVenta(); // Actualiza el total cada vez que se cambia el precio
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public int getUsuarioId() {
        return usuario != null ? usuario.getIdUsuario() : 0; // Devuelve el ID del usuario si existe
    }

    public int getCompraId() {
        return compra != null ? compra.getIdCompra() : 0; // Devuelve el ID de la compra si existe
    }
    
    public int getIdCompra() {
    return idCompra;
}


    // Lógica de cálculo
    private double calcularTotalVenta() {
        return this.cantidad * this.precioTicket;
    }

    // Métodos adicionales para manejar la relación con Compra (si es necesario)
    public void asociarCompra(Compra compra) {
        this.compra = compra;
    }

    public void eliminarCompra() {
        this.compra = null;
    }

    // Método toString para depuración
    @Override
    public String toString() {
        return "Venta{" +
                "idVenta=" + idVenta +
                ", fechaVenta=" + fechaVenta +
                ", ticket='" + ticket + '\'' +
                ", cantidad=" + cantidad +
                ", precioTicket=" + precioTicket +
                ", totalVenta=" + totalVenta +
                ", usuario=" + (usuario != null ? usuario.getNombre() : "No asignado") +
                ", compra=" + (compra != null ? compra.getIdCompra() : "No asignada") +
                '}';
    }
}
