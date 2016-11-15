package dev.mvc.product;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.tmember.MemberVO;

@Repository("dev.mvc.product.ProductDAO")

public class ProductDAO implements ProductDAOInter {
@Autowired
private SqlSessionTemplate mybatis; //mybatis ¿¬°á
  
public ProductDAO(){
  System.out.println("--> ProductDAO created");
}
  @Override
  public int create(ProductVO productVo) {
    return mybatis.insert("product.create", productVo);
  }

  @Override
  public List<ProductVO> list() {
    return mybatis.selectList("product.list");
  }
  @Override
  public ProductVO read(int pno) {
    return mybatis.selectOne("product.read", pno);
  }
  @Override
  public int update(ProductVO vo) {
    return mybatis.update("product.update", vo);
  }
  @Override
  public int delete(int pno) {
    return mybatis.delete("product.delete", pno);
  }
  @Override
  public int increaseCnt(int pno) {
    return mybatis.update("product.increaseCnt", pno);
  }
  @Override
  public List<ProductVO> list2(HashMap hashmap) {
    return mybatis.selectList("product.list2", hashmap );
  }
  @Override
  public int count(HashMap hashmap) {
    return mybatis.selectOne("product.count", hashmap);
  }
  @Override
  public List<ProductVO> list3(HashMap hashmap) {
    return mybatis.selectList("product.list3", hashmap);
  }
  @Override
  public MemberVO test(String userid) {
    return mybatis.selectOne("product.test", userid);
  }
  @Override
  public List<ProductVO> newlist() {
    return mybatis.selectList("product.newlist");
  }

  
  
}
