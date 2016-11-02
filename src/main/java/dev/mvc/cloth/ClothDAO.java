package dev.mvc.cloth;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.tmember.MemberVO;

@Repository("dev.mvc.cloth.ClothDAO")
public class ClothDAO implements ClothDAOInter {

@Autowired
private SqlSessionTemplate mybatis; //mybatis ¿¬°á
 
public ClothDAO(){
  System.out.println("--> ClothDAO created.");  
}

@Override
  public int create(ClothVO vo) {
    return mybatis.insert("cloth.create", vo);
  }

@Override
public List<ClothVO> list() {
  return mybatis.selectList("cloth.list");
}

@Override
public ClothVO read(int clothno) {
  return mybatis.selectOne("cloth.read", clothno);
}

@Override
public int update(ClothVO vo) {
  return mybatis.update("cloth.update", vo);
}

@Override
public int delete(int clothno) {
  return mybatis.delete("cloth.delete", clothno);
}

@Override
public int increaseCnt(int clothno) {
  return mybatis.update("cloth.increaseCnt", clothno);
}

@Override
public List<ClothVO> list2(HashMap hashmap) {
  return mybatis.selectList("cloth.list2", hashmap);
}

@Override
public int count(HashMap hashmap) {
  return mybatis.selectOne("cloth.count", hashmap);
}

@Override
public List<ClothVO> list3(HashMap hashmap) {
  return mybatis.selectList("cloth.list3", hashmap);
}

@Override
public MemberVO test(String userid) {
  return mybatis.selectOne("cloth.test", userid);
}
    
}
