package dev.mvc.art;

import java.util.HashMap;
import java.util.List;

import dev.mvc.camera.CameraVO;


public interface ArtDAOInter {

  /**
   * ���ڵ带 ����մϴ�.
   * <insert id="create" parameterType="ArtVO">
   * @param vo ����� ������ ��ü
   * @return ��ϵ� ���ڵ� ��
   */
  public int create(ArtVO vo);
  
  /**
   * �˻��� ���ڵ� ��
   * <select id="count" resultType="int" parameterType="HashMap" >
   * @param hashmap �˻� ����
   * @return
   */
  public int count(HashMap hashmap);
  
  /**
   * �˻� ���
   * <select id="list3" resultType="ArtVO" parameterType="HashMap" > 
   * @param hashmap �˻� ����
   * @return
   */
  public List<ArtVO> list3(HashMap hashmap);
  
  /**
   * ����/��ȭ ���� ��ȸ
   * <select id="read" resultType="ArtVO" parameterType="int">
   * @param ano
   * @return
   */
  public ArtVO read(int ano); 
  
  /**
   * ��ȸ�� ����
   * <update id="increaseCnt" parameterType="int">
   * @param ano
   * @return
   */
  public int increaseCnt(int ano);
  
  /**
   * ī�޶� �ۻ���
   * <delete id="delete" parameterType="int">
   * @param ano
   * @return
   */
  public int delete(int ano);
  
  /**
   * ����
   * @param artVO
   * @return
   */
  public int update(ArtVO artVO);
}
