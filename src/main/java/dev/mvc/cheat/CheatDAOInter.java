package dev.mvc.cheat;

import java.util.HashMap;
import java.util.List;

import dev.mvc.cheat.CheatVO;

public interface CheatDAOInter {
  public int create(CheatVO cheatVO);
  /** 
   * ���Ǹ����ȸ
   * @return
   */
 
  public CheatVO read(int ctno);
  
  /**
   * ����
   * @param codeVO
   * @return
   */
  public int update(CheatVO cheatVO);
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
  public List<CheatVO> list(HashMap hashMap);
  
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
