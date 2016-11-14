package dev.mvc.usedcar;

import java.util.HashMap;
import java.util.List;

import dev.mvc.tmember.MemberVO;
import dev.mvc.usedcar.UsedcarVO;


public interface UsedcarDAOInter {
  
  /** 
   * ���ڵ带 ����մϴ�.
   * <insert id="create" parameterType="CodeVO">
   * @param vo ����� ������ ��ü
   * @return ��ϵ� ���ڵ� ��
   */
  public int create(UsedcarVO vo);

  /**
   * �Ѱ��� ���ڵ� ����
   * <delete id="delete" parameterType="int">
   * @param u_no �� ��ȣ
   * @return
   */
  public int delete(int u_no);

  
  /**
   * ����
   * @param usedcarVO
   * @return
   */
  public int update(UsedcarVO usedcarVO);
  
  
  /**
   * �Ѱ��� ���ڵ� ��ȸ
   * <select id="read" resultType="usedcarVO" parameterType="int">
   * @param u_no �� ��ȣ
   * @return
   */
  public UsedcarVO read(int u_no);

  
  /**
   * ��ϵ� �ۼ��� ����
   * <update id="increaseCnt" parameterType="int">
   * @param u_no
   * @return
   */
  public int increaseCnt(int u_no);
  
  /**
   * �˻� ���
   * <select id="list2" resultType="UsedcarVO" parameterType="HashMap" > 
   * @param hashmap �˻� ����
   * @return
   */
  public List<UsedcarVO> list2(HashMap hashmap);
  
  /**
   * �˻��� ���ڵ� ��
   * <select id="count" resultType="int" parameterType="HashMap" > 
   * @param hashmap �˻� ����
   * @return
   */
  public int count(HashMap hashmap);

  /**
   * �˻� ���
   * <select id="list" resultType="UsedcarVO" parameterType="HashMap" > 
   * @param hashmap �˻� ����
   * @return
   */
  public List<UsedcarVO> list(HashMap hashmap);

  /**
   * ȸ������(�г���, �̸���) ��������
   * @param userid
   * @return
   */
  public MemberVO test(String userid);
  
  /**
   * �߰��� �ֽű� ���
   * @param hashmap
   * @return
   */
  public List<UsedcarVO> newlist();


}