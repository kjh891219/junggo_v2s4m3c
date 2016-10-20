package dev.mvc.cheat;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.cheat.CheatDAO")
public class CheatDAO implements CheatDAOInter {
  @Autowired
  private SqlSessionTemplate mybatis; // MyBATIS 3 ¿¬°á °´Ã¼

  public CheatDAO() {
    System.out.println("--> CheatDAO created.");
  }

  @Override
  public int create(CheatVO vo) {
    return mybatis.insert("cheat.create", vo);
  }

  @Override
  public CheatVO read(int ctno) {
    return mybatis.selectOne("cheat.read", ctno);
  }

  @Override
  public int update(CheatVO cheatVO) {
    return mybatis.update("cheat.update", cheatVO);
  }

  @Override
  public List<CheatVO> list(HashMap hashMap) {
    return mybatis.selectList("cheat.list", hashMap);    
  }

  @Override
  public int delete(int ctno) {
    return mybatis.delete("cheat.delete", ctno);
  }

  @Override
  public int count(HashMap hashMap) {
    return mybatis.selectOne("cheat.count", hashMap);
  }

  @Override
  public int passwordCheck(HashMap hashMap) {
    return mybatis.selectOne("cheat.passwordCheck", hashMap);
  }

  @Override
  public int setCnt(int ctno) {
    return mybatis.update("cheat.setCnt", ctno);
  }

}
