package dev.mvc.mo_reply;

import java.util.List;

public interface Mo_ReplyDAOInter {

  
  /**µî·Ï
   * <insert id="create" parameterType="Game_ReplyVO">
   * 
   */
  public int create(Mo_ReplyVO mo_replyVO);
  

  /**
   *    <select id="read" resultType="GameVO" parameterType="int">
   * 
   */
  public Mo_ReplyVO read(int rno);   
  /**
   *   <delete id="delete" parameterType="int">

   * 
   */
  public int delete(int rno);
  /**
   *    <update id ="increaseCnt" parameterType ="int">
   * 
   */
 public int intcreaseCnt(int grno);  
  /**
   *   <update id='updateAnsnum' parameterType="Game_ReplyVO">
   * 
   */
  public int updateAnsnum(Mo_ReplyVO vo);
  /**
   * 
   *    <insert id="reply" parameterType="Game_ReplyVO">
   */
   public int reply(Mo_ReplyVO vo);
  
  /**
   * 
   *   <select id="list" resultType="Game_ReplyVO" parameterType="HashMap" >
   */
   public List<Mo_ReplyVO> mo_replyList(int mno);
}
