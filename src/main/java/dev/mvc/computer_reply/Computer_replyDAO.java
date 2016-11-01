package dev.mvc.computer_reply;




import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.computer_reply.Computer_replyDAO")

public class Computer_replyDAO implements Computer_replyDAOInter{
  
  @Autowired
  private SqlSessionTemplate mybatis;
  
  @Override
  public int create(Computer_replyVO vo) {
    return mybatis.insert("computer_reply.create", vo);
  }

  @Override
  public List<Computer_replyVO> nreplyList(int ctno) {
    return mybatis.selectList("computer_reply.nreplyList", ctno);
  }

  @Override
  public int updateAnsnum(Computer_replyVO vo) {
    return mybatis.update("computer_reply.updateAnsnum", vo);
  }

  @Override
  public Computer_replyVO read(int rno) {
    return mybatis.selectOne("computer_reply.read", rno);
  }

  @Override
  public int reply(Computer_replyVO vo) {
    return mybatis.insert("computer_reply.reply", vo);
  }

  @Override
  public int delete(int rno) {
    return mybatis.delete("computer_reply.delete", rno);
  }
  
  

}
