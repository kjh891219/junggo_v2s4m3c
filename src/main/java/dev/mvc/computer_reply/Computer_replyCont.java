package dev.mvc.computer_reply;

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

import dev.mvc.camera_reply.Camera_replyVO;
import dev.mvc.computer.ComputerDAOInter;

@Controller
public class Computer_replyCont {

  @Autowired
  @Qualifier("dev.mvc.computer_reply.Computer_replyDAO")
  private Computer_replyDAOInter computer_replyDAO;
  
  @Autowired
  @Qualifier("dev.mvc.computer.ComputerDAO")
  private ComputerDAOInter computerDAO;
  
  
  public Computer_replyCont() {
    System.out.println("--> Computer_replyCont created.");
  }
  
  @RequestMapping(value = "/computer_reply/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session ) {
    System.out.println("--> create() GET called.");
    
    String userid = session.getAttribute("userid").toString();
    String nickname = session.getAttribute("nickname").toString();

    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/computer_reply/create"); // /webapp/member/create.jsp
    mav.addObject("userid", userid);
    mav.addObject("nickname", nickname);

    return mav;
  }

  @RequestMapping(value = "/computer_reply/create.do", method = RequestMethod.POST)
  public ModelAndView create(Computer_replyVO computer_replyVO, HttpServletResponse response, HttpSession session) throws IOException {
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
      session.setAttribute("url", "/computer/read.do?ctno="+computer_replyVO.getCtno());
    } 
    
    
    computer_replyDAO.create(computer_replyVO);
    
    mav.setViewName("redirect:/computer_reply/list.do?ctno="+computer_replyVO.getCtno());
    return mav;
  }
  
  
  @RequestMapping(value = "/computer_reply/list.do", method = RequestMethod.GET)
  public ModelAndView list(HttpServletRequest request, int ctno, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    
    
    
    
   // String userid = session.getAttribute("userid").toString();
   // String nickname = session.getAttribute("nickname").toString();
    mav.setViewName("/computer_reply/list");
    mav.addObject("list", computer_replyDAO.nreplyList(ctno));
    mav.addObject("ctno", ctno);
    //mav.addObject("nickname", nickname);
    //mav.addObject("userid", userid);
    return mav;
  }
  
  
  @RequestMapping(value = "/computer_reply/reply.do", method = RequestMethod.GET)
  public ModelAndView reply() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/computer_reply/reply"); // /webapp/blog/reply.jsp
    
    return mav;
  }
  
  
  @RequestMapping(value = "/computer_reply/reply.do", method = RequestMethod.POST)
  public ModelAndView reply(Computer_replyVO computer_replyVO, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
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
      session.setAttribute("url", "/computer/read.do?ctno="+computer_replyVO.getCtno());
    } 
    
    
    
    
    mav.setViewName("redirect:/computer_reply/list.do?ctno="+computer_replyVO.getCtno());
    System.out.println("여기 들어옴");
    // ---------- 답변 관련 코드 시작 ---------- 
    Computer_replyVO parentVO = computer_replyDAO.read(computer_replyVO.getRno()); // 부모글 정보 추출
    System.out.println("여기 들어2옴"); 
    System.out.println(computer_replyVO.getRno());
    computer_replyVO.setGrpno(parentVO.getGrpno()); // 그룹 번호

    computer_replyVO.setAnsnum(parentVO.getAnsnum()); // 답변 순서
    
    computer_replyDAO.updateAnsnum(computer_replyVO); // 현재 등록된 답변 뒤로 +1 처리함.
    
    computer_replyVO.setIndent(parentVO.getIndent()+1); // 답변 차수 증가
    computer_replyVO.setAnsnum(parentVO.getAnsnum()+1); //부모 바로 아래 등록
    // ---------- 답변 관련 코드 종료 ---------- 
   
   
    computer_replyDAO.reply(computer_replyVO); 
    return mav;
  }
  
  
  
  @RequestMapping(value = "/computer_reply/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(Computer_replyVO vo) {
    ModelAndView mav = new ModelAndView();
    computer_replyDAO.delete(vo.getRno());
    mav.setViewName("redirect:/computer_reply/list.do?ctno="+vo.getCtno());// 확장자 명시
    return mav;
  }
  
}
