package dev.mvc.message;

import java.util.HashMap;
import java.util.List;

import dev.mvc.camera.CameraVO;

public interface MessageDAOInter {
  
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
  
  /**
   * ���� �޽��� ����
   * @param memberVO
   * @return
   */
  public int visible(HashMap map);
  
  /**
   * �˻��� ���ڵ� ��
   * <select id="count" resultType="int" parameterType="HashMap" >
   * @param hashmap �˻� ����
   * @return
   */
  public int count(HashMap hashmap);
  
  /**
   * �޽��� �˻� ���
   * <select id="list" resultType="MessageVO" parameterType="HashMap" > 
   * @param hashmap �˻� ����
   * @return
   */
  public List<MessageVO> list(HashMap hashmap);
  
}
