package dev.mvc.usedcar_reply;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.usedcar_reply.Usedcar_replyDAO")

public class Usedcar_replyDAO implements Usedcar_replyDAOInter{
  
  @Autowired
  private SqlSessionTemplate mybatis;
  
  @Override
  public int create(Usedcar_replyVO vo) {
    return mybatis.insert("usedcar_reply.create", vo);
  }

  @Override
  public List<Usedcar_replyVO> usedcar_replyList(int u_no) {
    return mybatis.selectList("usedcar_reply.usedcar_replyList", u_no);
  }

  @Override
  public int updateAnsnum(Usedcar_replyVO vo) {
    return mybatis.update("usedcar_reply.updateAnsnum", vo);
  }

  @Override
  public Usedcar_replyVO read(int rno) {
    return mybatis.selectOne("usedcar_reply.read", rno);
  }

  @Override
  public int reply(Usedcar_replyVO vo) {
    return mybatis.insert("usedcar_reply.reply", vo);
  }

  @Override
  public int delete(int rno) {
    return mybatis.delete("usedcar_reply.delete", rno);
  }
  
  

}
