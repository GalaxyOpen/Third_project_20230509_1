package com.icia.board.Service;


import com.icia.board.DTO.MemberDTO;
import com.icia.board.DTO.MemberFileDTO;
import com.icia.board.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.List;
import java.util.UUID;


@Service
public class MemberService {
    @Autowired
    public MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) throws IOException {
        //파일 있음과 없음을 if로 나눌 예정임.
        if(memberDTO.getMemberProfile().get(0).isEmpty()){
            // 파일이 없는 경우
            System.out.println("파일없는경우");
            memberDTO.setFileAttached(0);
            memberRepository.save(memberDTO);
        }else{
            //파일이 있는 경우
            //파일 유무 값을 1과 0으로 지정ㅎ.
            // setFileAttacehd(1)로 지정하고
            // 파일의 이름 가져오고 DTO 필드 값에 세팅
            // 저장용 파일 이름 만들고 DTO 필드값에 세팅
            // 로컬에 파일 저장
            System.out.println("파일있는경우");
            memberDTO.setFileAttached(1);
            MemberDTO dto = memberRepository.save(memberDTO);
            for(MultipartFile memberFile : memberDTO.getMemberProfile()){
                // 원본파일 이름을 가져온다.
                String originalFilename = memberFile.getOriginalFilename();
                System.out.println("originalFilename = " + originalFilename);

                //저장용 이름 만들기
                System.out.println(System.currentTimeMillis());
                System.out.println(UUID.randomUUID().toString());

                String storedFileName = System.currentTimeMillis()+"-"+originalFilename;
                System.out.println("storedFileName = " + storedFileName);

                //저장을 위한 MemberFileDTO세팅
                MemberFileDTO memberFileDTO = new MemberFileDTO();
                memberFileDTO.setOriginalFileName(originalFilename);
                memberFileDTO.setStoredFileName(storedFileName);
                memberFileDTO.setMemberId(dto.getId());

                //로컬에 파일 저장하는데, 저장할 경로를 설정한다.(저장할 폴더와 이름!)
                String savepath = "D:\\springframework_img\\"+ storedFileName;

                //저장한다
                memberFile.transferTo(new File(savepath));
                memberRepository.saveFile(memberFileDTO);


            }
        }

    }
    public MemberDTO findByMemberEmail(String memberEmail){
        return memberRepository.findByMemberEmail(memberEmail);
    }
    public boolean login(MemberDTO memberDTO) {
        MemberDTO dto = memberRepository.login(memberDTO);
        if(dto!=null){
            return true;
        }else{
            return false;
        }
    }

    public void update(MemberDTO memberDTO) {
        memberRepository.update(memberDTO);
    }

    public List<MemberDTO> findAll() {
        List<MemberDTO> memberDTOList = memberRepository.findAll();
        return memberDTOList;
    }

    public void delete(Long id) {
        memberRepository.delete(id);
    }


}
