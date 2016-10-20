package dev.mvc.cosmetic;

import java.util.List;

import dev.mvc.cosmetic.CosmeticVO;

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
}
