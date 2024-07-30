<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
</head>
<body>
    <h2>회원가입</h2>
    <form action="SignupServlet" method="post">
        <label for="username">아이디:</label>
        <input type="text" id="username" name="username" required><br><br>
        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="password" required><br><br>
        <label for="name">이름:</label>
        <input type="text" id="name" name="name" required><br><br>
        <input type="submit" value="작성완료">
    </form>
</body>
</html>
