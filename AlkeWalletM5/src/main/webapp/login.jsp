<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link href="css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class=container>
		<div class=login>
			<h1>Iniciar Sesión</h1>
			<div class=datos>
				<input type="hidden" id="status"
					value="<%=request.getAttribute("status")%>">
				<form action="login" method="post">
					<label for="correo">Correo:</label> <input type="email" id="correo"
						name="correo" placeholder="Ingrese Correo" required> <label
						for="contrasena">Contraseña:</label> <input type="password"
						id="contrasena" name="contrasena" placeholder="*****" required>
					<input class ="btn btn-primary" type="submit" value="Acceder">
				</form>
			</div>
			<a href="registrarse">Registrarse</a>
		</div>
		<div class=version>
			<p>Version: 1.0 - 2024</p>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script type="text/javascript">
		var status = document.getElementById("status").value;
		if (status == "failed") {
			swal.fire("Error", "Correo o contraseña equivocadas", "error");
		}
	</script>
</body>
</html>