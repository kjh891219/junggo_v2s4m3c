package dev.mvc.music;

import java.util.HashMap;
import java.util.List;

import dev.mvc.tmember.MemberVO;


public interface MusicDAOInter {
  /** 
   * ���ڵ带 ����մϴ�.
   * <insert id="create" parameterType="MusicVO">
   * @param vo ����� ������ ��ü
   * @return ��ϵ� ���ڵ� ��
   */
  public int create(MusicVO vo);

  /**
   * �Ѱ��� ���ڵ� ��ȸ
   * <select id="read" resultType="MusicVO" parameterType="int">
   * @param ctno �� ��ȣ
   * @return
   */
  public MusicVO read(int ctno);
  

  /**
   * �˻��� ���ڵ� ��
   * <select id="count" resultType="int" parameterType="HashMap" > 
   * @param hashmap �˻� ����
   * @return
   */
  public int count(HashMap hashmap);

  
  /**
   * �Ѱ��� ���ڵ� ����
   * <delete id="delete" parameterType="int">
   * @param ctno �� ��ȣ
   * @return
   */
  public int delete(int ctno);

  
  /**
   * ����
   * @param musicVO
   * @return
   */
  public int update(MusicVO musicVO);
  
  
  /**
   * �˻� ���
   * <select id="list" resultType="MusicVO" parameterType="HashMap" > 
   * @param hashmap �˻� ����
   * @return
   */
  public List<MusicVO> list(HashMap hashmap);
  
  
  /**
   * ��ϵ� �ۼ��� ����
   * <update id="increaseCnt" parameterType="int">
   * @param ctno
   * @return
   */
  public int increaseCnt(int ctno);

  
  /**
   * �˻� ���
   * <select id="list2" resultType="MusicVO" parameterType="HashMap" >
   * @param hashmap �˻� ����
   * @return
   */
  List<MusicVO> list2(HashMap hashmap);

  
  /**
   * ȸ������(�г���, �̸���) ��������
   * @param userid
   * @return
   */
  public MemberVO test(String userid);
  
}