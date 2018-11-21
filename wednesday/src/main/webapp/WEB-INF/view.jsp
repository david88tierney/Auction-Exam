<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task</title>
</head>
<body>
	<!-- To display the product -->
		<h1>${ viewProduct.name }</h1>
		<p> <a href="/users/dashboard" >Home</a></p>
		<p> <a href="/users" >Logout</a></p>
		
		
		<p>Created by:  ${ viewProduct.user.name }</p>
		
		<p> End Date:  ${ viewProduct.endDate }  </p>

		<p>Description: ${ viewProduct.description }</p>
		


		<form:form action="/users/{id}/update" method="POST" modelAttribute="product">
		
        <p><form:label path="bid"> Enter Bid: </form:label></p>
		<p><form:errors path="bid"></form:errors> </p>
        <p><form:input path="bid"></form:input></p>
	
		<p><input type="submit" value="Bid!!" /></p>
		</form:form>
		
	

		
</body>
</html>