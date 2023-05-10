package com.icia.board.Repository;

import com.icia.board.DTO.BoardDTO;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BoardRepository {
    @Autowired
    public SqlSessionTemplate sql;

    public List<BoardDTO> pagingList(Map<String, Integer> pagingParams) {
        return sql.selectList("Board.paging",pagingParams);
    }

    public int boardCount() {
        return sql.selectOne("Board.count");
    }

    public List<BoardDTO> searchList(Map<String, Object> pagingParams) {
        return sql.selectList("Board.search", pagingParams);

    }

    public int boardSearchCount(Map<String, Object> pagingParams) {
        return sql.selectOne("Board.searchCount", pagingParams);
    }
}
