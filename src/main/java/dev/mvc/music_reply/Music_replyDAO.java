package dev.mvc.music_reply;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.music_reply.Music_replyDAO")

public class Music_replyDAO implements Music_replyDAOInter{
  
  @Autowired
  private SqlSessionTemplate mybatis;
  
  @Override
  public int create(Music_replyVO vo) {
    return mybatis.insert("music_reply.create", vo);
  }

  @Override
  public List<Music_replyVO> music_replyList(int ctno) {
    return mybatis.selectList("music_reply.music_replyList", ctno);
  }

  @Override
  public int updateAnsnum(Music_replyVO vo) {
    return mybatis.update("music_reply.updateAnsnum", vo);
  }

  @Override
  public Music_replyVO read(int rno) {
    return mybatis.selectOne("music_reply.read", rno);
  }

  @Override
  public int reply(Music_replyVO vo) {
    System.out.println(vo.getGrpno());
    System.out.println(vo.getIndent());
    System.out.println(vo.getAnsnum());
    return mybatis.insert("music_reply.reply", vo);
  }

  @Override
  public int delete(int rno) {
    return mybatis.delete("music_reply.delete", rno);
  }
  
  

}
