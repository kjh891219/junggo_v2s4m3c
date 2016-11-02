package dev.mvc.cloth_reply;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.cloth_reply.Cloth_replyDAO")
public class Cloth_replyDAO implements Cloth_replyDAOInter{

  @Autowired
  private SqlSessionTemplate mybatis; 
  
  @Override
  public int create(Cloth_replyVO vo) {
    return mybatis.insert("cloth_reply.create", vo);
  }

  @Override
  public List<Cloth_replyVO> cloth_replyList(int clothno) {
    return mybatis.selectList("cloth_reply.cloth_replyList", clothno);
  }

  @Override
  public int updateAnsnum(Cloth_replyVO vo) {
    return mybatis.update("cloth_reply.updateAnsnum", vo);
  }

  @Override
  public Cloth_replyVO read(int rno) {
    return mybatis.selectOne("cloth_reply.read", rno);
  }

  @Override
  public int reply(Cloth_replyVO vo) {
    return mybatis.insert("cloth_reply.reply", vo);
  }

  @Override
  public int delete(int rno) {
    return mybatis.delete("cloth_reply.delete", rno);
  }
    
}
