/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import modelo.Compra;
import modelo.Usuario;
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

@WebServlet(name = "CompraServlet", urlPatterns = {"/CompraServlet"})
public class CompraServlet extends HttpServlet {

    private CompraDAOImpl compraDAO;

    public CompraServlet() {
        try {
            this.compraDAO = new CompraDAOImpl(); // DAO utiliza el Singleton
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al inicializar CompraDAO: " + e.getMessage());
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
                    agregarCompra(request, response, usuario);
                    break;
                case "buscar":
                    buscarCompraPorId(request, response);
                    break;
                case "actualizar":
                    actualizarCompra(request, response, usuario);
                    break;
                case "eliminar":
                    eliminarCompra(request, response);
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

    private void agregarCompra(HttpServletRequest request, HttpServletResponse response, Usuario usuario)
            throws ServletException, IOException {
        try {
            String ticket = request.getParameter("ticket");
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            double precioTicket = Double.parseDouble(request.getParameter("precioTicket"));

            Compra compra = new Compra();
            compra.setFechaCompra(new Date());
            compra.setTicket(ticket);
            compra.setCantidad(cantidad);
            compra.setPrecioTicket(precioTicket);
            compra.setTotalInversion(cantidad * precioTicket);
            compra.setUsuario(usuario);

            compraDAO.agregarCompra(compra);

            response.sendRedirect(request.getContextPath() + "/vista/regcompra.jsp?success=Compra registrada correctamente");
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al agregar compra: " + e.getMessage());
            request.getRequestDispatcher("/vista/regcompra.jsp").forward(request, response);
        }
    }

    private void buscarCompraPorId(HttpServletRequest request, HttpServletResponse response)
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
            request.getRequestDispatcher("/vista/regcompra.jsp").forward(request, response);
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al buscar compra: " + e.getMessage());
            request.getRequestDispatcher("/vista/regcompra.jsp").forward(request, response);
        }
    }

    private void actualizarCompra(HttpServletRequest request, HttpServletResponse response, Usuario usuario)
            throws ServletException, IOException {
        try {
            int idCompra = Integer.parseInt(request.getParameter("idCompra"));
            String ticket = request.getParameter("ticket");
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            double precioTicket = Double.parseDouble(request.getParameter("precioTicket"));

            Compra compra = new Compra();
            compra.setIdCompra(idCompra);
            compra.setFechaCompra(new Date());
            compra.setTicket(ticket);
            compra.setCantidad(cantidad);
            compra.setPrecioTicket(precioTicket);
            compra.setTotalInversion(cantidad * precioTicket);
            compra.setUsuario(usuario);

            compraDAO.actualizarCompra(compra);

            response.sendRedirect(request.getContextPath() + "/vista/regcompra.jsp?success=Compra actualizada correctamente");
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al actualizar compra: " + e.getMessage());
            request.getRequestDispatcher("/vista/regcompra.jsp").forward(request, response);
        }
    }

    private void eliminarCompra(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idCompra = Integer.parseInt(request.getParameter("idCompra"));
            compraDAO.eliminarCompra(idCompra);
            response.sendRedirect(request.getContextPath() + "/vista/regcompra.jsp?success=Compra eliminada correctamente");
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al eliminar compra: " + e.getMessage());
            request.getRequestDispatcher("/vista/regcompra.jsp").forward(request, response);
        }
    }
}
