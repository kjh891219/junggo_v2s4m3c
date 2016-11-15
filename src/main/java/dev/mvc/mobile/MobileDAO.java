package dev.mvc.mobile;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.game.GameVO;
import dev.mvc.tmember.MemberVO;

@Repository("dev.mvc.mobile.MobileDAO")
public class MobileDAO implements MobileDAOInter {

  @Autowired
  private SqlSessionTemplate mybatis;
  
  @Override
  public int create(MobileVO mobileVO) {
    return mybatis.insert("mobile.create", mobileVO);
  }

  @Override
  public List<MobileVO> list() {
    return mybatis.selectList("mobile.list");
  }

  @Override
  public MobileVO read(int mno) {
    return mybatis.selectOne("mobile.read", mno);
  }

  @Override
  public int update(MobileVO mobileVO) {
    return mybatis.update("mobile.update", mobileVO);
    
  }

  @Override
  public int delete(int mno) {
    return mybatis.delete("mobile.delete", mno);
  }

  @Override
  public int increaseCnt(int mno) {
    return mybatis.update("mobile.increaseCnt", mno);
  }

  @Override
  public int count(HashMap hashMap) {
    return mybatis.selectOne ("mobile.count", hashMap);
  }

  @Override
  public List<MobileVO> list2(HashMap<String, Object> hashMap) {
    return mybatis.selectList("mobile.list2", hashMap);
  }

  @Override
  public MemberVO test(String userid) {
    return mybatis.selectOne("mobile.test", userid);
  }

  @Override
  public List<MobileVO> newlist() {
    return mybatis.selectList("mobile.newlist");
  }

}
