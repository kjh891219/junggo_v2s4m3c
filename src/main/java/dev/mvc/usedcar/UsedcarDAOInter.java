package dev.mvc.usedcar;

import java.util.List;

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
   * �Խñ� ��ü ���
   * <select id="list" resultType="CodeVO">
   * @return �ڵ� ���
   */
  public List<UsedcarVO> list();
 
  
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
  


}