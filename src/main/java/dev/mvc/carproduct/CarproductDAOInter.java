package dev.mvc.carproduct;

import java.util.List;


public interface CarproductDAOInter {
  /** 
   * 레코드를 등록합니다.
   * <insert id="create" parameterType="CodeVO">
   * @param vo 등록할 데이터 객체
   * @return 등록된 레코드 수
   */
  public int create(CarproductVO vo);

  /**
   * 게시글 전체 목록
   * <select id="list" resultType="CarproductVO">
   * @return 코드 목록
   */ 
  public List<CarproductVO> list();
  
  /**
   * 한건의 레코드 조회
   * <select id="read" resultType="CarproductVO" parameterType="int">
   * @param p_no 글 번호
   * @return
   */
  public CarproductVO read(int p_no);
  
}