package dev.mvc.cloth;

import java.util.HashMap;
import java.util.List;

import dev.mvc.cloth.ClothVO;
import dev.mvc.tmember.MemberVO;

public interface ClothDAOInter {
    
     /**
      * 레코드를 등록합니다.
     *<insert id="create" parameterType="ClothVO">
     *@param vo 등록할 데이터 객체
     *@return 등록된 레코드 수
     */
  public int create(ClothVO vo);
  
  /**
   * 회원 전체 목록
   * <select id="list" resultType="ClothVO">
   * @param
   * @return 회원 목록
   */
  public List<ClothVO> list();
  
  
  /**
   *  글 조회
   *  <select id="read" resultType="ClothVO" parameterType="int">
   * @param  
   */
  
  public ClothVO read(int clothno);
  
  /**
   * <update id ="update" parameterType="ClothVO">
   * 
   */
  public int update(ClothVO vo);
  
  /**
   * <delete id="delete" parameterType="int">
   * @param clothno
   * @return
   */
  
  public int delete(int clothno);
  
  
  /**
  *  <update id = "increaseCnt" parameterType = "int" >
  *  @param clothno
  *  @return 
  */
  public int increaseCnt(int clothno);
  
  
  /**
   * 검색 목록
   * <select id="list2" resultType="ClothVO" parameterType="HashMap" > 
   * @param hashmap 검색 조건
   * @return
   */
  public List<ClothVO> list2(HashMap hashmap);
  
  /**
   * 검색된 레코드 수
   * <select id="count" resultType="int" parameterType="HashMap" > 
   * @param hashmap 검색 조건
   * @return
   */
  public int count(HashMap hashmap);
  
  
  /**
   * 검색 목록
   * <select id="list3" resultType="ClothVO" parameterType="HashMap" > 
   * @param hashmap 검색 조건
   * @return
   */
  public List<ClothVO> list3(HashMap hashmap);

  /**
   * 회원정보(닉네임, 이메일) 가져오기
   * @param userid
   * @return
   */
  public MemberVO test(String userid);
  
  /**
   * 도서 최신글 목록
   * @param hashmap
   * @return
   */
  public List<ClothVO> newlist();

}

  
  

