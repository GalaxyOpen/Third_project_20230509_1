package com.icia.board.Controller;

import com.icia.board.DTO.BoardDTO;
import com.icia.board.DTO.BoardFileDTO;
import com.icia.board.DTO.CommentDTO;
import com.icia.board.Service.BoardService;
import com.icia.board.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
//    @Autowired
//    private BoardService boardService;

//    @GetMapping
//    public String findById(@RequestParam("id")Long id, Model model,
//                           @RequestParam(value="page", required=false, defaultValue="1")int page,
//                           @RequestParam(value="q", required = false, defaultValue="")String q,
//                           @RequestParam(value="type", required = false, defaultValue = "boardTitle")String type){
//        boardService.updateHits(id); // 조회수 처리를 위해
//        BoardDTO boardDTO = boardService.findById(id); // 상세조회를 위해
//
//        // 조회수가 올라가고 나서 상세조회를 불러와야 함(순서 엄수)
//        model.addAttribute("board", boardDTO);
//        model.addAttribute("page", page);
//        model.addAttribute("q", q);
//        model.addAttribute("type", type);
//        if(boardDTO.getFileAttached()==1){
//            List<BoardFileDTO> boardFileDTO = boardService.findFile(id);
//            model.addAttribute("boardFileList", boardFileDTO);
//            System.out.println("boardFileDTO = " + boardFileDTO);
//        }
//        List<CommentDTO> commentDTOList = commentService.findAll(id);
//        if(commentDTOList.size()==0){
//            model.addAttribute("commentList", null);
//        }else{
//            model.addAttribute("commentList", commentDTOList);
//        }
//        return "boardPages/boardDetail";
//    }

    @PostMapping("/comment/save")
    public ResponseEntity save(@ModelAttribute CommentDTO commentDTO){
        commentService.save(commentDTO);
        List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getBoardId());
        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }
}
