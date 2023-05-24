<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/main.css">
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
    <%-- 모멘트 라이브러리 --%>

    <style>
        #section a{
            display : block;
        }
        comment-area{
            align-content: center;
        }

        #heart{

        }

        #heart:active{
        <svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 0 24 24" width="24"><path d="M0 0h24v24H0z" fill="none"/><path d="M16.5 3c-1.74 0-3.41.81-4.5 2.09C10.91 3.81 9.24 3 7.5 3 4.42 3 2 5.42 2 8.5c0 3.78 3.4 6.86 8.55 11.54L12 21.35l1.45-1.32C18.6 15.36 22 12.28 22 8.5 22 5.42 19.58 3 16.5 3zm-4.4 15.55l-.1.1-.1-.1C7.14 14.24 4 11.39 4 8.5 4 6.5 5.5 5 7.5 5c1.54 0 3.04.99 3.57 2.36h1.87C13.46 5.99 14.96 5 16.5 5c2 0 3.5 1.5 3.5 3.5 0 2.89-3.14 5.74-7.9 10.05z"/></svg>
        }

    </style>
</head>
<body>
<%@include file="../component/header.jsp"%>
<%@include file="../component/nav.jsp"%>
<div id="section">
    <table>
        <tr>
            <th>id</th>
            <td>${board.id}</td>
        </tr>
        <tr>
            <th>writer</th>
            <td>${board.boardWriter}</td>
        </tr>
        <tr>
            <th>date</th>
            <td>
                <fmt:formatDate value="${board.boardCreatedDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
            </td>
        </tr>
        <tr>
            <th>hits</th>
            <td>${board.boardHits}</td>
        </tr>
        <tr>
            <th>title</th>
            <td>${board.boardTitle}</td>
        </tr>
        <tr>
            <th>contents</th>
            <td>${board.boardContents}</td>
        </tr>
        <c:if test="${board.fileAttached == 1}">
            <tr>
                <th>image</th>
                <td>
                    <c:forEach items ="${boardFileList}" var="boardFile">
                        <img src="${pageContext.request.contextPath}/upload/${boardFile.storedFileName}"
                             alt="" width="100" height="100">
                    </c:forEach>
                </td>
            </tr>
        </c:if>
    </table>
    <button onclick="board_list()">목록</button>

    <c:if test="${board.boardWriter == sessionScope.loginEmail}">
    <button onclick="board_update()">수정</button>
    <button onclick="board_delete()">삭제</button>
    </c:if>

        <div id="comment-area">
            <c:if test="${sessionScope.loginEmail != null}">
            <input type="text" id="comment-writer" placeholder="댓글 작성자 닉네임">
            <input type="text" id="comment-contents" placeholder="댓글 내용">
            </c:if>

            <button type="button" onclick="comment_write()">댓글 작성</button>
        </div>
    <div id="comment-list">
        <c:choose>
            <c:when test="${commentList == null}">
                <h3>작성된 댓글이 없습니다.</h3>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>id</th>
                        <th>작성자</th>
                        <th>내용</th>
                        <th>작성시간</th>
                    </tr>
                    <c:forEach items="${commentList}" var="comment">
                        <tr>
                            <td>${comment.id}</td>
                            <td>${comment.commentWriter}</td>
                            <td>${comment.commentContents}</td>
                            <td>
                                <fmt:formatDate value="${comment.commentCreatedDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                            </td>
                        </tr>

                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
    <div id="judgementArea">
        <div id="myHeart">
            <button onclick="goodUp()"<c:if test="${sessionScope.loginEmail==null}">disabled</c:if>><img id="img" src="/resources/images/full_heart.png" alt=""/>좋아요</button>
        </div>
    </div>
    <a href="/">처음으로 돌아가기</a>

</div>
<%@include file="../component/footer.jsp"%>
</body>
<script>
    const board_list =()=>{
        const type = '${type}';
        const q = '${q}';
        const page = '${page}';
        location.href="/board/paging?page="+page+"&type"+type+"&q"+q;
    }
    const board_update=()=>{
        const id='${board.id}'; // 보드의 아이디 변수 값을 가지고 와서 요청한다.
        location.href="/board/update?id="+id;
    }
    const board_delete=()=>{
        const id='${board.id}'; // 보드의 아이디 변수 값을 가지고 와서 요청한다.
        location.href="/board/delete-check?id="+id;
    }

    const comment_write=()=>{
        const commentWriter=document.getElementById("comment-writer").value;
        const commentContents = document.getElementById("comment-contents").value;
        const boardId = '${board.id}'
        const result = document.getElementById("comment-list");
        $.ajax({
            type: "post",
            url: "/comment/save",
            data: {
                "commentContents" : commentContents,
                "commentWriter" :  commentWriter,
                "boardId" : boardId
            },
            success: function(com){
                console.log(com);
                let output ="<table>";
                output+= "<tr>"
                output+= "<th>id</th>"
                output+= "<th>작성자</th>"
                output+= "<th>내용</th>"
                output+= "<th>작성시간</th>"
                output+= "</tr>"
                for(let i in com){
                    output +="<tr>";
                    output +="<td>"+com[i].id + "</td>";
                    output +="<td>"+com[i].commentWriter +"</td>";
                    output +="<td>"+com[i].commentContents+"</td>";
                    output +="<td>"+moment(com[i].commentCreatedDate).format("YYYY-MM-DD HH:mm:ss")+"</td>";
                    output +="</tr>"
                }
                output+="</table>";
                result.innerHTML = output;
                document.getElementById("comment-writer").value="";
                document.getElementById("comment-contents").value="";
            },
            error:function(){
                console.log("실패");
            }
        })

    }
    const goodUp=()=>{
        document.getElementById("img").src="/resources/images/heart.png";
        $.ajax({
            type: "post",
            url: "/goodUp",
            data: {
               "articleId" : '${board.id}',
                "memberId" : '${sessionScope.loginEmail}'
            },

            success:function(){
                console.log("좋아요 성공");
            },
            error:function(){
             console.log("좋아요 실패");

            }
    })
    }
    // HTML에서 버튼과 하트 요소를 찾습니다.



    <%--const goodDown=()=>{--%>
    <%--    const articleId = '${board.id}'--%>
    <%--    $.ajax({--%>
    <%--        type:"Post",--%>
    <%--    })--%>

    <%--}--%>

</script>
</html>
