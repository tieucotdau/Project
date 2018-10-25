<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
<title>Update Book</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">

</head>

<body>
	<%@ include file = "/header/header.jsp" %>
	<div id="container">
		<h3>Update Book</h3>

		<form action="BookControllerServlet" method="GET">

			<input type="hidden" name="command" value="UPDATE" /> <input
				type="hidden" name="bookId" value="${the_Book.id}" />

			<table>
				<tbody>
					<tr>
						<td><label>Name:</label></td>
						<td><input type="text" name="name"
							value="${the_Book.name}" /></td>
					</tr>
					
					<tr>
						<td><label>Price:</label></td>
						<td><input type="text" name="price"
							value="${the_Book.price}" /></td>
					</tr>
					
					<tr>
						<td><label>Category:</label></td>
						<td> <select name ="categoryName">
						 	 <option value="${the_Book.category.id}" selected>${the_Book.category.name}</option>
							<c:forEach var="row" items="${category_List}">  
								<c:if test="${row.id != the_Book.category.id}"> 
	      							<option    value='<c:out value="${row.id}" />'>${row.name}</option>
	      						</c:if>
							</c:forEach>
						</select>
						</td>
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
			<a href="BookControllerServlet">Back to List</a>
		</p>
	</div>
</body>

</html>











