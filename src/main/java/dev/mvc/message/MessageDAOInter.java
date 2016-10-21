package dev.mvc.message;

import java.util.List;

public interface MessageDAOInter {
  

  /**
   * 메시지 전송(쪽지함 삽입) 
    <insert id="create" parameterType="MessageVO">
   * @param messageVO
   */
/*  public void create(MessageVO messageVO);*/
  
  /**
   * 받은 메시지 모두 조회
   * @param messageVO
   * @return
   */
  public List<MessageVO> receive_list(String receiveid);
  
  /**
   * 보낸 메시지 모두 조회
   * <select id="send_list" resultType="MessageVO" parameterType="String">  
   * @param sendid
   * @return
   */
  public List<MessageVO> send_list(String sendid);

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
  
}
