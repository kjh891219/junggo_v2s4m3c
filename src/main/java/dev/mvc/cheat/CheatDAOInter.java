package dev.mvc.cheat;

import java.util.HashMap;
import java.util.List;

import dev.mvc.tmember.MemberVO;

public interface CheatDAOInter {

 /**
  * 레코드를 등록합니다.
  * <insert id="create" parameterType="CheatVO">
  * @param vo 등록할 데이터 객체
  * @return 등록된 레코드 수
  */
  public int create(CheatVO vo);
  
  /**
   * 회원 전체 목록
   * <select id="list3" resultType="CheatVO" parameterType="HashMap">
   * @param
   * @return
   */
  public List<CheatVO> list();
  
  /**
   * <select id="read" resultType="CheatVO" parameterType="int">
   * @param ctno
   * @return
   */
  public CheatVO read(int ctno);
  
  /**
   * 
   * @param vo
   * @return
   */
  public int update(CheatVO vo);
  
  public int delete(int ctno);
  
  public int increaseCnt(int ctno);
  
  public int count(HashMap hashmap);
  
  public List<CheatVO> list3(HashMap hashmap);
  
  /**
   * 회원정보(닉네임, 이메일) 가져오기
   * @param userid
   * @return
   */
  public MemberVO test(String userid);
  
}
