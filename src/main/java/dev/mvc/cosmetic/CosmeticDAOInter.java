package dev.mvc.cosmetic;

import java.util.HashMap;
import java.util.List;

import dev.mvc.cosmetic.CosmeticVO;
import dev.mvc.tmember.MemberVO;

public interface CosmeticDAOInter {
  /**
   * 레코드를 등록합니다.
   * <insert id="create" parameterType="CosmeticVO">
   * @param vo 등록할 데이터 객체
   * @return 등록된 레코드 수
   */
  public int create(CosmeticVO vo);
  
  /**
   * 회원 전체 목록
   * <select id="list" resultType="CosmeticVO">
   * @return 회원 목록
   */ 
  public List<CosmeticVO> list(); 
  
  /**
   * 정보 읽기
   * <select id="read" resultType="CosmeticVO" parameterType="int">
   */
  public CosmeticVO read(int cno); 
  
  
  /**
   * 수정처리
   * <update id ="update" parameterType="CosmeticVO">
   * @param vo
   * @return
   */
  
  public int update(CosmeticVO vo);
  
  /**
   * <delete id="delete" parameterType="int">
   * @param cno
   * @return
   */
  
  public int delete(int cno);
 
  /**
  *  <update id = "increaseCnt" parameterType = "int" >
  *  @param cno
  *  @return 
  */
  public int increaseCnt(int cno);
  
  /**
   * 검색 목록
   * <select id="list2" resultType="CosmeticVO" parameterType="HashMap" > 
   * @param hashmap 검색 조건
   * @return
   */
  public List<CosmeticVO> list2(HashMap hashmap);
  
  /**
   * 검색된 레코드 수
   * <select id="count" resultType="int" parameterType="HashMap" > 
   * @param hashmap 검색 조건
   * @return
   */
  public int count(HashMap hashmap);
  
  
  /**
   * 검색 목록
   * <select id="list3" resultType="CosmeticVO" parameterType="HashMap" > 
   * @param hashmap 검색 조건
   * @return
   */
  public List<CosmeticVO> list3(HashMap hashmap);

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
  public List<CosmeticVO> newlist();

}
