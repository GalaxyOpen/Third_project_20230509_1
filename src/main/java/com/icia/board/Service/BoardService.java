package com.icia.board.Service;

import com.icia.board.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    public BoardRepository boardRepository;


}
