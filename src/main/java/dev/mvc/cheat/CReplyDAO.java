package dev.mvc.cheat;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.cheat.CReplyDAO")
public class CReplyDAO implements CReplyDAOInter {
  @Autowired
  private SqlSessionTemplate mybatis; // MyBATIS 3 연결 객체
  
  public CReplyDAO() {
    System.out.println("--> CReplyDAO created.");
  }
  
  /**
   * 댓글 등록
   */
  @Override
  public int reply(CReplyVO cReplyVO) {
   return mybatis.insert("creply.reply", cReplyVO);
  }

  /**
   * 댓글 등록 시 상위 댓글의 ansnum 갱신하기
   */
  @Override
  public int updateAnsnum(CReplyVO cReplyVO) {
    return mybatis.update("creply.updateAnsnum", cReplyVO);
  }
  
  /**
   * 댓글 한건 조회
   */
  @Override
  public CReplyVO read(int rno) {
    return mybatis.selectOne("creply.read", rno);
  }
  
  /**
   * 허위상품 게시글 조회한 후 댓글 조회해서 보여주기
   */
  @Override
  public List<CReplyVO> listReply(int ctno) {
    return mybatis.selectList("creply.listReply", ctno);
  }
  
  /**
   * 허위상품 답글 등록 시 댓글일 경우 grpno max값 가져오기
   */
  @Override
  public int getMaxgrpno(int ctno) {
    return mybatis.selectOne("creply.getMaxgrpno", ctno);
  }
  
  /**
   * 허위상품등록 답글 삭제
   */
  @Override
  public int delete_reply(int rno) {
    return mybatis.delete("creply.delete_reply", rno);
  }

}
