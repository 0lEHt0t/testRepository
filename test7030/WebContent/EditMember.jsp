<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
</head>
<body>
    <h1>회원 정보 수정</h1>
    <form action="UpdateMemberServlet" method="post" accept-charset="UTF-8">
        <input type="hidden" name="id" value="<%= request.getParameter("id") %>" />
        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="password" required /><br>
        <label for="name">이름:</label>
        <input type="text" id="name" name="name" required /><br>
        <label for="point">포인트:</label>
        <input type="number" id="point" name="point" required /><br>
        <input type="submit" value="수정" />
    </form>
    <button onclick="window.location.href='AdminServlet'">취소</button>
</body>
</html>
