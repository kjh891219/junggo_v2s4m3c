package dev.mvc.gamedevice;

import java.util.List;

import dev.mvc.game.GameVO;

public interface GameDeviceDAOInter {

  /**
   * <insert id="create" parameterType="GameDeviceVO">
   */
  public int create(GameDeviceVO gamedeviceVO);
  
  /**
   * <select id="list" resultType="GameDeviceVO">
   * 
   */
  public List<GameDeviceVO> list();

  /**
   * 회원 정보 조회
   * <select id="read" resultType="MemberVO" parameterType="int">
   * @param mno
   * @return
   */
  public GameDeviceVO read(int gdno); 
 
  /**
   * Code를 수정합니다.
   * <update id="update" parameterType="CodeVO"> 
   * @param codeVO
   * @return 수정된 레코드 갯수
   */
  public int update(GameDeviceVO gamedeviceVO);
  
  /**
   * 레코드 1건 삭제
   * <delete id="delete" parameterType="int">
   * @param codeno 삭제할 코드 번호
   * @return 삭제된 레코드 갯수
   */
  public int delete(int gdno); 

}
