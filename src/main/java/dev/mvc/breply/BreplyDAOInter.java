package dev.mvc.breply;

import java.util.List;

public interface BreplyDAOInter {
  
   /**
    * ´ñ±Û µî·Ï
    * @param vo
    * @return
    */
   public int create(BreplyVO vo);
   
   /**
    * ´ñ±Û ¸ñ·Ï 
    * @param bno
    * @return
    */
   public List<BreplyVO> list(int bno);
   
   /**
    * ´ñ±Û ¼ø¼­ º¯°æ
    * @param vo
    * @return
    */
   public int updateAnsnum(BreplyVO vo);
   
   /**
    * ´ñ±Û Á¶È¸
    * @param rno
    * @return
    */
   public BreplyVO read(int rno);
   
   /**
    * ´ë´ñ±Û µî·Ï
    * @param vo
    * @return
    */
   public int reply(BreplyVO vo);
   
   /**
    * ´ñ±Û »èÁ¦
    * @param rno
    * @return
    */
   public int delete(int rno);
   
   

}
