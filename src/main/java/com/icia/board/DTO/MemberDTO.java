package com.icia.board.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class MemberDTO {
    public Long id;
    public String memberEmail;
    public String memberPassword;
    public String memberName;
    public String memberMobile;
    public String memberProfile;
}
