package csh.football.member.domain.member;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Member {

    private String id;
    @NotEmpty
    @Size(max = 16, message = "길이가 15자 이하여야 됩니다.")
    private String loginId; //로그인 ID
    @NotEmpty
    private String name; //사용자 이름
    @NotEmpty
    private String password;

    @NotEmpty
    private String passwordCheck;

    private String description;

    private MemberType memberType;

    public Member(String loginId, String name, String password){
        this.loginId=loginId;
        this.name=name;
        this.password=password;
    }

    public Member(){

    }
}
