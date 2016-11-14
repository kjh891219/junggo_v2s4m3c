package dev.mvc.usedcar;

import java.util.HashMap;
import java.util.List;

import dev.mvc.tmember.MemberVO;
import dev.mvc.usedcar.UsedcarVO;


public interface UsedcarDAOInter {
  
  /** 
   * 레코드를 등록합니다.
   * <insert id="create" parameterType="CodeVO">
   * @param vo 등록할 데이터 객체
   * @return 등록된 레코드 수
   */
  public int create(UsedcarVO vo);

  /**
   * 한건의 레코드 삭제
   * <delete id="delete" parameterType="int">
   * @param u_no 글 번호
   * @return
   */
  public int delete(int u_no);

  
  /**
   * 수정
   * @param usedcarVO
   * @return
   */
  public int update(UsedcarVO usedcarVO);
  
  
  /**
   * 한건의 레코드 조회
   * <select id="read" resultType="usedcarVO" parameterType="int">
   * @param u_no 글 번호
   * @return
   */
  public UsedcarVO read(int u_no);

  
  /**
   * 등록된 글수의 증가
   * <update id="increaseCnt" parameterType="int">
   * @param u_no
   * @return
   */
  public int increaseCnt(int u_no);
  
  /**
   * 검색 목록
   * <select id="list2" resultType="UsedcarVO" parameterType="HashMap" > 
   * @param hashmap 검색 조건
   * @return
   */
  public List<UsedcarVO> list2(HashMap hashmap);
  
  /**
   * 검색된 레코드 수
   * <select id="count" resultType="int" parameterType="HashMap" > 
   * @param hashmap 검색 조건
   * @return
   */
  public int count(HashMap hashmap);

  /**
   * 검색 목록
   * <select id="list" resultType="UsedcarVO" parameterType="HashMap" > 
   * @param hashmap 검색 조건
   * @return
   */
  public List<UsedcarVO> list(HashMap hashmap);

  /**
   * 회원정보(닉네임, 이메일) 가져오기
   * @param userid
   * @return
   */
  public MemberVO test(String userid);
  
  /**
   * 중고차 최신글 목록
   * @param hashmap
   * @return
   */
  public List<UsedcarVO> newlist();


}