package dev.mvc.computer;

import java.util.HashMap;
import java.util.List;

import dev.mvc.computer.ComputerVO;

public interface ComputerDAOInter {
  public int create(ComputerVO computerVO);
  /** 
   * ���Ǹ����ȸ
   * @return
   */
 
  public ComputerVO read(int ctno);
  
  /**
   * ����
   * @param codeVO
   * @return
   */
  public int update(ComputerVO computerVO);
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
  public List<ComputerVO> list(HashMap hashMap);
  
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
