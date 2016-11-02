package dev.mvc.cheat;

import java.util.HashMap;
import java.util.List;

import dev.mvc.tmember.MemberVO;

public interface CheatDAOInter {

 /**
  * ���ڵ带 ����մϴ�.
  * <insert id="create" parameterType="CheatVO">
  * @param vo ����� ������ ��ü
  * @return ��ϵ� ���ڵ� ��
  */
  public int create(CheatVO vo);
  
  /**
   * ȸ�� ��ü ���
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
   * ȸ������(�г���, �̸���) ��������
   * @param userid
   * @return
   */
  public MemberVO test(String userid);
  
}
