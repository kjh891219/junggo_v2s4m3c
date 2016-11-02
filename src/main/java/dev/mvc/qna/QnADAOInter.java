package dev.mvc.qna;

import java.util.HashMap;
import java.util.List;


public interface QnADAOInter {
   /**
    * ���� �Խ��� �� ���
    * @param qnavo
    * @return
    */
   public int create(QnAVO vo);
   
   /**
    * �亯 ���, ����¡ �����ϴ� ��ü ���
    * <select id="list" resultType="QVO" parameterType="HashMap" >
    * @return �Խ��� ���
    */
   public List<QnAVO> list(HashMap<String, Object> hashmap);
   
   /**
    * �˻��� ���ڵ� ��
    * @param hashmap
    * @return
    */
   public int count(HashMap hashmap);

   /**
    * ���̵� �� ���
    * <select id="idlist" parameterType="string userid">
    * @return ���̵� �Խ��� ���
    */
   /*
   public List<QnAVO> idlist(String userid);
   */
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
   public int update(QnAVO qnaVO);
   
   
   /**
    * ��ȸ�� ����
    * @param bno
    * @return
    */
   public int increaseCnt(int qnano);
   
   /**
    * �亯 ������ �����մϴ�
    * @param vo
    * @return 1:���� 0:����
    */
   public int updateAnsnum(QnAVO vo);
   
   /**
    * �亯 ���
    * @param vo
    * @return
    */
   public int reply(QnAVO qnavo);
   
   
   
 
}