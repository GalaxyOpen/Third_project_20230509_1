<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div id="section">
        <form action="/member/login" method="post">
        <input type="text" name="memberEmail" placeholder="Email"><br>
        <input type="text" name="memberPassword" placeholder="Password"><br>
        <input type="submit" value="로그인">
        </form>
    </div>
</body>
</html>
