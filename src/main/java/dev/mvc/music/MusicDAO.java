package dev.mvc.music;
 
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.tmember.MemberVO;
import dev.mvc.usedcar.UsedcarVO;

@Repository("dev.mvc.music.MusicDAO")
public class MusicDAO implements MusicDAOInter{
  
  @Autowired
  private SqlSessionTemplate mybatis; 
  
  public MusicDAO(){ 
     System.out.println("--> MusicDAO created.");
  }

  @Override
  public int create(MusicVO vo) {
    /*      
    <mapper namespace = "code">
    <insert id="create" parameterType="CodeVO">
    */      
    return mybatis.insert("music.create", vo);
  }

  @Override
  public MusicVO read(int ctno) {
    return mybatis.selectOne("music.read", ctno);
  }

  
  @Override
  public int delete(int ctno) { 
    return mybatis.delete("music.delete", ctno);
  }

  @Override
  public int update(MusicVO musicVO) {
    return mybatis.update("music.update", musicVO);
  }
  
  @Override
  public int increaseCnt(int ctno) {
    return mybatis.update("music.increaseCnt", ctno);
  }
  
  @Override
  public int count(HashMap hashmap) {
    return mybatis.selectOne("music.count", hashmap);
  }

  @Override
  public List<MusicVO> list(HashMap hashmap) {
    return mybatis.selectList("music.list", hashmap);
  }
  
  @Override
  public List<MusicVO> list2(HashMap hashmap) {
    return mybatis.selectList("music.list2", hashmap);
  }

  @Override
  public MemberVO test(String userid) { 
    return mybatis.selectOne("music.test", userid);
  }
  
}