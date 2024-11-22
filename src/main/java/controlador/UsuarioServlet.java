/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controlador;

import modelo.Usuario;
import dao.UsuarioDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    private UsuarioDAOImpl usuarioDAO;

    public UsuarioServlet() {
    try {
        this.usuarioDAO = new UsuarioDAOImpl(); // Maneja la excepción aquí
    } catch (SQLException e) {
        e.printStackTrace(); // Para depuración
        throw new RuntimeException("Error al inicializar UsuarioDAO: " + e.getMessage());
    }
}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Acción para manejar solicitudes GET (por ejemplo, cierre de sesión)
        String action = request.getParameter("action");

        if ("logout".equals(action)) {
            cerrarSesion(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/vista/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener la acción del formulario
        String action = request.getParameter("action");
        if (action == null) {
            action = "login"; // Acción predeterminada
        }

        switch (action) {
            case "login":
                iniciarSesion(request, response);
                break;
            case "registrar":
                registrarUsuario(request, response);
                break;
            case "editarPerfil":
                editarPerfil(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/vista/login.jsp"); // Redirigir al login si la acción no es válida
                break;
        }
    }

    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String contrasena = request.getParameter("password");

        System.out.println("Email recibido: " + email);
        System.out.println("Contraseña recibida: " + contrasena);

        try {
            Usuario usuario = usuarioDAO.iniciarSesion(email, contrasena);

            if (usuario != null) {
                System.out.println("Usuario encontrado: " + usuario.getNombre());
                // Guardar usuario en la sesión
                HttpSession session = request.getSession();
                session.setAttribute("usuarioLogueado", usuario);

                // Redirigir al dashboard (cambia la URL en el navegador)
                response.sendRedirect(request.getContextPath() + "/vista/dashboard.jsp");
            } else {
                System.out.println("Credenciales incorrectas. Redirigiendo a: " + request.getContextPath() + "/vista/login.jsp");
                // Redirigir al login con mensaje de error
                response.sendRedirect(request.getContextPath() + "/vista/login.jsp?error=Credenciales incorrectas");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error al iniciar sesión: " + e.getMessage());
        }
    }

    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");

        System.out.println("Nombre recibido: " + nombre);
        System.out.println("Correo recibido: " + correo);

        try {
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setEmail(correo);
            nuevoUsuario.setContrasena(contrasena);

            usuarioDAO.registrarUsuario(nuevoUsuario);

            System.out.println("Usuario registrado exitosamente.");
            // Redirigir al login con mensaje de éxito
            response.sendRedirect(request.getContextPath() + "/vista/login.jsp?success=Usuario registrado exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/vista/regusuario.jsp?error=Error al registrar usuario");
        }
    }

    private void editarPerfil(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

            if (usuario == null) {
                response.sendRedirect(request.getContextPath() + "/vista/login.jsp");
                return;
            }

            String nombre = request.getParameter("nombre");
            String correo = request.getParameter("correo");
            String contrasena = request.getParameter("contrasena");

            if (nombre == null || correo == null || contrasena == null) {
                response.sendRedirect(request.getContextPath() + "/vista/ediperfil.jsp?error=Todos los campos son obligatorios");
                return;
            }

            usuarioDAO.actualizarUsuario(usuario.getIdUsuario(), nombre, correo, contrasena);

            // Actualizar el usuario en la sesión
            usuario.setNombre(nombre);
            usuario.setEmail(correo);
            usuario.setContrasena(contrasena);
            session.setAttribute("usuarioLogueado", usuario);

            response.sendRedirect(request.getContextPath() + "/vista/ediperfil.jsp?success=Perfil actualizado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/vista/ediperfil.jsp?error=Error al actualizar el perfil");
        }
    }

    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/vista/login.jsp");
    }
}
