package dev.mvc.cosmetic;

import java.util.HashMap;
import java.util.List;

import dev.mvc.cosmetic.CosmeticVO;
import dev.mvc.tmember.MemberVO;

public interface CosmeticDAOInter {
  /**
   * ���ڵ带 ����մϴ�.
   * <insert id="create" parameterType="CosmeticVO">
   * @param vo ����� ������ ��ü
   * @return ��ϵ� ���ڵ� ��
   */
  public int create(CosmeticVO vo);
  
  /**
   * ȸ�� ��ü ���
   * <select id="list" resultType="CosmeticVO">
   * @return ȸ�� ���
   */ 
  public List<CosmeticVO> list(); 
  
  /**
   * ���� �б�
   * <select id="read" resultType="CosmeticVO" parameterType="int">
   */
  public CosmeticVO read(int cno); 
  
  
  /**
   * ����ó��
   * <update id ="update" parameterType="CosmeticVO">
   * @param vo
   * @return
   */
  
  public int update(CosmeticVO vo);
  
  /**
   * <delete id="delete" parameterType="int">
   * @param cno
   * @return
   */
  
  public int delete(int cno);
 
  /**
  *  <update id = "increaseCnt" parameterType = "int" >
  *  @param cno
  *  @return 
  */
  public int increaseCnt(int cno);
  
  /**
   * �˻� ���
   * <select id="list2" resultType="CosmeticVO" parameterType="HashMap" > 
   * @param hashmap �˻� ����
   * @return
   */
  public List<CosmeticVO> list2(HashMap hashmap);
  
  /**
   * �˻��� ���ڵ� ��
   * <select id="count" resultType="int" parameterType="HashMap" > 
   * @param hashmap �˻� ����
   * @return
   */
  public int count(HashMap hashmap);
  
  
  /**
   * �˻� ���
   * <select id="list3" resultType="CosmeticVO" parameterType="HashMap" > 
   * @param hashmap �˻� ����
   * @return
   */
  public List<CosmeticVO> list3(HashMap hashmap);

  /**
   * ȸ������(�г���, �̸���) ��������
   * @param userid
   * @return
   */
  public MemberVO test(String userid);
  
  /**
   * ���� �ֽű� ���
   * @param hashmap
   * @return
   */
  public List<CosmeticVO> newlist();

}
