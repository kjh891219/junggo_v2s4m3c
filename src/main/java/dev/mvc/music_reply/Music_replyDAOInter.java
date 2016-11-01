package dev.mvc.music_reply;


import java.util.List;

public interface Music_replyDAOInter {

  
  public int create(Music_replyVO vo);
  
  public List<Music_replyVO> music_replyList(int ctno);
  
  public int updateAnsnum(Music_replyVO vo);
  
  public Music_replyVO read(int rno);
  
  public int reply(Music_replyVO vo);

  public int delete(int rno);
}
