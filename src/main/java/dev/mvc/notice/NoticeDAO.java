package dev.mvc.notice;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.tmember.MemberVO;

@Repository("dev.mvc.notice.NoticeDAO")
public class NoticeDAO implements NoticeDAOInter {
  @Autowired
  private SqlSessionTemplate mybatis;

  @Override
  public int create(NoticeVO vo) {
    return mybatis.insert("notice.create", vo);
  }

  @Override
  public List<NoticeVO> list2(HashMap<String, Object> hashMap) {
    return mybatis.selectList("notice.list2", hashMap);
  }

  @Override
  public int update(NoticeVO noticeVO) {
    return mybatis.update("notice.update", noticeVO);
  }

  @Override
  public NoticeVO read(int noticeno) {
    return mybatis.selectOne("notice.read", noticeno);
  }

  @Override
  public int delete(int noticeno) {
    return mybatis.delete("notice.delete", noticeno);
  }

  @Override
  public int increaseCnt(int noticeno) {
    return mybatis.update("notice.increaseCnt", noticeno);
  }

  @Override
  public int count(HashMap hashMap) {
    return mybatis.selectOne("notice.count", hashMap);
  }

  @Override
  public List<NoticeVO> list3(HashMap<String, Object> hashMap) {
    return mybatis.selectList("notice.list3", hashMap);
  }

  @Override
  public int updateAnsnum(NoticeVO vo) {
    return mybatis.update("notice.updateAnsnum", vo);
  }

  @Override
  public int reply(NoticeVO vo) {
    return mybatis.insert("notice.reply", vo);
  }

  @Override
  public MemberVO test(String userid) {
    return mybatis.selectOne("notice.test", userid);
  }
}
