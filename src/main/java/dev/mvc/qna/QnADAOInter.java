package dev.mvc.qna;

import java.util.List;


public interface QnADAOInter {
   /**
    * 문의글 등록
    * <insert id="create" parameterType="QnAVO">
    * @param qnavo
    * @return
    */
   public int create(QnAVO vo);
   /**
    * 전체 목록 출력
    * <select id="list" resultType="CodeVO">
    * @return 회원 목록
    */
   public List<QnAVO> list();
   
   /**
    * 문의글 조회
    * <select id="read" parameterType="int">
    * @param qnano
    * @return
    */
   public QnAVO read(int qnano);
   
   /**
    * 글 수정
    * <select id="update" parameterType="QnAVO">
    * @param vo
    * @return
    */
   //public int update(QnAVO vo);
   
 
}