<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<title>Feed Items</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h3>5 Most Recent Articles</h3>
<c:if test="${!empty listMessagesById}">
	<table class="tg">
	<tr>
		<th width="10">ID</th>
		<th width="200">TITLE</th>
		<th width="300">LINK</th>
		<th width="800">DESCRIPTION</th>
		<th width="80">PUBLISHED</th>
	</tr>
	<c:forEach items="${listMessagesById}" var="messages">
		<tr>
			<td>${messages.id}</td>
			<td>${messages.feed_title}</td>
			<td><a href="<c:url value='${messages.link}' />" >${messages.link}</a></td>
			<td>${messages.description}</td>
			<td>${messages.published}</td>
		</tr>
	</c:forEach>
	</table>
</c:if>

</body>
</html>