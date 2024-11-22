/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import modelo.Venta;
import modelo.Compra;
import modelo.Usuario;
import dao.VentaDAOImpl;
import dao.CompraDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@WebServlet(name = "VentaServlet", urlPatterns = {"/VentaServlet"})
public class VentaServlet extends HttpServlet {

    private VentaDAOImpl ventaDAO;
    private CompraDAOImpl compraDAO;

    public VentaServlet() {
    try {
        this.ventaDAO = new VentaDAOImpl(); // DAO usa el singleton
        this.compraDAO = new CompraDAOImpl(); // DAO usa el singleton
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Error al inicializar DAOs: " + e.getMessage());
    }
}


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/vista/login.jsp?error=Debes iniciar sesión primero");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/vista/error.jsp");
            return;
        }

        try {
            switch (action) {
                case "agregar":
                    agregarVenta(request, response, usuario);
                    break;
                case "buscarCompra":
                    buscarCompra(request, response);
                    break;
                case "buscar":
                    buscarVentaPorId(request, response);
                    break;
                case "actualizar":
                    actualizarVenta(request, response, usuario);
                    break;
                case "eliminar":
                    eliminarVenta(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/vista/error.jsp");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/vista/error.jsp?error=Error en el servidor: " + e.getMessage());
        }
    }

    private void buscarCompra(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idCompra = Integer.parseInt(request.getParameter("idCompra"));
            Compra compra = compraDAO.obtenerCompraPorId(idCompra);

            if (compra != null) {
                request.setAttribute("compra", compra);
                request.setAttribute("success", "Compra encontrada correctamente.");
            } else {
                request.setAttribute("error", "Compra no encontrada.");
            }
            request.getRequestDispatcher("/vista/regventa.jsp").forward(request, response);
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al buscar compra: " + e.getMessage());
            request.getRequestDispatcher("/vista/regventa.jsp").forward(request, response);
        }
    }

    private void agregarVenta(HttpServletRequest request, HttpServletResponse response, Usuario usuario)
            throws ServletException, IOException {
        try {
            int idCompra = Integer.parseInt(request.getParameter("idCompra"));
            String ticket = request.getParameter("ticket");
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            double precioTicket = Double.parseDouble(request.getParameter("precioTicket"));

            Venta venta = new Venta();
            venta.setFechaVenta(new Date());
            venta.setTicket(ticket);
            venta.setCantidad(cantidad);
            venta.setPrecioTicket(precioTicket);
            venta.setTotalVenta(cantidad * precioTicket);
            venta.setUsuario(usuario);
            venta.setIdCompra(idCompra);

            ventaDAO.agregarVenta(venta);

            response.sendRedirect(request.getContextPath() + "/vista/regventa.jsp?success=Venta registrada correctamente");
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al registrar venta: " + e.getMessage());
            request.getRequestDispatcher("/vista/regventa.jsp").forward(request, response);
        }
    }

    private void buscarVentaPorId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idVenta = Integer.parseInt(request.getParameter("idVenta"));
            Venta venta = ventaDAO.obtenerVentaPorId(idVenta);

            if (venta != null) {
                request.setAttribute("venta", venta);
                request.setAttribute("success", "Venta encontrada correctamente.");
            } else {
                request.setAttribute("error", "Venta no encontrada.");
            }
            request.getRequestDispatcher("/vista/regventa.jsp").forward(request, response);
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al buscar venta: " + e.getMessage());
            request.getRequestDispatcher("/vista/regventa.jsp").forward(request, response);
        }
    }

    private void actualizarVenta(HttpServletRequest request, HttpServletResponse response, Usuario usuario)
            throws ServletException, IOException {
        try {
            int idVenta = Integer.parseInt(request.getParameter("idVenta"));
            String ticket = request.getParameter("ticket");
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            double precioTicket = Double.parseDouble(request.getParameter("precioTicket"));
            int idCompra = Integer.parseInt(request.getParameter("idCompra"));

            Venta venta = new Venta();
            venta.setIdVenta(idVenta);
            venta.setFechaVenta(new Date());
            venta.setTicket(ticket);
            venta.setCantidad(cantidad);
            venta.setPrecioTicket(precioTicket);
            venta.setTotalVenta(cantidad * precioTicket);
            venta.setUsuario(usuario);
            venta.setIdCompra(idCompra);

            ventaDAO.actualizarVenta(venta);

            response.sendRedirect(request.getContextPath() + "/vista/regventa.jsp?success=Venta actualizada correctamente");
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al actualizar venta: " + e.getMessage());
            request.getRequestDispatcher("/vista/regventa.jsp").forward(request, response);
        }
    }

    private void eliminarVenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idVenta = Integer.parseInt(request.getParameter("idVenta"));
            ventaDAO.eliminarVenta(idVenta);
            response.sendRedirect(request.getContextPath() + "/vista/regventa.jsp?success=Venta eliminada correctamente");
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al eliminar venta: " + e.getMessage());
            request.getRequestDispatcher("/vista/regventa.jsp").forward(request, response);
        }
    }
}
