package dev.mvc.book;

import java.util.HashMap;
import java.util.List;

public interface BookDAOInter {
   int String = 0;

/**
    * 도서 글 등록
    * @param bookvo
    * @return
    */
   public int create(BookVO vo);
   
   /**
    * 도서 글 전체 목록
    * @return
    */
   public List<BookVO> list(HashMap hashmap);
   
   /**
    * 검색된 레코드 수
    * @param hashmap
    * @return
    */
   public int count(HashMap hashmap);

   /**
    * 도서 카테고리별 목록
    * @param categoryno
    * @return
    */
   public BookVO list(int categoryno);
   
   /**
    * 도서 게시물 조회
    * @param bookVO
    * @return
    */
   public BookVO read(int bno);
   
   
   /**
    * 도서 게시물 수정
    * @param bno
    * @return
    */
   public int update(BookVO bookVO);
   
   /**
    * 도서 게시물 삭제
    * @param bno
    * @return
    */
   public int delete(int bno);
   
   /**
    * 조회수 증가
    * @param bno
    * @return
    */
   public int increaseCnt(int bno);

   
 
}