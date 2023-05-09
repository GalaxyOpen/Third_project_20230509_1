package com.icia.board.Controller;

import com.icia.board.DTO.MemberDTO;
import com.icia.board.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    public MemberService memberService;
    @GetMapping("/save")
    public String saveForm(){
        return "/boardPages/memberSave";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO){
        int result = memberService.save(memberDTO);
        return "/boardPages/memberLogin";

    }
}
