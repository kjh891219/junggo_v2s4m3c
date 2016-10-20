package dev.mvc.message;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.tmember.MessageDAO")
public class MessageDAO implements MessageDAOInter{
    
  @Autowired
  private SqlSessionTemplate mybatis; //MyBatis 
   
  public MessageDAO(){
     System.out.println("--> MessageDAO created.");
  }

  @Override
  public List<MessageVO> receive_list(String receiveid) {
    return mybatis.selectList("message.receive_list", receiveid);
  }
  
  @Override
  public List<MessageVO> send_list(String sendid) {
    return mybatis.selectList("message.send_list", sendid);
  }

  @Override
  public MessageVO read_msg(int msg_no) {
    return mybatis.selectOne("message.read_msg", msg_no);
  }

  @Override
  public int create(MessageVO messageVO) {
    return mybatis.insert("message.create", messageVO);
  }
}
