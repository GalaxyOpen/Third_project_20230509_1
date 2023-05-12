<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../resources/css/main.css">
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body>
<%@include file="../component/header.jsp"%>
<%@include file="../component/nav.jsp"%>
<div id="section">
    <h2>회원리스트</h2>
    <table>
        <tr>
            <th>번호</th>
            <th>이메일</th>
            <th>이름</th>
            <th>휴대폰번호</th>
            <th>삭제</th>
        </tr>
        <c:forEach items="${memberList}" var ="member">
            <tr>
                <td onclick="member_detail_ajax('${member.id}')">${member.id}</td>
                <td>${member.memberEmail}</td>
                <td>${member.memberName}</td>
                <td>${member.memberMobile}</td>
            <c:if test="${member.fileAttached == 1}">
                <tr>
                    <th>image</th>
                    <td>
                        <c:forEach items ="${memberFileList}" var="memberFile">
                            <img src="${pageContext.request.contextPath}/upload/${memberFile.storedFileName}"
                                 alt="" width="100" height="100">
                        </c:forEach>
                    </td>
                </tr>
            </c:if>
                <td><button onclick="member_delete('${member.id}')">삭제</button></td>
            </tr>
        </c:forEach>
    </table>
    <div id="detail-area"></div>
    <a href="/">처음으로 돌아가기</a>
</div>
<%@include file="../component/footer.jsp"%>
</body>
<script>
    const member_delete =(id)=>{
        location.href="/member/delete?id="+id;

    }
    const member_detail_ajax = (id) => {
        const resultArea = document.getElementById("detail-area")
        $.ajax({
            type: "get",
            url: "/member/admin",
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
