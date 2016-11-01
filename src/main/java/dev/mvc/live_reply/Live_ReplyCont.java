package dev.mvc.live_reply;

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

import dev.mvc.living.LivingDAOInter;

@Controller
public class Live_ReplyCont {
 @Autowired 
 @Qualifier("dev.mvc.live_reply.Live_ReplyDAO")
 private Live_ReplyDAOInter live_replyDAO;
 
 @Autowired            
 @Qualifier("dev.mvc.living.LivingDAO")
 private LivingDAOInter livngDAO;
 

 @RequestMapping(value = "/live_reply/create.do", method = RequestMethod.GET)
 public ModelAndView create(HttpSession session) {

   String userid = session.getAttribute("userid").toString();
   String nickname = session.getAttribute("nickname").toString();

   ModelAndView mav = new ModelAndView();
   mav.setViewName("/live_reply/create"); // /webapp/blog/create.jsp
   mav.addObject("userid", userid);
   mav.addObject("nickname", nickname);

   
      return mav;
 }

 @RequestMapping(value = "/live_reply/create.do", method = RequestMethod.POST)
 public ModelAndView create(Live_ReplyVO live_replyVO, HttpServletResponse response, HttpSession session)throws IOException  {
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
     session.setAttribute("url", "living/read.do?lno="+live_replyVO.getLno());
     
   } 
   
   
   live_replyDAO.create(live_replyVO);
   
   mav.setViewName("redirect:/live_reply/list.do?lno="+live_replyVO.getLno());
   return mav;
 }




 
 /**
  * 삭제폼
  * @param blogno
  * @return
  */
   
 @RequestMapping(value = "/live_reply/delete.do", method = RequestMethod.POST)
 public ModelAndView delete(Live_ReplyVO vo) {
   ModelAndView mav = new ModelAndView();
   live_replyDAO.delete(vo.getRno());
   mav.setViewName("redirect:/live_reply/list.do?lno="+vo.getLno());// 확장자 명시
   return mav;
 }
 

 
 @RequestMapping(value = "/live_reply/list.do", method = RequestMethod.GET)
 public ModelAndView list(HttpServletRequest request, int lno, HttpSession session) {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/live_reply/list");
 
   mav.addObject("list", live_replyDAO.live_replyList(lno));
   mav.addObject("lno", lno);

  
   return mav;
 }  
 
 @RequestMapping(value = "/live_reply/reply.do", method = RequestMethod.GET)
 public ModelAndView reply() {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/live_reply/reply"); // /webapp/blog/reply.jsp
   
   return mav;
 }
 
 
 
 @RequestMapping(value = "/live_reply/reply.do", method = RequestMethod.POST)
 public ModelAndView reply(Live_ReplyVO live_replyVO, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
   ModelAndView mav = new ModelAndView();

   response.setCharacterEncoding("UTF-8");
   response.setContentType("text/html; charset=UTF-8");
   if (session.getAttribute("userid") == null){
     PrintWriter writer = response.getWriter();
     writer.println
     ("<script>alert('로그인 한 사용자만 사용이 가능합니다.');" 
      + "parent.location.href = '../member/login.do';" 
      + "</script>"); 
     session.setAttribute("url", "live_reply/read.do?lno="+live_replyVO.getLno());
     
   } 
   
       
   
   mav.setViewName("redirect:/live_reply/list.do?lno="+live_replyVO.getLno());
   System.out.println("여기 들어옴");
   // ---------- 답변 관련 코드 시작 ---------- 
   Live_ReplyVO parentVO = live_replyDAO.read(live_replyVO.getLno()); // 부모글 정보 추출
   System.out.println("여기 들어2옴"); 
   System.out.println(live_replyVO.getRno());
   live_replyVO.setGrpno(parentVO.getGrpno()); // 그룹 번호
         
   live_replyVO.setAnsnum(parentVO.getAnsnum()); // 답변 순서
   
   live_replyDAO.updateAnsnum(live_replyVO); // 현재 등록된 답변 뒤로 +1 처리함.
              
   live_replyVO.setIndent(parentVO.getIndent()+1); // 답변 차수 증가
   live_replyVO.setAnsnum(parentVO.getAnsnum()+1); //부모 바로 아래 등록
   // ---------- 답변 관련 코드 종료 ---------- 
  
  
   live_replyDAO.reply(live_replyVO); 
   return mav;
 
 }
 
}
