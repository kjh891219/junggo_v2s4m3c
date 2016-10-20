package dev.mvc.game;

import java.util.HashMap;
import java.util.List;

import dev.mvc.game.GameVO;

public interface GameDAOInter {
 
  /**
   * 레코드를 등록합니다.
   * <insert id="create" parameterType="MemberVO">
   * @param vo 등록할 데이터 객체
   * @return 등록된 레코드 수
   */
  public int create(GameVO vo);
            
  /**
   * 회원 전체 목록
   * <select id="list" resultType="GameVO">
   * @return 회원 목록
   */
  public List<GameVO> list();
  
  /**
   * 회원 정보 조회
   * <select id="read" resultType="MemberVO" parameterType="int">
   * @param mno
   * @return
   */
  public GameVO read(int gno); 
 
  /**
   * Code를 수정합니다.
   * <update id="update" parameterType="CodeVO"> 
   * @param codeVO
   * @return 수정된 레코드 갯수
   */
  public int update(GameVO gameVO);
  
  /**
   * 레코드 1건 삭제
   * <delete id="delete" parameterType="int">
   * @param codeno 삭제할 코드 번호
   * @return 삭제된 레코드 갯수
   */
  public int delete(int gno); 
  
  /**
   * 조회수 증가 
   *  <update id ="increaseCnt" parameterType ="int">
   * 
   */
  public int increaseCnt(int gno);
  
  /**
   * 페이징 리스트
   *  <select id="list2" resultType="BlogVO" parameterType="HashMap" >

   */
  
  public List<GameVO> list2(HashMap<String, Object> hashMap);

  /**
   *  <select id = "count" resultType="int" parameterType="HashMap">
   * 
   */
  public int count(HashMap hashMap);  
  
}
