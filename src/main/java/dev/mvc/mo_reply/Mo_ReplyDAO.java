package dev.mvc.mo_reply;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.mo_reply.Mo_ReplyDAO")
public class Mo_ReplyDAO implements Mo_ReplyDAOInter {

  @Autowired 
  private SqlSessionTemplate mybatis;

  @Override
  public int create(Mo_ReplyVO mo_replyVO) {
    return mybatis.insert("mo_reply.create", mo_replyVO);
  }

  @Override
  public Mo_ReplyVO read(int rno) {
    return mybatis.selectOne("mo_reply.read", rno);
  }

  @Override
  public int delete(int rno) {
    return mybatis.delete("mo_reply.delete", rno);
  }

  @Override
  public int intcreaseCnt(int grno) {
    return 0;
  }

  @Override
  public int updateAnsnum(Mo_ReplyVO vo) {
    return mybatis.update("mo_reply.updateAnsnum", vo);
  }

  @Override
  public int reply(Mo_ReplyVO vo) {
    return mybatis.insert("mo_reply.reply", vo);
  }

  @Override
  public List<Mo_ReplyVO> mo_replyList(int mno) {
    return mybatis.selectList("mo_reply.mo_replyList", mno);
  }

 
}
