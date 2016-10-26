package dev.mvc.camera_reply;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.camera_reply.Camera_replyDAO")

public class Camera_replyDAO implements Camera_replyDAOInter{
  
  @Autowired
  private SqlSessionTemplate mybatis;
  
  @Override
  public int create(Camera_replyVO vo) {
    return mybatis.insert("camera_reply.create", vo);
  }

  @Override
  public List<Camera_replyVO> camera_replyList(int ctno) {
    return mybatis.selectList("camera_reply.camera_replyList", ctno);
  }

  @Override
  public int updateAnsnum(Camera_replyVO vo) {
    return mybatis.update("camera_reply.updateAnsnum", vo);
  }

  @Override
  public Camera_replyVO read(int rno) {
    return mybatis.selectOne("camera_reply.read", rno);
  }

  @Override
  public int reply(Camera_replyVO vo) {
    return mybatis.insert("camera_reply.reply", vo);
  }
  
  

}
