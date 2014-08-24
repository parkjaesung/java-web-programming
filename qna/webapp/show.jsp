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
	<div class="post">
	    <h2 class="post-title">
	        <a href="">${question.title}</a>
	    </h2>
	    <div class="post-metadata">
	        <span class="post-author">${question.writer}</span>,
	        <span class="post-date"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${question.createdDate}" /></span>
	    </div>
	    <div class="post-content">
	        <div class="about">내용 : </div>
	        ${question.contents}
	    </div>
	</div>    

  	<br /> <a href="/list.next">목록으로</a>
  	
	<h3>답변</h3>
	<div class="answerWrite">
	<form method="post">
	<input type="hidden" name="questionId" value="${question.questionId}">
    <p>
        <label for="author">이름: </label>
        <input type="text" name="writer" id="writer" />
    </p>
    <p>
        <label for="content">내용 : </label>
        <textarea name="content" id="content"></textarea>
    </p>
    <p>
        <input type="submit" value="저장" />
    </p>
    </form>
    </div>
    
    <!-- comments start -->
	<div class="comments">
	    <h3>
	        댓글 수 : ${question.countOfComment}
	    </h3>
	        <div class="comment">
	            <div class="comment-metadata">
	                <span class="comment-author">by Toby Lee,</span>
	                <span class="comment-date">
	                    2014-06-17 16:40:54
	                </span>
	            </div>
	            <div class="comment-content">
	                <div class="about">내용 : </div>
	                람다식에서 사용되는 변수라면 람다식 내부에서 정의된 로컬 변수이거나 람다식이 선언된 외부의 변수를 참조하는 것일텐데, 전자라면 아무리 변경해도 문제될 이유가 없고, 후자는 변경 자체가 허용이 안될텐데. 이 설명이 무슨 뜻인지 이해가 안 됨.
	            </div>
	        </div>	    
	    
	        <div class="comment">
	            <div class="comment-metadata">
	                <span class="comment-author">by 강우,</span>
	                <span class="comment-date">
	                    2014-06-17 16:40:54
	                </span>
	            </div>
	            <div class="comment-content">
	                <div class="about">내용 : </div>
	                저도 잘은 모르겠지만, 그냥 몇글자 적어볼께요.
					일단 변수의 생명 주기랑, 값이 아닌 레퍼런스에 의한 부수효과는 무시하고,
					쓰레드 관점에서만 볼때에,
					간단히 생각하면, 서블릿에서 인스턴스 변수를 사용하는 것은 쓰레드에 안전할까요? 안전하지 않을까요?
					저는 같은 맥락인거 같은데 ^^;;
					아 그러고 보니 "안정"이라고 되어있네요. 저건 다른 의미인가.. ^^;;
	            </div>
	        </div>	    
	</div>
	<!-- comments end -->
	
    </div>
    <%@ include file="/include/footer.jspf"%>
</body>
</html>