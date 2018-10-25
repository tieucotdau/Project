<!DOCTYPE html>
<html>

<head>
	<title>Update Author</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">

</head>

<body>
	<%@ include file = "/header/header.jsp" %>
	
	<div id="container">
		<h3>Update Author</h3>
		
		<form action="AuthorServletController" method="GET">
		
			<input type="hidden" name="command" value="UPDATE" />

			<input type="hidden" name="authorId" value="${the_Author.id}" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Name:</label></td>
						<td><input type="text" name="name" 
								   value="${the_Author.name}" /></td>
					</tr>
					<tr>
						<td><label>DOB:</label></td>
						<td><input type="text" name="dob" 
								   value="${the_Author.dobString}" /></td>
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
			<a href="AuthorServletController">Back to List</a>
		</p>
	</div>
</body>

</html>











