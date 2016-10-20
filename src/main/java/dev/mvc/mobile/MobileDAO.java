package dev.mvc.mobile;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}
