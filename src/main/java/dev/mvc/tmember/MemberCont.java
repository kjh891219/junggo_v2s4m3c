package dev.mvc.tmember;
 
import java.util.*;
import javax.mail.*;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import web.tool.Tool;

@Controller
public class MemberCont {
  @Autowired
  @Qualifier("dev.mvc.tmember.MemberDAO")
  private MemberDAOInter memberDAO;
  
  public MemberCont(){
    System.out.println("--> MemberCont created.");
  }
  
  @RequestMapping(value = "/member/home.do", method = RequestMethod.GET)
  public ModelAndView home() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/index.jsp"); // member�� create.do�� ���� ��� �̵� -> /webapp/member/create.jsp
 
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
  public ModelAndView create(MemberVO memberVO) {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message"); // /webapp/member/message.jsp
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
  
    
// ����, ���� �߰� ---------------------------------------------------------    
    memberVO.setAuth(Tool.key()); // ABC012345678901234
    memberVO.setDroupout("N");
     
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

    content += "http://localhost:9090/junggo/member/confirm.do?email=" + memberVO.getEmail() + "&auth=" + memberVO.getAuth();

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
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    } else {
      msgs.add("ȸ�� ���Կ� �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    }
 
    links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
 
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
   * ��ü ����� ����մϴ�.
   * 
   * @return
   */
  @RequestMapping(value = "/member/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/list"); //  /webapp/member/list.jsp
    mav.addObject("list", memberDAO.list());
 
    return mav;
  }
  
  @RequestMapping(value = "/member/read.do", method = RequestMethod.GET)
  public ModelAndView read(int mno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/read");
    mav.addObject("memberVO", memberDAO.read(mno));
 
    return mav;
  }
  
  @RequestMapping(value = "/member/update.do", method = RequestMethod.POST)
  public ModelAndView update(MemberVO memberVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message");
 
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
 
    if (memberDAO.update(memberVO) == 1) {
      msgs.add("ȸ�������� �����Ǿ����ϴ�.");
      links.add("<button type='button' onclick=\"location.href='./read.do?mno="+memberVO.getMno()+"'\">����� ȸ������ Ȯ��</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    } else {
      msgs.add("ȸ�� ���� ���濡 �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    }
 
    links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
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
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    } else {
      msgs.add("�̸��� ������ �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
      links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    }
 
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
 
    return mav;
  }
  
  @RequestMapping(value = "/member/login.do", 
                              method = RequestMethod.GET)
  public ModelAndView login() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/login_ck_form"); // /webapp/member/login_ck_form.jsp

    return mav;
  }
  
  @RequestMapping(value = "/member/login.do", 
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
        System.out.println("act:"+ ("MY".indexOf(act)));
        System.out.println("act:"+ ("MY".indexOf("M")));
        System.out.println("act:"+ ("MY".indexOf("Y")));
        
        session.setAttribute("userid", memberVO.getUserid());
        session.setAttribute("pwd", memberVO.getPwd());
        session.setAttribute("act", act);
        session.setAttribute("mno", mno);
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
          mav.setViewName("redirect:/index.jsp"); // Ȯ���� ���
        }
    
        } else {
          mav.setViewName("/member/message");
          msgs.add("���� ������ ��� �Ұ��մϴ�.");
          msgs.add("�����ڿ��� �������ּ���.");
          links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
          links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
        }
      
      } else {
        mav.setViewName("/member/message");
        msgs.add("�α��ο� �����߽��ϴ�.");
        msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
        links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
        links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
      }
      
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
      
      return mav;
  }
  
  @RequestMapping(value = "/member/logout.do", 
                              method = RequestMethod.GET)
  public ModelAndView logout(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/message"); // /webapp/member/message.jsp
  
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
  
    msgs.add("�̿����ּż� �����մϴ�.");
    links.add("<button type='button' onclick=\"location.href='../index.do'\">Ȩ������</button>");
  
    session.invalidate(); // ��� session ���� ����
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
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