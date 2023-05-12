<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../resources/css/main.css">
</head>
<body>
<%@include file="./component/header.jsp"%>
<%@include file="./component/nav.jsp"%>
<div id="section">
<c:choose>
    <c:when test="${sessionScope.loginEmail == null}">
        <a href="/board/paging">글목록</a>
        <a href="/member/save">회원가입</a>
        <a href="/member/login">로그인</a>
    </c:when>
    <c:otherwise>
        <a href="/board/paging">글목록</a>
        <a href="/member/myPage">마이페이지</a>
    </c:otherwise>
</c:choose>
    <c:if test="${sessionScope.loginEmail == 'admin'}">
        <a href="/member/admin">회원목록</a>
    </c:if>
</div>
<%@include file="./component/footer.jsp"%>

</body>
</html>