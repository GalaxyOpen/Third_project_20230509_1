package com.icia.board.Repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LikeRepository {
    @Autowired
    private SqlSessionTemplate sql;
    public void addLike(int articleId) {
        sql.insert("Like.addLike",articleId);
    }
    public void addDislike(int articleId){
        sql.delete("Like.disLike", articleId);
    }

    public void addHate(int articleId) {
    }

    public void addDisHate(int articleId) {
    }
}
