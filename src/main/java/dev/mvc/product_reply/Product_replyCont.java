package dev.mvc.product_reply;

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

import dev.mvc.product.ProductDAOInter;
import dev.mvc.product.ProductVO;
import dev.mvc.tmember.MemberVO;





@Controller
public class Product_replyCont {
  @Autowired
  @Qualifier("dev.mvc.product_reply.Product_replyDAO")
  private Product_replyDAOInter product_replyDAO;
  
  
  @Autowired
  @Qualifier("dev.mvc.product.ProductDAO")
  private ProductDAOInter productDAO;
  
  
  
  public Product_replyCont() {
    System.out.println("--> Product_replyCont created.");
  }
  
  @RequestMapping(value = "/product_reply/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session ) {
    System.out.println("--> create() GET called.");
    
    String userid = session.getAttribute("userid").toString();
    String nickname = session.getAttribute("nickname").toString();

    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/product_reply/create"); // /webapp/member/create.jsp
    mav.addObject("userid", userid);
    mav.addObject("nickname", nickname);

    return mav;
  }

  @RequestMapping(value = "/product_reply/create.do", method = RequestMethod.POST)
  public ModelAndView create(Product_replyVO product_replyVO, HttpServletResponse response, HttpSession session) throws IOException {
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
      session.setAttribute("url", "/product_reply/list.do?pno="+ product_replyVO.getPno());
      
    } 
    
    
    product_replyDAO.create(product_replyVO);
    
    mav.setViewName("redirect:/product_reply/list.do?pno="+product_replyVO.getPno());
    return mav;
  }
    

  
  
  @RequestMapping(value = "/product_reply/list.do", method = RequestMethod.GET)
  public ModelAndView list(HttpServletRequest request, int pno, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    
    
    
    
   // String userid = session.getAttribute("userid").toString();
   // String nickname = session.getAttribute("nickname").toString();
    mav.setViewName("/product_reply/list");
    mav.addObject("list", product_replyDAO.product_replyList(pno));
    mav.addObject("pno", pno);
    //mav.addObject("nickname", nickname);
    //mav.addObject("userid", userid);
    return mav;
  }
  
  
  @RequestMapping(value = "/product_reply/reply.do", method = RequestMethod.GET)
  public ModelAndView reply() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/product_reply/reply"); // /webapp/blog/reply.jsp
    
    return mav;
  }
  
  
  @RequestMapping(value = "/product_reply/reply.do", method = RequestMethod.POST)
  public ModelAndView reply(Product_replyVO product_replyVO, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
    ModelAndView mav = new ModelAndView();
    
    
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    if (session.getAttribute("userid") == null){
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('�α��� �� ����ڸ� ����� �����մϴ�.');" 
       + "location.href = '../member/login.do';"
       + "</script>"); 
      session.setAttribute("url", "/product_reply/list.do?pno="+product_replyVO.getPno());
      
    } 
    
    
    
    
    mav.setViewName("redirect:/product_reply/list.do?pno="+product_replyVO.getPno());
    System.out.println("���� ����");
    // ---------- �亯 ���� �ڵ� ���� ---------- 
    Product_replyVO parentVO = product_replyDAO.read(product_replyVO.getRno()); // �θ�� ���� ����
    System.out.println("���� ���2��"); 
    System.out.println(product_replyVO.getRno());
    product_replyVO.setGrpno(parentVO.getGrpno()); // �׷� ��ȣ

    product_replyVO.setAnsnum(parentVO.getAnsnum()); // �亯 ����
    
   product_replyDAO.updateAnsnum(product_replyVO); // ���� ��ϵ� �亯 �ڷ� +1 ó����.
    
   product_replyVO.setIndent(parentVO.getIndent()+1); // �亯 ���� ����
   product_replyVO.setAnsnum(parentVO.getAnsnum()+1); //�θ� �ٷ� �Ʒ� ���
    // ---------- �亯 ���� �ڵ� ���� ---------- 
   
   
    product_replyDAO.reply(product_replyVO); 
    return mav;
  }
  
  
  
  @RequestMapping(value = "/product_reply/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(Product_replyVO vo) {
    ModelAndView mav = new ModelAndView();
    product_replyDAO.delete(vo.getRno());
    mav.setViewName("redirect:/product_reply/list.do?pno="+vo.getPno());// Ȯ���� ���
    return mav;
  }
  
  
}
