package dev.mvc.carproduct;
 
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
  public List<CarproductVO> list() {
    return mybatis.selectList("carproduct.list");
  }

  @Override
  public CarproductVO read(int p_no) {
    return mybatis.selectOne("carproduct.read", p_no);
  }
  
}