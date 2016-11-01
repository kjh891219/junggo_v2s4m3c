package dev.mvc.live_reply;

import java.util.List;


public interface Live_ReplyDAOInter {

  /**µî·Ï
   * <insert id="create" parameterType="Game_ReplyVO">
   * 
   */
  public int create(Live_ReplyVO gd_replyVO);
  

  /**
   *    <select id="read" resultType="GameVO" parameterType="int">
   * 
   */
  public Live_ReplyVO read(int rno);   
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
  public int updateAnsnum(Live_ReplyVO vo);
  /**
   * 
   *    <insert id="reply" parameterType="Game_ReplyVO">
   */
   public int reply(Live_ReplyVO vo);
  
  /**
   * 
   *   <select id="list" resultType="Game_ReplyVO" parameterType="HashMap" >
   */
   public List<Live_ReplyVO> live_replyList(int lno);
}