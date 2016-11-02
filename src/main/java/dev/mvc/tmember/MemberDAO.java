package dev.mvc.tmember;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.art.ArtVO;
import dev.mvc.camera.CameraVO;

 
@Repository("dev.mvc.tmember.MemberDAO")
public  class MemberDAO implements MemberDAOInter{
   

  @Autowired
  private SqlSessionTemplate mybatis; //MyBatis 3 ��?��? �Ƣ�?��
  
  public MemberDAO(){
     System.out.println("--> MemberDAO created.");
  }
  
  @Override
  public int checkId(String id) {
/*
     <Mapper namespace = "member">
     <select id='checkId' resultType='int' parameterType='String'>
*/      
     return mybatis.selectOne("member.checkId", id); 
  }
  
  @Override
  public int checkNickname(String nickname) {
    /*
     <Mapper namespace = "member">
     <select id='checkNickname' resultType='int' parameterType='String'>
     */      
    return mybatis.selectOne("member.checkNickname", nickname); 
  }
  
  @Override
  public int checkEmail(String email) {
    /*
     <Mapper namespace = "member">
     <select id='checkEmail' resultType='int' parameterType='String'>
     */      
    return mybatis.selectOne("member.checkEmail", email); 
  }

  @Override
  public int create(MemberVO vo) {
/*      
     <Mapper namespace = "member">
     <insert id="create" parameterType="MemberVO">
*/      
     return mybatis.insert("member.create", vo);
  }

  @Override
  public List<MemberVO> list() {
    return mybatis.selectList("member.list");
  }
  
  @Override
  public List<MemberVO> list2(HashMap map) {
    System.out.println(map.get("dropout"));
    return mybatis.selectList("member.list2", map);
  }

  @Override
  public MemberVO read(int mno) {
    return mybatis.selectOne("member.read", mno);
  }
  
  @Override
  public MemberVO read_userid(String userid) {
    return mybatis.selectOne("member.read_userid", userid);
  }
  
  @Override
  public int update(MemberVO memberVO) {
    return mybatis.update("member.update", memberVO);
  }

  @Override
  public int admin_search(String act) {
    return mybatis.selectOne("member.admin_search", act);
  }

  @Override
  public int confirm(MemberVO memberVO) {
    return mybatis.update("member.confirm", memberVO);
  }

  @Override
  public int login(MemberVO memberVO) {
    return mybatis.selectOne("member.login", memberVO);
  }

  @Override
  public int dropout(MemberVO memberVO) {
    return mybatis.update("member.dropout", memberVO);
  }

  @Override
  public int checkPwd(String userid, String pwd) {
    Map map = new HashMap();
    map.put("userid", userid);
    map.put("pwd", pwd);
    return mybatis.selectOne("member.checkPwd", map);
  }

  @Override
  public int checkNickname_update(HashMap map) {
    return mybatis.selectOne("member.checkNickname_update", map);
  }

  @Override
  public int checkEmail_update(HashMap map) {
    return mybatis.selectOne("member.checkEmail_update", map);
  }

  @Override
  public int delete(int mno) {
    return mybatis.delete("member.delete", mno);
  }

  @Override
  public int count(HashMap hashmap) {
    return mybatis.selectOne("member.count", hashmap);
  }

  @Override
  public int act_update(HashMap map) {
    System.out.println("�ٿ�");
    System.out.println(map.get("mno"));
    System.out.println(map.get("act"));
    return mybatis.update("member.act_update", map);
  }

  @Override
  public List<ArtVO> art_list(String userid) {
    return mybatis.selectList("member.art_list", userid);
  }

  @Override
  public List<CameraVO> camera_list(String userid) {
    return mybatis.selectList("member.camera_list", userid);
  }
   
  
}