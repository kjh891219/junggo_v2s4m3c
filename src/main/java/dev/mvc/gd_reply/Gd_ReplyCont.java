package dev.mvc.gd_reply;

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


import dev.mvc.gamedevice.GameDeviceDAOInter;

@Controller
public class Gd_ReplyCont {
 @Autowired 
 @Qualifier("dev.mvc.gd_reply.Gd_ReplyDAO")
 private Gd_ReplyDAOInter gd_replyDAO;
    
 @Autowired 
 @Qualifier("dev.mvc.gamedevice.GameDeviceDAO")
 private GameDeviceDAOInter gamedeviceDAO;

 @RequestMapping(value = "/gd_reply/create.do", method = RequestMethod.GET)
 public ModelAndView create(HttpSession session) {

   String userid = session.getAttribute("userid").toString();
   String nickname = session.getAttribute("nickname").toString();

   ModelAndView mav = new ModelAndView();
   mav.setViewName("/gd_reply/create"); // /webapp/blog/create.jsp
   mav.addObject("userid", userid);
   mav.addObject("nickname", nickname);

   
      return mav;
 }

 @RequestMapping(value = "/gd_reply/create.do", method = RequestMethod.POST)
 public ModelAndView create(Gd_ReplyVO gd_replyVO, HttpServletResponse response, HttpSession session)throws IOException  {
   // System.out.println("--> create() POST called.");
   ModelAndView mav = new ModelAndView();
 
   
   response.setCharacterEncoding("UTF-8");
   response.setContentType("text/html; charset=UTF-8");
   if (session.getAttribute("userid") == null) {
     PrintWriter writer = response.getWriter();
     writer.println   
     ("<script>alert('로그인 한 사용자만 사용이 가능합니다.');" 
         + "parent.location.href = '../member/login.do';" 
      + "</script>"); 
     session.setAttribute("url", "gamedevice/read.do?gdno="+gd_replyVO.getGdno());
     
   } 
   
   
   gd_replyDAO.create(gd_replyVO);
   
   mav.setViewName("redirect:/gd_reply/list.do?gdno="+gd_replyVO.getGdno());
   return mav;
 }




 
 /**
  * 삭제폼
  * @param blogno
  * @return
  */
   
 @RequestMapping(value = "/gd_reply/delete.do", method = RequestMethod.POST)
 public ModelAndView delete(Gd_ReplyVO vo) {
   ModelAndView mav = new ModelAndView();
   gd_replyDAO.delete(vo.getRno());
   mav.setViewName("redirect:/gd_reply/list.do?gdno="+vo.getGdno());// 확장자 명시
   return mav;
 }
 

 
 @RequestMapping(value = "/gd_reply/list.do", method = RequestMethod.GET)
 public ModelAndView list(HttpServletRequest request, int gdno, HttpSession session) {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/gd_reply/list");
 
   mav.addObject("list", gd_replyDAO.gd_replyList(gdno));
   mav.addObject("gdno", gdno);

  
   return mav;
 }  
 
 @RequestMapping(value = "/gd_reply/reply.do", method = RequestMethod.GET)
 public ModelAndView reply() {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/gd_reply/reply"); // /webapp/blog/reply.jsp
   
   return mav;
 }
 
 
 
 @RequestMapping(value = "/gd_reply/reply.do", method = RequestMethod.POST)
 public ModelAndView reply(Gd_ReplyVO gd_replyVO, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
   ModelAndView mav = new ModelAndView();

   response.setCharacterEncoding("UTF-8");
   response.setContentType("text/html; charset=UTF-8");
   if (session.getAttribute("userid") == null){
     PrintWriter writer = response.getWriter();
     writer.println
     ("<script>alert('로그인 한 사용자만 사용이 가능합니다.');" 
      + "parent.location.href = '../member/login.do';" 
      + "</script>"); 
     session.setAttribute("url", "gamedevice/read.do?gdno="+gd_replyVO.getGdno());
     
   } 
   
       
   
   mav.setViewName("redirect:/gd_reply/list.do?gdno="+gd_replyVO.getGdno());
   System.out.println("여기 들어옴");
   // ---------- 답변 관련 코드 시작 ---------- 
  Gd_ReplyVO parentVO = gd_replyDAO.read(gd_replyVO.getRno()); // 부모글 정보 추출
   System.out.println("여기 들어2옴"); 
   System.out.println(gd_replyVO.getRno());
   gd_replyVO.setGrpno(parentVO.getGrpno()); // 그룹 번호
         
   gd_replyVO.setAnsnum(parentVO.getAnsnum()); // 답변 순서
   
  gd_replyDAO.updateAnsnum(gd_replyVO); // 현재 등록된 답변 뒤로 +1 처리함.
   
  gd_replyVO.setIndent(parentVO.getIndent()+1); // 답변 차수 증가
  gd_replyVO.setAnsnum(parentVO.getAnsnum()+1); //부모 바로 아래 등록
   // ---------- 답변 관련 코드 종료 ---------- 
  
  
   gd_replyDAO.reply(gd_replyVO); 
   return mav;
 
 }

}
