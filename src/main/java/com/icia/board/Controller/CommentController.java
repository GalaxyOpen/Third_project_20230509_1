package com.icia.board.Controller;

import com.icia.board.DTO.CommentDTO;
import com.icia.board.DTO.MemberDTO;
import com.icia.board.Service.CommentService;
import com.icia.board.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private MemberService memberService;
    @PostMapping("/comment/save")
    public ResponseEntity save(@ModelAttribute CommentDTO commentDTO, HttpSession session){
        System.out.println("commentDTO = " + commentDTO);
        MemberDTO memberDTO = memberService.findByMemberEmail((String)session.getAttribute("loginEmail"));
        commentDTO.setBoardId(memberDTO.getId());
        commentService.save(commentDTO);
        List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getBoardId());
        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }
}
