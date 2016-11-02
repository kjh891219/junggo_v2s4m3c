package dev.mvc.sports_reply;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.sports.SportsDAOInter;
import dev.mvc.sports.SportsVO;
import dev.mvc.tmember.MemberVO;





@Controller
public class Sports_replyCont {
  @Autowired
  @Qualifier("dev.mvc.sports_reply.Sports_replyDAO")
  private Sports_replyDAOInter sports_replyDAO;
  
  
  @Autowired
  @Qualifier("dev.mvc.sports.SportsDAO")
  private SportsDAOInter sportsDAO;
  
  
  
  public Sports_replyCont() {
    System.out.println("--> Sports_replyCont created.");
  }
  
  @RequestMapping(value = "/sports_reply/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session ) {
    System.out.println("--> create() GET called.");
    
    String userid = session.getAttribute("userid").toString();
    String nickname = session.getAttribute("nickname").toString();

    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/sports_reply/create"); // /webapp/member/create.jsp
    mav.addObject("userid", userid);
    mav.addObject("nickname", nickname);

    return mav;
  }

  @RequestMapping(value = "/sports_reply/create.do", method = RequestMethod.POST)
  public ModelAndView create(Sports_replyVO sports_replyVO, HttpServletResponse response, HttpSession session) throws IOException {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    if (session.getAttribute("userid") == null){
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('�α��� �� ����ڸ� ����� �����մϴ�.');" 
      + "location.href = '../member/login.do';"
       + "</script>"); 
      session.setAttribute("url", "/sports_reply/list.do?sno="+sports_replyVO.getSno());
      
    } 
    
    
    sports_replyDAO.create(sports_replyVO);
    
    mav.setViewName("redirect:/sports_reply/list.do?sno="+sports_replyVO.getSno());
    return mav;
  }
    

  
  
  @RequestMapping(value = "/sports_reply/list.do", method = RequestMethod.GET)
  public ModelAndView list(HttpServletRequest request, int sno, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    
    
    
    
   // String userid = session.getAttribute("userid").toString();
   // String nickname = session.getAttribute("nickname").toString();
    mav.setViewName("/sports_reply/list");
    mav.addObject("list", sports_replyDAO.sports_replyList(sno));
    mav.addObject("sno", sno);
    //mav.addObject("nickname", nickname);
    //mav.addObject("userid", userid);
    return mav;
  }
  
  
  @RequestMapping(value = "/sports_reply/reply.do", method = RequestMethod.GET)
  public ModelAndView reply() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/sports_reply/reply"); // /webapp/blog/reply.jsp
    
    return mav;
  }
  
  
  @RequestMapping(value = "/sports_reply/reply.do", method = RequestMethod.POST)
  public ModelAndView reply(Sports_replyVO sports_replyVO, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
    ModelAndView mav = new ModelAndView();
    
    
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    if (session.getAttribute("userid") == null){
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('�α��� �� ����ڸ� ����� �����մϴ�.');" 
       + "location.href = '../member/login.do';"
       + "</script>"); 
      session.setAttribute("url", "/sports_reply/list.do?sno="+sports_replyVO.getSno());
      
    } 
    
    
    
    
    mav.setViewName("redirect:/sports_reply/list.do?sno="+sports_replyVO.getSno());
    System.out.println("���� ����");
    // ---------- �亯 ���� �ڵ� ���� ---------- 
    Sports_replyVO parentVO = sports_replyDAO.read(sports_replyVO.getRno()); // �θ�� ���� ����
    System.out.println("���� ���2��"); 
    System.out.println(sports_replyVO.getRno());
    sports_replyVO.setGrpno(parentVO.getGrpno()); // �׷� ��ȣ

    sports_replyVO.setAnsnum(parentVO.getAnsnum()); // �亯 ����
    
   sports_replyDAO.updateAnsnum(sports_replyVO); // ���� ��ϵ� �亯 �ڷ� +1 ó����.
    
   sports_replyVO.setIndent(parentVO.getIndent()+1); // �亯 ���� ����
   sports_replyVO.setAnsnum(parentVO.getAnsnum()+1); //�θ� �ٷ� �Ʒ� ���
    // ---------- �亯 ���� �ڵ� ���� ---------- 
   
   
   sports_replyDAO.reply(sports_replyVO); 
    return mav;
  }
  
  
  
  @RequestMapping(value = "/sports_reply/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(Sports_replyVO vo) {
    ModelAndView mav = new ModelAndView();
    sports_replyDAO.delete(vo.getRno());
    mav.setViewName("redirect:/sports_reply/list.do?sno="+vo.getSno());// Ȯ���� ���
    return mav;
  }
  
  
}
