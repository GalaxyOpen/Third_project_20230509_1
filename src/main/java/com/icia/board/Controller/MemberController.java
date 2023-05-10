package com.icia.board.Controller;

import com.icia.board.DTO.MemberDTO;
import com.icia.board.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class MemberController {
    @Autowired
    public MemberService memberService;
    @GetMapping("/member/save")
    public String saveForm(){
        return "/memberPages/memberSave";
    }
    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) throws IOException{
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "/memberPages/memberLogin";
        

    }
    @PostMapping("/email_check")
    public ResponseEntity email_check(@RequestParam("memberEmail") String memberEmail){
        MemberDTO memberDTO = memberService.findByMemberEmail(memberEmail);
       if(memberDTO == null){
           return new ResponseEntity<>(memberDTO, HttpStatus.OK);
       }else{
           return new ResponseEntity<>(memberDTO, HttpStatus.CONFLICT);
       }
    }

    //


    @GetMapping("/member/login")
    public String loginForm(){
        return "/memberPages/memberLogin";
    }
    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, Model model, HttpSession session){
        boolean loginResult = memberService.login(memberDTO);
        if(loginResult){
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
            return "/memberPages/memberMain";
        }else {
            return "/memberPages/memberLogin";

        }
    }
    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        // 세션에 담긴 값 전체 삭제
//        session.invalidate();
        // 특정 파라미터만 삭제
        session.removeAttribute("loginEmail");
        return "redirect:/";
    }

}
