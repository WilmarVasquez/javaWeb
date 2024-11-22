<%-- 
    Document   : regcompra
    Created on : 19/11/2024, 10:05:38 p. m.
    Author     : alberto
--%>

<%@page import="modelo.Compra"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Compra</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
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
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .buttons {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
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
        <h1>Gestión de Compra</h1>
        
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
        
        <form action="<%= request.getContextPath() %>/CompraServlet" method="POST">
            <!-- Campo ID Compra -->
            <div class="form-group">
                <label for="idCompra">ID de Compra</label>
                <input type="number" id="idCompra" name="idCompra"
                       value="<%= request.getAttribute("compra") != null ? ((Compra) request.getAttribute("compra")).getIdCompra() : "" %>"
                       placeholder="Ingrese el ID de la compra">
            </div>
            
            <!-- Campos para registrar o editar una compra -->
            <div class="form-group">
                <label for="ticket">Ticket</label>
                <input type="text" id="ticket" name="ticket"
                       value="<%= request.getAttribute("compra") != null ? ((Compra) request.getAttribute("compra")).getTicket() : "" %>"
                       placeholder="Ingrese el ticket">
            </div>
            <div class="form-group">
                <label for="cantidad">Cantidad</label>
                <input type="number" id="cantidad" name="cantidad"
                       value="<%= request.getAttribute("compra") != null ? ((Compra) request.getAttribute("compra")).getCantidad() : "" %>"
                       placeholder="Ingrese la cantidad">
            </div>
            <div class="form-group">
                <label for="precioTicket">Precio por Ticket</label>
                <input type="number" step="0.01" id="precioTicket" name="precioTicket"
                       value="<%= request.getAttribute("compra") != null ? ((Compra) request.getAttribute("compra")).getPrecioTicket() : "" %>"
                       placeholder="Ingrese el precio por ticket">
            </div>
            <div class="form-group">
                <label for="totalInversion">Total de Inversión</label>
                <input type="number" step="0.01" id="totalInversion" name="totalInversion"
                       value="<%= request.getAttribute("compra") != null ? ((Compra) request.getAttribute("compra")).getTotalInversion() : "" %>"
                       placeholder="Total de la inversión" readonly>
            </div>

            <!-- Botones para acciones -->
            <div class="buttons">
                <button type="submit" name="action" value="buscar">Buscar por ID</button>
                <button type="submit" name="action" value="agregar">Registrar</button>
                <button type="submit" name="action" value="actualizar">Editar</button>
                <button type="submit" name="action" value="eliminar">Eliminar</button>
                <button type="button" onclick="window.location.href='<%= request.getContextPath() %>/vista/dashboard.jsp'">Cancelar</button>
            </div>
        </form>
    </div>
</body>
</html>
