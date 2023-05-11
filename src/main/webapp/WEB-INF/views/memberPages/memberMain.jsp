<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="section">
    ${sessionScope.loginEmail} 님 환영해요!

    <button onclick="update()">개인정보 관리하기</button>
    <a href="/board/save">글 작성하기</a>
    <a href="/board/paging">글 목록보기</a>
    <a href="/member/logout">로그아웃</a>

</div>
</body>
<script>
    const update=()=>{
        location.href="/member/update";
    }
</script>


</html>
