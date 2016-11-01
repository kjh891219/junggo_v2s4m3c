package dev.mvc.carproduct_reply;

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

import dev.mvc.carproduct.CarproductDAOInter;
import dev.mvc.carproduct.CarproductVO;
import dev.mvc.tmember.MemberVO;


@Controller
public class Carproduct_replyCont {
  @Autowired
  @Qualifier("dev.mvc.carproduct_reply.Carproduct_replyDAO")
  private Carproduct_replyDAOInter carproduct_replyDAO;
  
  
  @Autowired
  @Qualifier("dev.mvc.carproduct.CarproductDAO")
  private CarproductDAOInter carproductDAO;
  
  
  
  public Carproduct_replyCont() {
    System.out.println("--> Carproduct_replyCont created.");
  }
  
  @RequestMapping(value = "/carproduct_reply/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session ) {
    System.out.println("--> create() GET called.");
    
    String userid = session.getAttribute("userid").toString();
    String nickname = session.getAttribute("nickname").toString();

    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/carproduct_reply/create"); // /webapp/member/create.jsp
    mav.addObject("userid", userid);
    mav.addObject("nickname", nickname);

    return mav;
  }

  @RequestMapping(value = "/carproduct_reply/create.do", method = RequestMethod.POST)
  public ModelAndView create(Carproduct_replyVO carproduct_replyVO, HttpServletResponse response, HttpSession session) throws IOException {
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
      session.setAttribute("url", "/carproduct_reply/list.do?p_no="+carproduct_replyVO.getP_no());
      
    } 
    
    
    carproduct_replyDAO.create(carproduct_replyVO);
    
    mav.setViewName("redirect:/carproduct_reply/list.do?p_no="+carproduct_replyVO.getP_no());
    return mav;
  }
    

  
  
  @RequestMapping(value = "/carproduct_reply/list.do", method = RequestMethod.GET)
  public ModelAndView list(HttpServletRequest request, int p_no, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    
    
    
    
   // String userid = session.getAttribute("userid").toString();
   // String nickname = session.getAttribute("nickname").toString();
    mav.setViewName("/carproduct_reply/list");
    mav.addObject("list", carproduct_replyDAO.carproduct_replyList(p_no));
    mav.addObject("p_no", p_no);
    //mav.addObject("nickname", nickname);
    //mav.addObject("userid", userid);
    return mav;
  }
  
  
  @RequestMapping(value = "/carproduct_reply/reply.do", method = RequestMethod.GET)
  public ModelAndView reply() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/carproduct_reply/reply"); // /webapp/blog/reply.jsp
    
    return mav;
  }
  
  
  @RequestMapping(value = "/carproduct_reply/reply.do", method = RequestMethod.POST)
  public ModelAndView reply(Carproduct_replyVO carproduct_replyVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("redirect:/carproduct_reply/list.do?p_no="+carproduct_replyVO.getP_no());
    System.out.println("���� ����");
    // ---------- �亯 ���� �ڵ� ���� ---------- 
    Carproduct_replyVO parentVO = carproduct_replyDAO.read(carproduct_replyVO.getRno()); // �θ�� ���� ����
    System.out.println("���� ���2��"); 
    System.out.println(carproduct_replyVO.getRno());
    carproduct_replyVO.setGrpno(parentVO.getGrpno()); // �׷� ��ȣ

    carproduct_replyVO.setAnsnum(parentVO.getAnsnum()); // �亯 ����
    
    carproduct_replyDAO.updateAnsnum(carproduct_replyVO); // ���� ��ϵ� �亯 �ڷ� +1 ó����.
    
    carproduct_replyVO.setIndent(parentVO.getIndent()+1); // �亯 ���� ����
    carproduct_replyVO.setAnsnum(parentVO.getAnsnum()+1); //�θ� �ٷ� �Ʒ� ���
    // ---------- �亯 ���� �ڵ� ���� ---------- 
   
   
    carproduct_replyDAO.reply(carproduct_replyVO); 
    return mav;
  }
  
  @RequestMapping(value = "/carproduct_reply/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(Carproduct_replyVO vo) {
    ModelAndView mav = new ModelAndView();
    carproduct_replyDAO.delete(vo.getRno());
    mav.setViewName("redirect:/carproduct_reply/list.do?p_no="+vo.getP_no());// Ȯ���� ���
    return mav;
  }
  
  
}
