package dev.mvc.qna;

import java.util.List;


public interface QnADAOInter {
   /**
    * ���� �Խ��� �� ���
    * @param qnavo
    * @return
    */
   public int create(QnAVO vo);
   
   /**
    * ��ü ���
    * <select id="list" resultType="QnAVO">
    * @return �Խ��� ���
    */
   public List<QnAVO> list();
   
   /**
    * ���̵� �� ���
    * <select id="idlist" parameterType="string userid">
    * @return ���̵� �Խ��� ���
    */
   public List<QnAVO> idlist(String userid);
   
   /**
    * ���ǰԽ��� �� ��ȸ
    * <select id="read" resultType="QnAVO" parameterType="int">
    * @param qnano
    * @return
    */
   public QnAVO read(int qnano);
   
   /**
    * ���ǰԽù� ����
    * <select id="delete" parameterType="int">
    * @param qnano
    * @return
    */
   public int delete(int qnano);
   
   /**
    * ���ǰԽù� ����
    * <select id="update" parameterType="QnAVO">
    * @param vo
    * @return
    */
   public int update(QnAVO vo);
   
 
}