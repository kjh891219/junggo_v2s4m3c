package dev.mvc.product_reply;

import java.util.List;

public interface Product_replyDAOInter {

  public int create(Product_replyVO vo);
  
  public List<Product_replyVO> product_replyList(int pno);
  
  public int updateAnsnum(Product_replyVO vo);
  
  public Product_replyVO read(int rno);
  
  public int reply(Product_replyVO vo);
  
  public int delete(int rno);
}
