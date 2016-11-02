package dev.mvc.cosmetic_reply;

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

import dev.mvc.cosmetic.CosmeticDAOInter;
import dev.mvc.cosmetic.CosmeticVO;
import dev.mvc.tmember.MemberVO;







@Controller
public class Cosmetic_replyCont {
  @Autowired
  @Qualifier("dev.mvc.cosmetic_reply.Cosmetic_replyDAO")
  private Cosmetic_replyDAOInter cosmetic_replyDAO;
  
  
  @Autowired
  @Qualifier("dev.mvc.cosmetic.CosmeticDAO")
  private CosmeticDAOInter cosmeticDAO;
  
  
  
  public Cosmetic_replyCont() {
    System.out.println("--> Cosmetic_replyCont created.");
  }
  
  @RequestMapping(value = "/cosmetic_reply/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session ) {
    System.out.println("--> create() GET called.");
    
    String userid = session.getAttribute("userid").toString();
    String nickname = session.getAttribute("nickname").toString();

    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cosmetic_reply/create"); // /webapp/member/create.jsp
    mav.addObject("userid", userid);
    mav.addObject("nickname", nickname);

    return mav;
  }

  @RequestMapping(value = "/cosmetic_reply/create.do", method = RequestMethod.POST)
  public ModelAndView create(Cosmetic_replyVO cosmetic_replyVO, HttpServletResponse response, HttpSession session) throws IOException {
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
      session.setAttribute("url", "/cosmetic_reply/list.do?cno="+cosmetic_replyVO.getCno());
      
    } 
    
    
    cosmetic_replyDAO.create(cosmetic_replyVO);
    
    mav.setViewName("redirect:/cosmetic_reply/list.do?cno="+cosmetic_replyVO.getCno());
    return mav;
  }
    

  
  
  @RequestMapping(value = "/cosmetic_reply/list.do", method = RequestMethod.GET)
  public ModelAndView list(HttpServletRequest request, int cno, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    
    
    
    
   // String userid = session.getAttribute("userid").toString();
   // String nickname = session.getAttribute("nickname").toString();
    mav.setViewName("/cosmetic_reply/list");
    mav.addObject("list", cosmetic_replyDAO.cosmetic_replyList(cno));
    mav.addObject("cno", cno);
    //mav.addObject("nickname", nickname);
    //mav.addObject("userid", userid);
    return mav;
  }
  
  
  @RequestMapping(value = "/cosmetic_reply/reply.do", method = RequestMethod.GET)
  public ModelAndView reply() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cosmetic_reply/reply"); // /webapp/blog/reply.jsp
    
    return mav;
  }
  
  
  @RequestMapping(value = "/cosmetic_reply/reply.do", method = RequestMethod.POST)
  public ModelAndView reply(Cosmetic_replyVO cosmetic_replyVO, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
    ModelAndView mav = new ModelAndView();
    
    
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    if (session.getAttribute("userid") == null){
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('�α��� �� ����ڸ� ����� �����մϴ�.');" 
       + "location.href = '../member/login.do';"
       + "</script>"); 
      session.setAttribute("url", "/cosmetic_reply/list.do?cno="+cosmetic_replyVO.getCno());
      
    } 
    
    
    
    
    mav.setViewName("redirect:/cosmetic_reply/list.do?cno="+cosmetic_replyVO.getCno());
    System.out.println("���� ����");
    // ---------- �亯 ���� �ڵ� ���� ---------- 
    Cosmetic_replyVO parentVO = cosmetic_replyDAO.read(cosmetic_replyVO.getRno()); // �θ�� ���� ����
    System.out.println("���� ���2��"); 
    System.out.println(cosmetic_replyVO.getRno());
    cosmetic_replyVO.setGrpno(parentVO.getGrpno()); // �׷� ��ȣ

    cosmetic_replyVO.setAnsnum(parentVO.getAnsnum()); // �亯 ����
    
   cosmetic_replyDAO.updateAnsnum(cosmetic_replyVO); // ���� ��ϵ� �亯 �ڷ� +1 ó����.
    
   cosmetic_replyVO.setIndent(parentVO.getIndent()+1); // �亯 ���� ����
   cosmetic_replyVO.setAnsnum(parentVO.getAnsnum()+1); //�θ� �ٷ� �Ʒ� ���
    // ---------- �亯 ���� �ڵ� ���� ---------- 
   
   
    cosmetic_replyDAO.reply(cosmetic_replyVO); 
    return mav;
  }
  
  
  
  @RequestMapping(value = "/cosmetic_reply/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(Cosmetic_replyVO vo) {
    ModelAndView mav = new ModelAndView();
    cosmetic_replyDAO.delete(vo.getRno());
    mav.setViewName("redirect:/cosmetic_reply/list.do?cno="+vo.getCno());// Ȯ���� ���
    return mav;
  }
  
  
}
