package dev.mvc.reviews_reply;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.reviews_reply.Reviews_replyDAO")

public class Reviews_replyDAO implements Reviews_replyDAOInter{
  
  @Autowired
  private SqlSessionTemplate mybatis;
  
  @Override
  public int create(Reviews_replyVO vo) {
    return mybatis.insert("reviews_reply.create", vo);
  }

  @Override
  public List<Reviews_replyVO> reviews_replyList(int r_no) {
    return mybatis.selectList("reviews_reply.reviews_replyList", r_no);
  }

  @Override
  public int updateAnsnum(Reviews_replyVO vo) {
    return mybatis.update("reviews_reply.updateAnsnum", vo);
  }

  @Override
  public Reviews_replyVO read(int rno) {
    return mybatis.selectOne("reviews_reply.read", rno);
  }

  @Override
  public int reply(Reviews_replyVO vo) {
    return mybatis.insert("reviews_reply.reply", vo);
  }

  @Override
  public int delete(int rno) {
    return mybatis.delete("reviews_reply.delete", rno);
  }
  
  

}
