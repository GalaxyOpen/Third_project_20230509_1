package com.icia.board.Service;

import com.icia.board.DTO.CommentDTO;
import com.icia.board.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    public List<CommentDTO> findAll(Long boardId) {
        return commentRepository.findAll(boardId);
    }

    public void save(CommentDTO commentDTO) {
        commentRepository.save(commentDTO);
    }
}
