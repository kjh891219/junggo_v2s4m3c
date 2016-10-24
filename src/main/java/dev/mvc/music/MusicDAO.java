package dev.mvc.music;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.music.MusicDAO")
public class MusicDAO implements MusicDAOInter {
  @Autowired
  private SqlSessionTemplate mybatis; // MyBATIS 3 ¿¬°á °´Ã¼

  public MusicDAO() {
    System.out.println("--> MusicDAO created.");
  }

  @Override
  public int create(MusicVO vo) {
    return mybatis.insert("music.create", vo);
  }

  @Override
  public MusicVO read(int ctno) {
    return mybatis.selectOne("music.read", ctno);
  }

  @Override
  public int update(MusicVO musicVO) {
    return mybatis.update("music.update", musicVO);
  }

  @Override
  public List<MusicVO> list(HashMap hashMap) {
    return mybatis.selectList("music.list", hashMap);    
  }

  @Override
  public int delete(int ctno) {
    return mybatis.delete("music.delete", ctno);
  }

  @Override
  public int count(HashMap hashMap) {
    return mybatis.selectOne("music.count", hashMap);
  }

  @Override
  public int passwordCheck(HashMap hashMap) {
    return mybatis.selectOne("music.passwordCheck", hashMap);
  }

  @Override
  public int setCnt(int ctno) {
    return mybatis.update("music.setCnt", ctno);
  }

}
