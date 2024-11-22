<%-- 
    Document   : ediperfil
    Created on : 19/11/2024, 10:07:09 p. m.
    Author     : alberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Perfil</title>
</head>
<body>
    <h1>Editar Perfil</h1>

    <!-- Mostrar mensaje de error si existe -->
    <%
        String error = (String) request.getAttribute("error");
        String success = (String) request.getAttribute("success");
        if (error != null) {
    %>
        <p style="color: red;"><%= error %></p>
    <%
        }
        if (success != null) {
    %>
        <p style="color: green;"><%= success %></p>
    <%
        }
    %>

    <form action="../UsuarioServlet" method="POST">
        <input type="hidden" name="action" value="editarPerfil">

        <label for="nombre">Nombre:</label><br>
        <input type="text" id="nombre" name="nombre" value="<%= request.getAttribute("nombre") != null ? request.getAttribute("nombre") : "" %>" required><br><br>

        <label for="correo">Correo:</label><br>
        <input type="email" id="correo" name="correo" value="<%= request.getAttribute("correo") != null ? request.getAttribute("correo") : "" %>" required><br><br>

        <label for="contrasena">Contraseña:</label><br>
        <input type="password" id="contrasena" name="contrasena" value="<%= request.getAttribute("contrasena") != null ? request.getAttribute("contrasena") : "" %>" required><br><br>

        <button type="submit">Editar</button>
        <button type="button" onclick="window.location.href='dashboard.jsp';">Cancelar</button>
    </form>
</body>
</html>
