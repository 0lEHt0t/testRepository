package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Connection connection = Common.getConnection();

            String sql = "SELECT * FROM member WHERE id = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                if ("admin".equals(username)) {
                    response.sendRedirect("AdminServlet");
                } else {
                	 HttpSession session = request.getSession();
                     session.setAttribute("username", username);
                     session.setAttribute("point", rs.getInt("point"));
                    response.sendRedirect("main.jsp");
                }
            } else {
                response.getWriter().println("<script>alert('아이디/비밀번호를 다시 확인하세요'); location.href='login.jsp';</script>");
            }

            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<script>alert('오류가 발생했습니다. 다시 시도해주세요.'); location.href='login.jsp';</script>");
        }
    }
}
