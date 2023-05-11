package com.icia.board.Repository;

import com.icia.board.DTO.BoardDTO;
import com.icia.board.DTO.BoardFileDTO;
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

    public BoardDTO save(BoardDTO boardDTO) {
        sql.insert("Board.save", boardDTO);
        return boardDTO;
    }

    public void saveFile(BoardFileDTO boardFileDTO) {
        sql.insert("Board.saveFile", boardFileDTO);
    }

    public void updateHits(Long id) {
        sql.update("Board.updateHits",id);
    }

    public BoardDTO findById(Long id) {
        return sql.selectOne("Board.findById", id);
    }

    public List<BoardFileDTO> findFile(Long boardId) {
        return sql.selectList("Board.findFile", boardId);
    }

    public List<BoardDTO> findAll() {
        return sql.selectList("Board.findAll");
    }

    public void delete(Long id) {
        sql.delete("Board.delete",id);
    }

    public void update(BoardDTO boardDTO) {
        sql.update("board.update",boardDTO);
    }
}
