package dev.mvc.art_reply;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.art_reply.Art_replyDAO")

public class Art_replyDAO implements Art_replyDAOInter{
  
  @Autowired
  private SqlSessionTemplate mybatis;
  
  @Override
  public int create(Art_replyVO vo) {
    return mybatis.insert("art_reply.create", vo);
  }

  @Override
  public List<Art_replyVO> art_replyList(int ano) {
    return mybatis.selectList("art_reply.art_replyList", ano);
  }

  @Override
  public int updateAnsnum(Art_replyVO vo) {
    return mybatis.update("art_reply.updateAnsnum", vo);
  }

  @Override
  public Art_replyVO read(int rno) {
    return mybatis.selectOne("art_reply.read", rno);
  }

  @Override
  public int reply(Art_replyVO vo) {
    return mybatis.insert("art_reply.reply", vo);
  }

  @Override
  public int delete(int rno) {
    return mybatis.delete("art_reply.delete", rno);
  }
  
  

}
