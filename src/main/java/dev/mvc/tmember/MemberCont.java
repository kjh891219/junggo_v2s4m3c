package dev.mvc.tmember;
 
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.art.ArtVO;
import dev.mvc.camera.CameraVO;
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
  
  @RequestMapping(value = "/home.do", method = RequestMethod.GET)
  public ModelAndView home() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/index"); // member�� create.do�� ���� ��� �̵� -> /webapp/member/create.jsp
 
    return mav;
  }
  
  @RequestMapping(value = "/member/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/create"); // member�� create.do�� ���� ��� �̵� -> /webapp/member/create.jsp
 
    return mav;
  }
 
  @RequestMapping(value = "/member/create.do", method = RequestMethod.POST)
  public ModelAndView create(MemberVO memberVO ) {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message"); // /webapp/member/message.jsp
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
  
    
// ����, ���� �߰� ---------------------------------------------------------    
    memberVO.setAuth(Tool.key()); // ABC012345678901234
    memberVO.setDropout("N");
     
    if (memberDAO.admin_search("M") == 0){ // �÷���, ������ ����
      msgs.add("���� ��� ���������� Master �����Դϴ�.<br><br>");
      memberVO.setAct("M");  // �ְ� ������ ����
      memberVO.setConfirm("Y"); //  �ְ� ������������ ���� Ȯ�� ó��
    }else{
      memberVO.setAct("H");  // �����ڰ� ������ �ʿ���, H: hold.
      memberVO.setConfirm("N"); // ���� Ȯ�� �ȵ�, �����ڰ� ���� Ȯ�� ����. 
    }
    
// �̸��� ---------------------------------------------------------     
    String subject = "Blog ������ ���� �����Դϴ�.";  // ����
    String content = "���� ����<br><br>";  // ����
    content += "�Ʒ��� ��ũ�� Ŭ���ϸ� ������ �Ϸ�˴ϴ�.<br><br>";
    // http://172.16.12.1:9090/admin_v1jq/admin1/confirm.jsp?email=testcell2010@gmail.com&auth=ABC1234567890

    content += "http://localhost:9090/tmember/member/confirm.do?email=" + memberVO.getEmail() + "&auth=" + memberVO.getAuth();

    // mw-002.cafe24.com, Cafe24
    String host = "mw-002.cafe24.com";    // smtp mail server(����������)     
    String from = "chanmi_blog@gmail.com";   // ������ �ּ�, ��α� ������ �ּ�
    String to = memberVO.getEmail();    // �޴� ���

    Properties props = new Properties();  // SMTP �������� ���, port 25
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.auth","true");

    Authenticator authenticator = new MyAuthentication();
    Session sess = Session.getInstance(props, authenticator);   // ���� ���� �˻�

    try {
      Message msg = new MimeMessage(sess);   // ���� ���� ��ü ����
      msg.setFrom(new InternetAddress(from));   // ������ ��� ����
            
      // �Ѹ��Ը� ����
      InternetAddress[] address = {new InternetAddress(to)}; // �޴� ��� ����
      
      msg.setRecipients(Message.RecipientType.TO, address); // ������ �ּ� ����
            
      msg.setSubject(subject);                  // ���� ���� 
      msg.setSentDate(new Date());          // ���� ��¥ ����
            
      // msg.setText(msgText); // ���� �������� ���ڸ� ���� ���

      // ������ �������� HTML �������� ���� ���
      msg.setContent(content, "text/html;charset=utf-8");
            
      Transport.send(msg);  // ���� ����

      msgs.add("<u>���� ������ �߼۵Ǿ���ϴ�.</u><br><br>");
      msgs.add("<u>������ ���� ��ũ�� Ŭ�����ּ���.</u><br>");
      
    } catch (MessagingException mex) {
      System.out.println(mex.getMessage());
      // out.println(mex.getMessage()+"<br><br>");
      // out.println(to + "�Կ��� ���� �߼��� ���� �߽��ϴ�.");
    }
// ---------------------------------------------------------     
    
    if (memberDAO.create(memberVO) == 1) {
      msgs.add("ȸ�������� ó�� �Ǿ����ϴ�.");
      msgs.add("�������ּż� �����մϴ�.");
      msgs.add("�̸��� ���� �� �α����� �����մϴ�.");
      links.add("<button type='button' onclick=\"location.href='./login.do'\">�α���</button>");
      links.add("<button type='button' onclick=\"location.href='../home.do'\">Ȩ������</button>");
    } else {
      msgs.add("ȸ�� ���Կ� �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='../home.do'\">Ȩ������</button>");
    }
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
 
  /**
   * �ߺ� ���̵� �˻��մϴ�.
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
   * �ߺ� �г��Ӹ� �˻��մϴ�.
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
   * �ߺ� �̸����� �˻��մϴ�.
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
   * ���� �� �ߺ� �̸����� �˻��մϴ�.
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
   * ���� �� �ߺ� �̸����� �˻��մϴ�.
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
   * ��ü ����� ����մϴ�.
   * 
   * @return
   */
/*  @RequestMapping(value = "/member/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/list"); //  /webapp/member/list.jsp
    mav.addObject("list", memberDAO.list());
 
    return mav;
  }*/
  
  @RequestMapping(value = "/member/read.do", method = RequestMethod.GET)
  public ModelAndView read(int mno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/read");
    mav.addObject("memberVO", memberDAO.read(mno));
 
    return mav;
  }
  
  /**
   * ��ü ����� ����մϴ�.
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
    
    
    
    int recordPerPage = 10; // �������� ����� ���ڵ� ����
    // ���������� ����� ���� ���ڵ� ��ȣ ���, nowPage�� 1���� ����
    int beginOfPage = (searchDTO.getNowPage() - 1) * 10; 
    // 1 page: 0
    // 2 page: 10
    // 3 page: 20
    int startNum = beginOfPage + 1; // ���� rownum, 1
    int endNum = beginOfPage + recordPerPage; // ���� rownum, 10
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    
    
    
    System.out.println(searchDTO.getCol());
    System.out.println(searchDTO.getWord());
    
    
    int totalRecord = 0;
    List<MemberVO> list = memberDAO.list2(hashMap);
    Iterator<MemberVO> iter = list.iterator();
    
    while (iter.hasNext() == true) { // ���� ��� �˻�
      MemberVO vo = iter.next(); // ��� ����
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
    
    System.out.println(paging);
    
    
    return mav;
  }
  
 
  /**
   * �̸��� ���� �� ó��
   * confirm(���� ����), act(����) Y�� ����
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
      msgs.add("�̸��� ������ �Ϸ�Ǿ����ϴ�.");
      links.add("<button type='button' onclick=\"location.href='./login.do'\">�α���</button>");
      links.add("<button type='button' onclick=\"location.href='../home.do'\">Ȩ������</button>");
    } else {
      msgs.add("�̸��� ������ �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='../home.do'\">Ȩ������</button>");
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
                                         HttpServletResponse response) {
    // System.out.println("--> login() POST called.");
  
    ModelAndView mav = new ModelAndView();
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
  
    if (memberDAO.login(memberVO) == 1) {
      String act = memberDAO.read_userid(memberVO.getUserid()).getAct();
      int mno = memberDAO.read_userid(memberVO.getUserid()).getMno();
      if ("MY".indexOf(act) >= 0){ // �α��� ���� ����. M: Master, Y:�Ϲ� ȸ��
        session.setAttribute("userid", memberVO.getUserid());
        session.setAttribute("pwd", memberVO.getPwd());
        session.setAttribute("act", act);
        session.setAttribute("mno", mno);
        session.setAttribute("email", memberDAO.read_userid(memberVO.getUserid()).getEmail());
        session.setAttribute("nickname", memberDAO.read_userid(memberVO.getUserid()).getNickname());
        session.setAttribute("tel", memberDAO.read_userid(memberVO.getUserid()).getTel());
        // ------------------------------------------------------------------
        // userid ���� ���� ��Ű ����
        // ------------------------------------------------------------------
        String userid_save = Tool.checkNull(memberVO.getUserid_save());
        if (userid_save.equals("Y")){ // id ���� �� ���
          Cookie ck_userid = new Cookie("ck_userid", memberVO.getUserid()); // id ����
          ck_userid.setMaxAge(600); // �� ����, 10��
          response.addCookie(ck_userid);
        }else{ // id�� �������� ���� ���
          Cookie ck_userid = new Cookie("ck_userid", ""); 
          ck_userid.setMaxAge(0); // ��Ű ����
          response.addCookie(ck_userid);
        }
        // id ���� ���θ� �����ϴ� ��� ���, Y or "" ����
        Cookie ck_userid_save = new Cookie("ck_userid_save", memberVO.getUserid_save());
        ck_userid_save.setMaxAge(600); // ��
        response.addCookie(ck_userid_save);
        // ------------------------------------------------------------------
      
        // ------------------------------------------------------------------
        // pwd ���� ���� ��Ű ����
        // ------------------------------------------------------------------
        String pwd_save = Tool.checkNull(memberVO.getPwd_save());
        if (pwd_save.equals("Y")){ 
          Cookie ck_pwd = new Cookie("ck_pwd", memberVO.getPwd()); 
          ck_pwd.setMaxAge(600); // ��
          response.addCookie(ck_pwd);
      
        }else{ // passwd�� �������� ���� ���
          Cookie ck_pwd = new Cookie("ck_pwd", "");
          ck_pwd.setMaxAge(0); // ��
          response.addCookie(ck_pwd);
      
        }
        // pwd ���� ���θ� �����ϴ� ��Ű ���, Y or "" ����
        Cookie ck_pwd_save = new Cookie("ck_pwd_save", memberVO.getPwd_save());
        ck_pwd_save.setMaxAge(600); // ��
        response.addCookie(ck_pwd_save);
        // ------------------------------------------------------------------
      
        String url_address = Tool.checkNull(memberVO.getUrl_address());
        if (url_address.length() > 0){
          mav.setViewName("redirect:" + memberVO.getUrl_address());
        }else{
          System.out.println("--> index.jsp �������� �̵��մϴ�.");
          String url = session.getAttribute("url").toString();
          mav.setViewName("redirect:/" + url);
        }
    
        } else {
          mav.setViewName("/member/message");
          msgs.add("���� ������ ��� �Ұ��մϴ�.");
          msgs.add("�����ڿ��� �������ּ���.");
          links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
          links.add("<button type='button' onclick=\"location.href='../home.do'\">Ȩ������</button>");
        }
      
      } else {
        mav.setViewName("/member/message");
        msgs.add("�α��ο� �����߽��ϴ�.");
        msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
        links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
        links.add("<button type='button' onclick=\"location.href='../home.do'\">Ȩ������</button>");
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
    
    session.invalidate(); // ��� session ���� ����

    PrintWriter writer = response.getWriter();
    writer.println
    ("<script>"
        + "alert('" + userid2 + "�� �̿��� �ּż� �����մϴ�');"
        + "location.href='../home.do';"
        + "</script>"
        );
  } 
  
  /**
   * Ż�� ��û
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
      msgs.add("Ż�� ���������� ó���Ǿ����ϴ�.");
      msgs.add("�̿��� �ּż� �����մϴ�.");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
      links.add("<button type='button' onclick=\"location.href='./create.do'\">ȸ������</button>");
    } else {
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    }
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
  
  /**
   * �н����� Ȯ�� �� ���
   * @param mno ȸ�� ��ȣ
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
  public ModelAndView checkPwd(MemberVO memberVO, String flag) {
    ModelAndView mav = new ModelAndView();
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    // ���� �н����� ��ġ ���� �˻�
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
   * ȸ�� ���� - ���� �޽����� ���谡 �ξ��� �־ �޽����� �����ϸ� ������ �� ��
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
  * ȸ�� ���� ����
  * �̸��� ���� �� ������ �ʿ�
  * @param memberVO
  * @param updateFlag
  * @return
  */
  @RequestMapping(value = "/member/update.do", method = RequestMethod.POST)
  public ModelAndView update(MemberVO memberVO, String updateFlag, HttpSession session) {
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if(updateFlag.equals("1")) { // �̸����� ���� �Ǿ��ٸ� ����
      // �α��� �Ұ��� ����
      memberVO.setConfirm("N");
      memberVO.setAct("H");
      
        
  // �̸��� ---------------------------------------------------------     
      String subject = "Blog ������ ���� �����Դϴ�.";  // ����
      String content = "���� ����<br><br>";  // ����
      content += "�Ʒ��� ��ũ�� Ŭ���ϸ� ������ �Ϸ�˴ϴ�.<br><br>";
      // http://172.16.12.1:9090/admin_v1jq/admin1/confirm.jsp?email=testcell2010@gmail.com&auth=ABC1234567890
  
      content += "http://localhost:9090/tmember/member/confirm.do?email=" + memberVO.getEmail() + "&auth=" + memberVO.getAuth();
  
      // mw-002.cafe24.com, Cafe24
      String host = "mw-002.cafe24.com";    // smtp mail server(����������)     
      String from = "chanmi_blog@gmail.com";   // ������ �ּ�, ��α� ������ �ּ�
      String to = memberVO.getEmail();    // �޴� ���
  
      Properties props = new Properties();  // SMTP �������� ���, port 25
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.auth","true");
  
      Authenticator authenticator = new MyAuthentication();
      Session sess = Session.getInstance(props, authenticator);   // ���� ���� �˻�
  
      try {
        Message msg = new MimeMessage(sess);   // ���� ���� ��ü ����
        msg.setFrom(new InternetAddress(from));   // ������ ��� ����
              
        // �Ѹ��Ը� ����
        InternetAddress[] address = {new InternetAddress(to)}; // �޴� ��� ����
        
        msg.setRecipients(Message.RecipientType.TO, address); // ������ �ּ� ����
              
        msg.setSubject(subject);                  // ���� ���� 
        msg.setSentDate(new Date());          // ���� ��¥ ����
              
        // msg.setText(msgText); // ���� �������� ���ڸ� ���� ���
  
        // ������ �������� HTML �������� ���� ���
        msg.setContent(content, "text/html;charset=utf-8");
              
        Transport.send(msg);  // ���� ����
  
        msgs.add("<u>�̸��� �������� �ʿ��մϴ�.</u><br><br>");
        msgs.add("<u>������ ���� ��ũ�� Ŭ�����ּ���.</u><br>");
        
      } catch (MessagingException mex) {
        System.out.println(mex.getMessage());
        // out.println(mex.getMessage()+"<br><br>");
        // out.println(to + "�Կ��� ���� �߼��� ���� �߽��ϴ�.");
      }
      
      } // if��
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message");
 
    if (memberDAO.update(memberVO) == 1) {
      msgs.add("ȸ�������� �����Ǿ����ϴ�.");
      msgs.add("�ٽ� �α��� ���ּ���.");
      links.add("<button type='button' onclick=\"location.href='./login.do'\">�α���</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    } else {
      msgs.add("ȸ�� ���� ���濡 �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    }
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    session.invalidate();
    
    return mav;
    
  }
  
  /**
   * ���� ���� ��
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
   * ���� ����
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
    System.out.println("��Ʈ��"+mno);
    System.out.println("��Ʈ��"+act);
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
    ("<script>alert('�����߽��ϴ�.');" 
     + "</script>"
        );
  }

}
  
  @RequestMapping(value = "/member/mylist.do", method = RequestMethod.GET)
  public ModelAndView mylist(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/mylist");
    String userid = session.getAttribute("userid").toString();
    
    List<ArtVO> art_list = memberDAO.art_list(userid);
    Iterator<ArtVO> art_iter = art_list.iterator();
    while (art_iter.hasNext() == true) { // ���� ��� �˻�
      ArtVO vo = art_iter.next(); // ��� ����
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    List<CameraVO> camera_list = memberDAO.camera_list(userid);
    Iterator<CameraVO> camera_iter = camera_list.iterator();
    while (camera_iter.hasNext() == true) { // ���� ��� �˻�
      CameraVO vo = camera_iter.next(); // ��� ����
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      vo.setWdate(vo.getWdate().substring(0, 10));
    }
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("art_list", art_list);
    hashMap.put("camera_list", camera_list);

    mav.addObject("hashMap", hashMap);
    return mav;
  }
}


//javamail lib �� �ʿ��մϴ�.
class MyAuthentication extends Authenticator {
  PasswordAuthentication pa;
  public MyAuthentication(){
   pa = new PasswordAuthentication("test@nulunggi.pe.kr", "test2016");
  }
  
  public PasswordAuthentication getPasswordAuthentication() {
   return pa;
  }
}