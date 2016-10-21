package dev.mvc.message;

import java.util.List;

public interface MessageDAOInter {
  

  /**
   * �޽��� ����(������ ����) 
    <insert id="create" parameterType="MessageVO">
   * @param messageVO
   */
/*  public void create(MessageVO messageVO);*/
  
  /**
   * ���� �޽��� ��� ��ȸ
   * @param messageVO
   * @return
   */
  public List<MessageVO> receive_list(String receiveid);
  
  /**
   * ���� �޽��� ��� ��ȸ
   * <select id="send_list" resultType="MessageVO" parameterType="String">  
   * @param sendid
   * @return
   */
  public List<MessageVO> send_list(String sendid);

  /**
   * ���� �޽��� �ϳ��� ��ȸ
   * @param messageVO
   * @return
   */
  public MessageVO read_msg(int msg_no);
  
  /**
   * �޽��� ����
   * <insert id="create" parameterType="MessageVO">
   * @param messageVO
   * @return
   */
  public int create(MessageVO messageVO);
  
}
