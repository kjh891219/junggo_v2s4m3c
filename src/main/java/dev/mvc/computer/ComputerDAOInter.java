package dev.mvc.computer;

import java.util.HashMap;
import java.util.List;

import dev.mvc.computer.ComputerVO;

public interface ComputerDAOInter {
  public int create(ComputerVO computerVO);
  /** 
   * 조건목록조회
   * @return
   */
 
  public ComputerVO read(int ctno);
  
  /**
   * 수정
   * @param codeVO
   * @return
   */
  public int update(ComputerVO computerVO);
  /**
   * 삭제
   * @param ctno
   * @return
   */
  public int delete(int ctno);
  
  /**
   * 조회 조건 검색
   * @param hashMap
   * @return
   */
  public List<ComputerVO> list(HashMap hashMap);
  
  /**
   * 조회된 전체 레코드 수
   * @param hashMap
   * @return
   */
  public int count(HashMap hashMap);
  
  /**
   * 비밀번호 체크
   * @param hashMap
   * @return
   */
  public int passwordCheck(HashMap hashMap);
  
  
  /**
   * 조회수 증가
   * @param ctno
   * @return
   */
  public int setCnt(int ctno);
}
