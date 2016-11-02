package dev.mvc.cosmetic_reply;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.cosmetic_reply.Cosmetic_replyVO;

@Repository("dev.mvc.cosmetic_reply.Cosmetic_replyDAO")
public class Cosmetic_replyDAO implements Cosmetic_replyDAOInter{
@Autowired
private SqlSessionTemplate mybatis;

@Override
public int create(Cosmetic_replyVO vo) {
  return mybatis.insert("cosmetic_reply.create", vo);
}

@Override
public List<Cosmetic_replyVO> cosmetic_replyList(int cno) {
  return mybatis.selectList("cosmetic_reply.cosmetic_replyList", cno);
}

@Override
public int updateAnsnum(Cosmetic_replyVO vo) {
  return mybatis.update("cosmetic_reply.updateAnsnum", vo);
}

@Override
public Cosmetic_replyVO read(int rno) {
  return mybatis.selectOne("cosmetic_reply.read", rno);
}

@Override
public int delete(int rno) {
  return mybatis.delete("cosmetic_reply.delete", rno);
}

@Override
public int reply(Cosmetic_replyVO vo) {
  return mybatis.insert("cosmetic_reply.reply", vo);
}





  
}
