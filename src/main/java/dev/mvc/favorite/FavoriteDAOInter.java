package dev.mvc.favorite;

import java.util.HashMap;
import java.util.List;


public interface FavoriteDAOInter {
  
  
  /** 
   * ���ڵ带 ����մϴ�.
   * <insert id="create" parameterType="CodeVO">
   * @param vo ����� ������ ��ü
   * @return ��ϵ� ���ڵ� ��
   */
  public int create(FavoriteVO favoriteVO);

  /**
   * �˻��� ���ڵ� ��
   * <select id="count" resultType="int" parameterType="HashMap" > 
   * @param hashmap �˻� ����
   * @return
   */
  public int count(HashMap hashmap);
  
  /**
   * �˻� ���
   * <select id="list" resultType="FavoriteVO" parameterType="HashMap" > 
   * @param hashmap �˻� ����
   * @return
   */
  public List<FavoriteVO> list(HashMap hashmap);


  /**
   * ���� �޽��� ����
   * @param memberVO
   * @return
   */
  public int visible(HashMap map);

  public List<FavoriteVO> list_userid(String userid);

  
}
