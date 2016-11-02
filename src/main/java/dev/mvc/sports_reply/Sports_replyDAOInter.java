package dev.mvc.sports_reply;

import java.util.List;

public interface Sports_replyDAOInter {

  public int create(Sports_replyVO vo);
  
  public List<Sports_replyVO> sports_replyList(int sno);
  
  public int updateAnsnum(Sports_replyVO vo);
  
  public Sports_replyVO read(int rno);
  
  public int reply(Sports_replyVO vo);
  
  public int delete(int rno);
}
