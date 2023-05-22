package com.icia.board.Controller;

import com.icia.board.DTO.MemberDTO;
import com.icia.board.DTO.MemberFileDTO;
import com.icia.board.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

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
    @GetMapping("/member/login")
    public String loginForm(){
        return "/memberPages/memberLogin";
    }
    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, Model model, HttpSession session){
        boolean loginResult = memberService.login(memberDTO);
        if(loginResult){
            session.setAttribute("member", memberDTO.getId());
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
    @GetMapping("/member/update")
    public String updateFrom(HttpSession session, Model model){
        String loginEmail=(String)session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        model.addAttribute("member",memberDTO);
        return "/memberPages/memberUpdate";
    }

    @PostMapping("/member/update")
    public String update (@ModelAttribute MemberDTO memberDTO){
        memberService.update(memberDTO);
        return "/memberPages/memberMain";
    }

    @GetMapping("/member/myPage")
    public String myPage (){
        return "/memberPages/memberMain";
    }

    @GetMapping("/member/admin")
    public String findAll(Model model){
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList", memberDTOList);
        System.out.println(memberDTOList.size());
        return "/memberPages/memberAdmin";
    }
    @GetMapping("/member/list")
    public String findById(@RequestParam("id")Long id, Model model){
        MemberDTO memberDTO=memberService.findById(id);
        model.addAttribute("member",memberDTO);
        System.out.println("memberDTO = " + memberDTO);

        if(memberDTO.getFileAttached()==1){
            List<MemberFileDTO> memberFileDTO = memberService.findFile(memberDTO.getId());
            model.addAttribute("memberFileList", memberFileDTO);
            System.out.println("memberFileDTO = " + memberFileDTO);
        }
        return "/memberPages/memberDetail";
    }

    @GetMapping("/member/delete")
    public String delete(@RequestParam("id")Long id){
        memberService.delete(id);
        return "redirect:/member/admin";
    }
}
