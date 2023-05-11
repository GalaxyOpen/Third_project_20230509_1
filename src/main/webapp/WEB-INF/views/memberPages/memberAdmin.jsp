<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
</head>
<body>
<div id="section">
    <h2>회원리스트</h2>
    <table>
        <tr>
            <th>번호</th>
            <th>이메일</th>
            <th>이름</th>
            <th>생년월일</th>
            <th>휴대폰번호</th>
            <th>조회</th>
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
</body>
<script>
    const member_delete =(id)=>{
        location.href="/delete?id="+id;

    }
</script>
</html>
