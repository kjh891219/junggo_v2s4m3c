package dev.mvc.sports;

import java.util.HashMap;
import java.util.List;

import dev.mvc.sports.SportsVO;
import dev.mvc.tmember.MemberVO;

public interface SportsDAOInter {
    
     /**
      * 레코드를 등록합니다.
     *<insert id="create" parameterType="SportsVO">
     *@param vo 등록할 데이터 객체
     *@return 등록된 레코드 수
     */
  public int create(SportsVO vo);
  
  /**
   * 회원 전체 목록
   * <select id="list" resultType="SportsVO">
   * @param
   * @return 회원 목록
   */
  public List<SportsVO> list();
  
  
  /**
   *  글 조회
   *  <select id="read" resultType="SportsVO" parameterType="int">
   * @param  
   */
  
  public SportsVO read(int sno);
  
  /**
   * <update id ="update" parameterType="SportsVO">
   * 
   */
  public int update(SportsVO vo);
  
  /**
   * <delete id="delete" parameterType="int">
   * @param sno
   * @return
   */
  
  public int delete(int sno);
  
  
  /**
  *  <update id = "increaseCnt" parameterType = "int" >
  *  @param sno
  *  @return 
  */
  public int increaseCnt(int sno);
  
  
  /**
   * 검색 목록
   * <select id="list2" resultType="SportsVO" parameterType="HashMap" > 
   * @param hashmap 검색 조건
   * @return
   */
  public List<SportsVO> list2(HashMap hashmap);
  
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
  public List<SportsVO> list3(HashMap hashmap);

  
  /**
   * 회원정보(닉네임, 이메일) 가져오기
   * @param userid
   * @return
   */
  public MemberVO test(String userid);
  
  
  /**
   * 스포츠 최신글 목록
   * @param hashmap
   * @return
   */
  public List<SportsVO> newlist();

  
}

  
  

