<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link href="css/index.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<input type="hidden" id="status"
		value="<%=request.getAttribute("status")%>">
	<div class=container>
		<div class=registro>
			<h1>Registrar</h1>
			<form action="registrarse" method="post">
				<label for="nombre">Nombre:</label> <input type="text" id="nombre"
					name="nombre" placeholder="Ingresa Nombre" required> <label
					for="apellido">Apellido:</label> <input type="text" id="apellido"
					name="apellido" placeholder="Ingresa Apellido" required> <br>
				<label for="correo">Correo:</label> <input type="email" id="correo"
					name="correo" placeholder="ejemplo@gmail.com" required> <label
					for="contrasena">Contraseña:</label> <input type="password"
					id="contrasena" name="contrasena" placeholder="*****" minlength="4"
					required> <input class="btn btn-primary" type="submit" value="Enviar">
			</form>
			<a href="login">Iniciar sesión</a>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script type="text/javascript">
		var status = document.getElementById("status").value;
		if (status == "success") {
			swal.fire("Felicitaciones", "Cuenta creada", "Success");
		} else if (status == "failed") {
			swal.fire("Error", "No se pudo crear la cuenta", "Error");
		}
	</script>
</body>
</html>