package dev.mvc.cheat;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.cheat.CReplyDAO")
public class CReplyDAO implements CReplyDAOInter {
  @Autowired
  private SqlSessionTemplate mybatis; // MyBATIS 3 ���� ��ü
  
  public CReplyDAO() {
    System.out.println("--> CReplyDAO created.");
  }
  
  /**
   * ��� ���
   */
  @Override
  public int reply(CReplyVO cReplyVO) {
   return mybatis.insert("creply.reply", cReplyVO);
  }

  /**
   * ��� ��� �� ���� ����� ansnum �����ϱ�
   */
  @Override
  public int updateAnsnum(CReplyVO cReplyVO) {
    return mybatis.update("creply.updateAnsnum", cReplyVO);
  }
  
  /**
   * ��� �Ѱ� ��ȸ
   */
  @Override
  public CReplyVO read(int rno) {
    return mybatis.selectOne("creply.read", rno);
  }
  
  /**
   * ������ǰ �Խñ� ��ȸ�� �� ��� ��ȸ�ؼ� �����ֱ�
   */
  @Override
  public List<CReplyVO> listReply(int ctno) {
    return mybatis.selectList("creply.listReply", ctno);
  }
  
  /**
   * ������ǰ ��� ��� �� ����� ��� grpno max�� ��������
   */
  @Override
  public int getMaxgrpno(int ctno) {
    return mybatis.selectOne("creply.getMaxgrpno", ctno);
  }
  
  /**
   * ������ǰ��� ��� ����
   */
  @Override
  public int delete_reply(int rno) {
    return mybatis.delete("creply.delete_reply", rno);
  }

}
