package com.icia.board.Repository;

import com.icia.board.DTO.MemberDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    @Autowired
    public SqlSessionTemplate sql;
    public int save(MemberDTO memberDTO) {
       return sql.insert("Member.save", memberDTO);
    }
}
