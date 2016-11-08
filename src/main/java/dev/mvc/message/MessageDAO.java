package dev.mvc.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.message.MessageDAO")
public class MessageDAO implements MessageDAOInter{
    
  @Autowired
  private SqlSessionTemplate mybatis; //MyBatis 
   
  public MessageDAO(){
     System.out.println("--> MessageDAO created.");
  }

  @Override
  public MessageVO read_msg(int msg_no) {
    return mybatis.selectOne("message.read_msg", msg_no);
  }

  @Override
  public int create(MessageVO messageVO) {
    return mybatis.insert("message.create", messageVO);
  }

  @Override
  public int visible(HashMap map) {
/*    System.out.println("DAO");
    System.out.println(map.get("msg_no_arr"));
    System.out.println(map.get("userid"));*/
    return mybatis.update("message.visible", map);
  }
  
  @Override
  public int count(HashMap hashmap) {
    return mybatis.selectOne("message.count", hashmap);
  }

  @Override
  public List<MessageVO> list(HashMap hashmap) {
    return mybatis.selectList("message.list", hashmap);
  }

  @Override
  public int read_ck(HashMap map) {
    return mybatis.update("message.read_ck", map);
  }

  @Override
  public int delete(HashMap map) {
    return mybatis.delete("message.delete", map);
  }
  
  
}
