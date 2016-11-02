package dev.mvc.cosmetic_reply;

import java.util.List;


public interface Cosmetic_replyDAOInter {

  public int create(Cosmetic_replyVO vo);
  
  public List<Cosmetic_replyVO> cosmetic_replyList(int clothno);
  
  public int updateAnsnum(Cosmetic_replyVO vo);
  
  public Cosmetic_replyVO read(int rno);
  
  public int delete(int rno);
  
  public int reply(Cosmetic_replyVO vo);
}
