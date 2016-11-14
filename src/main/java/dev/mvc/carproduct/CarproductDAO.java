package dev.mvc.carproduct;
 
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.tmember.MemberVO;
import dev.mvc.usedcar.UsedcarVO;

@Repository("dev.mvc.carproduct.CarproductDAO")
public class CarproductDAO implements CarproductDAOInter{
  
  @Autowired
  private SqlSessionTemplate mybatis; 
  
  public CarproductDAO(){ 
     System.out.println("--> CarproductDAO created.");
  }

  @Override
  public int create(CarproductVO vo) {
    /*      
    <mapper namespace = "code">
    <insert id="create" parameterType="CodeVO">
    */      
    return mybatis.insert("carproduct.create", vo);
  }

  @Override
  public CarproductVO read(int p_no) {
    return mybatis.selectOne("carproduct.read", p_no);
  }

  
  @Override
  public int delete(int p_no) { 
    return mybatis.delete("carproduct.delete", p_no);
  }

  @Override
  public int update(CarproductVO carproductVO) {
    return mybatis.update("carproduct.update", carproductVO);
  }
  
  @Override
  public int increaseCnt(int p_no) {
    return mybatis.update("carproduct.increaseCnt", p_no);
  }
  
  @Override
  public int count(HashMap hashmap) {
    return mybatis.selectOne("carproduct.count", hashmap);
  }

  @Override
  public List<CarproductVO> list(HashMap hashmap) {
    return mybatis.selectList("carproduct.list", hashmap);
  }
  
  @Override
  public List<CarproductVO> list2(HashMap hashmap) {
    return mybatis.selectList("carproduct.list2", hashmap);
  }

  @Override
  public MemberVO test(String userid) { 
    return mybatis.selectOne("carproduct.test", userid);
  }

  @Override
  public List<CarproductVO> newlist() {
    return mybatis.selectList("carproduct.newlist");
  }
  
}