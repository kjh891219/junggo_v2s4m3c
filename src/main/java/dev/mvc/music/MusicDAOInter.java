package dev.mvc.music;

import java.util.HashMap;
import java.util.List;

import dev.mvc.music.MusicVO;

public interface MusicDAOInter {
  public int create(MusicVO musicVO);
  /** 
   * ���Ǹ����ȸ
   * @return
   */
 
  public MusicVO read(int ctno);
  
  /**
   * ����
   * @param codeVO
   * @return
   */
  public int update(MusicVO musicVO);
  /**
   * ����
   * @param ctno
   * @return
   */
  public int delete(int ctno);
  
  /**
   * ��ȸ ���� �˻�
   * @param hashMap
   * @return
   */
  public List<MusicVO> list(HashMap hashMap);
  
  /**
   * ��ȸ�� ��ü ���ڵ� ��
   * @param hashMap
   * @return
   */
  public int count(HashMap hashMap);
  
  /**
   * ��й�ȣ üũ
   * @param hashMap
   * @return
   */
  public int passwordCheck(HashMap hashMap);
  
  
  /**
   * ��ȸ�� ����
   * @param ctno
   * @return
   */
  public int setCnt(int ctno);
}
