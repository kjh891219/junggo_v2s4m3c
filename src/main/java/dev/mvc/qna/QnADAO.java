package dev.mvc.qna;
 
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

 
@Repository("dev.mvc.qna.QnADAO")
public class QnADAO implements QnADAOInter{
   @Autowired
   private SqlSessionTemplate mybatis; //MyBatis 3 

   @Override
   public int create(QnAVO vo) {
      return mybatis.insert("qna.create", vo);
   }

   @Override
   public List<QnAVO> list() {
      return mybatis.selectList("qna.list");
   }

   @Override
   public QnAVO read(int qnano) {
      return mybatis.selectOne("qna.read", qnano);
   }

/*   @Override
   public int update(QnAVO vo) {
      return mybatis.update("qna.update", vo);
   } */
   
 
}