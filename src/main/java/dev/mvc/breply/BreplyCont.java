package dev.mvc.breply;

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

import dev.mvc.book.BookDAOInter;

@Controller
public class BreplyCont {
   @Autowired
   @Qualifier("dev.mvc.breply.BreplyDAO")
   private BreplyDAOInter breplyDAO;
   
   @Autowired
   @Qualifier("dev.mvc.book.BookDAO")
   private BookDAOInter bookDAO;
   
   public BreplyCont() {
      System.out.println("--> BreplyCont created.");
   }
   
   @RequestMapping(value = "/breply/create.do", method = RequestMethod.GET)
   public ModelAndView create(HttpSession session) {
      System.out.println("-->create() GET called");
      
      String userid = session.getAttribute("userid").toString();
      String nickname = session.getAttribute("nickname").toString();
      
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/breply/create");
      mav.addObject("userid", userid);
      mav.addObject("nickname", nickname);
      return mav;
   }
   
   @RequestMapping(value = "/breply/create.do", method = RequestMethod.POST)
   public ModelAndView create(BreplyVO breplyVO , HttpServletResponse response, HttpSession session) throws IOException {
      System.out.println("--> create() POST called.");
      ModelAndView mav = new ModelAndView();
      
      response.setCharacterEncoding("UTF-8");
      response.setContentType("text/html; charset=UTF-8");
      if (session.getAttribute("userid") == null){
         PrintWriter writer = response.getWriter();
         writer.println
         ("<script>alert('로그인 한 사용자만 사용이 가능합니다.');" 
          + "parent.location.href = '../member/login.do';" 
          + "</script>"); 
         //session.setAttribute("url", "/camera_reply/list.do?ctno="+camera_replyVO.getCtno());
         session.setAttribute("url", "book/read.do?bno="+breplyVO.getBno());
       }
      
      breplyDAO.create(breplyVO);
      
      mav.setViewName("redirect:/breply/list.do?bno="+breplyVO.getBno());
      return mav;
   }
   
   @RequestMapping(value = "/breply/list.do", method = RequestMethod.GET)
   public ModelAndView list(HttpServletRequest requset, int bno, HttpSession session){
      ModelAndView mav = new ModelAndView();
      
      mav.setViewName("/breply/list");
      mav.addObject("list", breplyDAO.list(bno));
      mav.addObject("bno", bno);
      
      return mav;
   }
   
   @RequestMapping(value = "/breply/reply.do", method = RequestMethod.GET)
   public ModelAndView reply(){
      ModelAndView mav = new ModelAndView();
      
      mav.setViewName("/breply/reply");
      return mav;
   }
   
   @RequestMapping(value = "/breply/reply.do", method = RequestMethod.POST)
   public ModelAndView reply(BreplyVO breplyVO, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
      ModelAndView mav = new ModelAndView();
      
      response.setCharacterEncoding("UTF-8");
      response.setContentType("text/html; charset=UTF-8");
      if (session.getAttribute("userid") == null){
        PrintWriter writer = response.getWriter();
        writer.println
        ("<script>alert('로그인 한 사용자만 사용이 가능합니다.');" 
         + "parent.location.href = '../member/login.do';" 
         + "</script>"); 
        //session.setAttribute("url", "/camera_reply/list.do?ctno="+camera_replyVO.getCtno());
        session.setAttribute("url", "/book/read.do?bno="+breplyVO.getBno());
      } 
      
      mav.setViewName("redirect:/breply/list.do?bno="+breplyVO.getBno());
      BreplyVO parentVO = breplyDAO.read(breplyVO.getRno());
      System.out.println(breplyVO.getRno());
      breplyVO.setGrpno(parentVO.getGrpno());
      
      breplyVO.setAnsnum(parentVO.getAnsnum());
      
      breplyDAO.updateAnsnum(breplyVO);
      
      breplyVO.setIndent(parentVO.getIndent()+1);
      breplyVO.setAnsnum(parentVO.getAnsnum()+1);
      
      breplyDAO.reply(breplyVO);
      
      return mav;
      
   }
   
   @RequestMapping(value = "/breply/delete.do", method = RequestMethod.POST)
   public ModelAndView delete(BreplyVO breplyVO){
      ModelAndView mav = new ModelAndView();
      breplyDAO.delete(breplyVO.getRno());
      mav.setViewName("redirect:/breply/list.do?bno="+breplyVO.getBno());
      
      
      return mav;
      
   }
   
   
   
   

}
