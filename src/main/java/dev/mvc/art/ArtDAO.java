package dev.mvc.art;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.art.ArtDAO")
public class ArtDAO implements ArtDAOInter {
  @Autowired
  private SqlSessionTemplate mybatis;
  
  public ArtDAO() {
    System.out.println("--> ArtDAO created.");
  }

  @Override
  public int create(ArtVO vo) {
    return mybatis.insert("art.create", vo);
  }

  @Override
  public List<ArtVO> list3(HashMap hashmap) {
    return mybatis.selectList("art.list3", hashmap);
  }
  
  @Override
  public int count(HashMap hashmap) {
    return mybatis.selectOne("art.count", hashmap);
  }

  @Override
  public ArtVO read(int ano) {
    return mybatis.selectOne("art.read", ano);
  }

  @Override
  public int increaseCnt(int ano) {
    return mybatis.update("art.increaseCnt", ano);
  }

  @Override
  public int delete(int ano) {
    return mybatis.delete("art.delete", ano);
  }

  @Override
  public int update(ArtVO artVO) {
    return mybatis.update("art.update", artVO);
  }
}
