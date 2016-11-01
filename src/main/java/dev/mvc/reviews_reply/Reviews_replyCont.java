package dev.mvc.reviews_reply;

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

import dev.mvc.reviews.ReviewsDAOInter;
import dev.mvc.tmember.MemberVO;


@Controller
public class Reviews_replyCont {
  @Autowired
  @Qualifier("dev.mvc.reviews_reply.Reviews_replyDAO")
  private Reviews_replyDAOInter reviews_replyDAO;
  
  
  @Autowired
  @Qualifier("dev.mvc.reviews.ReviewsDAO")
  private ReviewsDAOInter reviewsDAO;
  
  
  
  public Reviews_replyCont() {
    System.out.println("--> Reviews_replyCont created.");
  }
  
  @RequestMapping(value = "/reviews_reply/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session ) {
    System.out.println("--> create() GET called.");
    
    String userid = session.getAttribute("userid").toString();
    String nickname = session.getAttribute("nickname").toString();

    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/reviews_reply/create"); // /webapp/member/create.jsp
    mav.addObject("userid", userid);
    mav.addObject("nickname", nickname);

    return mav;
  }

  @RequestMapping(value = "/reviews_reply/create.do", method = RequestMethod.POST)
  public ModelAndView create(Reviews_replyVO reviews_replyVO, HttpServletResponse response, HttpSession session) throws IOException {
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
      session.setAttribute("url", "/reviews_reply/list.do?r_no="+reviews_replyVO.getR_no());
      
    } 
    
    
    reviews_replyDAO.create(reviews_replyVO);
    
    mav.setViewName("redirect:/reviews_reply/list.do?r_no="+reviews_replyVO.getR_no());
    return mav;
  }
    

  
  
  @RequestMapping(value = "/reviews_reply/list.do", method = RequestMethod.GET)
  public ModelAndView list(HttpServletRequest request, int r_no, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    
    
    
    
   // String userid = session.getAttribute("userid").toString();
   // String nickname = session.getAttribute("nickname").toString();
    mav.setViewName("/reviews_reply/list");
    mav.addObject("list", reviews_replyDAO.reviews_replyList(r_no));
    mav.addObject("r_no", r_no);
    //mav.addObject("nickname", nickname);
    //mav.addObject("userid", userid);
    return mav;
  }
  
  
  @RequestMapping(value = "/reviews_reply/reply.do", method = RequestMethod.GET)
  public ModelAndView reply() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/reviews_reply/reply"); // /webapp/blog/reply.jsp
    
    return mav;
  }
  
  
  @RequestMapping(value = "/reviews_reply/reply.do", method = RequestMethod.POST)
  public ModelAndView reply(Reviews_replyVO reviews_replyVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("redirect:/reviews_reply/list.do?r_no="+reviews_replyVO.getR_no());
    System.out.println("���� ����");
    // ---------- �亯 ���� �ڵ� ���� ---------- 
    Reviews_replyVO parentVO = reviews_replyDAO.read(reviews_replyVO.getRno()); // �θ�� ���� ����
    System.out.println("���� ���2��"); 
    System.out.println(reviews_replyVO.getRno());
    reviews_replyVO.setGrpno(parentVO.getGrpno()); // �׷� ��ȣ

    reviews_replyVO.setAnsnum(parentVO.getAnsnum()); // �亯 ����
    
    reviews_replyDAO.updateAnsnum(reviews_replyVO); // ���� ��ϵ� �亯 �ڷ� +1 ó����.
    
    reviews_replyVO.setIndent(parentVO.getIndent()+1); // �亯 ���� ����
    reviews_replyVO.setAnsnum(parentVO.getAnsnum()+1); //�θ� �ٷ� �Ʒ� ���
    // ---------- �亯 ���� �ڵ� ���� ---------- 
   
   
    reviews_replyDAO.reply(reviews_replyVO); 
    return mav;
  }
  
  @RequestMapping(value = "/reviews_reply/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(Reviews_replyVO vo) {
    ModelAndView mav = new ModelAndView();
    reviews_replyDAO.delete(vo.getRno());
    mav.setViewName("redirect:/reviews_reply/list.do?r_no="+vo.getR_no());// Ȯ���� ���
    return mav;
  }
  
  
}
