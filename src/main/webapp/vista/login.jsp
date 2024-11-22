<%-- 
    Document   : login
    Created on : 19/11/2024, 12:07:57 p.m.
    Author     : alberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LLEVA CUENTAS</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }
        .container h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }
        .container form {
            display: flex;
            flex-direction: column;
            gap: 10px;
        }
        .container form input {
            padding: 8px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .container form button {
            padding: 10px;
            font-size: 16px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .container form button:hover {
            background-color: #0056b3;
        }
        .container .links {
            margin-top: 15px;
            font-size: 14px;
        }
        .container .links a {
            color: #007bff;
            text-decoration: none;
        }
        .container .links a:hover {
            text-decoration: underline;
        }
        .error-message {
            color: red;
            font-size: 14px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Iniciar Sesión</h1>

        <!-- Mostrar mensaje de error si existe -->
        <c:if test="${error != null}">
            <p class="error-message">${error}</p>
        </c:if>

        <form action="../UsuarioServlet" method="POST">
            <input type="hidden" name="action" value="login">
            
            <label for="email">Correo:</label>
            <input type="email" id="email" name="email" placeholder="Ingrese su correo" required>
            
            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" placeholder="Ingrese su contraseña" required>
            
            <button type="submit">Ingresar</button>
        </form>

        <div class="links">
            <p><a href="../vista/regusuario.jsp">¿No tiene cuenta? Regístrese</a></p>
            <p><a href="../vista/rclave.jsp">¿Olvidó su contraseña?</a></p>
        </div>
    </div>
</body>
</html>
