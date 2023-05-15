<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href="../resources/css/main.css">
  <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
</head>
<body>
<%@include file="../component/header.jsp"%>
<%@include file="../component/nav.jsp"%>
<div id="section">
  <table>
    <tr>
      <th>id</th>
      <td>${member.id}</td>
    </tr>
    <tr>
      <th>email</th>
      <td>${member.memberEmail}</td>
    </tr>
    <tr>
      <th>password</th>
      <td>${member.memberPassword}</td>
    </tr>
    <tr>
      <th>name</th>
      <td>${member.memberName}</td>
    </tr>
    <tr>
      <th>mobile</th>
      <td>${member.memberMobile}</td>
    </tr>
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
  </table>
</div>
<%@include file="../component/footer.jsp"%>
</body>
</html>
