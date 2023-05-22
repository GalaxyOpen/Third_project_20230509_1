package com.icia.board.Service;

import com.icia.board.DTO.BoardDTO;
import com.icia.board.DTO.LikeDTO;
import com.icia.board.DTO.MemberDTO;
import com.icia.board.Repository.BoardRepository;
import com.icia.board.Repository.LikeRepository;
import com.icia.board.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService{
        @Autowired
        private LikeRepository likeRepository;
        @Autowired
        private MemberRepository memberRepository;
        @Autowired
        private BoardRepository boardRepository;

        public boolean addLike(Long articleId, Long memberId) {

            BoardDTO boardDTO = boardRepository.findById(articleId);
            MemberDTO memberDTO = memberRepository.findById(memberId);
//            System.out.println("memberDTO = " + memberDTO);
//            System.out.println("boardDTO = " + boardDTO);


            if(boardDTO == null || memberDTO == null){
                throw new IllegalArgumentException("게시글이나 회원이 존재하지 않습니다.");
            }
            if(memberDTO.getLikeArticles().contains(articleId)){
                return false; // 이미 좋아요를 누른 상태
            }

            // 좋아요 추가 로직
            Integer articleIdInt = articleId.intValue();
            memberDTO.getLikeArticles().add(articleIdInt); // Integer 값을 List에 추가 (toString() 메서드로 String으로 변환)

            // 다음, 좋아요 수 증가
            boardDTO.setLikes(boardDTO.getLikes()+1);

            // 변경된 데이터 저장
            boardRepository.save(boardDTO);
            memberRepository.save(memberDTO);


           return likeRepository.addLike(articleId, memberId);

       }


        public void addDislike(int articleId) {
            likeRepository.addDislike(articleId);
        }

        public void addHate(int articleId) {
             likeRepository.addHate(articleId);
        }
        public void addDisHate(int articleId) {
              likeRepository.addDisHate(articleId);
        }


    public LikeDTO findById(String memberId) {
        return likeRepository.findById(memberId);
    }
}

