<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<title>Author Management</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

	<%@ include file="/header/header.jsp"%>


	<div id="container">

		<div id="content">
			<form action="AuthorServletController" method="get">
				<!-- put new button: Add BookStore -->

				<a href="AuthorServletController?command=NEW">Add new Author</a>
				</br></br>
				<table>

					<tr>
						<th>Author Name</th>
						<th>DOB</th>
						<th>Action</th>
					</tr>

					<c:forEach var="tempAuthor" items="${AUTHOR_LIST}">

						<!-- set up a link for each student -->
						<c:url var="tempLink" value="AuthorServletController">
							<c:param name="command" value="LOAD" />
							<c:param name="authorId" value="${tempAuthor.id}" />
						</c:url>

						<c:url var="deleteLink" value="AuthorServletController">
							<c:param name="command" value="DELETE" />
							<c:param name="authorId" value="${tempAuthor.id}" />
						</c:url>

						<tr>
							<td>${tempAuthor.name}</td>
							<td>${tempAuthor.dobString}</td>
							<td><a href="${tempLink}">Update</a> | <a
								href="${deleteLink}"
								onclick="if (!(confirm('Are you sure you want to delete this author?'))) return false">
									Delete</a></td>
						</tr>

					</c:forEach>

				</table>
				<%
					int order_page_count = (Integer) request.getAttribute("order_page_count");
					for (int i = 1; i <= order_page_count; i++) {
				%>
				<c:url var="page" value="AuthorServletController">
					<c:param name="name" value="${search_name}" />
				</c:url>

				<%
					if (i < order_page_count) {
				%>
				<a href="${page}&page=<%=i%>"> <%=i%>,
				</a>

				<%
					} else {
				%>
				<a href="${page}&page=<%=i%>"> <%=i%></a>

				<%
					}
					}
				%>
				</br></br></br>
				<p style="color: red">Search Author By Name</p>
				Tên:<input type="text" name="name" value="${search_name}">
				<input type="submit" value="Tìm kiếm" />
				
			</form>
		</div>

	</div>
</body>


</html>








