package dev.mvc.qna;
 
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

 
@Repository("dev.mvc.qna.QnADAO")
public class QnADAO implements QnADAOInter{
   @Autowired
   private SqlSessionTemplate mybatis; //MyBatis 3 


   @Override
   public List<QnAVO> list(HashMap<String, Object> hashmap) {
      return mybatis.selectList("qna.list", hashmap);
   }
   
   @Override
   public int create(QnAVO vo) {
      return mybatis.insert("qna.create", vo);
   }

/*
   @Override
   public List<QnAVO> idlist(String userid) {
      return mybatis.selectList("qna.idlist");
   }
*/
   @Override
   public QnAVO read(int qnano) {
      return mybatis.selectOne("qna.read", qnano);
   }

   @Override
   public int delete(int qnano) {
      return mybatis.delete("qna.delete", qnano);
   }

   @Override
   public int update(QnAVO vo) {
      return mybatis.update("qna.update", vo);
   }

	@Override
	public int increaseCnt(int qnano) {
		return mybatis.update("qna.increaseCnt", qnano);
	}

   @Override
   public int count(HashMap hashmap) {
      return mybatis.selectOne("qna.count", hashmap);
   }

   @Override
   public int updateAnsnum(QnAVO vo) {
      return mybatis.update("qna.updateAnsnum", vo);
   }

   @Override
   public int reply(QnAVO vo) {
      return mybatis.insert("qna.reply", vo);
   }

 
}