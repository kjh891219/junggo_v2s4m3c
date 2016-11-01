package dev.mvc.nt_reply;

import java.util.List;



public interface Nt_ReplyDAOInter {

  /**µî·Ï
   * <insert id="create" parameterType="Game_ReplyVO">
   * 
   */
  public int create(Nt_ReplyVO nt_replyVO);
  

  /**
   *    <select id="read" resultType="GameVO" parameterType="int">
   * 
   */
  public Nt_ReplyVO read(int rno);   
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
  public int updateAnsnum(Nt_ReplyVO vo);
  /**
   * 
   *    <insert id="reply" parameterType="Game_ReplyVO">
   */
   public int reply(Nt_ReplyVO vo);
  
  /**
   * 
   *   <select id="list" resultType="Game_ReplyVO" parameterType="HashMap" >
   */
   public List<Nt_ReplyVO> nt_replyList(int noticeno);
}
