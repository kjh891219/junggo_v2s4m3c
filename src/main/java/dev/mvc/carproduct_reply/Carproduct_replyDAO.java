package dev.mvc.carproduct_reply;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.carproduct_reply.Carproduct_replyDAO")

public class Carproduct_replyDAO implements Carproduct_replyDAOInter{
  
  @Autowired
  private SqlSessionTemplate mybatis;
  
  @Override
  public int create(Carproduct_replyVO vo) {
    return mybatis.insert("carproduct_reply.create", vo);
  }

  @Override
  public List<Carproduct_replyVO> carproduct_replyList(int p_no) {
    return mybatis.selectList("carproduct_reply.carproduct_replyList", p_no);
  }

  @Override
  public int updateAnsnum(Carproduct_replyVO vo) {
    return mybatis.update("carproduct_reply.updateAnsnum", vo);
  }

  @Override
  public Carproduct_replyVO read(int rno) {
    return mybatis.selectOne("carproduct_reply.read", rno);
  }

  @Override
  public int reply(Carproduct_replyVO vo) {
    return mybatis.insert("carproduct_reply.reply", vo);
  }

  @Override
  public int delete(int rno) {
    return mybatis.delete("carproduct_reply.delete", rno);
  }
  
  

}
