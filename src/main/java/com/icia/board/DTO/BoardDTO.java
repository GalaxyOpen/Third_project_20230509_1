package com.icia.board.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

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
    private List<MultipartFile> boardFile;
    private Long memberId;
    private int likes;

}
