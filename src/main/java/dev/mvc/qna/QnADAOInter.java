package dev.mvc.qna;

import java.util.HashMap;
import java.util.List;


public interface QnADAOInter {
   /**
    * 문의 게시판 글 등록
    * @param qnavo
    * @return
    */
   public int create(QnAVO vo);
   
   /**
    * 답변 기능, 페이징 지원하는 전체 목록
    * <select id="list" resultType="QVO" parameterType="HashMap" >
    * @return 게시판 목록
    */
   public List<QnAVO> list(HashMap<String, Object> hashmap);
   
   /**
    * 검색된 레코드 수
    * @param hashmap
    * @return
    */
   public int count(HashMap hashmap);

   /**
    * 아이디 별 목록
    * <select id="idlist" parameterType="string userid">
    * @return 아이디별 게시판 목록
    */
   /*
   public List<QnAVO> idlist(String userid);
   */
   /**
    * 문의게시판 글 조회
    * <select id="read" resultType="QnAVO" parameterType="int">
    * @param qnano
    * @return
    */
   public QnAVO read(int qnano);
   
   /**
    * 문의게시물 삭제
    * <select id="delete" parameterType="int">
    * @param qnano
    * @return
    */
   public int delete(int qnano);
   
   /**
    * 문의게시물 수정
    * <select id="update" parameterType="QnAVO">
    * @param vo
    * @return
    */
   public int update(QnAVO qnaVO);
   
   
   /**
    * 조회수 증가
    * @param bno
    * @return
    */
   public int increaseCnt(int qnano);
   
   /**
    * 답변 순서를 변경합니다
    * @param vo
    * @return 1:성공 0:실패
    */
   public int updateAnsnum(QnAVO vo);
   
   /**
    * 답변 등록
    * @param vo
    * @return
    */
   public int reply(QnAVO qnavo);
   
   
   
 
}