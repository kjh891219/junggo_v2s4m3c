package dev.mvc.favorite;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.favorite.FavoriteDAO")
public class FavoriteDAO implements FavoriteDAOInter{
  
  @Autowired
  private SqlSessionTemplate mybatis; 
  
  public FavoriteDAO(){
    System.out.println("--> FavoriteDAO created.");
  }

  @Override
  public int count(HashMap hashmap) {
    return mybatis.selectOne("favorite.count", hashmap);
  }

  @Override
  public List<FavoriteVO> list(HashMap hashmap) {
    return mybatis.selectList("favorite.list", hashmap);
  }

  @Override
  public int create(FavoriteVO vo) {
    return mybatis.insert("favorite.create", vo);
  }
  
  @Override
  public int visible(HashMap map) {
    System.out.println("DAO");
    System.out.println(map.get("msg_no_arr"));
    System.out.println(map.get("userid"));
    return mybatis.update("favorite.visible", map);
  }

  @Override
  public List<FavoriteVO> list_userid(String userid) {
    return mybatis.selectList("favorite.list_userid", userid);
  }
  
 
  
}
