<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
    <h1>Current Auctions</h1>  

    <h3>Hi, ${ user.name }</h3>
    <p> <a href="/users/" >Logout</a></p>
	
	<h2>Tasks</h2>
	<table border="1">
		<thead>
			<th>Product</th>
			<th>Seller</th>
			<th>Top Bid</th>
            <th>End Date</th>
 
		</thead>
		<tbody>
		<c:forEach var="product" items="${ products }">
		<tr>
			<td> <a href="products/${ product.id }">${product.name} </a> </td>
			<td>  ${product.user.name} </td>
			<td> ${ product.bid } </td>
			<td>  ${product.endDate}  </td>
		
		</tr>
		</c:forEach>


		</tbody>
	</table>

	<button><a href="/users/add">Sell your stuff</a></button>
</body>
</html>