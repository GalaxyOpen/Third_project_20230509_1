package com.icia.board.Controller;
import com.icia.board.DTO.*;
import com.icia.board.Service.BoardService;
import com.icia.board.Service.CommentService;
import com.icia.board.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.xml.stream.events.Comment;
import java.io.IOException;
import java.util.List;

@Controller
public class BoardController {
    @Autowired
    public BoardService boardService;
    @Autowired
    public MemberService memberService;
    @Autowired
    public CommentService commentService;
    @GetMapping("/board/save")
    public String saveForm(){
        //신욱님이 써준 줄 추가
//        String email = (String) session.getAttribute("loginEmail");
//        MemberDTO dto = memberService.findByMemberEmail(email);
//        model.addAttribute("boardWriter",dto.getId());
        return "/boardPages/boardSave";
    }
    @PostMapping("/board/save")
    public String save(@ModelAttribute BoardDTO boardDTO) throws IOException {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "redirect:/board?id="+boardDTO.getId();
    }
    @GetMapping("/board/list")
    public String findAll(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "/boardPages/boardList";
    }

    @GetMapping("/board/paging")
    public String paging(Model model,
                         @RequestParam(value="page", required=false, defaultValue ="1") int page,
                         @RequestParam(value="q", required = false, defaultValue ="")String q,
                         @RequestParam(value="type", required = false, defaultValue = "boardTitle") String type){
        System.out.println("page="+page+",q ="+q);
        List<BoardDTO> boardDTOList =null;
        PageDTO pageDTO = null;
        if(q.equals("")){
            //사용자가 요청한 페이지에 해당하는 글 목록 데이터
            boardDTOList = boardService.pagingList(page);

            // 하단에 보여줄 페이지 목록 데이터 (이전 /다음/ 숫자)
            pageDTO = boardService.pagingParam(page);
        }else{
            boardDTOList = boardService.searchList(page, type,q);
            pageDTO = boardService.pagingSearchParam(page, type, q);
        }
        model.addAttribute("boardList", boardDTOList);
        model.addAttribute("paging", pageDTO);
        model.addAttribute("q", q);
        model.addAttribute("type", type);
        return "/boardPages/boardPaging";
    }
    @GetMapping("/board")
    public String findById(@RequestParam("id") Long id, Model model,
                           @RequestParam(value="page", required = false, defaultValue = "1")int page,
                           @RequestParam(value="q", required = false, defaultValue = "")String q,
                           @RequestParam(value="type",required = false, defaultValue = "boardTitle")String type){
        boardService.updateHits(id);

        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board",boardDTO);
        model.addAttribute("page",page);
        model.addAttribute("q", q);
        model.addAttribute("type", type);

        if(boardDTO.getFileAttached()==1){
            List<BoardFileDTO> boardFileDTO = boardService.findFile(id);
            model.addAttribute("boardFileList", boardFileDTO);
            System.out.println("boardFileDTO = " + boardFileDTO);
        }
        List<CommentDTO> commentDTOList = commentService.findAll(id);

        if(commentDTOList.size()==0){
            model.addAttribute("commentList", null);
        }else{
            model.addAttribute("commentList",commentDTOList);
        }
        return "/boardPages/boardDetail";
    }
    @GetMapping("/board/update")
    public String updateForm(@RequestParam("id")Long id, Model model){
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "/boardPages/boardUpdate";
    }
    @PostMapping("/board/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model){
        boardService.update(boardDTO);
        BoardDTO dto = boardService.findById(boardDTO.getId());
        model.addAttribute("board", dto);
        return "redirect:/board?id="+boardDTO.getId();
    }
    @GetMapping("/board/delete")
    public String delete(@RequestParam("id") Long id){
        boardService.delete(id);
        return "/index";
    }

}
