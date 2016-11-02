package dev.mvc.product;

import java.util.HashMap;
import java.util.List;

import dev.mvc.cloth.ClothVO;
import dev.mvc.tmember.MemberVO;

public interface ProductDAOInter {

  /** 
   *  �����͸� ����մϴ�.
   *   <insert id="create" parameterType="ProductVO">
   *   @param productVo ����� ������ ��ü
   *   @return ��ϵ� ���ڵ� ����
   */
  public int create(ProductVO productVo);
  
  
  /**
   *  ȸ�� ��� ��ü�� ����մϴ�
   *  <select id="list" resultType="ProductVO">
   *  @param
   *  @return ProductVO ȸ�� ���
   */
  public List<ProductVO> list();
  
  
  /**  
   * �� ��ȸ.
   * <select id="read" resultType="ProductVO" parameterType="int">
   * @param
   * @return
   */
    public ProductVO read(int pno);
    
    /**
     *   <update id ="update" parameterType="ProductVO">
     *   
     */
    public int update(ProductVO vo);
    
    /**
     * ���� ���� �մϴ�.
     *  <delete id="delete" parameterType="int">
     */
    public int delete(int pno);
    
    /**
     *  <update id = "increaseCnt" parameterType = "int" >
     * @param pno
     * @return
     */
    public int increaseCnt(int pno);
    
    /**
     * �˻� ���
     * <select id="list2" resultType="ProductVO" parameterType="HashMap" > 
     * @param hashmap �˻� ����
     * @return
     */
    public List<ProductVO> list2(HashMap hashmap);
    
    /**
     * �˻��� ���ڵ� ��
     * <select id="count" resultType="int" parameterType="HashMap" > 
     * @param hashmap �˻� ����
     * @return
     */
    public int count(HashMap hashmap);
    
    
    /**
     * �˻� ���
     * <select id="list3" resultType="ProductVO" parameterType="HashMap" > 
     * @param hashmap �˻� ����
     * @return
     */
    public List<ProductVO> list3(HashMap hashmap);


    /**
     * ȸ������(�г���, �̸���) ��������
     * @param userid
     * @return
     */
    public MemberVO test(String userid);
}
