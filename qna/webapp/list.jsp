<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ include file="/include/tags.jspf"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/header.jspf"%>
</head>

<body>
    <div id="header">
        <div id="title">
            <h2><a href="/list.next">Java Web Programming 실습</a></h2>
        </div>
    </div>
     
    <div id="main">
    <div class="older-posts">    
	<c:forEach items="${questions}" var="each">
	  <div class="post">
	      <h2 class="post-title">
	          <a href="/show.next?questionId=${each.questionId}">${each.title}</a>
	      </h2>
	      <div class="post-metadata">
	          <span class="post-author">${each.writer}</span>
	          <span class="post-date"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${each.createdDate}" /></span>
	          <span class="post-comments"></span>
	      </div>
	  </div>
	</c:forEach>
	</div>

  	<br /> <a href="/form.next">질문하기</a> 
    </div>
</body>
</html>