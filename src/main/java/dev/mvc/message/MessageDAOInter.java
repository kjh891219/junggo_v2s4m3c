package dev.mvc.message;

import java.util.HashMap;
import java.util.List;

import dev.mvc.camera.CameraVO;

public interface MessageDAOInter {
  
  /**
   * 받은 메시지 하나만 조회
   * @param messageVO
   * @return
   */
  public MessageVO read_msg(int msg_no);
  
  /**
   * 메시지 전송
   * <insert id="create" parameterType="MessageVO">
   * @param messageVO
   * @return
   */
  public int create(MessageVO messageVO);
  
  /**
   * 받은 메시지 삭제 (안 보이기)
   * @param memberVO
   * @return
   */
  public int visible(HashMap map);
  
  /**
   * 검색된 레코드 수
   * <select id="count" resultType="int" parameterType="HashMap" >
   * @param hashmap 검색 조건
   * @return
   */
  public int count(HashMap hashmap);
  
  /**
   * 메시지 검색 목록
   * <select id="list" resultType="MessageVO" parameterType="HashMap" > 
   * @param hashmap 검색 조건
   * @return
   */
  public List<MessageVO> list(HashMap hashmap);
  
  /**
   * 메시지 읽음 표시 
   * <update id="read_ck" parameterType="Map">
   * @param memberVO
   * @return
   */
  public int read_ck(HashMap map);
  
  /**
   * 관리자용 - 메시지 삭제
   * @param date 범위 날짜
   * @return
   */
  public int delete(HashMap map);
  
}
