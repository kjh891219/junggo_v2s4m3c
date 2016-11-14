package dev.mvc.favorite;

import java.util.HashMap;
import java.util.List;


public interface FavoriteDAOInter {
  
  
  /** 
   * 레코드를 등록합니다.
   * <insert id="create" parameterType="CodeVO">
   * @param vo 등록할 데이터 객체
   * @return 등록된 레코드 수
   */
  public int create(FavoriteVO favoriteVO);

  /**
   * 검색된 레코드 수
   * <select id="count" resultType="int" parameterType="HashMap" > 
   * @param hashmap 검색 조건
   * @return
   */
  public int count(HashMap hashmap);
  
  /**
   * 검색 목록
   * <select id="list" resultType="FavoriteVO" parameterType="HashMap" > 
   * @param hashmap 검색 조건
   * @return
   */
  public List<FavoriteVO> list(HashMap hashmap);


  /**
   * 받은 메시지 삭제
   * @param memberVO
   * @return
   */
  public int visible(HashMap map);

  public List<FavoriteVO> list_userid(String userid);

  
}
