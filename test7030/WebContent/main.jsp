<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<style>
   body {
    font-family: Arial, sans-serif;
    background-color: #fff;
    margin: 0;
    padding: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
}

header {
    width: 100%;
    padding: 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 1px solid #ccc;
}

h1 {
    font-size: 2em;
    margin: 0;
}

.user-info {
    display: flex;
    align-items: center;
}

.user-info span {
    margin-right: 10px;
}

.user-info button {
    padding: 5px 10px;
    border: 1px solid #ccc;
    background-color: #f0f0f0;
    cursor: pointer;
}

main {
    width: 100%;
    max-width: 1200px;
    text-align: center;
    position: relative;
}

h2 {
    margin-top: 20px;
}

.courses {
    display: flex;
    justify-content: space-between;
    margin: 20px 0;
}

.course-card {
    width: 30%;
    border: 1px solid #ccc;
    border-radius: 5px;
    padding: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    text-align: left;
    cursor: pointer;
}

.course-image {
    height: 150px;
    display: flex;
    align-items: center;
    justify-content: center;
    font-weight: bold;
    color: white;
}

h3 {
    margin: 10px 0;
}

.rating {
    color: #f39c12;
}

.level {
    color: #7f8c8d;
}

.estimated-time {
    font-weight: bold;
}

.price {
    margin-top: 10px;
    font-weight: bold;
    text-align: center;
}

.ad {
    position: absolute;
    right: 20px;
}

.ad p {
    margin: 0;
}

.ad-image {
    height: 100px;
    width: 200px;
    margin-top: 5px;
    display: flex;
    align-items: center;
    justify-content: center;
    color: white;
    font-weight: bold;
    border: 1px solid #ccc;
    border-radius: 5px;
    cursor: pointer;
}
</style>
<script>
    function purchase(course) {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "PurchaseServlet", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
    	console.log("실행");
                alert(xhr.responseText);
            }
        };
        xhr.send("course=" + course);
    }

    function logout() {
        if (confirm("로그아웃 하시겠습니까?")) {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "LogoutServlet", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    alert("로그아웃 되었습니다.");
                    window.location.href = "login.jsp";
                }
            };
            xhr.send();
        }
    }

    function clickAd() {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "AdServlet", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var point = xhr.responseText;
                alert(point + "점이 적립되었습니다.");
                window.location.href = "https://koreaisacademy.com";
            }
        };
        xhr.send();
    }
</script>
</head>
<body>
<header>
    <h1>메인페이지</h1>
    <div class="user-info">
        <span><%= session.getAttribute("username") %>님 안녕하세요.</span>
        <span>포인트 : <%= session.getAttribute("point") %>점</span>
        <button onclick="logout()">로그아웃</button>
    </div>
</header>
<main>
    <h2>구입할 컨텐츠를 선택하세요.</h2>
    <div class="courses">
        <div class="course-card" onclick="purchase('intro')">
                <img src="img/intro.png" alt="Intro to Programming">
            <p class="price">100,000포인트</p>
        </div>
        <div class="course-card" onclick="purchase('java')">
                <img src="img/java.png" alt="Java Programming">
            <p class="price">500,000포인트</p>
        </div>
        <div class="course-card" onclick="purchase('c++')">
                <img src="img/cpp.png" alt="C++">
            <p class="price">300,000포인트</p>
        </div>
    </div>
    <div class="ad">
        <p><광고></p>
        <div class="ad-image" onclick="clickAd()">
        <img src="img/korea_it.png" alt="코리아IT아카데미학원" width="200px"/>
        </div>
    </div>
</main>
</body>
</html>
