<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../resources/css/main.css">
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body>
<%@include file="../component/header.jsp"%>
<%@include file="../component/nav.jsp"%>
<div id="section">
    <h2>글 수정</h2>
    <form action="/board/update" method="post" name="updateForm">
        글 번호 : <input type="text" name="id" value="${board.id}" readonly><br>
        글 제목 : <input type="text" name="boardTitle" placeholder="수정할 글 제목"><br>
        작성자 : <input type="text" name="boardWriter" value="${board.boardWriter}" readonly><br>
        글 내용 : <textarea name="boardContents"  cols="30" rows="10">${board.boardContents}</textarea>
        <input type="submit" value="수정">
        <a href="/">처음으로 돌아가기</a>
    </form>
</div>
<%@include file="../component/footer.jsp"%>
</body>
</html>
