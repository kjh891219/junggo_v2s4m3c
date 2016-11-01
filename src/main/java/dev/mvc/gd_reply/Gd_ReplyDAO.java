package dev.mvc.gd_reply;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.gd_reply.Gd_ReplyDAO")
public class Gd_ReplyDAO implements Gd_ReplyDAOInter{

  @Autowired
  private SqlSessionTemplate mybatis;
  
  
  @Override
  public int create(Gd_ReplyVO gd_replyVO) {
    return mybatis.insert("gd_reply.create", gd_replyVO);
  }

  @Override
  public Gd_ReplyVO read(int rno) {
    return mybatis.selectOne("gd_reply.read", rno);
  }

  @Override
  public int delete(int rno) {
    return mybatis.delete("gd_reply.delete", rno);
  }

  @Override
  public int intcreaseCnt(int grno) {
    return 0;
  }

  @Override
  public int updateAnsnum(Gd_ReplyVO vo) {
    return mybatis.update("gd_reply.updateAnsnum", vo);
  }

  @Override
  public int reply(Gd_ReplyVO vo) {
    return mybatis.insert("gd_reply.reply", vo);
  }

  @Override
  public List<Gd_ReplyVO> gd_replyList(int gdno) {
    return mybatis.selectList("gd_reply.gd_replyList", gdno);
  }

}
