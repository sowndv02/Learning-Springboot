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
<h1>Edit Student</h1>

<hr>
	<form action="students/update" method="post">
		StudentID: <input type="text" name="studentId" value="${student.studentId }"> <br>
		Student Name: <input type="text" name="studentName" value="${student.studentName }"> <br>
		<button>Update</button>
		<button formmethod="get" formaction="students/list">List</button>
		<button formmethod="get" formaction="students/search">Search</button>
	</form>
</body>
</html>