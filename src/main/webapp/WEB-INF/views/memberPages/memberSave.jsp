<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../resources/css/main.css">
</head>
<body>
<%@include file="../component/header.jsp"%>
<%@include file="../component/nav.jsp"%>
<div id="section">
    <form action="/member/save" method="post" enctype="multipart/form-data">
       Email: <input type="text" id="memberEmail" name="memberEmail" onblur="email_duplicate_check()" placeholder="아이디를 입력해주세요">
        <p id="email-area"></p>
       Password: <input type="text" id="memberPassword" name="memberPassword" onblur="password_check()" placeholder="비밀번호를 입력해주세요">
        <p id="password-area"></p>
        Name: <input type="text" id="memberName" name="memberName" placeholder="이름을 입력해주세요">
        <p id="name-area"></p>
        Phone number : <input type="text" id="memberMobile" name="memberMobile" placeholder="010-xxxx-xxxx">
        <p id="mobile-area"></p>
        Photo : <input type="file" id="memberProfile" name="memberProfile" multiple>
        <input type="submit" value="회원가입">
    </form>
</div>
<%@include file="../component/footer.jsp"%>
</body>
    <script>
        // const email_check =()=> {
        //     const email = document.getElementById("memberEmail").value;
        //     const emailcheck = document.getElementById("email-area");
        //     const exp = /^(?=.*[a-z])(?=.*\d)[a-z\d]{8,16}$/
        //
        //     if (email.length == 0) {
        //         emailcheck.innerHTML = "필수입력입니다.";
        //         emailcheck.style.color = "red";
        //     } else if (!email.match(exp)) {
        //         emailcheck.innerHTML = "8~16자리 이내의 소문자와 숫자만 가능합니다.";
        //         emailcheck.style.color = "red";
        //     } else {
        //         emailcheck.innerHTML = "아이디 사용이 가능합니다.";
        //         emailcheck.style.color = "green";
        //     }
        // }
        const email_duplicate_check =()=>{
            const typingEmail = document.getElementById("memberEmail").value;
            const email_check = document.getElementById("email-area");
            $.ajax({
                type: "post",
                url: "/email_check",
                data: {
                    "memberEmail":typingEmail
                },
                success: function(){
                        email_check.innerHTML="사용가능한 이메일 주소입니다.";
                        email_check.style.color="green";
                },
                error :function(){
                    email_check.innerHTML="중복되었거나 올바르지 않은 이메일 주소입니다.";
                    email_check.style.color="red";
                }
            })
        }

        const password_check =()=>{
            const password = document.getElementById("memberPassword").value;
            const passwordcheck = document.getElementById("password-area");
            const exp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[-_!#])[A-Za-z\d-_!#]{8,16}$/;

            if(password.length==0){
                passwordcheck.innerHTML="비밀번호를 입력해주세요";
                passwordcheck.style.color="red";
            }else if(!password.match(exp)){
                passwordcheck.innerHTML="8~16자리 사이 특수문자 및 대소문자 포함 숫자를 반드시 입력해주세요."
                passwordcheck.style.color="red";
            }else{
                passwordcheck.innerHTML="비밀번호 사용이 가능합니다.";
                passwordcheck.style.color= "green";
            }
        }
        const mobile_check=()=>{
            const mobile=document.getElementById("memberMobile").value;
            const mobilecheck =document.getElementById("mobile-area")
            const exp = /^\d{3}-\d{4}-\d{4}$/
            if(mobile.match(exp)){
                mobilecheck.innerHTML="전화번호가 입력되었습니다.";
                mobilecheck.style.color="green";
            }else{
                mobilecheck.innerHTML="형식에 맞게 다시 입력해주세요.";
                mobilecheck.style.color="red";
            }
        }
    </script>
</html>
