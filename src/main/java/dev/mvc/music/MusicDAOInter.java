package dev.mvc.music;

import java.util.HashMap;
import java.util.List;

import dev.mvc.tmember.MemberVO;


public interface MusicDAOInter {
  /** 
   * 레코드를 등록합니다.
   * <insert id="create" parameterType="MusicVO">
   * @param vo 등록할 데이터 객체
   * @return 등록된 레코드 수
   */
  public int create(MusicVO vo);

  /**
   * 한건의 레코드 조회
   * <select id="read" resultType="MusicVO" parameterType="int">
   * @param ctno 글 번호
   * @return
   */
  public MusicVO read(int ctno);
  

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
   * @param ctno 글 번호
   * @return
   */
  public int delete(int ctno);

  
  /**
   * 수정
   * @param musicVO
   * @return
   */
  public int update(MusicVO musicVO);
  
  
  /**
   * 검색 목록
   * <select id="list" resultType="MusicVO" parameterType="HashMap" > 
   * @param hashmap 검색 조건
   * @return
   */
  public List<MusicVO> list(HashMap hashmap);
  
  
  /**
   * 등록된 글수의 증가
   * <update id="increaseCnt" parameterType="int">
   * @param ctno
   * @return
   */
  public int increaseCnt(int ctno);

  
  /**
   * 검색 목록
   * <select id="list2" resultType="MusicVO" parameterType="HashMap" >
   * @param hashmap 검색 조건
   * @return
   */
  List<MusicVO> list2(HashMap hashmap);

  
  /**
   * 회원정보(닉네임, 이메일) 가져오기
   * @param userid
   * @return
   */
  public MemberVO test(String userid);
  
}