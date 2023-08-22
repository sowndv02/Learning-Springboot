<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> <!--   Khai báo sử dụng taglib -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<base href="/">  <strong>Căn cứ vào đường dẫn gốc </strong>
</head>
<body>
	<h1>New student</h1>
	<hr>
	<!-- <form action="students/saveOrUpdate" method="post">
		StudentID: <input type="text" name="studentId"> <br>
		Student Name: <input type="text" name="studentName"> <br>
		<button>Save</button>
		<button formmethod="get" formaction="students/list">List</button>
		<button formmethod="get" formaction="students/search">Search</button>
	</form> -->

	<form:form action="students/saveOrUpdate" method="POST" enctype="multipart/form-data" modelAttribute="student" >
		StudentID: <form:input path="studentId" /> 
		<form:errors path="studentId" />
		<br/>
		StudentName: <form:input path="studentName" /><form:errors path="studentName" /><br/>
		Email: <form:input path="email" /><form:errors path="email" /><br/>
		PhoneNumber: <form:input path="phoneNumber" /><form:errors path="phoneNumber" /><br/>
		Dob: <form:input path="dob" /><br/>
		Mark: <form:input path="mark" /><br/>
		Position: <form:radiobuttons path="position" items="${positions}" delimiter=" " /><br/>
		Hobbies: <form:checkboxes path="hobbies" items="${hobbies}" delimiter=" " /><br/>
		Country: <form:select path="country.id" cssClass="form-control">
			<form:options items="${countries}" itemValue="id" itemLabel="name" />
		</form:select><br/>
		Img: <input type="file" name= "imgFile"/> <br/>
		<form:button>Save</form:button>
	</form:form>

		
</body>
</html>