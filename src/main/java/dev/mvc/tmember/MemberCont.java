package dev.mvc.tmember;
 
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
import web.tool.AES256Util;
import web.tool.Paging;
import web.tool.SearchDTO;
import web.tool.Tool;

@Controller
public class MemberCont {
  @Autowired
  @Qualifier("dev.mvc.tmember.MemberDAO")
  private MemberDAOInter memberDAO;
  
  public MemberCont(){
    System.out.println("--> MemberCont created.");
  }
  
/*  @RequestMapping(value = "/home.do", method = RequestMethod.GET)
  public ModelAndView home() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/index"); // member에 create.do가 들어올 경우 이동 -> /webapp/member/create.jsp
    return mav;
  }*/
  
  @RequestMapping(value = "/member/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/create"); // member에 create.do가 들어올 경우 이동 -> /webapp/member/create.jsp
 
    return mav;
  }
 
  @RequestMapping(value = "/member/create.do", method = RequestMethod.POST)
  public ModelAndView create(MemberVO memberVO ) throws Exception {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message"); // /webapp/member/message.jsp
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
  
    
// 권한, 인증 추가 ---------------------------------------------------------    
    memberVO.setAuth(Tool.key()); // ABC012345678901234
    memberVO.setDropout("N");
     
    if (memberDAO.admin_search("M") == 0){ // 컬럼명, 마스터 계정
      msgs.add("최초 등록 계정임으로 Master 계정입니다.<br><br>");
      memberVO.setAct("M");  // 최고 관리자 지정
      memberVO.setConfirm("Y"); //  최고 관리자임으로 메일 확인 처리
    }else{
      memberVO.setAct("H");  // 관리자가 승인이 필요함, H: hold.
      memberVO.setConfirm("N"); // 메일 확인 안됨, 가입자가 메일 확인 안함. 
    }
    
// 이메일 ---------------------------------------------------------     
    String subject = "Blog 관리자 메일 인증입니다.";  // 제목
    String content = "메일 인증<br><br>";  // 내용
    content += "아래의 링크를 클릭하면 가입이 완료됩니다.<br><br>";
    // http://172.16.12.1:9090/admin_v1jq/admin1/confirm.jsp?email=testcell2010@gmail.com&auth=ABC1234567890

    content += "http://localhost:9090/tmember/member/confirm.do?email=" + memberVO.getEmail() + "&auth=" + memberVO.getAuth();

    // mw-002.cafe24.com, Cafe24
    String host = "mw-002.cafe24.com";    // smtp mail server(서버관리자)     
    String from = "chanmi_blog@gmail.com";   // 보내는 주소, 블로그 관리자 주소
    String to = memberVO.getEmail();    // 받는 사람

    Properties props = new Properties();  // SMTP 프로토콜 사용, port 25
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.auth","true");

    Authenticator authenticator = new MyAuthentication();
    Session sess = Session.getInstance(props, authenticator);   // 계정 인증 검사

    try {
      Message msg = new MimeMessage(sess);   // 메일 내용 객체 생성
      msg.setFrom(new InternetAddress(from));   // 보내는 사람 설정
            
      // 한명에게만 보냄
      InternetAddress[] address = {new InternetAddress(to)}; // 받는 사람 설정
      
      msg.setRecipients(Message.RecipientType.TO, address); // 수령인 주소 설정
            
      msg.setSubject(subject);                  // 제목 설정 
      msg.setSentDate(new Date());          // 보낸 날짜 설정
            
      // msg.setText(msgText); // 메일 내용으로 문자만 보낼 경우

      // 보내는 내용으로 HTML 형식으로 보낼 경우
      msg.setContent(content, "text/html;charset=utf-8");
            
      Transport.send(msg);  // 메일 전송

      msgs.add("<u>인증 메일이 발송되어습니다.</u><br><br>");
      msgs.add("<u>메일을 열고 링크를 클릭해주세요.</u><br>");
      
    } catch (MessagingException mex) {
      System.out.println(mex.getMessage());
      // out.println(mex.getMessage()+"<br><br>");
      // out.println(to + "님에게 메일 발송을 실패 했습니다.");
    }
// ---------------------------------------------------------     
// 비밀번호 암호화
// ---------------------------------------------------------     
    System.out.println("비밀번호 암호화 전: "+memberVO.getPwd());
    
    AES256Util aes256 = new AES256Util();
    String encrypt_pwd = aes256.aesEncode(memberVO.getPwd());
    memberVO.setPwd(new String(encrypt_pwd));
    System.out.println("비밀번호 암호화 후"+memberVO.getPwd());
// ---------------------------------------------------------     
    if (memberDAO.create(memberVO) == 1) {
      msgs.add("회원가입이 처리 되었습니다.");
      msgs.add("가입해주셔서 감사합니다.");
      msgs.add("이메일 인증 시 로그인이 가능합니다.");
      links.add("<button type='button' onclick=\"location.href='../index.jsp'\">홈페이지</button>");
    } else {
      msgs.add("회원 가입에 실패했습니다.");
      msgs.add("죄송하지만 다시한번 시도해주세요.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='../index.jsp'\">홈페이지</button>");
    }
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
 
  /**
   * 중복 아이디를 검사합니다.
   * @param id
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/member/checkId.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String checkId(String id) {
 
    JSONObject obj = new JSONObject();
 
    int cnt = memberDAO.checkId(id);
    obj.put("cnt", cnt);
 
    return obj.toJSONString();
  }
  
  /**
   * 중복 닉네임를 검사합니다.
   * @param nickname
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/member/checkNickname.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String checkNickname(String nickname) {
    System.out.print(nickname);
    JSONObject obj = new JSONObject();
    int cnt = memberDAO.checkNickname(nickname);
    obj.put("cnt", cnt);
 
    return obj.toJSONString();
  }
  
  /**
   * 중복 이메일을 검사합니다.
   * @param email
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/member/checkEmail.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String checkEmail(String email) {
    JSONObject obj = new JSONObject();
    
    int cnt = memberDAO.checkEmail(email);
    obj.put("cnt", cnt);
    
    return obj.toJSONString();
  }
  
  /**
   * 수정 시 중복 이메일을 검사합니다.
   * @param email
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/member/checkNickname_update.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String checkNickname_update(String userid, String nickname) {
    JSONObject obj = new JSONObject();
    
    HashMap<String, String> hashMap = new HashMap<String, String>();
    hashMap.put("userid", userid);
    hashMap.put("nickname", nickname);
   
    int cnt = memberDAO.checkNickname_update(hashMap);
    obj.put("cnt", cnt);
    
    return obj.toJSONString();
  }
  
  /**
   * 수정 시 중복 이메일을 검사합니다.
   * @param email
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/member/checkEmail_update.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String checkEmail_update(String userid, String email) {
    JSONObject obj = new JSONObject();
    
    HashMap<String, String> hashMap = new HashMap<String, String>();
    hashMap.put("userid", userid);
    hashMap.put("email", email);
    
    int cnt = memberDAO.checkEmail_update(hashMap);
    obj.put("cnt", cnt);
    
    return obj.toJSONString();
  }

  /**
   * 회원 정보 읽기
   * @param mno
   * @return
   */
  @RequestMapping(value = "/member/read.do", method = RequestMethod.GET)
  public ModelAndView read(int mno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/read");
    mav.addObject("memberVO", memberDAO.read(mno));
 
    return mav;
  }
  
  /**
   * 전체 목록을 출력합니다.
   * 
   * @return
   */
  @RequestMapping(value = "/member/list.do", method = RequestMethod.GET)
  public ModelAndView list(SearchDTO searchDTO, HttpServletRequest request, String dropout) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/list");
    
    System.out.println(dropout);
    
    // HashMap hashMap = new HashMap();
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("col", searchDTO.getCol());
    hashMap.put("word", searchDTO.getWord());
    hashMap.put("dropout", dropout);
    
    
    
    int recordPerPage = 10; // 페이지당 출력할 레코드 갯수
    // 페이지에서 출력할 시작 레코드 번호 계산, nowPage는 1부터 시작
    int beginOfPage = (searchDTO.getNowPage() - 1) * 10; 
    // 1 page: 0
    // 2 page: 10
    // 3 page: 20
    int startNum = beginOfPage + 1; // 시작 rownum, 1
    int endNum = beginOfPage + recordPerPage; // 종료 rownum, 10
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    System.out.println(searchDTO.getCol());
    System.out.println(searchDTO.getWord());
    
    int totalRecord = 0;
    List<MemberVO> list = memberDAO.list2(hashMap);
    Iterator<MemberVO> iter = list.iterator();
    
    while (iter.hasNext() == true) { // 다음 요소 검사
      MemberVO vo = iter.next(); // 요소 추출
      vo.setUserid(vo.getUserid());
      vo.setMno(vo.getMno());
      vo.setPwd(vo.getPwd());
      vo.setName(vo.getName());
      vo.setNickname(vo.getNickname());
      vo.setEmail(vo.getEmail());
      vo.setAddress1(vo.getAddress1());
      vo.setAddress2(vo.getAddress2());
      vo.setAuth(vo.getAuth());
      vo.setConfirm(vo.getConfirm());
      vo.setAct(vo.getAct());
      vo.setDropout(vo.getDropout());
      vo.setMdate(vo.getMdate().substring(0, 10));
      
    }
    
    mav.addObject("list", list);
    mav.addObject("root", request.getContextPath());
    
    
    totalRecord = memberDAO.count(hashMap);
    mav.addObject("totalRecord", memberDAO.count(hashMap));
    
    String paging = new Paging().paging5(
        totalRecord, 
        searchDTO.getNowPage(), 
        recordPerPage, 
        searchDTO.getCol(), 
        searchDTO.getWord());
    mav.addObject("paging", paging);
    mav.addObject("dropout", dropout);
    
   // System.out.println(paging);
    
    
    return mav;
  }
  
 
  /**
   * 이메일 인증 후 처리
   * confirm(인증 여부), act(권한) Y로 변경
   * @param memberVO
   * @return
   */
  @RequestMapping(value = "/member/confirm.do", method = RequestMethod.GET)
  public ModelAndView confirm(MemberVO memberVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message");
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
 
    if (memberDAO.confirm(memberVO) == 1) {
      msgs.add("이메일 인증이 완료되었습니다.");
      links.add("<button type='button' onclick=\"location.href='../login.do'\">로그인</button>");
      links.add("<button type='button' onclick=\"location.href='../index.jsp'\">홈페이지</button>");
    } else {
      msgs.add("이메일 인증이 실패했습니다.");
      msgs.add("죄송하지만 다시한번 시도해주세요.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='../index.jsp'\">홈페이지</button>");
    }
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
  
  @RequestMapping(value = "/login.do", 
                              method = RequestMethod.GET)
  public ModelAndView login(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
  
    mav.setViewName("/menu/login"); // /webapp/member/login_ck_form.jsp

    return mav;
  }
  
  @RequestMapping(value = "/login.do", 
                              method = RequestMethod.POST)
  public ModelAndView login(MemberVO memberVO, 
                                         HttpSession session, 
                                         HttpServletRequest request,
                                         HttpServletResponse response) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    // System.out.println("--> login() POST called.");
  
    ModelAndView mav = new ModelAndView();
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
 // ---------------------------------------------------------     
 // 비밀번호 암호화
 // ---------------------------------------------------------     
/*    System.out.println("비밀번호 암호화 전: "+memberVO.getPwd());
    
    AES256Util aes256 = new AES256Util();
    String encrypt_pwd = aes256.aesEncode(memberVO.getPwd());
    memberVO.setPwd(new String(encrypt_pwd));
    System.out.println("비밀번호 암호화 후"+memberVO.getPwd());*/
 // --------------------------------------------------------- 
    
 // ---------------------------------------------------------     
 // 비밀번호 복호화
      
 //  decrypt: byte[] array 받아서 String으로 반환
 // ---------------------------------------------------------   
    //String pwd = memberDAO.read_userid(memberVO.getUserid()).getPwd(); // DB 비밀번호
     String pwd = memberVO.getPwd();
     System.out.println("비밀번호 암호화 전: "+ pwd); // String 상태

     AES256Util aes256 = new AES256Util();
     String endcode_pwd = aes256.aesEncode(pwd);
     
     System.out.println("암호화 테스트 : " + endcode_pwd);
     
     memberVO.setPwd(endcode_pwd);
     
     System.out.println("비밀번호 암호화 후: "+memberVO.getPwd());
 // ---------------------------------------------------------    
     
    if (memberDAO.login(memberVO) == 1) {
      String act = memberDAO.read_userid(memberVO.getUserid()).getAct();
      int mno = memberDAO.read_userid(memberVO.getUserid()).getMno();
      if ("MY".indexOf(act) >= 0){ // 로그인 권한 있음. M: Master, Y:일반 회원
        session.setAttribute("userid", memberVO.getUserid());
        session.setAttribute("pwd", memberVO.getPwd());
        session.setAttribute("act", act);
        session.setAttribute("mno", mno);
        session.setAttribute("email", memberDAO.read_userid(memberVO.getUserid()).getEmail());
        session.setAttribute("nickname", memberDAO.read_userid(memberVO.getUserid()).getNickname());
        session.setAttribute("tel", memberDAO.read_userid(memberVO.getUserid()).getTel());
        // ------------------------------------------------------------------
        // userid 저장 관련 쿠키 저장
        // ------------------------------------------------------------------
        String userid_save = Tool.checkNull(memberVO.getUserid_save());
        if (userid_save.equals("Y")){ // id 저장 할 경우
          Cookie ck_userid = new Cookie("ck_userid", memberVO.getUserid()); // id 저장
          ck_userid.setMaxAge(600); // 초 단위, 10분
          response.addCookie(ck_userid);
        }else{ // id를 저장하지 않을 경우
          Cookie ck_userid = new Cookie("ck_userid", ""); 
          ck_userid.setMaxAge(0); // 쿠키 삭제
          response.addCookie(ck_userid);
        }
        // id 저장 여부를 결정하는 쿠기 기록, Y or "" 저장
        Cookie ck_userid_save = new Cookie("ck_userid_save", memberVO.getUserid_save());
        ck_userid_save.setMaxAge(600); // 초
        response.addCookie(ck_userid_save);
        // ------------------------------------------------------------------
      
        // ------------------------------------------------------------------
        // pwd 저장 관련 쿠키 저장
        // ------------------------------------------------------------------
        String pwd_save = Tool.checkNull(memberVO.getPwd_save());
        if (pwd_save.equals("Y")){ 
          Cookie ck_pwd = new Cookie("ck_pwd", memberVO.getPwd()); 
          ck_pwd.setMaxAge(600); // 초
          response.addCookie(ck_pwd);
      
        }else{ // passwd를 저장하지 않을 경우
          Cookie ck_pwd = new Cookie("ck_pwd", "");
          ck_pwd.setMaxAge(0); // 초
          response.addCookie(ck_pwd);
      
        }
        // pwd 저장 여부를 결정하는 쿠키 기록, Y or "" 저장
        Cookie ck_pwd_save = new Cookie("ck_pwd_save", memberVO.getPwd_save());
        ck_pwd_save.setMaxAge(600); // 초
        response.addCookie(ck_pwd_save);
        // ------------------------------------------------------------------
      
        String url_address = Tool.checkNull(memberVO.getUrl_address());
        if (url_address.length() > 0){
          mav.setViewName("redirect:" + memberVO.getUrl_address());
        }else{
          System.out.println("--> index.jsp 페이지로 이동합니다.");
          String url = session.getAttribute("url").toString();
          mav.setViewName("redirect:/" + url);
        }
    
        } else {
          mav.setViewName("/member/message");
          msgs.add("현재 계정이 사용 불가합니다.");
          msgs.add("관리자에게 문의해주세요.");
          links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
          links.add("<button type='button' onclick=\"location.href='../index.jsp'\">홈페이지</button>");
        }
      
      } else {
        mav.setViewName("/member/message");
        msgs.add("로그인에 실패했습니다.");
        msgs.add("죄송하지만 다시한번 시도해주세요.");
        links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
        links.add("<button type='button' onclick=\"location.href='../index.jsp'\">홈페이지</button>");
      }
      
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
      
      return mav;
  }
  
  @RequestMapping(value = "/member/logout.do", 
                              method = RequestMethod.GET)
  public void logout(HttpSession session, HttpServletResponse response) throws IOException {
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    String userid2 = session.getAttribute("userid").toString();
    
    session.invalidate(); // 모든 session 변수 삭제

    PrintWriter writer = response.getWriter();
    writer.println
    ("<script>"
        + "alert('" + userid2 + "님 이용해 주셔서 감사합니다');"
        + "location.href='../index.jsp';"
        + "</script>"
        );
  } 
  
  /**
   * 탈퇴 신청
   * @param memberVO
   * @return
   */
  @RequestMapping(value = "/member/dropout.do", method = RequestMethod.POST)
  public ModelAndView dropout(MemberVO memberVO, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message");
    
    System.out.println(memberVO.getUserid());
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    if (memberDAO.dropout(memberVO) == 1) {
      session.invalidate();
      msgs.add("탈퇴가 정상적으로 처리되었습니다.");
      msgs.add("이용해 주셔서 감사합니다.");
      links.add("<button type='button' onclick=\"location.href='../index.jsp'\">홈페이지</button>");
      links.add("<button type='button' onclick=\"location.href='./create.do'\">회원가입</button>");
    } else {
      msgs.add("죄송하지만 다시한번 시도해주세요.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='../index.jsp'\">홈페이지</button>");
    }
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
  
  /**
   * 본인 인증
   * 패스워드 확인 폼 출력
   * @param mno 회원 번호
   * @return
   */
  @RequestMapping(value = "/member/checkPwd.do", method = RequestMethod.GET)
  public ModelAndView checkPwd(int mno, String flag) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/checkPwd"); // /webapp/member/checkPwd.jsp
    
    mav.addObject("flag", flag);
    mav.addObject("mno", mno);
    return mav;
  }
  
  @RequestMapping(value = "/member/checkPwd.do", method = RequestMethod.POST)
  public ModelAndView checkPwd(MemberVO memberVO, String flag, HttpSession session) throws Exception {
    ModelAndView mav = new ModelAndView();
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    String pwd = memberVO.getPwd();

    AES256Util aes256 = new AES256Util();
    String endcode_pwd = aes256.aesEncode(pwd);
    
    memberVO.setPwd(endcode_pwd);
    
    mav.addObject("flag", flag);
    
    // 현재 패스워드 일치 여부 검사
    if (memberDAO.checkPwd(memberVO.getUserid(), memberVO.getPwd()) == 1){
      mav.addObject("memberVO", memberDAO.read(memberVO.getMno()));
        if(flag.equals("1")){
          mav.setViewName("/member/read");
        } else {
          mav.setViewName("/member/dropout");
        }
      } else { }
    return mav;
  }
  
  /**
   * 회원 삭제 - 지금 메시지가 관계가 맺어져 있어서 메시지가 존재하면 삭제가 안 됨
   * @param mno 
   * @return
   */
  @RequestMapping(value = "/member/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int mno) {
    ModelAndView mav = new ModelAndView();
    memberDAO.delete(mno);
    //mav.setViewName("/member/list.do"); 
    mav.setViewName("redirect:/member/list.do");
    return mav;
  }
  
 /**
  * 회원 정보 수정
  * 이메일 변경 시 재인증 필요
  * @param memberVO
  * @param updateFlag
  * @return
  */
  @RequestMapping(value = "/member/update.do", method = RequestMethod.POST)
  public ModelAndView update(MemberVO memberVO, String updateFlag, HttpSession session) throws Exception {
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if(updateFlag.equals("1")) { // 이메일이 변경 되었다면 실행
      // 로그인 불가능 상태
      memberVO.setConfirm("N");
      memberVO.setAct("H");
      
        
  // 이메일 ---------------------------------------------------------     
      String subject = "Blog 관리자 메일 인증입니다.";  // 제목
      String content = "메일 인증<br><br>";  // 내용
      content += "아래의 링크를 클릭하면 가입이 완료됩니다.<br><br>";
      // http://172.16.12.1:9090/admin_v1jq/admin1/confirm.jsp?email=testcell2010@gmail.com&auth=ABC1234567890
  
      content += "http://localhost:9090/tmember/member/confirm.do?email=" + memberVO.getEmail() + "&auth=" + memberVO.getAuth();
  
      // mw-002.cafe24.com, Cafe24
      String host = "mw-002.cafe24.com";    // smtp mail server(서버관리자)     
      String from = "chanmi_blog@gmail.com";   // 보내는 주소, 블로그 관리자 주소
      String to = memberVO.getEmail();    // 받는 사람
  
      Properties props = new Properties();  // SMTP 프로토콜 사용, port 25
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.auth","true");
  
      Authenticator authenticator = new MyAuthentication();
      Session sess = Session.getInstance(props, authenticator);   // 계정 인증 검사
  
      try {
        Message msg = new MimeMessage(sess);   // 메일 내용 객체 생성
        msg.setFrom(new InternetAddress(from));   // 보내는 사람 설정
              
        // 한명에게만 보냄
        InternetAddress[] address = {new InternetAddress(to)}; // 받는 사람 설정
        
        msg.setRecipients(Message.RecipientType.TO, address); // 수령인 주소 설정
              
        msg.setSubject(subject);                  // 제목 설정 
        msg.setSentDate(new Date());          // 보낸 날짜 설정
              
        // msg.setText(msgText); // 메일 내용으로 문자만 보낼 경우
  
        // 보내는 내용으로 HTML 형식으로 보낼 경우
        msg.setContent(content, "text/html;charset=utf-8");
              
        Transport.send(msg);  // 메일 전송
  
        msgs.add("<u>이메일 재인증이 필요합니다.</u><br><br>");
        msgs.add("<u>메일을 열고 링크를 클릭해주세요.</u><br>");
        
      } catch (MessagingException mex) {
        System.out.println(mex.getMessage());
        // out.println(mex.getMessage()+"<br><br>");
        // out.println(to + "님에게 메일 발송을 실패 했습니다.");
      }
      
      } // if문
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message");
 
 // ---------------------------------------------------------     
 // 비밀번호 암호화
 // ---------------------------------------------------------     
     System.out.println("비밀번호 암호화 전: "+memberVO.getPwd());
     
     AES256Util aes256 = new AES256Util();
     String encrypt_pwd = aes256.aesEncode(memberVO.getPwd());
     memberVO.setPwd(new String(encrypt_pwd));
     System.out.println("비밀번호 암호화 후"+memberVO.getPwd());
 // ---------------------------------------------------------     
    
    if (memberDAO.update(memberVO) == 1) {
      msgs.add("회원정보가 수정되었습니다.");
      links.add("<button type='button' onclick=\"location.href='../login.do'\">로그인</button>");
      links.add("<button type='button' onclick=\"location.href='../index.jsp'\">홈페이지</button>");
    } else {
      msgs.add("회원 정보 변경에 실패했습니다.");
      msgs.add("죄송하지만 다시한번 시도해주세요.");
      links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
      links.add("<button type='button' onclick=\"location.href='../index.jsp'\">홈페이지</button>");
    }
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    if(session.getAttribute("act").toString().equals("M")) {
    } else {
      session.invalidate();
    }
    
    return mav;
    
  }
  
  /**
   * 권한 변경 폼
   * @param mno
   * @return
   */
  @RequestMapping(value = "/member/act_update.do", method = RequestMethod.GET)
  public ModelAndView act_update(String mno)   {
    ModelAndView mav = new ModelAndView();
    mav.addObject("mno", mno);
    mav.setViewName("/member/act_form"); 
    return mav;
}
  
  /**
   * 권한 변경
   * @param mno
   * @param act
   * @param response
   * @throws IOException
   */
  @RequestMapping(value = "/member/act_update.do", method = RequestMethod.POST)
  public void act_update(String mno, String act, HttpServletResponse response) throws IOException {
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    HashMap<String, Object> map = new HashMap<String, Object>();
    System.out.println("컨트롤"+mno);
    System.out.println("컨트롤"+act);
    map.put("mno", mno);
    map.put("act", act);
    int sendOK = memberDAO.act_update(map); 
    if (sendOK == 1) {
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>"
          + "opener.location.reload();"
          + "window.close();"
          + "</script>"
          );
  } else {
    PrintWriter writer = response.getWriter();
    writer.println
    ("<script>alert('실패했습니다.');" 
     + "</script>"
        );
  }

}
  
/******************************** 마이페이지 시작 ***********************************/

  @RequestMapping(value = "/member/mylist2.do", method = RequestMethod.GET)
  public ModelAndView mylist2(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/mylist2");
    String userid = session.getAttribute("userid").toString();
    
    List<ArtVO> art_list = memberDAO.art_list(userid);
    Iterator<ArtVO> art_iter = art_list.iterator();
    while (art_iter.hasNext() == true) { // 다음 요소 검사
      ArtVO vo = art_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<CameraVO> camera_list = memberDAO.camera_list(userid);
    Iterator<CameraVO> camera_iter = camera_list.iterator();
    while (camera_iter.hasNext() == true) { // 다음 요소 검사
      CameraVO vo = camera_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<BookVO> book_list = memberDAO.book_list(userid);
    Iterator<BookVO> book_iter = book_list.iterator();
    while (book_iter.hasNext() == true) { // 다음 요소 검사
      BookVO vo = book_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<ComputerVO> computer_list = memberDAO.computer_list(userid);
    Iterator<ComputerVO> computer_iter = computer_list.iterator();
    while (computer_iter.hasNext() == true) { // 다음 요소 검사
      ComputerVO vo = computer_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<ClothVO> cloth_list = memberDAO.cloth_list(userid);
    Iterator<ClothVO> cloth_iter = cloth_list.iterator();
    while (cloth_iter.hasNext() == true) { // 다음 요소 검사
      ClothVO vo = cloth_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<CosmeticVO> cosmetic_list = memberDAO.cosmetic_list(userid);
    Iterator<CosmeticVO> cosmetic_iter = cosmetic_list.iterator();
    while (cosmetic_iter.hasNext() == true) { // 다음 요소 검사
      CosmeticVO vo = cosmetic_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<ProductVO> product_list = memberDAO.product_list(userid);
    Iterator<ProductVO> product_iter = product_list.iterator();
    while (product_iter.hasNext() == true) { // 다음 요소 검사
      ProductVO vo = product_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<GameVO> game_list = memberDAO.game_list(userid);
    Iterator<GameVO> game_iter = game_list.iterator();
    while (game_iter.hasNext() == true) { // 다음 요소 검사
      GameVO vo = game_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<GameDeviceVO> gamedevice_list = memberDAO.gamedevice_list(userid);
    Iterator<GameDeviceVO> gamedevice_iter = gamedevice_list.iterator();
    while (gamedevice_iter.hasNext() == true) { // 다음 요소 검사
      GameDeviceVO vo = gamedevice_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<MobileVO> mobile_list = memberDAO.mobile_list(userid);
    Iterator<MobileVO> mobile_iter = mobile_list.iterator();
    while (mobile_iter.hasNext() == true) { // 다음 요소 검사
      MobileVO vo = mobile_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<LivingVO> living_list = memberDAO.living_list(userid);
    Iterator<LivingVO> living_iter = living_list.iterator();
    while (living_iter.hasNext() == true) { // 다음 요소 검사
      LivingVO vo = living_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<SportsVO> sports_list = memberDAO.sports_list(userid);
    Iterator<SportsVO> sports_iter = sports_list.iterator();
    while (sports_iter.hasNext() == true) { // 다음 요소 검사
      SportsVO vo = sports_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }    
    List<UsedcarVO> usedcar_list = memberDAO.usedcar_list(userid);
    Iterator<UsedcarVO> usedcar_iter = usedcar_list.iterator();
    while (usedcar_iter.hasNext() == true) { // 다음 요소 검사
      UsedcarVO vo = usedcar_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<CarproductVO> carproduct_list = memberDAO.carproduct_list(userid);
    Iterator<CarproductVO> carproduct_iter = carproduct_list.iterator();
    while (carproduct_iter.hasNext() == true) { // 다음 요소 검사
      CarproductVO vo = carproduct_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<MusicVO> music_list = memberDAO.music_list(userid);
    Iterator<MusicVO> music_iter = music_list.iterator();
    while (music_iter.hasNext() == true) { // 다음 요소 검사
      MusicVO vo = music_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    
    
    List<ReviewsVO> reviews_list = memberDAO.reviews_list(userid);
    Iterator<ReviewsVO> reviews_iter = reviews_list.iterator();
    while (music_iter.hasNext() == true) { // 다음 요소 검사
      ReviewsVO vo = reviews_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<CheatVO> cheat_list = memberDAO.cheat_list(userid);
    Iterator<CheatVO> cheat_iter = cheat_list.iterator();
    while (cheat_iter.hasNext() == true) { // 다음 요소 검사
      CheatVO vo = cheat_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
   
   //  LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String, Object>();
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("art_list", art_list);
    hashMap.put("camera_list", camera_list);
    hashMap.put("book_list", book_list);
    hashMap.put("computer_list", computer_list);
    hashMap.put("cloth_list", cloth_list);
    hashMap.put("cosmetic_list", cosmetic_list);
    hashMap.put("product_list", product_list);
    hashMap.put("game_list", game_list);
    hashMap.put("gamedevice_list", gamedevice_list);
    hashMap.put("mobile_list", mobile_list);
    hashMap.put("living_list", living_list);
    hashMap.put("sports_list", sports_list);
    hashMap.put("usedcar_list", usedcar_list);
    hashMap.put("carproduct_list", carproduct_list);
    hashMap.put("music_list", music_list);
    
    
    mav.addObject("hashMap", hashMap);
    
    mav.addObject("reviews_list", reviews_list);
    mav.addObject("cheat_list", cheat_list);
    
    return mav;
  }
  
  @RequestMapping(value = "/member/mylist.do", method = RequestMethod.GET)
  public ModelAndView mylist(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/mylist");
    String userid = session.getAttribute("userid").toString();
    
    List<ArtVO> art_list = memberDAO.art_list(userid);
    Iterator<ArtVO> art_iter = art_list.iterator();
    while (art_iter.hasNext() == true) { // 다음 요소 검사
      ArtVO vo = art_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<CameraVO> camera_list = memberDAO.camera_list(userid);
    Iterator<CameraVO> camera_iter = camera_list.iterator();
    while (camera_iter.hasNext() == true) { // 다음 요소 검사
      CameraVO vo = camera_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<BookVO> book_list = memberDAO.book_list(userid);
    Iterator<BookVO> book_iter = book_list.iterator();
    while (book_iter.hasNext() == true) { // 다음 요소 검사
      BookVO vo = book_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<ComputerVO> computer_list = memberDAO.computer_list(userid);
    Iterator<ComputerVO> computer_iter = computer_list.iterator();
    while (computer_iter.hasNext() == true) { // 다음 요소 검사
      ComputerVO vo = computer_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<ClothVO> cloth_list = memberDAO.cloth_list(userid);
    Iterator<ClothVO> cloth_iter = cloth_list.iterator();
    while (cloth_iter.hasNext() == true) { // 다음 요소 검사
      ClothVO vo = cloth_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<CosmeticVO> cosmetic_list = memberDAO.cosmetic_list(userid);
    Iterator<CosmeticVO> cosmetic_iter = cosmetic_list.iterator();
    while (cosmetic_iter.hasNext() == true) { // 다음 요소 검사
      CosmeticVO vo = cosmetic_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<ProductVO> product_list = memberDAO.product_list(userid);
    Iterator<ProductVO> product_iter = product_list.iterator();
    while (product_iter.hasNext() == true) { // 다음 요소 검사
      ProductVO vo = product_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<GameVO> game_list = memberDAO.game_list(userid);
    Iterator<GameVO> game_iter = game_list.iterator();
    while (game_iter.hasNext() == true) { // 다음 요소 검사
      GameVO vo = game_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<GameDeviceVO> gamedevice_list = memberDAO.gamedevice_list(userid);
    Iterator<GameDeviceVO> gamedevice_iter = gamedevice_list.iterator();
    while (gamedevice_iter.hasNext() == true) { // 다음 요소 검사
      GameDeviceVO vo = gamedevice_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<MobileVO> mobile_list = memberDAO.mobile_list(userid);
    Iterator<MobileVO> mobile_iter = mobile_list.iterator();
    while (mobile_iter.hasNext() == true) { // 다음 요소 검사
      MobileVO vo = mobile_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<LivingVO> living_list = memberDAO.living_list(userid);
    Iterator<LivingVO> living_iter = living_list.iterator();
    while (living_iter.hasNext() == true) { // 다음 요소 검사
      LivingVO vo = living_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<SportsVO> sports_list = memberDAO.sports_list(userid);
    Iterator<SportsVO> sports_iter = sports_list.iterator();
    while (sports_iter.hasNext() == true) { // 다음 요소 검사
      SportsVO vo = sports_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }    
    List<UsedcarVO> usedcar_list = memberDAO.usedcar_list(userid);
    Iterator<UsedcarVO> usedcar_iter = usedcar_list.iterator();
    while (usedcar_iter.hasNext() == true) { // 다음 요소 검사
      UsedcarVO vo = usedcar_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<CarproductVO> carproduct_list = memberDAO.carproduct_list(userid);
    Iterator<CarproductVO> carproduct_iter = carproduct_list.iterator();
    while (carproduct_iter.hasNext() == true) { // 다음 요소 검사
      CarproductVO vo = carproduct_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<MusicVO> music_list = memberDAO.music_list(userid);
    Iterator<MusicVO> music_iter = music_list.iterator();
    while (music_iter.hasNext() == true) { // 다음 요소 검사
      MusicVO vo = music_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    
    
    List<ReviewsVO> reviews_list = memberDAO.reviews_list(userid);
    Iterator<ReviewsVO> reviews_iter = reviews_list.iterator();
    while (music_iter.hasNext() == true) { // 다음 요소 검사
      ReviewsVO vo = reviews_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<CheatVO> cheat_list = memberDAO.cheat_list(userid);
    Iterator<CheatVO> cheat_iter = cheat_list.iterator();
    while (cheat_iter.hasNext() == true) { // 다음 요소 검사
      CheatVO vo = cheat_iter.next(); // 요소 추출
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    
    //  LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String, Object>();
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("art_list", art_list);
    hashMap.put("camera_list", camera_list);
    hashMap.put("book_list", book_list);
    hashMap.put("computer_list", computer_list);
    hashMap.put("cloth_list", cloth_list);
    hashMap.put("cosmetic_list", cosmetic_list);
    hashMap.put("product_list", product_list);
    hashMap.put("game_list", game_list);
    hashMap.put("gamedevice_list", gamedevice_list);
    hashMap.put("mobile_list", mobile_list);
    hashMap.put("living_list", living_list);
    hashMap.put("sports_list", sports_list);
    hashMap.put("usedcar_list", usedcar_list);
    hashMap.put("carproduct_list", carproduct_list);
    hashMap.put("music_list", music_list);
    
    
    mav.addObject("hashMap", hashMap);
    
    mav.addObject("reviews_list", reviews_list);
    mav.addObject("cheat_list", cheat_list);
    
    return mav;
  }
  
  /**
   * 프로필
   * @param nickname
   * @return
   */
  @RequestMapping(value = "/member/profile.do", method = RequestMethod.GET)
  public ModelAndView read(String nickname, String userid) {
    ModelAndView mav = new ModelAndView();
    String nickname2 = nickname.trim();
    mav.setViewName("/member/profile"); ///webapp/member/profile.jsp
    mav.addObject("vo", memberDAO.readprofile(nickname2));
    return mav;
  }
}

/******************************** 마이페이지 종료 ***********************************/

//javamail lib 이 필요합니다.
class MyAuthentication extends Authenticator {
  PasswordAuthentication pa;
  public MyAuthentication(){
   pa = new PasswordAuthentication("test@nulunggi.pe.kr", "test2016");
  }
  
  public PasswordAuthentication getPasswordAuthentication() {
   return pa;
  }
}