package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.MemberDto;
import servlet.Common;

public class MemberDao {

    public List<MemberDto> getMemberList() throws Exception {
        List<MemberDto> members = new ArrayList<>();
        String query = "SELECT id, password, name, point FROM member";

        try (Connection conn = Common.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
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
}
