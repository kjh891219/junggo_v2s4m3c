package dev.mvc.living;

import java.util.HashMap;
import java.util.List;

public interface LivingDAOInter {
/**
 * <insert id="create" parameterType="LivingVO">
 */
  
  public int create(LivingVO livingVO);
  /**
   *  <update id="update" parameterType="LivingVO">
   */

  public int update(LivingVO livingVO);
  /**
   *    <select id="read" resultType="LivingVO" parameterType="int">
   */
  public LivingVO read(int lno);
  
  /**
   *   <delete id="delete" parameterType="int">
   */

  public int delete(int lno);
  
  /**
   *    <update id ="increaseCnt" parameterType ="int">
   */

  public int increaseCnt(int lno);
  /**
   *  <select id = "count" resultType="int" parameterType="HashMap">
   */

  public int count(HashMap hashMap);
  
  /**
   *  <select id="list2" resultType="LivingVO" parameterType="HashMap" >
   */
 
  public List<LivingVO> list2(HashMap<String, Object> hashMap);
  
}
