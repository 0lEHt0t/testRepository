package dto;

public class MemberDto {
    private String id;         // 회원 ID
    private String password;   // 회원 비밀번호
    private String name;       // 회원 이름
    private int point;         // 회원 포인트

    // 기본 생성자
    public MemberDto() {}

    // 파라미터가 있는 생성자
    public MemberDto(String id, String password, String name, int point) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.point = point;
    }

    // Getter 및 Setter 메서드
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "MemberDto [id=" + id + ", password=" + password + ", name=" + name + ", point=" + point + "]";
    }
}
