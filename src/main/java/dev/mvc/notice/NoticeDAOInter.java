package dev.mvc.notice;

import java.util.HashMap;
import java.util.List;

public interface NoticeDAOInter {
  
  
  public int create(NoticeVO vo);
  
  
  public List<NoticeVO> list2(HashMap<String, Object> hashMap);
  
  public int update(NoticeVO noticeVO);
  
  public NoticeVO read(int noticeno);
  
  public int delete(int noticeno);
  
  public int increaseCnt(int noticeno);
  
  public int count(HashMap hashMap);
}
