package dev.mvc.breply;

import java.util.List;

public interface BreplyDAOInter {
  
   /**
    * ��� ���
    * @param vo
    * @return
    */
   public int create(BreplyVO vo);
   
   /**
    * ��� ��� 
    * @param bno
    * @return
    */
   public List<BreplyVO> list(int bno);
   
   /**
    * ��� ���� ����
    * @param vo
    * @return
    */
   public int updateAnsnum(BreplyVO vo);
   
   /**
    * ��� ��ȸ
    * @param rno
    * @return
    */
   public BreplyVO read(int rno);
   
   /**
    * ���� ���
    * @param vo
    * @return
    */
   public int reply(BreplyVO vo);
   
   /**
    * ��� ����
    * @param rno
    * @return
    */
   public int delete(int rno);
   
   

}
