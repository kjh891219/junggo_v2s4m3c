package dev.mvc.living;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.living.LivingDAO")
public class LivingDAO implements LivingDAOInter {

  @Autowired
  private SqlSessionTemplate mybatis;
  @Override
  public int create(LivingVO livingVO) {
    return mybatis.insert("living.create", livingVO);
  }

  @Override
  public int update(LivingVO livingVO) {
    return mybatis.update("living.update", livingVO);
  }

  @Override
  public LivingVO read(int lno) {
    return mybatis.selectOne("living.read" , lno);
  }

  @Override
  public int delete(int lno) {
    return mybatis.delete("living.delete", lno);
  }

  @Override
  public int increaseCnt(int lno) {
    return mybatis.update("living.increaseCnt", lno);
  }

  @Override
  public int count(HashMap hashMap) {
    return mybatis.selectOne("living.count", hashMap);
  }

  @Override
  public List<LivingVO> list2(HashMap<String, Object> hashMap) {
    return mybatis.selectList("living.list2", hashMap);
  }

  @Override
  public List<LivingVO> newlist() {
    return mybatis.selectList("living.newlist");
  }

}
