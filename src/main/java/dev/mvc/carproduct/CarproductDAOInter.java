package dev.mvc.carproduct;

import java.util.HashMap;
import java.util.List;

import dev.mvc.tmember.MemberVO;


public interface CarproductDAOInter {
  /** 
   * ���ڵ带 ����մϴ�.
   * <insert id="create" parameterType="CodeVO">
   * @param vo ����� ������ ��ü
   * @return ��ϵ� ���ڵ� ��
   */
  public int create(CarproductVO vo);

  /**
   * �Ѱ��� ���ڵ� ��ȸ
   * <select id="read" resultType="CarproductVO" parameterType="int">
   * @param p_no �� ��ȣ
   * @return
   */
  public CarproductVO read(int p_no);
  

  /**
   * �˻��� ���ڵ� ��
   * <select id="count" resultType="int" parameterType="HashMap" > 
   * @param hashmap �˻� ����
   * @return
   */
  public int count(HashMap hashmap);

  
  /**
   * �Ѱ��� ���ڵ� ����
   * <delete id="delete" parameterType="int">
   * @param p_no �� ��ȣ
   * @return
   */
  public int delete(int p_no);

  
  /**
   * ����
   * @param carproductVO
   * @return
   */
  public int update(CarproductVO carproductVO);
  
  
  /**
   * �˻� ���
   * <select id="list" resultType="CarproductVO" parameterType="HashMap" > 
   * @param hashmap �˻� ����
   * @return
   */
  public List<CarproductVO> list(HashMap hashmap);
  
  
  /**
   * ��ϵ� �ۼ��� ����
   * <update id="increaseCnt" parameterType="int">
   * @param p_no
   * @return
   */
  public int increaseCnt(int p_no);

  
  /**
   * �˻� ���
   * <select id="list2" resultType="CarproductVO" parameterType="HashMap" >
   * @param hashmap �˻� ����
   * @return
   */
  List<CarproductVO> list2(HashMap hashmap);

  
  /**
   * ȸ������(�г���, �̸���) ��������
   * @param userid
   * @return
   */
  public MemberVO test(String userid);
  
  /**
   * �ڵ�����ǰ �ֽű� ���
   * @param hashmap
   * @return
   */
  public List<CarproductVO> newlist();

  
}