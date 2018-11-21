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
		<h1>Sell your Stuff</h1>
		<p> <a href="/users/dashboard" >Home</a></p>
		<p> <a href="/users/" >Logout</a></p>
		
		
		<form:form action="/users/create" method="POST" modelAttribute="product">
    
        <p><form:label path="name"> Product Name: </form:label></p>
		<p><form:errors path="name"></form:errors> </p>
        <p><form:input path="name"></form:input></p>

        <p><form:label path="bid"> Starting Bid: </form:label></p>
		<p><form:errors path="bid"></form:errors> </p>
        <p><form:input path="bid"></form:input></p>
        
        <p><form:label path="description"> Description: </form:label></p>
		<p><form:errors path="description"></form:errors> </p>
        <p><form:input path="description"></form:input></p>
        
        
        <p><form:label path="endDate"> End Date: </form:label></p>
		<p><form:errors path="endDate"></form:errors> </p>
        <p><form:input type="date" path="endDate"></form:input></p>

        
      
		<p><input type="submit" value="Create" /></p>
		
		</form:form>
</body>
</html>