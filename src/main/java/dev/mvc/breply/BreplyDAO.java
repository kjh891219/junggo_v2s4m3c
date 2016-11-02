package dev.mvc.breply;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.breply.BreplyDAO")
public class BreplyDAO implements BreplyDAOInter{

   @Autowired
   private SqlSessionTemplate mybatis;
   
   @Override
   public int create(BreplyVO vo) {
      return mybatis.insert("breply.create", vo);
   }

   @Override
   public List<BreplyVO> list(int bno) {
      return mybatis.selectList("breply.list", bno);
   }

   @Override
   public int updateAnsnum(BreplyVO vo) {
      return mybatis.update("breply.updateAnsnum", vo);
   }

   @Override
   public BreplyVO read(int rno) {
      return mybatis.selectOne("breply.read", rno);
   }

   @Override
   public int reply(BreplyVO vo) {
      return mybatis.insert("breply.reply", vo);
   }

   @Override
   public int delete(int rno) {
      return mybatis.delete("breply.delete", rno);
   }

}
