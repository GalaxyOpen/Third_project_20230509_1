package com.icia.board.Controller;

import com.icia.board.DTO.MemberDTO;
import com.icia.board.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
    @Autowired
    public BoardService boardService;





//    @GetMapping("/pagingList")
//    public findAll(){
//
//    }

}
