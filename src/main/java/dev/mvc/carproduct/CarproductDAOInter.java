package dev.mvc.carproduct;

import java.util.HashMap;
import java.util.List;

import dev.mvc.tmember.MemberVO;


public interface CarproductDAOInter {
  /** 
   * 레코드를 등록합니다.
   * <insert id="create" parameterType="CodeVO">
   * @param vo 등록할 데이터 객체
   * @return 등록된 레코드 수
   */
  public int create(CarproductVO vo);

  /**
   * 한건의 레코드 조회
   * <select id="read" resultType="CarproductVO" parameterType="int">
   * @param p_no 글 번호
   * @return
   */
  public CarproductVO read(int p_no);
  

  /**
   * 검색된 레코드 수
   * <select id="count" resultType="int" parameterType="HashMap" > 
   * @param hashmap 검색 조건
   * @return
   */
  public int count(HashMap hashmap);

  
  /**
   * 한건의 레코드 삭제
   * <delete id="delete" parameterType="int">
   * @param p_no 글 번호
   * @return
   */
  public int delete(int p_no);

  
  /**
   * 수정
   * @param carproductVO
   * @return
   */
  public int update(CarproductVO carproductVO);
  
  
  /**
   * 검색 목록
   * <select id="list" resultType="CarproductVO" parameterType="HashMap" > 
   * @param hashmap 검색 조건
   * @return
   */
  public List<CarproductVO> list(HashMap hashmap);
  
  
  /**
   * 등록된 글수의 증가
   * <update id="increaseCnt" parameterType="int">
   * @param p_no
   * @return
   */
  public int increaseCnt(int p_no);

  
  /**
   * 검색 목록
   * <select id="list2" resultType="CarproductVO" parameterType="HashMap" >
   * @param hashmap 검색 조건
   * @return
   */
  List<CarproductVO> list2(HashMap hashmap);

  
  /**
   * 회원정보(닉네임, 이메일) 가져오기
   * @param userid
   * @return
   */
  public MemberVO test(String userid);
  
  /**
   * 자동차용품 최신글 목록
   * @param hashmap
   * @return
   */
  public List<CarproductVO> newlist();

  
}