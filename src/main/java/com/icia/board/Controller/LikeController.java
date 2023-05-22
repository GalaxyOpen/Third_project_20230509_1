package com.icia.board.Controller;

import com.icia.board.DTO.LikeDTO;
import com.icia.board.DTO.MemberDTO;
import com.icia.board.Service.LikeService;
import com.icia.board.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LikeController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private MemberService memberService;

    @PostMapping("/goodUp")
    public ResponseEntity<String> addLike(@ModelAttribute Long articleId,
                                          HttpSession session){

        // 1. 세션에 이메일 값을 가지고 여기서 회원의 아이디를 조회한 것을 addlike로 보내라.
        //세션에 있는걸 컨트롤러 어디서든 꺼낼 수 있다.
        String memberId = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByMemberEmail(memberId);

        System.out.println("memberDTO = " + memberDTO);
        System.out.println("memberId = " + memberId);
        System.out.println("articleId = " + articleId);

       boolean result = likeService.addLike(articleId, memberDTO.getId());
        System.out.println("result = " + result);
       if(result){
           return ResponseEntity.ok("좋아요 추가");
       }else{
           return ResponseEntity.badRequest().body("이미 좋아요가 추가되었습니다");
       }
    }

    @PostMapping("/goodDown")
    public ResponseEntity<String> addDislike(@RequestParam("articleId") int articleId) {
        try {
            likeService.addDislike(articleId);
            return ResponseEntity.ok("Dislike added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add dislike");
        }
    }
}
