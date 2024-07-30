package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdServlet")
public class AdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        Random rand = new Random();
        int bonusPoints = rand.nextInt(1000) + 1;

        try {
            Connection connection = Common.getConnection();
            String query = "SELECT point FROM member WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int point = rs.getInt("point");
                point += bonusPoints;

                query = "UPDATE member SET point = ? WHERE id = ?";
                pstmt = connection.prepareStatement(query);
                pstmt.setInt(1, point);
                pstmt.setString(2, username);
                pstmt.executeUpdate();

                session.setAttribute("point", point);
                response.getWriter().println(bonusPoints);
            }
            rs.close();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
