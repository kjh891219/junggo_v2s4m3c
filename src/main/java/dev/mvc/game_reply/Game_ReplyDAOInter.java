package dev.mvc.game_reply;


import java.util.List;


public interface Game_ReplyDAOInter {

  /**µî·Ï
   * <insert id="create" parameterType="Game_ReplyVO">
   * 
   */
  public int create(Game_ReplyVO game_replyVO);
  

  /**
   *    <select id="read" resultType="GameVO" parameterType="int">
   * 
   */
  public Game_ReplyVO read(int rno);   
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
  public int updateAnsnum(Game_ReplyVO vo);
  /**
   * 
   *    <insert id="reply" parameterType="Game_ReplyVO">
   */
   public int reply(Game_ReplyVO vo);
  
  /**
   * 
   *   <select id="list" resultType="Game_ReplyVO" parameterType="HashMap" >
   */
   public List<Game_ReplyVO> game_replyList(int gno);
}
