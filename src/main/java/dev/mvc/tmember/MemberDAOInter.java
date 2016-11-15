package dev.mvc.tmember;

import java.util.HashMap;
import java.util.List;

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
import dev.mvc.qna.QnAVO;
import dev.mvc.reviews.ReviewsVO;
import dev.mvc.sports.SportsVO;
import dev.mvc.usedcar.UsedcarVO;



public interface MemberDAOInter {
  /**
   * 중복 아이디를 검사합니다.
   * <select id='checkId' resultType='int' parameterType='String'>
   * @param id 아이디
   * @return 0: 중복 아님, 1: 중복
   */
  public int checkId(String id);
  
  /**
   * 중복 아이디를 검사합니다.
   * <select id='checkNickname' resultType='int' parameterType='String'>
   * @param checkNickname 닉네임
   * @return 0: 중복 아님, 1: 중복
   */
  public int checkNickname(String nickname);
  
  /**
   * 중복 아이디를 검사합니다.
   * <select id='checkEmail' resultType='int' parameterType='String'>
   * @param email 이메일
   * @return 0: 중복 아님, 1: 중복
   */
  public int checkEmail(String email);
 
  /**
   * 레코드를 등록합니다.
   * <insert id="create" parameterType="MemberVO">
   * @param vo 등록할 데이터 객체
   * @return 등록된 레코드 수
   */
  public int create(MemberVO vo);
   
  /**
   * 회원 전체 목록
   * <select id="list" resultType="MemberVO">
   * @return 회원 목록
   */
  public List<MemberVO> list();
  
  /**
   * 회원 전체 목록 - 페이징!!!!!!!
   * <select id="list" resultType="MemberVO">
   * @return 회원 목록
   */
  public List<MemberVO> list2(HashMap map);
  
  /**
   * 검색된 레코드 수
   * <select id="count" resultType="int" parameterType="HashMap" >
   * @param hashmap 검색 조건
   * @return
   */
  public int count(HashMap hashmap);
  
  /**
   * 회원 정보 조회
   * <select id="read" resultType="MemberVO" parameterType="int">
   * @param mno
   * @return
   */
  public MemberVO read(int mno); 
  
  /**
   * 회원 정보 조회
   * <select id="read" resultType="MemberVO" parameterType="String">
   * @param userid
   * @return
   */
  public MemberVO read_userid(String userid); 
  
  /**
   * 회원을 수정합니다.
   * <update id="update" parameterType="MemberVO"> 
   * @param memberVO
   * @return 수정된 레코드 갯수
   */
  public int update(MemberVO memberVO);
  
  /** 최초 가입시 관리자 조회
   *  <select id="admin_search" resultType="int" parameterType="String">
   * @param act
   * @return
   */
  public int admin_search(String act);
  
  /** 이메일 인증 처리
   * <update id="confirm" parameterType="MemberVO">
   * @param memberVO
   * @return
   */
  public int confirm(MemberVO memberVO);
  
  /** 로그인
   * <select id="login" resultType="int" parameterType="MemberVO">
   * @param memberVO
   * @return
   */
  public int login(MemberVO memberVO);
  
  /**
   * 탈퇴 신청
   * <update id="dropout" parameterType="MemberVO">
   * @param memberVO
   * @return 
   */
  public int dropout(MemberVO memberVO);
  
  /**
   * 본인 인증 - 비밀번호 확인
    <select id="checkPwd" resultType="int" parameterType="Map">
   * @param userid
   * @param pwd
   * @return
   */
  public int checkPwd(String userid, String pwd);

  /** 수정 시 닉네임 중복 환인
   * <select id='checkNickname_update' resultType='int' parameterType='map'>
   * @param userid 자신을 제외
   * @param pwd
   * @return
   */
  public int checkNickname_update(HashMap map);
  
  /** 수정 시 이메일 중복 확인
   * <select id='checkEmail_update' resultType='int' parameterType='Map'>
   * @param userid
   * @param pwd
   * @return
   */
  public int checkEmail_update(HashMap map);
  
  /**
   * 회원 삭제 - 관리자
   * @param mno
   * @return
   */
  public int delete(int mno); 
  
  /**
   * 회원 권한 변경
   * <update id="act_update" parameterType="Map">
   * @param map
   * @return
   */
  public int act_update(HashMap map);
  
  /**
   * 마이 페이지
   * @param userid
   * @return
   */
  public List<ArtVO> art_list(String userid);

  public List<CameraVO> camera_list(String userid);
  public List<BookVO> book_list(String userid);
  public List<ComputerVO> computer_list(String userid);
 
  
  public List<UsedcarVO> usedcar_list(String userid);
  public List<CarproductVO> carproduct_list(String userid);
  public List<MusicVO> music_list(String userid);
  
  
  public List<ClothVO> cloth_list(String userid);
  public List<CosmeticVO> cosmetic_list(String userid);
  public List<ProductVO> product_list(String userid);
  
  public List<GameVO> game_list(String userid);
  public List<GameDeviceVO> gamedevice_list(String userid);
  public List<MobileVO> mobile_list(String userid);
  
  public List<LivingVO> living_list(String userid);
  public List<SportsVO> sports_list(String userid);
 
  public List<ReviewsVO> reviews_list(String userid);
  public List<CheatVO> cheat_list(String userid);

/*  public int qna_count(HashMap hashmap);
  public List<QnAVO> qna_list(HashMap hashmap);*/
  
  
  
  
  
  
  
  
  
  
  
}