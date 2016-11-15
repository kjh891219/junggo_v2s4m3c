package dev.mvc.tmember;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.art.ArtVO;
import dev.mvc.book.BookVO;
import dev.mvc.camera.CameraVO;
import dev.mvc.carproduct.CarproductVO;
import dev.mvc.cheat.CheatVO;
import dev.mvc.cloth.ClothVO;
import dev.mvc.computer.ComputerVO;
import dev.mvc.cosmetic.CosmeticVO;
import dev.mvc.game.GameVO;
import dev.mvc.gamedevice.GameDeviceVO;
import dev.mvc.living.LivingVO;
import dev.mvc.mobile.MobileVO;
import dev.mvc.music.MusicVO;
import dev.mvc.product.ProductVO;
import dev.mvc.reviews.ReviewsVO;
import dev.mvc.sports.SportsVO;
import dev.mvc.usedcar.UsedcarVO;

 
@Repository("dev.mvc.tmember.MemberDAO")
public  class MemberDAO implements MemberDAOInter{
   

  @Autowired
  private SqlSessionTemplate mybatis; //MyBatis 3 ¢¯?¡Æ? ¡Æ¢¥?¨ù
  
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
    System.out.println("´Ù¿À");
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
  
  @Override
  public List<BookVO> book_list(String userid) {
    return mybatis.selectList("member.book_list", userid);
  }
  
  @Override
  public List<ComputerVO> computer_list(String userid) {
    return mybatis.selectList("member.computer_list", userid);
  }

   
  @Override
  public List<UsedcarVO> usedcar_list(String userid) {
    return mybatis.selectList("member.usedcar_list", userid);
  }
  @Override
  public List<CarproductVO> carproduct_list(String userid) {
    return mybatis.selectList("member.carproduct_list", userid);
  }
  @Override
  public List<MusicVO> music_list(String userid) {
    return mybatis.selectList("member.music_list", userid);
  }
  @Override
  public List<ReviewsVO> reviews_list(String userid) {
    return mybatis.selectList("member.reviews_list", userid);
  }
  
  @Override
  public List<ClothVO> cloth_list(String userid) {
    return mybatis.selectList("member.cloth_list", userid);
  }
  @Override
  public List<CosmeticVO> cosmetic_list(String userid) {
    return mybatis.selectList("member.cosmetic_list", userid);
  }
  @Override
  public List<ProductVO> product_list(String userid) {
    return mybatis.selectList("member.product_list", userid);
  }
  @Override
  public List<GameVO> game_list(String userid) {
    return mybatis.selectList("member.game_list", userid);
  }
  @Override
  public List<GameDeviceVO> gamedevice_list(String userid) {
    return mybatis.selectList("member.gamedevice_list", userid);
  }
  @Override
  public List<MobileVO> mobile_list(String userid) {
    return mybatis.selectList("member.mobile_list", userid);
  }
  @Override
  public List<LivingVO> living_list(String userid) {
    return mybatis.selectList("member.living_list", userid);
  }
  @Override
  public List<SportsVO> sports_list(String userid) {
    return mybatis.selectList("member.sports_list", userid);
  }
  @Override
  public List<CheatVO> cheat_list(String userid) {
    return mybatis.selectList("member.cheat_list", userid);
  }
   
   
   
   
  
}