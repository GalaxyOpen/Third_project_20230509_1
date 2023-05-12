<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="nav">
    <ul>
        <li>
            <a href="">
                <i class="bi bi-house"></i>
            </a>
        <c:choose>
        <c:when test="${sessionScope.loginEmail == null}">
        <li>
        <a href="/board/paging">글목록</a>
        </li>
        <li>
        <a href="/member/save">회원가입</a>
        </li>
        <li>
        <a href="/member/login">로그인</a>
        </li>
        </c:when>
        <c:otherwise>
        <li>
        <a href="/board/paging">글목록</a>
        </li>
        <li>
        <a href="/member/myPage">마이페이지</a>
        </li>
        </c:otherwise>
        </c:choose>

        <c:if test="${sessionScope.loginEmail == 'admin'}">
        <li>
        <a href="/member/admin">회원목록</a>
        </li>
        </c:if>
</div>
<%--        <li class="login-name" id="login-area">--%>
<%--            <c:choose>--%>
<%--                <c:when test="${sessionScope.loginEmail != null}">--%>
<%--                    <a href="/mypage" style="color: black;">${sessionScope.loginEmail} 님 환영해요!</a>--%>
<%--                    <a href="/logout">logout</a>--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    <a href="/login">login</a>--%>
<%--                </c:otherwise>--%>
<%--            </c:choose>--%>
<%--        </li>--%>

        <li class="login-name" id="login-area">

        </li>
    </ul>
</div>
<script>
    const loginArea = document.getElementById("login-area");
    const loginEmail= '${sessionScope.loginEmail}'
    console.log(loginEmail.length);

    if(loginEmail.length !=0){
        loginArea.innerHTML = "<a href='/mypage>' style='color: black;'>"+loginEmail +"님 어서오세요!</a>"+
                                "<a href='/member/logout'>logout</a>";
    }else {
        loginArea.innerHTML = "<a href='/board/save'>글쓰기</a>";
    }
</script>