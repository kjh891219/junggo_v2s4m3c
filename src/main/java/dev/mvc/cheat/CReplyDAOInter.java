package dev.mvc.cheat;

import java.util.List;

public interface CReplyDAOInter {

  public int reply(CReplyVO cReplyVO);

  public int updateAnsnum(CReplyVO cReplyVO);
  
  public CReplyVO read(int rno);
  
  public List<CReplyVO> listReply(int ctno);  
  
  public int getMaxgrpno(int ctno); 
}
