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

@WebServlet("/PurchaseServlet")
public class PurchaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String course = request.getParameter("course");

        int coursePrice = 0;
        switch (course) {
            case "intro": coursePrice = 100000; break;
            case "java": coursePrice = 500000; break;
            case "c++": coursePrice = 300000; break;
        }

        try {
            Connection connection = Common.getConnection();
            String query = "SELECT point FROM member WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int point = rs.getInt("point");
                if (point < coursePrice) {
                    response.getWriter().println("포인트가 부족합니다. 광고를 클릭하세요.");
                } else {
                    point -= coursePrice;
                    query = "UPDATE member SET point = ? WHERE id = ?";
                    pstmt = connection.prepareStatement(query);
                    pstmt.setInt(1, point);
                    pstmt.setString(2, username);
                    pstmt.executeUpdate();

                    session.setAttribute("point", point);
                    response.getWriter().println("컨텐츠(" + course + ")를 구입하였습니다.");
                }
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
