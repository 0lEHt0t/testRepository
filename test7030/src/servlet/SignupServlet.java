package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");

        try {
            Connection connection = Common.getConnection();

            // 확인용 로그 출력
            System.out.println("데이터베이스 연결 성공");

            String sql = "INSERT INTO member (id, password, name, point) VALUES (?, ?, ?, 1000)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, name);
            statement.executeUpdate();

            // 확인용 로그 출력
            System.out.println("회원가입 성공");

            statement.close();
            connection.close();

            response.getWriter().println("<script>alert('가입되었습니다. 로그인 해주세요.'); location.href='login.jsp';</script>");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<script>alert('오류가 발생했습니다. 다시 시도해주세요.'); location.href='signup.jsp';</script>");
        }
    }
}
