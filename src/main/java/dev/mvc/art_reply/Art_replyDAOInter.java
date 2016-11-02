package dev.mvc.art_reply;


import java.util.List;

public interface Art_replyDAOInter {

  
  public int create(Art_replyVO vo);
  
  public List<Art_replyVO> art_replyList(int ano);
  
  public int updateAnsnum(Art_replyVO vo);
  
  public Art_replyVO read(int rno);
  
  public int reply(Art_replyVO vo);
  
  public int delete(int rno);
}
