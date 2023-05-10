package com.icia.board.Controller;
import com.icia.board.DTO.BoardDTO;
import com.icia.board.DTO.PageDTO;
import com.icia.board.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    public BoardService boardService;

    @GetMapping("/board/list")
    public String paging(@RequestParam(value="page", required=false, defaultValue ="1") int page,
                             @RequestParam(value="q", required = false, defaultValue ="")String q,
                             @RequestParam(value="type", required = false, defaultValue = "boardTitle") String type,
                             Model model){
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
        return "boardPages/boardList";


    }


}
