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
<h1>Register Form</h1>

<hr>
	<form action="accounts/register" method="post" enctype="multipart/form-data">
		UserName: <input type="text" name="username" placeholder="Enter username"/> <br>
		Password: <input type="password" name="password" placeholder="Enter password" /> <br>
		Image: <input type="file" name="img"/><br>
		<button>Register</button>
	</form>
    <h3>${message}</h3>
</body>
</html>