package dev.mvc.reviews;

import java.util.HashMap;
import java.util.List;

import dev.mvc.tmember.MemberVO;

public interface ReviewsDAOInter {


  /** 
   * ���ڵ带 ����մϴ�.
   * <insert id="create" parameterType="CodeVO">
   * @param vo ����� ������ ��ü
   * @return ��ϵ� ���ڵ� ��
   */
  public int create(ReviewsVO reviewsVO);
  
  /**
   * �Ѱ��� ���ڵ� ��ȸ
   * <select id="read" resultType="CarproductVO" parameterType="int">
   * @param r_no �� ��ȣ
   * @return
   */
  public ReviewsVO read(int r_no);
  
  /**
   * ȸ������(�г���, �̸���) ��������
   * @param userid
   * @return
   */
  public MemberVO test(String userid);

  /**
   * �˻� ���
   * <select id="list" resultType="ReviewsVO" parameterType="HashMap" > 
   * @param hashmap �˻� ����
   * @return
   */
  public List<ReviewsVO> list(HashMap hashmap);

  /**
   * �˻��� ���ڵ� ��
   * <select id="count" resultType="int" parameterType="HashMap" > 
   * @param hashmap �˻� ����
   * @return
   */
  public int count(HashMap hashmap);
  
  /**
   * ��ϵ� �ۼ��� ����
   * <update id="increaseCnt" parameterType="int">
   * @param r_no
   * @return
   */
  public int increaseCnt(int r_no);

  /**
   * �Ѱ��� ���ڵ� ����
   * <delete id="delete" parameterType="int">
   * @param r_no �� ��ȣ
   * @return
   */
  public int delete(int r_no);
  
  /**
   * ����
   * @param reviewsVO
   * @return
   */
  public int update(ReviewsVO reviewsVO);

  

}
