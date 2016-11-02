package dev.mvc.product_reply;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.product_reply.Product_replyDAO")
public class Product_replyDAO implements Product_replyDAOInter{

 @Autowired
 private SqlSessionTemplate mybatis;

@Override
public int create(Product_replyVO vo) {
  return mybatis.insert("product_reply.create", vo);
}

@Override
public List<Product_replyVO> product_replyList(int pno) {
  return mybatis.selectList("product_reply.product_replyList", pno);
}

@Override
public int updateAnsnum(Product_replyVO vo) {
  return mybatis.update("product_reply.updateAnsnum", vo);
}

@Override
public Product_replyVO read(int rno) {
  return mybatis.selectOne("product_reply.read", rno);
}

@Override
public int reply(Product_replyVO vo) {
  return mybatis.insert("product_reply.reply", vo);
}

@Override
public int delete(int rno) {
  return mybatis.delete("product_reply.delete", rno);
}
 
 
}
