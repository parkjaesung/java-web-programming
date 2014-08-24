<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ include file="/include/tags.jspf"%><!DOCTYPE html>
<html>
<head>
<%@ include file="/include/header.jspf"%>
</head>
<body>
	<div id="main">
		<c:set var="method" value="post" />
				
		<form name="questionForm" action="/save.next" method="${method}">
			<table>
				<tr>
					<td width="80">글쓴이</td>
					<td><input type="text" name="writer" size="40"/></td>
				</tr>			
				<tr>
					<td width="80">제목</td>
					<td><input type="text" name="title" size="70"/></td>
				</tr>
				<tr>
					<td width="80">내용</td>
					<td><textarea name="contents" rows="5" cols="130"></textarea></td>
				</tr>
			</table>
			<input type="submit" value="질문하기" />			
		</form>
	</div>
</body>
</html>