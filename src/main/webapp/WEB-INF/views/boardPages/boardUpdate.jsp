<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div id="section">
    <h2>글 수정</h2>
    <form action="/board/update" method="post" name="updateForm">
        글 번호 : <input type="text" name="id" value="${board.id}" readonly><br>
        글 제목 : <input type="text" name="boardTitle" placeholder="수정할 글 제목"><br>
        작성자 : <input type="text" name="boardWriter" value="${board.memberId}" readonly><br>
        글 내용 : <textarea name="boardContents"  cols="30" rows="10">${board.boardContents}</textarea>
        <input type="submit" value="수정">
        <a href="/">처음으로 돌아가기</a>
    </form>
</div>

</body>

</html>
