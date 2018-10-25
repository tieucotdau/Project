<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>Category Management</title>
<link type="text/css" rel="stylesheet" href="css/style.css">

</head>

<body>

	<%@ include file = "/header/header.jsp" %>

	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add BookStore -->
			
			<a href="CategoryControllerServlet?command=NEW">Add new Category</a>
			</br></br>
			<table>
			
				<tr>
					<th>Category Name</th>
					<th>Action</th>
					
				</tr>
				
				<c:forEach var="tempCategory" items="${CATEGORY_LIST}">
					
					<!-- set up a link for each student -->
					<c:url var="tempLink" value="CategoryControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="categoryId" value="${tempCategory.id}" />
					</c:url>

					<c:url var="deleteLink" value="CategoryControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="categoryId" value="${tempCategory.id}" />
					</c:url>
																		
					<tr>
						<td> ${tempCategory.name} </td>
						<td> 
							<a href="${tempLink}">Update</a> 
							 | 
							<a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this category?'))) return false">
							Delete</a>	
						</td>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
</body>


</html>








