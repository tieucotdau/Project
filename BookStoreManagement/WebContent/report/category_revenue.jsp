<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<title>BookManagement</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

	<%@ include file = "/header/header.jsp" %>
	
	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add BookStore -->
			
			Top Revenue By Category
			</br></br>
			<table>
			
				<tr>
					<th>Category Name</th>
					<th>Revenue</th>
					
				</tr>
				
				<c:forEach var="tempCategory" items="${category_List}">
					<tr>
						<td> ${tempCategory.name} </td>
						<td> ${tempCategory.revenue}</td>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
</body>


</html>
