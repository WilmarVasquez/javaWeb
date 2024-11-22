<%-- 
    Document   : dashboard
    Created on : 19/11/2024, 12:30:00 p.m.
    Author     : alberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }
        p {
            font-size: 18px;
            margin-bottom: 30px;
        }
        .buttons {
            display: flex;
            justify-content: center;
            gap: 10px;
        }
        .buttons a {
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }
        .btn-compra {
            background-color: #007bff;
        }
        .btn-compra:hover {
            background-color: #0056b3;
        }
        .btn-venta {
            background-color: #28a745;
        }
        .btn-venta:hover {
            background-color: #218838;
        }
        .btn-perfil {
            background-color: #ffc107;
        }
        .btn-perfil:hover {
            background-color: #e0a800;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Bienvenido, <c:out value="${usuario.nombre}"/>!</h1>
        <p>Desde aquí puedes gestionar tus compras, ventas y perfil.</p>

        <div class="buttons">
            <a href="regcompra.jsp" class="btn-compra">Registrar Compra</a>
            <a href="regventa.jsp" class="btn-venta">Registrar Venta</a>
            <a href="ediperfil.jsp" class="btn-perfil">Editar Perfil</a>
        </div>
    </div>
        
<di>

</di>
</body>
</html>
