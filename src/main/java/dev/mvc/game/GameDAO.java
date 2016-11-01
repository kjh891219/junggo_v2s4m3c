package dev.mvc.game;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
     
@Repository("dev.mvc.game.GameDAO")
public class GameDAO implements GameDAOInter {
   @Autowired             
   private SqlSessionTemplate mybatis;
   
   public GameDAO(){
     System.out.println("--> GameDAO created.");
   }
   @Override 
   public int create(GameVO vo){   
     return mybatis.insert("game.create", vo);
   }

   @Override
   public List<GameVO> list() { 
     return mybatis.selectList("game.list");
   }
 
  @Override
  public GameVO read(int gno) {
    return mybatis.selectOne("game.read" , gno);
  }
  @Override
  public int update(GameVO gameVO) {
    return mybatis.update("game.update", gameVO);
  }
  @Override
  public int delete(int gno) {
    return mybatis.delete("game.delete", gno);
  }
  @Override
  public int increaseCnt(int gno) {
    return mybatis.update("game.increaseCnt", gno);
  }
  @Override
  public List<GameVO> list2(HashMap<String, Object> hashMap) {
    return mybatis.selectList("game.list2", hashMap);
  }
  @Override
  public int count(HashMap hashMap) {
    return mybatis.selectOne("game.count", hashMap);
  }

}
