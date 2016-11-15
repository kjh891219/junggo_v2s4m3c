package dev.mvc.gamedevice;

import java.util.HashMap;
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
   * ȸ�� ���� ��ȸ
   * <select id="read" resultType="MemberVO" parameterType="int">
   * @param mno
   * @return
   */
  public GameDeviceVO read(int gdno); 
 
  /**
   * Code�� �����մϴ�.
   * <update id="update" parameterType="CodeVO"> 
   * @param codeVO
   * @return ������ ���ڵ� ����
   */
  public int update(GameDeviceVO gamedeviceVO);
  
  /**
   * ���ڵ� 1�� ����
   * <delete id="delete" parameterType="int">
   * @param codeno ������ �ڵ� ��ȣ
   * @return ������ ���ڵ� ����
   */
  public int delete(int gdno); 
  
  public int increaseCnt(int gdno);
  
  public int count(HashMap hashMap);  

  public List<GameDeviceVO> list2(HashMap<String, Object> hashMap);
  
  /**
   * ���ӿ�ǰ �ֽű� ���
   * @param hashmap
   * @return
   */
  public List<GameDeviceVO> newlist();

}
