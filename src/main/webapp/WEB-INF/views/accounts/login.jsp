<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<base href="/"> 

</head>
<body>
<h1>Login Form</h1>

<hr>
	<form action="accounts/login" method="post">
		UserName: <input type="text" name="username" placeholder="Enter username"/> <br>
		Password: <input type="password" name="password" placeholder="Enter password" /> <br>
		<label for=""><input type="checkbox" name="remember" /> Remember</label> <br>
		<button>Login</button>
	</form>
    <h3>${message}</h3>
</body>
</html>