package com.icia.board.Repository;

import com.icia.board.DTO.LikeDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class LikeRepository {
    @Autowired
    private SqlSessionTemplate sql;
    public boolean addLike(Long articleId, Long memberId) {
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setArticleId(articleId);
        likeDTO.setMemberId(memberId);
        sql.insert("Like.addLike", likeDTO);
        return true;
    }


    public void addDislike(int articleId){
        sql.delete("Like.disLike", articleId);
    }

    public void addHate(int articleId) {
    }

    public void addDisHate(int articleId) {
    }


    public LikeDTO findById(String memberId) {
        return sql.selectOne("Like.findById",memberId);
    }
}
