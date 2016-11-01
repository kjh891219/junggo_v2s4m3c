package dev.mvc.game_reply;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.game_reply.Game_ReplyDAO")
public class Game_ReplyDAO implements Game_ReplyDAOInter {

  @Autowired
  private SqlSessionTemplate mybatis;
  
  @Override
  public int create(Game_ReplyVO game_replyVO) {
    return mybatis.insert("game_reply.create", game_replyVO);
  }

  @Override
  public int delete(int rno) {
    return mybatis.delete("game_reply.delete", rno);

  }

  @Override
  public int intcreaseCnt(int grno) {
    return 0;
  }

  @Override
  public int updateAnsnum(Game_ReplyVO vo) {
    return mybatis.update("game_reply.updateAnsnum", vo);
  }

  @Override
  public int reply(Game_ReplyVO vo) {
    return mybatis.insert("game_reply.reply", vo);
  }

  @Override
  public List<Game_ReplyVO> game_replyList(int gno) {
    return mybatis.selectList("game_reply.game_replyList", gno);

  }

  @Override
  public Game_ReplyVO read(int rno) {
    return mybatis.selectOne("game_reply.read", rno);
  }

}
