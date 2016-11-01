package dev.mvc.mo_reply;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.mobile.MobileDAOInter;

@Controller
public class Mo_ReplyCont {
  @Autowired
  @Qualifier("dev.mvc.mo_reply.Mo_ReplyDAO")
  private Mo_ReplyDAOInter mo_replyDAO;
  
  
  @Autowired
  @Qualifier("dev.mvc.mobile.MobileDAO")
  private MobileDAOInter mobileDAO;
  
  @RequestMapping(value = "/mo_reply/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session) {

    String userid = session.getAttribute("userid").toString();
    String nickname = session.getAttribute("nickname").toString();

    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mo_reply/create"); // /webapp/blog/create.jsp
    mav.addObject("userid", userid);
    mav.addObject("nickname", nickname);

    
       return mav;
  }

  @RequestMapping(value = "/mo_reply/create.do", method = RequestMethod.POST)
  public ModelAndView create(Mo_ReplyVO mo_replyVO, HttpServletResponse response, HttpSession session)throws IOException  {
    // System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
  
    
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    if (session.getAttribute("userid") == null) {
      PrintWriter writer = response.getWriter();
      writer.println   
      ("<script>alert('�α��� �� ����ڸ� ����� �����մϴ�.');" 
       + "parent.location.href = '../member/login.do';"
       + "</script>"); 
      session.setAttribute("url", "mobile/read.do?mno="+mo_replyVO.getMno());
      
    } 
    
    
    mo_replyDAO.create(mo_replyVO);
    
    mav.setViewName("redirect:/mo_reply/list.do?mno="+mo_replyVO.getMno());
    return mav;
  }




  
  /**
   * ������
   * @param blogno
   * @return
   */
    
  @RequestMapping(value = "/mo_reply/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(Mo_ReplyVO vo) {
    ModelAndView mav = new ModelAndView();
    mo_replyDAO.delete(vo.getRno());
    mav.setViewName("redirect:/mo_reply/list.do?mno="+vo.getMno());// Ȯ���� ���
    return mav;
  }
  

  
  @RequestMapping(value = "/mo_reply/list.do", method = RequestMethod.GET)
  public ModelAndView list(HttpServletRequest request, int mno, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mo_reply/list");
  
    mav.addObject("list", mo_replyDAO.mo_replyList(mno));
    mav.addObject("mno", mno);

   
    return mav;
  }  
  
  @RequestMapping(value = "/mo_reply/reply.do", method = RequestMethod.GET)
  public ModelAndView reply() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/mo_reply/reply"); // /webapp/blog/reply.jsp
    
    return mav;
  }
  
  
  
  @RequestMapping(value = "/mo_reply/reply.do", method = RequestMethod.POST)
  public ModelAndView reply(Mo_ReplyVO mo_replyVO, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
    ModelAndView mav = new ModelAndView();

    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    if (session.getAttribute("userid") == null){
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('�α��� �� ����ڸ� ����� �����մϴ�.');" 
       + "parent.location.href = '../member/login.do';"
       + "</script>"); 
      session.setAttribute("url", "mobile/list.do?mno="+mo_replyVO.getMno());
      
    } 
    
        
    
    mav.setViewName("redirect:/mo_reply/list.do?mno="+mo_replyVO.getMno());
    System.out.println("���� ����");
    // ---------- �亯 ���� �ڵ� ���� ---------- 
   Mo_ReplyVO parentVO = mo_replyDAO.read(mo_replyVO.getRno()); // �θ�� ���� ����
    System.out.println("���� ���2��"); 
    System.out.println(mo_replyVO.getRno());
    mo_replyVO.setGrpno(parentVO.getGrpno()); // �׷� ��ȣ
          
    mo_replyVO.setAnsnum(parentVO.getAnsnum()); // �亯 ����
    
   mo_replyDAO.updateAnsnum(mo_replyVO); // ���� ��ϵ� �亯 �ڷ� +1 ó����.
    
   mo_replyVO.setIndent(parentVO.getIndent()+1); // �亯 ���� ����
   mo_replyVO.setAnsnum(parentVO.getAnsnum()+1); //�θ� �ٷ� �Ʒ� ���
    // ---------- �亯 ���� �ڵ� ���� ---------- 
   
   
    mo_replyDAO.reply(mo_replyVO); 
    return mav;
  
  }
                
 
}
