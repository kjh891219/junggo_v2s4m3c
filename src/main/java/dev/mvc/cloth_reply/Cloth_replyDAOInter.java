package dev.mvc.cloth_reply;

import java.util.List;

public interface Cloth_replyDAOInter {

  public int create(Cloth_replyVO vo);
  
  public List<Cloth_replyVO> cloth_replyList(int clothno);
  
  public int updateAnsnum(Cloth_replyVO vo);
  
  public Cloth_replyVO read(int rno);
  
  public int reply(Cloth_replyVO vo);
  
  public int delete(int rno);
}
