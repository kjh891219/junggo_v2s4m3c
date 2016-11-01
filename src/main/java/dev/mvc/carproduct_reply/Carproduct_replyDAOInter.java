package dev.mvc.carproduct_reply;


import java.util.List;

public interface Carproduct_replyDAOInter {

  
  public int create(Carproduct_replyVO vo);
  
  public List<Carproduct_replyVO> carproduct_replyList(int p_no);
  
  public int updateAnsnum(Carproduct_replyVO vo);
  
  public Carproduct_replyVO read(int rno);
  
  public int reply(Carproduct_replyVO vo);

  public int delete(int rno);
}
