package dev.mvc.cosmetic;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.tmember.MemberVO;

@Repository("dev.mvc.cosmetic.CosmeticDAO")
public class CosmeticDAO implements CosmeticDAOInter {
  
  @Autowired
  private SqlSessionTemplate mybatis; // MyBATIS 3 ¿¬°á °´Ã¼
  
  public CosmeticDAO(){
    System.out.println("--> CosmeticDAO created.");  
  }
  
  @Override
  public int create(CosmeticVO vo) {
    return mybatis.insert("cosmetic.create", vo);
  }

  @Override
  public List<CosmeticVO> list() {
    return mybatis.selectList("cosmetic.list");
  }

  @Override
  public CosmeticVO read(int cno) {
    return mybatis.selectOne("cosmetic.read", cno);
  }

  @Override
  public int update(CosmeticVO vo) {
    return mybatis.update("cosmetic.update", vo);
  }

  @Override
  public int delete(int cno) {
    return mybatis.delete("cosmetic.delete", cno);
  }

  @Override
  public int increaseCnt(int cno) {
    return mybatis.update("cosmetic.increaseCnt", cno);
  }


  @Override
  public int count(HashMap hashmap) {
    return mybatis.selectOne("cosmetic.count", hashmap);
  }

  @Override
  public List<CosmeticVO> list2(HashMap hashmap) {
    return mybatis.selectList("cosmetic.list2", hashmap);
  }

  @Override
  public List<CosmeticVO> list3(HashMap hashmap) {
    return mybatis.selectList("cosmetic.list3", hashmap);
  }

  @Override
  public MemberVO test(String userid) {
    return mybatis.selectOne("cosmetic.test", userid);
  }

 
}
