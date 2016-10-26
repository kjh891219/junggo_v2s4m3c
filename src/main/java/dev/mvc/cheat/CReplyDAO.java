package dev.mvc.cheat;

import java.util.HashMap;
import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.cheat.CReplyDAO")
public class CReplyDAO implements CReplyDAOInter {
  @Autowired
  private SqlSessionTemplate mybatis; // MyBATIS 3 ¿¬°á °´Ã¼
  
  public CReplyDAO() {
    System.out.println("--> CReplyDAO created.");
  }
  @Override
  public int reply(CReplyVO cReplyVO) {
   return mybatis.insert("creply.reply", cReplyVO);
  }

  @Override
  public int updateAnsnum(CReplyVO cReplyVO) {
    return mybatis.update("creply.updateAnsnum", cReplyVO);
  }
  @Override
  public CReplyVO read(int rno) {
    return mybatis.selectOne("creply.read", rno);
  }
  @Override
  public List<CReplyVO> listReply(int ctno) {
    return mybatis.selectList("creply.listReply", ctno);
  }
  
  public List<CheatVO> list(HashMap hashMap) {
    return mybatis.selectList("cheat.list", hashMap);    
  }
  @Override
  public int getMaxgrpno(int ctno) {
    return mybatis.selectOne("creply.getMaxgrpno", ctno);
  }

}
