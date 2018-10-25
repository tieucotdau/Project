<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>Add Book</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">

</head>

<body>
	<%@ include file = "/header/header.jsp" %>
	
	<div id="container">
		<h3>Add Book</h3>
		
		<form action="BookControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="ADD" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Book Name:</label></td>
						<td><input type="text" name="name" /></td>
					</tr>
					<tr>
						<td><label>Book Price:</label></td>
						<td><input type="text" name="price" /></td>
					</tr>
					<tr>
						<td><label>Category:</label></td>
						 <td><select name ="categoryName">
							<c:forEach var="row" items="${category_List}">  
	      							<option    value='<c:out value="${row.id}" />'>${row.name}</option>
							</c:forEach>
						</select>
						<td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
			<a href="./BookControllerServlet">Back to List</a>
		</p>
	</div>
</body>

</html>











