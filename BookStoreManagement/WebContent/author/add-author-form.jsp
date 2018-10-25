<!DOCTYPE html>
<html>

<head>
	<title>Add Author</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">

</head>
<body>
	<%@ include file = "/header/header.jsp" %>

	<div id="container">
		<form action="AuthorServletController" method="get">
			<input type="hidden" name="command" value="ADD" />
			<table>
				<tbody>
					<tr>
						<td><label>Author Name:</label></td>
						<td><input type="text" name="nameAuthor"></td>
					</tr>

					<tr>
						<td><label>Date of birth:</label></td>
						<td><input type="text" name="dobString"></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Add Author" class="save" /></td>
					</tr>

				</tbody>
				<p>
					<a href=AuthorServletController>Back to List</a>
				</p>
			</table>
		</form>
	</div>




</body>
</html>