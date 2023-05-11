<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="section">
    <h2>회원 정보 수정</h2>
    <form action="/member/update" method="post" name="updateForm">
        회원번호 : <input type="text" name="id" value="${member.id}" readonly>
        ID :<input type="text" name="memberEmail" value="${member.memberEmail}" readonly placeholder="이메일"><br>
        Password : <input type="text" id="memberPassword" name="memberPassword" placeholder="비밀번호"><br>
        Name : <input type="text" name="memberName" value="${member.memberName}" placeholder="이름"><br>
        Mobile : <input type="text" name="memberMobile" value="${member.memberMobile}" placeholder="전화번호">
        <input type="button" onclick="update_check()" value="수정">
    </form>
</div>
</body>
<script>
    const update_check = () => {
        const inputPass = document.getElementById("memberPassword").value;
        const DBPass = '${member.memberPassword}';
        if (inputPass == DBPass) {
            document.updateForm.submit();

        } else {
            alert("비밀번호가 일치하지 않습니다!!");
        }
    }
</script>
</html>
