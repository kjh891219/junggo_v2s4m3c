package dev.mvc.qna;

import java.util.List;


public interface QnADAOInter {
   /**
    * ���Ǳ� ���
    * <insert id="create" parameterType="QnAVO">
    * @param qnavo
    * @return
    */
   public int create(QnAVO vo);
   /**
    * ��ü ��� ���
    * <select id="list" resultType="CodeVO">
    * @return ȸ�� ���
    */
   public List<QnAVO> list();
   
   /**
    * ���Ǳ� ��ȸ
    * <select id="read" parameterType="int">
    * @param qnano
    * @return
    */
   public QnAVO read(int qnano);
   
   /**
    * �� ����
    * <select id="update" parameterType="QnAVO">
    * @param vo
    * @return
    */
   //public int update(QnAVO vo);
   
 
}