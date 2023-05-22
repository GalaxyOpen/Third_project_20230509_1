package com.icia.board.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LikeDTO {
    private Long id;
    private Long articleId;
    private Long memberId;
}