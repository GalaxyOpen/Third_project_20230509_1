<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../resources/css/main.css">
</head>
<body>
<%@include file="../component/header.jsp"%>
<%@include file="../component/nav.jsp"%>
<div id="section">
    ${sessionScope.loginEmail} 님 환영해요!

    <button onclick="update()">개인정보 관리하기</button><br>
    <a href="/board/save">글 작성하기</a><br>
    <a href="/board/paging">글 목록보기</a><br>
    <a href="/member/logout">로그아웃</a><br>

<c:if test="${sessionScope.loginEmail == 'admin'}">

    <a href="/member/admin">회원목록</a><br>

 </c:if>
        <a href="/">처음으로 돌아가기</a><br>
</div>
<%@include file="../component/footer.jsp"%>
</body>
<script>
    const update=()=>{
        location.href="/member/update";
    }
    const member_delete =(id)=>{
        location.href="/member/delete?id="+id;
    }
    const member_detail_ajax = (id) => {
        const resultArea = document.getElementById("detail-area")
        $.ajax({
            type: "get",
            url: "/detail_ajax",
            data: {
                "id": id
            },
            success: function(res) {
                let result = "<table>";
                result += "<tr>";
                result += "<td>" + res.memberEmail + "</td>";
                result += "<td>" + res.memberName + "</td>";
                result += "<td>" + res.memberBirth + "</td>";
                result += "<td>" + res.memberMobile + "</td>";
                result += "</tr>";
                result += "</table>";
                resultArea.innerHTML = result;
            },
            error: function(){
                alert("일치하는 정보가 없습니다")
            }
        })
    }
</script>


</html>
