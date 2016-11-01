package dev.mvc.reviews_reply;


import java.util.List;

public interface Reviews_replyDAOInter {

  
  public int create(Reviews_replyVO vo);
  
  public List<Reviews_replyVO> reviews_replyList(int r_no);
  
  public int updateAnsnum(Reviews_replyVO vo);
  
  public Reviews_replyVO read(int rno);
  
  public int reply(Reviews_replyVO vo);

  public int delete(int rno);
}
