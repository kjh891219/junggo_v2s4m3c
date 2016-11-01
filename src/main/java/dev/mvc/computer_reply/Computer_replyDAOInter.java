package dev.mvc.computer_reply;

import java.util.List;

import dev.mvc.camera_reply.Camera_replyVO;

public interface Computer_replyDAOInter {
  
  
  public int create(Computer_replyVO vo);
  
  public List<Computer_replyVO> nreplyList(int ctno);
  
  public int updateAnsnum(Computer_replyVO vo);
  
  public Computer_replyVO read(int rno);
  
  public int reply(Computer_replyVO vo);
  
  
  public int delete(int rno);
  
}
