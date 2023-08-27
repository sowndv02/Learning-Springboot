<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Student Detail</h1>
<h3>Information</h3>
<h4>StudentId: ${student.studentId}</h4>
<%-- 
    path: * là tất cả thuộc tính còn không thì phải liệt kê
    element: Thẻ hiển thị thông báo lỗi
    delimiter: Phân cách lỗi
 --%>

<h4>Student Name: ${student.studentName}</h4>
<h4>Mark: ${student.mark}</h4>
<h4>Position: ${student.position}</h4>
<h4>Country: ${student.country.id}</h4>
<h4>Hobbies: ${student.hobbies}</h4>
<h4>Email: ${student.email}</h4>
<h4>PhoneNumber: ${student.phoneNumber}</h4>
<h4>Dob: ${student.dob}</h4>

<img src="/images/${student.imgUrl }" alt="Girl in a jacket" width="500" height="600">
<p>
	<a href="/students/edit/${student.studentId}">Edit Student</a>
</p>	
<hr>
<a href="/">Index</a>
</body>
</html>