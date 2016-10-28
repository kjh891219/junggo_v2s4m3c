package dev.mvc.camera_reply;


import java.util.List;

public interface Camera_replyDAOInter {

  
  public int create(Camera_replyVO vo);
  
  public List<Camera_replyVO> camera_replyList(int ctno);
  
  public int updateAnsnum(Camera_replyVO vo);
  
  public Camera_replyVO read(int rno);
  
  public int reply(Camera_replyVO vo);
  
  
  public int delete(int rno);
}
