package com.icia.board.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString

public class CommentDTO {
    public Long id;
    public String boardId;
    public String commentWriter;
    public Timestamp commentCreatedDate;
}
