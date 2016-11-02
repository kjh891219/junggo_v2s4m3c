package dev.mvc.cheat_reply;

import java.util.List;

public interface Cheat_replyDAOInter {

  public int create(Cheat_replyVO vo);
  
  public List<Cheat_replyVO> cheat_replyList(int ctno);
  
  public int updateAnsnum(Cheat_replyVO vo);
  
  public Cheat_replyVO read(int ctno);
  
  public int reply(Cheat_replyVO vo);
  
  public int delete(int rno);
}
