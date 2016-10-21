package dev.mvc.mobile;

import java.util.List;

public interface MobileDAOInter {
 
  /**
   * <insert id="create" parameterType="MobileVO">
   */
 public int create(MobileVO mobileVO);
 
  /**
   *  <select id="list" resultType="MobileVO">
   */
public List<MobileVO> list();
 
  /**
   *  <update id="update" parameterType="MobileVO">
   */
 public MobileVO read(int mno);

  /**
   *    <select id="read" resultType="MobileVO" parameterType="int">
   */
 public int update(MobileVO mobileVO);
 
  /**
   * <delete id="delete" parameterType="int">
   */
 public int delete(int mno);
 
  
}