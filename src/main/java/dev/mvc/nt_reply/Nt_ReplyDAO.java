package dev.mvc.nt_reply;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.nt_reply.Nt_ReplyDAO")
public class Nt_ReplyDAO implements Nt_ReplyDAOInter {
   
  
  @Autowired
  private SqlSessionTemplate mybatis;

  @Override
  public int create(Nt_ReplyVO nt_replyVO) {
    return mybatis.insert("nt_reply.create", nt_replyVO);
  }

  @Override
  public Nt_ReplyVO read(int rno) {
    return mybatis.selectOne("nt_reply.read", rno);
  }

  @Override
  public int delete(int rno) {
    return mybatis.delete("nt_reply.delete", rno);
  }

  @Override
  public int intcreaseCnt(int grno) {
    return 0;
  }

  @Override
  public int updateAnsnum(Nt_ReplyVO vo) {
    return mybatis.update("nt_reply.updateAnsnum", vo);
  }

  @Override
  public int reply(Nt_ReplyVO vo) {
    return mybatis.insert("nt_reply.reply", vo);
  }

  @Override
  public List<Nt_ReplyVO> nt_replyList(int noticeno) {
    return mybatis.selectList("nt_reply.nt_replyList", noticeno);
  }
}
