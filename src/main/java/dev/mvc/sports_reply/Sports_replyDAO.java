package dev.mvc.sports_reply;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.sports_reply.Sports_replyDAO")
public class Sports_replyDAO implements Sports_replyDAOInter{

  @Autowired
  private SqlSessionTemplate mybatis;

  @Override
  public int create(Sports_replyVO vo) {
    return mybatis.insert("sports_reply.create", vo);
  }

  @Override
  public List<Sports_replyVO> sports_replyList(int sno) {
    return mybatis.selectList("sports_reply.sports_replyList", sno);
  }

  @Override
  public int updateAnsnum(Sports_replyVO vo) {
    return mybatis.update("sports_reply.updateAnsnum", vo);
  }

  @Override
  public Sports_replyVO read(int rno) {
    return mybatis.selectOne("sports_reply.read", rno);
  }

  @Override
  public int reply(Sports_replyVO vo) {
    return mybatis.insert("sports_reply.reply", vo);
  }

  @Override
  public int delete(int rno) {
    return mybatis.delete("sports_reply.delete", rno);
  }
  
  
}
