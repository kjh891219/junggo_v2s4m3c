package dev.mvc.sports;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.tmember.MemberVO;

@Repository("dev.mvc.sports.SportsDAO")
public class SportsDAO implements SportsDAOInter {

@Autowired
private SqlSessionTemplate mybatis; //mybatis ¿¬°á
 
public SportsDAO(){
  System.out.println("--> SportsDAO created.");  
}

@Override
  public int create(SportsVO vo) {
    return mybatis.insert("sports.create", vo);
  }

@Override
public List<SportsVO> list() {
  return mybatis.selectList("sports.list");
}

@Override
public SportsVO read(int sno) {
  return mybatis.selectOne("sports.read", sno);
}

@Override
public int update(SportsVO vo) {
  return mybatis.update("sports.update", vo);
}

@Override
public int delete(int sno) {
  return mybatis.delete("sports.delete", sno);
}

@Override
public int increaseCnt(int sno) {
  return mybatis.update("sports.increaseCnt", sno);
}

@Override
public List<SportsVO> list2(HashMap hashmap) {
  return mybatis.selectList("sports.list2", hashmap);
}

@Override
public int count(HashMap hashmap) {
  return mybatis.selectOne("sports.count", hashmap);
}

@Override
public List<SportsVO> list3(HashMap hashmap) {
  return mybatis.selectList("sports.list3", hashmap);
}

@Override
public MemberVO test(String userid) {
  return mybatis.selectOne("sports.test", userid);
}

@Override
public List<SportsVO> newlist() {
  return mybatis.selectList("sports.newlist");
}
    
}
