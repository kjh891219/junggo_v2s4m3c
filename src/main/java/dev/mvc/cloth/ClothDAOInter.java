package dev.mvc.cloth;

import java.util.HashMap;
import java.util.List;

import dev.mvc.cloth.ClothVO;
import dev.mvc.tmember.MemberVO;

public interface ClothDAOInter {
    
     /**
      * ���ڵ带 ����մϴ�.
     *<insert id="create" parameterType="ClothVO">
     *@param vo ����� ������ ��ü
     *@return ��ϵ� ���ڵ� ��
     */
  public int create(ClothVO vo);
  
  /**
   * ȸ�� ��ü ���
   * <select id="list" resultType="ClothVO">
   * @param
   * @return ȸ�� ���
   */
  public List<ClothVO> list();
  
  
  /**
   *  �� ��ȸ
   *  <select id="read" resultType="ClothVO" parameterType="int">
   * @param  
   */
  
  public ClothVO read(int clothno);
  
  /**
   * <update id ="update" parameterType="ClothVO">
   * 
   */
  public int update(ClothVO vo);
  
  /**
   * <delete id="delete" parameterType="int">
   * @param clothno
   * @return
   */
  
  public int delete(int clothno);
  
  
  /**
  *  <update id = "increaseCnt" parameterType = "int" >
  *  @param clothno
  *  @return 
  */
  public int increaseCnt(int clothno);
  
  
  /**
   * �˻� ���
   * <select id="list2" resultType="ClothVO" parameterType="HashMap" > 
   * @param hashmap �˻� ����
   * @return
   */
  public List<ClothVO> list2(HashMap hashmap);
  
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
  public List<ClothVO> list3(HashMap hashmap);

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
  public List<ClothVO> newlist();

}

  
  

