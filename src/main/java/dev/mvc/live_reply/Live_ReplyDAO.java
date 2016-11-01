package dev.mvc.live_reply;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.live_reply.Live_ReplyDAO")
public class Live_ReplyDAO implements Live_ReplyDAOInter {
 
  
  @Autowired 
 private SqlSessionTemplate mybatis;

  @Override
  public int create(Live_ReplyVO live_replyVO) {
    return mybatis.insert("live_reply.create", live_replyVO);
  }

  @Override
  public Live_ReplyVO read(int rno) {
    return mybatis.selectOne("live_reply.read", rno);
  }

  @Override
  public int delete(int rno) {
    return mybatis.delete("live_reply.delete", rno);
  }

  @Override
  public int intcreaseCnt(int grno) {
    return 0;
  }

  @Override
  public int updateAnsnum(Live_ReplyVO vo) {
    return mybatis.update("live_reply.updateAnsnum", vo);
  }

  @Override
  public int reply(Live_ReplyVO vo) {
    return mybatis.insert("live_reply.reply", vo);
  }

  @Override
  public List<Live_ReplyVO> live_replyList(int lno) {
    return mybatis.selectList("live_reply.live_replyList", lno);
  }
}
