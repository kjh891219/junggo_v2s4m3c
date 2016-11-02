package dev.mvc.book;
 

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

 
@Repository("dev.mvc.book.BookDAO")
public class BookDAO implements BookDAOInter{
   @Autowired
   private SqlSessionTemplate mybatis; //MyBatis 3 

   @Override 
   public List<BookVO> list(HashMap hashmap) {
	   return mybatis.selectList("book.list", hashmap);
   }

   @Override
   public int create(BookVO vo) {
      return mybatis.insert("book.create",vo);
   } 


   @Override
   public BookVO list(int categoryno) {
      return null;
   }

@Override
public int update(BookVO bookVO){
	return mybatis.update("book.update", bookVO);
}

@Override
public int delete(int bno) {
	return mybatis.delete("book.delete", bno);
}

@Override
public BookVO read(int bno) {
	return mybatis.selectOne("book.read", bno);
}

@Override
public int count(HashMap hashmap) {
	return mybatis.selectOne("book.count", hashmap);
}

@Override
public int increaseCnt(int bno) {
	return mybatis.update("book.increaseCnt", bno);
}

}



 
