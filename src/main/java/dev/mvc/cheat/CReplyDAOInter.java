package dev.mvc.cheat;

public interface CReplyDAOInter {

  public int reply(CReplyVO cReplyVO);

  public int updateAnsnum(CReplyVO cReplyVO);
  
  public CReplyVO read(int rno);
}
