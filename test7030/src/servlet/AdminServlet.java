package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.MemberDto;
import scheduler.SchedulerManager;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        String action = request.getParameter("action");

        try {
            if ("delete".equals(action)) {
                String memberId = request.getParameter("id");
                deleteMember(memberId);
                response.setContentType("text/html");
                response.getWriter().println("<script>alert('삭제되었습니다.'); location.href='AdminServlet';</script>");
                return;
            } else if ("start".equals(action)) {
                SchedulerManager.getInstance().startScheduler();
            } else if ("stop".equals(action)) {
                SchedulerManager.getInstance().stopScheduler();
            }

            // 회원 목록 가져오기
            List<MemberDto> members = getMemberList();
            request.setAttribute("members", members);
            request.getRequestDispatcher("/admin.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private List<MemberDto> getMemberList() throws Exception {
        List<MemberDto> members = new ArrayList<>();
        try (Connection conn = Common.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT id, password, name, point FROM member");
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                MemberDto member = new MemberDto(
                    rs.getString("id"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getInt("point")
                );
                members.add(member);
            }
        }
        return members;
    }

    private void deleteMember(String memberId) throws Exception {
        try (Connection conn = Common.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM member WHERE id = ?")) {

            pstmt.setString(1, memberId);
            pstmt.executeUpdate();
        }
    }
}
