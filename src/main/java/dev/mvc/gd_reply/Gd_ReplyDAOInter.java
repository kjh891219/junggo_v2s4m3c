package dev.mvc.gd_reply;

import java.util.List;



public interface Gd_ReplyDAOInter {

  /**µî·Ï
   * <insert id="create" parameterType="Game_ReplyVO">
   * 
   */
  public int create(Gd_ReplyVO gd_replyVO);
  

  /**
   *    <select id="read" resultType="GameVO" parameterType="int">
   * 
   */
  public Gd_ReplyVO read(int rno);   
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
  public int updateAnsnum(Gd_ReplyVO vo);
  /**
   * 
   *    <insert id="reply" parameterType="Game_ReplyVO">
   */
   public int reply(Gd_ReplyVO vo);
  
  /**
   * 
   *   <select id="list" resultType="Game_ReplyVO" parameterType="HashMap" >
   */
   public List<Gd_ReplyVO> gd_replyList(int gdno);
}