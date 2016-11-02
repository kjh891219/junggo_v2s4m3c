package dev.mvc.cheat_reply;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository("dev.mvc.cheat_reply.Cheat_replyDAO")
public class Cheat_replyDAO implements Cheat_replyDAOInter{

  @Autowired
  private SqlSessionTemplate mybatis; 
  
  @Override
  public int create(Cheat_replyVO vo) {
    return mybatis.insert("cheat_reply.create", vo);
  }

  @Override
  public List<Cheat_replyVO> cheat_replyList(int ctno) {
    return mybatis.selectList("cheat_reply.cheat_replyList", ctno);
  }

  @Override
  public int updateAnsnum(Cheat_replyVO vo) {
    return mybatis.update("cheat_reply.updateAnsnum", vo);
  }

  @Override
  public Cheat_replyVO read(int ctno) {
    return mybatis.selectOne("cheat_reply.read", ctno);
  }

  @Override
  public int reply(Cheat_replyVO vo) {
    return mybatis.insert("cheat_reply.reply", vo);
  }

  @Override
  public int delete(int rno) {
    return mybatis.delete("cheat_reply.delete", rno);
  }

}
