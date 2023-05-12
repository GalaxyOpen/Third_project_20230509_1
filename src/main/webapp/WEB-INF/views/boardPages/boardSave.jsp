
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
    <form action="/board/save" method="post" enctype="multipart/form-data">
        제목 : <input type="text" name="boardTitle" placeholder="제목"><br>
        <input type="text" id="board_memberId" name="memberId" value="${board.memberid}" style="display:none;"><br>
        작성자 : <input type="text" name="boardWriter" value="${sessionScope.loginEmail}"><br>
        글 내용 : <textarea name="boardContents" cols="30" rows="10" placeholder="내용을 입력하세요"></textarea><br>
        파일 첨부 : <input type="file" name="boardFile" multiple><br>
        <input type="submit" value="작성">
        <a href="/">처음으로 돌아가기</a>
</form>
<%@include file="../component/footer.jsp"%>
</body>
</html>
