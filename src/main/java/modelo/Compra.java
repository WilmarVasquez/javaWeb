/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Compra {
    private int idCompra;
    private Date fechaCompra;
    private String ticket;
    private int cantidad;
    private double precioTicket;
    private double totalInversion;
    private Usuario usuario; // Relación con Usuario
    private List<Venta> ventas; // Relación con Venta (una compra puede tener múltiples ventas)

    // Constructor por defecto
    public Compra() {
        this.ventas = new ArrayList<>();
    }

    // Constructor con parámetros
    public Compra(int idCompra, Date fechaCompra, String ticket, int cantidad, double precioTicket) {
        this();
        this.idCompra = idCompra;
        this.fechaCompra = fechaCompra;
        this.ticket = ticket;
        this.cantidad = cantidad;
        this.precioTicket = precioTicket;
        this.totalInversion = calcularTotalInversion();
    }

    // Getters y Setters
    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
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
        this.totalInversion = calcularTotalInversion(); // Actualiza el total
    }

    public double getPrecioTicket() {
        return precioTicket;
    }

    public void setPrecioTicket(double precioTicket) {
        this.precioTicket = precioTicket;
        this.totalInversion = calcularTotalInversion(); // Actualiza el total
    }

    public double getTotalInversion() {
        return totalInversion;
    }

    public void setTotalInversion(double totalInversion) {
        this.totalInversion = totalInversion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public int getUsuarioId() {
        return usuario != null ? usuario.getIdUsuario() : 0; // Devuelve el ID del usuario si existe
    }

    // Métodos de relación
    public void agregarVenta(Venta venta) {
        this.ventas.add(venta);
    }

    public void removerVenta(Venta venta) {
        this.ventas.remove(venta);
    }

    // Lógica de cálculo
    private double calcularTotalInversion() {
        return this.cantidad * this.precioTicket;
    }

    // Método toString para depuración
    @Override
    public String toString() {
        return "Compra{" +
                "idCompra=" + idCompra +
                ", fechaCompra=" + fechaCompra +
                ", ticket='" + ticket + '\'' +
                ", cantidad=" + cantidad +
                ", precioTicket=" + precioTicket +
                ", totalInversion=" + totalInversion +
                ", usuario=" + (usuario != null ? usuario.getNombre() : "No asignado") +
                ", ventas=" + (ventas != null ? ventas.size() : 0) +
                '}';
    }
}