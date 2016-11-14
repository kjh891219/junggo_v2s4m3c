package dev.mvc.computer;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.computer.ComputerDAO")
public class ComputerDAO implements ComputerDAOInter {
  @Autowired
  private SqlSessionTemplate mybatis; // MyBATIS 3 ¿¬°á °´Ã¼

  public ComputerDAO() {
    System.out.println("--> ComputerDAO created.");
  }

  @Override
  public int create(ComputerVO vo) {
    return mybatis.insert("computer.create", vo);
  }

  @Override
  public ComputerVO read(int ctno) {
    return mybatis.selectOne("computer.read", ctno);
  }

  @Override
  public int update(ComputerVO computerVO) {
    return mybatis.update("computer.update", computerVO);
  }

  @Override
  public List<ComputerVO> list(HashMap hashMap) {
    return mybatis.selectList("computer.list", hashMap);    
  }

  @Override
  public int delete(int ctno) {
    return mybatis.delete("computer.delete", ctno);
  }

  @Override
  public int count(HashMap hashMap) {
    return mybatis.selectOne("computer.count", hashMap);
  }

  @Override
  public int passwordCheck(HashMap hashMap) {
    return mybatis.selectOne("computer.passwordCheck", hashMap);
  }

  @Override
  public int setCnt(int ctno) {
    return mybatis.update("computer.setCnt", ctno);
  }

  @Override
  public List<ComputerVO> newlist() {
    return mybatis.selectList("computer.newlist");
  }

}
