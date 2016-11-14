package dev.mvc.sports;

import java.util.HashMap;
import java.util.List;

import dev.mvc.sports.SportsVO;
import dev.mvc.tmember.MemberVO;

public interface SportsDAOInter {
    
     /**
      * ���ڵ带 ����մϴ�.
     *<insert id="create" parameterType="SportsVO">
     *@param vo ����� ������ ��ü
     *@return ��ϵ� ���ڵ� ��
     */
  public int create(SportsVO vo);
  
  /**
   * ȸ�� ��ü ���
   * <select id="list" resultType="SportsVO">
   * @param
   * @return ȸ�� ���
   */
  public List<SportsVO> list();
  
  
  /**
   *  �� ��ȸ
   *  <select id="read" resultType="SportsVO" parameterType="int">
   * @param  
   */
  
  public SportsVO read(int sno);
  
  /**
   * <update id ="update" parameterType="SportsVO">
   * 
   */
  public int update(SportsVO vo);
  
  /**
   * <delete id="delete" parameterType="int">
   * @param sno
   * @return
   */
  
  public int delete(int sno);
  
  
  /**
  *  <update id = "increaseCnt" parameterType = "int" >
  *  @param sno
  *  @return 
  */
  public int increaseCnt(int sno);
  
  
  /**
   * �˻� ���
   * <select id="list2" resultType="SportsVO" parameterType="HashMap" > 
   * @param hashmap �˻� ����
   * @return
   */
  public List<SportsVO> list2(HashMap hashmap);
  
  /**
   * �˻��� ���ڵ� ��
   * <select id="count" resultType="int" parameterType="HashMap" > 
   * @param hashmap �˻� ����
   * @return
   */
  public int count(HashMap hashmap);
  
  
  /**
   * �˻� ���
   * <select id="list3" resultType="ClothVO" parameterType="HashMap" > 
   * @param hashmap �˻� ����
   * @return
   */
  public List<SportsVO> list3(HashMap hashmap);

  
  /**
   * ȸ������(�г���, �̸���) ��������
   * @param userid
   * @return
   */
  public MemberVO test(String userid);
  
  
  /**
   * ������ �ֽű� ���
   * @param hashmap
   * @return
   */
  public List<SportsVO> newlist();

  
}

  
  

