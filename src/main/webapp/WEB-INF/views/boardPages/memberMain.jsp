<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="section">
    ${sessionScope.loginEmail} 님 환영해요!

    <button onclick="update()">개인정보 관리하기</button>


</div>
</body>
<script>
    const update=()=>{
        location.href="/update";
    }
</script>
</html>
