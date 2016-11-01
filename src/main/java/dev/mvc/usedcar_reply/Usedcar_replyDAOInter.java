package dev.mvc.usedcar_reply;


import java.util.List;

public interface Usedcar_replyDAOInter {

  
  public int create(Usedcar_replyVO vo);
  
  public List<Usedcar_replyVO> usedcar_replyList(int u_no);
  
  public int updateAnsnum(Usedcar_replyVO vo);
  
  public Usedcar_replyVO read(int rno);
  
  public int reply(Usedcar_replyVO vo);

  public int delete(int rno);
}
