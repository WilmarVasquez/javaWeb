<%-- 
    Document   : regusuario
    Created on : 19/11/2024, 12:24:04 p. m.
    Author     : alberto
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Usuario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 500px;
            margin: 50px auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #555;
        }
        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .buttons {
            display: flex;
            justify-content: space-between;
        }
        .buttons button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            background-color: #007bff;
            color: white;
            cursor: pointer;
        }
        .buttons button:hover {
            background-color: #0056b3;
        }
        .error {
            color: red;
            text-align: center;
            margin-top: 10px;
        }
        .success {
            color: green;
            text-align: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Registro de Usuarios</h1>
        
        <!-- Mostrar mensajes de error o éxito -->
        <%
            if (request.getAttribute("error") != null) {
        %>
            <p class="error"><%= request.getAttribute("error") %></p>
        <%
            }
            if (request.getAttribute("success") != null) {
        %>
            <p class="success"><%= request.getAttribute("success") %></p>
        <%
            }
        %>
        
        <form action="../UsuarioServlet" method="POST">
            <div class="form-group">
                <label for="nombre">Nombre</label>
                <input type="text" id="nombre" name="nombre" placeholder="Ingrese su nombre" required>
            </div>
            <div class="form-group">
                <label for="correo">Correo Electrónico</label>
                <input type="email" id="correo" name="correo" placeholder="Ingrese su correo" required>
            </div>
            <div class="form-group">
                <label for="contrasena">Contraseña</label>
                <input type="password" id="contrasena" name="contrasena" placeholder="Ingrese su contraseña" required>
            </div>

            <!-- Botones para acciones -->
            <div class="buttons">
                <button type="submit" name="action" value="registrar">Registrar</button>
                <button type="button" onclick="window.location.href='/javaWeb/vista/login.jsp'">Cancelar</button>
            </div>
        </form>
    </div>
</body>
</html>
