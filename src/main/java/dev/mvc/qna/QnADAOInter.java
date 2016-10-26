package dev.mvc.qna;

import java.util.List;


public interface QnADAOInter {
   /**
    * 문의 게시판 글 등록
    * @param qnavo
    * @return
    */
   public int create(QnAVO vo);
   
   /**
    * 전체 목록
    * <select id="list" resultType="QnAVO">
    * @return 게시판 목록
    */
   public List<QnAVO> list();
   
   /**
    * 아이디 별 목록
    * <select id="idlist" parameterType="string userid">
    * @return 아이디별 게시판 목록
    */
   public List<QnAVO> idlist(String userid);
   
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
   public int update(QnAVO vo);
   
 
}