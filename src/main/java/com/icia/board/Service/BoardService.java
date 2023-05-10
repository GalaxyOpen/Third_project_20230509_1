package com.icia.board.Service;

import com.icia.board.DTO.BoardDTO;
import com.icia.board.DTO.PageDTO;
import com.icia.board.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {
    @Autowired
    public BoardRepository boardRepository;


    public List<BoardDTO> pagingList(int page) {
        int pageLimit = 10; // 한페이지에 보여줄 글 수
        int pagingStart = (page-1)*pageLimit;
        Map<String, Integer> pagingParams = new HashMap<>();

        pagingParams.put("start", pagingStart);
        pagingParams.put("limit", pageLimit);
        List<BoardDTO> boardDTOList = boardRepository.pagingList(pagingParams);
        return boardDTOList;
    }

    public PageDTO pagingParam(int page) {
        //총 글 수가 n개라 가정.
        //이걸 한 페이지에 글을 10개씩 보여줄 예정.
        // 홈페이지 하단에 나오는 숫자가 1~10까지 10개.
        // 총글이 n개라 n/10 이 정수면 맞게 떨어지고 아래 숫자도 10개씩 놓는다면
        // 글이 90개 이하일 경우, 10페이지는 비워지게 된다.

        int pageLimit = 10; // 한페이지에 보여줄 갯수
        int blockLimit = 10; // 하단에 보여줄 페이지 번호 블락의 갯수

        // 전체 글 개수를 먼저 조회를 해야함. Mapper에서 mysql에서 쓰던 count함수를 써서 파악해보면 됨.
        int boardCount = boardRepository.boardCount();

        //그리고 전체 폐이지 계수를 계산한다.
        int maxPage = (int)(Math.ceil((double)boardCount)/pageLimit);

        //시작할 페이지 값 계산(도출이 어렵다. 그냥 알아만 두자)
        int startPage = (((int)(Math.ceil((double)page / blockLimit)))-1)*blockLimit+1;

        // 마지막 페이지 값을 계산
        int endPage = startPage + blockLimit-1;

        // 전체 페이지 갯수가 계산한 endPage보다 작을 때는 endPage 값을 maxPage값과 같게 세팅한다.
        if(endPage>maxPage){
            endPage=maxPage;
        }
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setEndPage(endPage);
        pageDTO.setStartPage(startPage);
        return pageDTO;
    }

    public List<BoardDTO> searchList(int page, String type, String q) {
        int pageLimit = 10; // 한페이지에 보여줄 글 갯수
        int pagingStart = (page-1)*pageLimit;
        Map<String, Object> pagingParams = new HashMap<>();
        pagingParams.put("start", pagingStart);
        pagingParams.put("limit", pageLimit);
        pagingParams.put("q",q);
        pagingParams.put("type", type);
        List<BoardDTO> boardDTOList = boardRepository.searchList(pagingParams);
        return boardDTOList;
    }

    public PageDTO pagingSearchParam(int page, String type, String q) {
        int pageLimit = 10; // 한페이지에 보여줄 글의 갯수
        int blockLimit = 10; // 하단에 보여줄 페이지 갯수
        Map<String, Object> pagingParams = new HashMap<>();
        pagingParams.put("q", q);
        pagingParams.put("type", type);

        // 전체 글 개수 조회
        int boardCount = boardRepository.boardSearchCount(pagingParams);

        //전체 페이지 갯수 계산
        int maxPage = (int)(Math.ceil((double)boardCount/pageLimit));

        // 시작페이지 값 계산
        int startPage = (((int)(Math.ceil((double)page/blockLimit)))-1)*blockLimit+1;

        //마지막 페이지 값 계산
        int endPage = startPage + blockLimit-1;

        // 전체 페이지 갯수가 계산한 endPage보다 작을 때는 endPage값을 maxPage 값과 같게 세팅

        if(endPage>maxPage){
            endPage=maxPage;
        }
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setEndPage(endPage);
        pageDTO.setStartPage(startPage);

        return pageDTO;




    }
}
