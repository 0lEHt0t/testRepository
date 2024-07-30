package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateMemberServlet")
public class UpdateMemberServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 request.setCharacterEncoding("UTF-8"); // 인코딩 설정
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        int point = Integer.parseInt(request.getParameter("point"));

        try {
            updateMember(id, password, name, point);
            response.sendRedirect("AdminServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void updateMember(String id, String password, String name, int point) throws Exception {
        try (Connection conn = Common.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("UPDATE member SET password = ?, name = ?, point = ? WHERE id = ?")) {

            pstmt.setString(1, password);
            pstmt.setString(2, name);
            pstmt.setInt(3, point);
            pstmt.setString(4, id);
            pstmt.executeUpdate();
        }
    }
}
