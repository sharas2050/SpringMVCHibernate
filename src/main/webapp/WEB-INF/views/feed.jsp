<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<title>RSS Feeds</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1 style="display:inline">Add XML RSS Feed</h1>
<sub style="display:block;font-style: italic;">Please provide new XML RSS Feed information</sub>
<br>

<c:url var="addAction" value="/feeds/add" ></c:url>

<form:form action="${addAction}" commandName="feed">
<table>
	<c:if test="${!empty feed.url}">
	<tr>
		<td>
			<form:label path="id">
				<spring:message text="ID"/>
			</form:label>
		</td>
		<td>
			<form:input path="id" readonly="true" size="8"  disabled="true" />
			<form:hidden path="id" />
		</td> 
	</tr>
	</c:if>
	<tr>
		<td>
			<form:label path="url">
				<spring:message text="XML RSS Feed URL"/>
			</form:label>
		</td>
		<td>
			<form:input path="url" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="feed_name">
				<spring:message text="XML RSS Feed Name"/>
			</form:label>
		</td>
		<td>
			<form:input path="feed_name" />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${empty feed.url}">
				<input type="submit"
					value="<spring:message text="Add Feed"/>" />
			</c:if>
		</td>
	</tr>
</table>	
</form:form>
<br>
<h3 style="display:inline">XML RSS Feed</h3>
<sub style="display:block;font-style: italic;">Please find the list of all available feeds</sub>
<br>
<c:if test="${!empty listFeeds}">
	<table class="tg">
	<tr>
		<th width="20">ID</th>
		<th width="120">URL</th>
		<th width="240">TITLE</th>
		<th width="120">LAST_UPDATE</th>
        <th width="120">FEED_NAME</th>
        <th width="120">LINK_TO_DETAILS</th>
	</tr>
	<c:forEach items="${listFeeds}" var="feed">
		<tr>
			<td>${feed.id}</td>
			<td>${feed.url}</td>
			<td>${feed.title}</td>
		    <td>${feed.last_update}</td>
		    <td>${feed.feed_name}</td>
		    <td><a href="<c:url value='/feeds/details/${feed.id}' />" >Details</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>
