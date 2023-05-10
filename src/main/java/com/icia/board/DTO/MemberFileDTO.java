package com.icia.board.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class MemberFileDTO {
    private Long id;
    private String originalFileName;
    private String storedFileName;
    private Long memberId;
}
