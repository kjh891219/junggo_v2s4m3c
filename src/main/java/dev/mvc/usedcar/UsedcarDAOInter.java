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
   * �ڵ� ��ü ���
   * <select id="list" resultType="CodeVO">
   * @return �ڵ� ���
   */
  public List<UsedcarVO> list();
  
  public int update(UsedcarVO usedcarVO);
}