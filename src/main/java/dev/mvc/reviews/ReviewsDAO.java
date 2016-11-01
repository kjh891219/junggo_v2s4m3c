package dev.mvc.reviews;
 
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.carproduct.CarproductVO;
import dev.mvc.tmember.MemberVO;
import dev.mvc.usedcar.UsedcarVO;

@Repository("dev.mvc.reviews.ReviewsDAO")
public class ReviewsDAO implements ReviewsDAOInter{
  
  @Autowired
  private SqlSessionTemplate mybatis; 
  
  public ReviewsDAO(){ 
     System.out.println("--> ReviewsDAO created.");
  }

  @Override
  public int create(ReviewsVO vo) {
    /*      
    <mapper namespace = "code">
    <insert id="create" parameterType="CodeVO">
    */      
    return mybatis.insert("reviews.create", vo);
  }

  @Override
  public ReviewsVO read(int r_no) {
    return mybatis.selectOne("reviews.read", r_no);
  }

  @Override
  public MemberVO test(String userid) { 
    return mybatis.selectOne("reviews.test", userid);
  }

  @Override
  public List<ReviewsVO> list(HashMap hashmap) {
    return mybatis.selectList("reviews.list", hashmap);
  }

  @Override
  public int count(HashMap hashmap) {
    return mybatis.selectOne("reviews.count", hashmap);
  }

  @Override
  public int increaseCnt(int r_no) {
    return mybatis.update("reviews.increaseCnt", r_no);
  }

  @Override
  public int update(ReviewsVO reviewsVO) {
    return mybatis.update("reviews.update", reviewsVO);
  }
  
  @Override
  public int delete(int r_no) {
    return mybatis.delete("reviews.delete", r_no);
  }

}