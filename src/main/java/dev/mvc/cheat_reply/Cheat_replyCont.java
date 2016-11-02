package dev.mvc.cheat_reply;

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

import dev.mvc.cheat.CheatDAOInter;
import dev.mvc.tmember.MemberVO;
import dev.mvc.cheat_reply.Cheat_replyDAOInter;




@Controller
public class Cheat_replyCont {
  @Autowired
  @Qualifier("dev.mvc.cheat_reply.Cheat_replyDAO")
  private Cheat_replyDAOInter cheat_replyDAO;
  
  
  @Autowired
  @Qualifier("dev.mvc.cheat.CheatDAO")
  private CheatDAOInter cheatDAO;
  
  
  
  public Cheat_replyCont() {
    System.out.println("--> Cheat_replyCont created.");
  }
  
  @RequestMapping(value = "/cheat_reply/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session ) {
    System.out.println("--> create() GET called.");
    
    String userid = session.getAttribute("userid").toString();
    String nickname = session.getAttribute("nickname").toString();

    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cheat_reply/create"); // /webapp/member/create.jsp
    mav.addObject("userid", userid);
    mav.addObject("nickname", nickname);

    return mav;
  }

  @RequestMapping(value = "/cheat_reply/create.do", method = RequestMethod.POST)
  public ModelAndView create(Cheat_replyVO cheat_replyVO, HttpServletResponse response, HttpSession session) throws IOException {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    if (session.getAttribute("userid") == null){
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('로그인 한 사용자만 사용이 가능합니다.');" 
      + "location.href = '../member/login.do';"
       + "</script>"); 
      session.setAttribute("url", "/cheat_reply/list.do?ctno="+cheat_replyVO.getCtno());
      
    } 
    
    
    cheat_replyDAO.create(cheat_replyVO);
    
    mav.setViewName("redirect:/cheat_reply/list.do?ctno="+cheat_replyVO.getCtno());
    return mav;
  }
    

  
  
  @RequestMapping(value = "/cheat_reply/list.do", method = RequestMethod.GET)
  public ModelAndView list(HttpServletRequest request, int ctno, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    
    
    
    
   // String userid = session.getAttribute("userid").toString();
   // String nickname = session.getAttribute("nickname").toString();
    mav.setViewName("/cheat_reply/list");
    mav.addObject("list", cheat_replyDAO.cheat_replyList(ctno));
    mav.addObject("ctno", ctno);
    //mav.addObject("nickname", nickname);
    //mav.addObject("userid", userid);
    return mav;
  }
  
  
  @RequestMapping(value = "/cheat_reply/reply.do", method = RequestMethod.GET)
  public ModelAndView reply() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cheat_reply/reply"); // /webapp/blog/reply.jsp
    
    return mav;
  }
  
  
  @RequestMapping(value = "/cheat_reply/reply.do", method = RequestMethod.POST)
  public ModelAndView reply(Cheat_replyVO cheat_replyVO, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
    ModelAndView mav = new ModelAndView();
    
    
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    if (session.getAttribute("userid") == null){
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('로그인 한 사용자만 사용이 가능합니다.');" 
       + "location.href = '../member/login.do';"
       + "</script>"); 
      session.setAttribute("url", "/cheat_reply/list.do?ctno="+cheat_replyVO.getCtno());
      
    } 
    
    
    
    
    mav.setViewName("redirect:/cheat_reply/list.do?ctno="+cheat_replyVO.getCtno());
    System.out.println("여기 들어옴");
    // ---------- 답변 관련 코드 시작 ---------- 
    Cheat_replyVO parentVO = cheat_replyDAO.read(cheat_replyVO.getRno()); // 부모글 정보 추출
    System.out.println("여기 들어2옴"); 
    System.out.println(cheat_replyVO.getRno());
    cheat_replyVO.setGrpno(parentVO.getGrpno()); // 그룹 번호

    cheat_replyVO.setAnsnum(parentVO.getAnsnum()); // 답변 순서
    
   cheat_replyDAO.updateAnsnum(cheat_replyVO); // 현재 등록된 답변 뒤로 +1 처리함.
    
   cheat_replyVO.setIndent(parentVO.getIndent()+1); // 답변 차수 증가
   cheat_replyVO.setAnsnum(parentVO.getAnsnum()+1); //부모 바로 아래 등록
    // ---------- 답변 관련 코드 종료 ---------- 
   
   
    cheat_replyDAO.reply(cheat_replyVO); 
    return mav;
  }
  
  
  
  @RequestMapping(value = "/cheat_reply/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(Cheat_replyVO vo) {
    ModelAndView mav = new ModelAndView();
    cheat_replyDAO.delete(vo.getRno());
    mav.setViewName("redirect:/cheat_reply/list.do?ctno="+vo.getCtno());// 확장자 명시
    return mav;
  }
  
  
}
