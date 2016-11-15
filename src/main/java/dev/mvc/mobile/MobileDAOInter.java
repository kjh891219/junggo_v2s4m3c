package dev.mvc.mobile;

import java.util.HashMap;
import java.util.List;

import dev.mvc.game.GameVO;
import dev.mvc.tmember.MemberVO;

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
 
 public int increaseCnt(int mno);
 
 public int count(HashMap hashMap);
 
 public List<MobileVO> list2(HashMap<String, Object> hashMap);
 
 
 public MemberVO test(String userid);
 

 public List<MobileVO> newlist();
  
}
