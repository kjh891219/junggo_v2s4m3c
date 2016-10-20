package dev.mvc.usedcar;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

@Repository("dev.mvc.usedcar.UsedcarDAO")
public class UsedcarDAO implements UsedcarDAOInter{
  
  @Autowired
  private SqlSessionTemplate mybatis; 
  
  public UsedcarDAO(){ 
     System.out.println("--> UsedcarDAO created.");
  }

  @Override
  public int create(UsedcarVO vo) {
    /*      
    <mapper namespace = "code">
    <insert id="create" parameterType="CodeVO">
    */      
    return mybatis.insert("usedcar.create", vo);
  }
  
/*  @Override
  public List<UsedcarVO> list2() {
    return mybatis.selectList("usedcar.list2");
  } */
  
  @Override
  public List<UsedcarVO> list(HashMap hashmap) {
    return mybatis.selectList("usedcar.list", hashmap);
  }
  
/*  @Override
  public List<UsedcarVO> list3(HashMap hashmap) {
    return mybatis.selectList("usedcar.list3", hashmap);
  }*/

  @Override
  public int update(UsedcarVO usedcarVO) {
    return mybatis.update("usedcar.update", usedcarVO);
  }
  
  @Override
  public int delete(int u_no) { 
    return mybatis.delete("usedcar.delete", u_no);
  }

  @Override
  public UsedcarVO read(int u_no) {
    return mybatis.selectOne("usedcar.read", u_no);
  }

  @Override
  public int increaseCnt(int u_no) {
    return mybatis.update("usedcar.increaseCnt", u_no);
  }
  
  @Override
  public int count(HashMap hashmap) {
    return mybatis.selectOne("usedcar.count", hashmap);
  }

  @Override
  public List<UsedcarVO> list() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<UsedcarVO> list2(HashMap hashmap) {
    // TODO Auto-generated method stub
    return null;
  }


}