package dev.mvc.reviews;

import java.util.HashMap;
import java.util.List;

import dev.mvc.tmember.MemberVO;

public interface ReviewsDAOInter {


  /** 
   * 레코드를 등록합니다.
   * <insert id="create" parameterType="CodeVO">
   * @param vo 등록할 데이터 객체
   * @return 등록된 레코드 수
   */
  public int create(ReviewsVO reviewsVO);
  
  /**
   * 한건의 레코드 조회
   * <select id="read" resultType="CarproductVO" parameterType="int">
   * @param r_no 글 번호
   * @return
   */
  public ReviewsVO read(int r_no);
  
  /**
   * 회원정보(닉네임, 이메일) 가져오기
   * @param userid
   * @return
   */
  public MemberVO test(String userid);

  /**
   * 검색 목록
   * <select id="list" resultType="ReviewsVO" parameterType="HashMap" > 
   * @param hashmap 검색 조건
   * @return
   */
  public List<ReviewsVO> list(HashMap hashmap);

  /**
   * 검색된 레코드 수
   * <select id="count" resultType="int" parameterType="HashMap" > 
   * @param hashmap 검색 조건
   * @return
   */
  public int count(HashMap hashmap);
  
  /**
   * 등록된 글수의 증가
   * <update id="increaseCnt" parameterType="int">
   * @param r_no
   * @return
   */
  public int increaseCnt(int r_no);

  /**
   * 한건의 레코드 삭제
   * <delete id="delete" parameterType="int">
   * @param r_no 글 번호
   * @return
   */
  public int delete(int r_no);
  
  /**
   * 수정
   * @param reviewsVO
   * @return
   */
  public int update(ReviewsVO reviewsVO);

  

}
