package dev.mvc.carproduct;

import java.util.List;


public interface CarproductDAOInter {
  /** 
   * ���ڵ带 ����մϴ�.
   * <insert id="create" parameterType="CodeVO">
   * @param vo ����� ������ ��ü
   * @return ��ϵ� ���ڵ� ��
   */
  public int create(CarproductVO vo);

  /**
   * �Խñ� ��ü ���
   * <select id="list" resultType="CarproductVO">
   * @return �ڵ� ���
   */ 
  public List<CarproductVO> list();
  
  /**
   * �Ѱ��� ���ڵ� ��ȸ
   * <select id="read" resultType="CarproductVO" parameterType="int">
   * @param p_no �� ��ȣ
   * @return
   */
  public CarproductVO read(int p_no);
  
}