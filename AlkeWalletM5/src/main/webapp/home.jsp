<%@page import="modelo.Usuario"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link href="css/home.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<%
	Usuario usuario = (Usuario) session.getAttribute("usuario");
	%>
	<input type="hidden" id="status"
		value="<%=session.getAttribute("status")%>">

	<h1>
		Bienvenido,
		<c:out value="${usuario.getNombre()} ${usuario.getApellido()}"></c:out>
	</h1>
	<h3 class=saldo>
		Tienes un saldo de:
		<c:out value="${usuario.getSaldo()}"></c:out>
	</h3>
	<%-- el action es la ruta a la que va a redirigir al momento de hacer submit --%>
	<div class=operaciones>
	<form action="operacion" method="post">
		<h4>Escoge la operación a realizar:</h4>
		<label for="operacion">Operación:</label> <label for="monto">Ingrese
			monto:</label> <input type="number" id="monto" name="monto"> <select
			id="operacion" name="operacion">
			<option value="depositar">Depositar</option>
			<option value="retirar">Retirar</option>
		</select> <input class="btn btn-primary - sm" type="submit" value="Enviar">
	</form>
	<button id="logout">
		<a href="logout">Cerrar Sesión</a>
	</button>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script type="text/javascript">
		var status = document.getElementById("status").value;
		if (status == "success") {
			swal.fire("Felicitaciones", "Operación exitosa", "success");
		} else if (status == "failed") {
			swal.fire("Error", "Operación fallida", "error");
		}
	</script>
</body>
</html>