package dev.mvc.game;

import java.util.HashMap;
import java.util.List;

import dev.mvc.game.GameVO;

public interface GameDAOInter {
 
  /**
   * ���ڵ带 ����մϴ�.
   * <insert id="create" parameterType="MemberVO">
   * @param vo ����� ������ ��ü
   * @return ��ϵ� ���ڵ� ��
   */
  public int create(GameVO vo);
            
  /**
   * ȸ�� ��ü ���
   * <select id="list" resultType="GameVO">
   * @return ȸ�� ���
   */
  public List<GameVO> list();
  
  /**
   * ȸ�� ���� ��ȸ
   * <select id="read" resultType="MemberVO" parameterType="int">
   * @param mno
   * @return
   */
  public GameVO read(int gno); 
 
  /**
   * Code�� �����մϴ�.
   * <update id="update" parameterType="CodeVO"> 
   * @param codeVO
   * @return ������ ���ڵ� ����
   */
  public int update(GameVO gameVO);
  
  /**
   * ���ڵ� 1�� ����
   * <delete id="delete" parameterType="int">
   * @param codeno ������ �ڵ� ��ȣ
   * @return ������ ���ڵ� ����
   */
  public int delete(int gno); 
  
  /**
   * ��ȸ�� ���� 
   *  <update id ="increaseCnt" parameterType ="int">
   * 
   */
  public int increaseCnt(int gno);
  
  /**
   * ����¡ ����Ʈ
   *  <select id="list2" resultType="BlogVO" parameterType="HashMap" >

   */
  
  public List<GameVO> list2(HashMap<String, Object> hashMap);

  /**
   *  <select id = "count" resultType="int" parameterType="HashMap">
   * 
   */
  public int count(HashMap hashMap);  
  
}
