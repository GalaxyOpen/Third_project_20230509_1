package com.icia.board.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString

public class BoardDTO {
    public Long id;
    public String boardTitle;
    public String boardWriter;
    public String boardContents;
    public int boardHits;
    public Timestamp boardCreatedDate;
    public int fileAttached;

}
