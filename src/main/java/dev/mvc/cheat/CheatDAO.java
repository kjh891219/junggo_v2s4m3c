package dev.mvc.cheat;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.tmember.MemberVO;

@Repository("dev.mvc.cheat.CheatDAO")
public class CheatDAO implements CheatDAOInter {

@Autowired
private SqlSessionTemplate mybatis; //mybatis ¿¬°á

public CheatDAO(){
  System.out.println("--> CheatDAO created.");
}

  @Override
  public int create(CheatVO vo) {
    return mybatis.insert("cheat.create", vo);
  }

  @Override
  public List<CheatVO> list() {
    return mybatis.selectList("cheat.list");
  }

  @Override
  public CheatVO read(int ctno) {
    return mybatis.selectOne("cheat.read", ctno);
  }

  @Override
  public int update(CheatVO vo) {
    return mybatis.update("cheat.update", vo);
  }

  @Override
  public int delete(int ctno) {
    return mybatis.delete("cheat.delete", ctno);
  }

  @Override
  public int increaseCnt(int ctno) {
    return mybatis.update("cheat.increaseCnt", ctno);
  }

  @Override
  public int count(HashMap hashmap) {
    return mybatis.selectOne("cheat.count", hashmap);
  }

  @Override
  public List<CheatVO> list3(HashMap hashmap) {
    return mybatis.selectList("cheat.list3", hashmap);
  }

  @Override
  public MemberVO test(String userid) {
    return mybatis.selectOne("cheat.test", userid);
  }

}
