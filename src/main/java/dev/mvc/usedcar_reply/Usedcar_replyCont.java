package dev.mvc.usedcar_reply;

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

import dev.mvc.tmember.MemberVO;
import dev.mvc.usedcar.UsedcarDAOInter;


@Controller
public class Usedcar_replyCont {
  @Autowired
  @Qualifier("dev.mvc.usedcar_reply.Usedcar_replyDAO")
  private Usedcar_replyDAOInter usedcar_replyDAO;
  
  
  @Autowired
  @Qualifier("dev.mvc.usedcar.UsedcarDAO")
  private UsedcarDAOInter usedcarDAO;
  
  
  
  public Usedcar_replyCont() {
    System.out.println("--> Usedcar_replyCont created.");
  }
  
  @RequestMapping(value = "/usedcar_reply/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session ) {
    System.out.println("--> create() GET called.");
    
    String userid = session.getAttribute("userid").toString();
    String nickname = session.getAttribute("nickname").toString();

    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar_reply/create"); // /webapp/member/create.jsp
    mav.addObject("userid", userid);
    mav.addObject("nickname", nickname);

    return mav;
  }

  @RequestMapping(value = "/usedcar_reply/create.do", method = RequestMethod.POST)
  public ModelAndView create(Usedcar_replyVO usedcar_replyVO, HttpServletResponse response, HttpSession session) throws IOException {
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
      session.setAttribute("url", "/usedcar_reply/list.do?u_no="+usedcar_replyVO.getU_no());
      
    } 
    usedcar_replyDAO.create(usedcar_replyVO);
    System.out.println("크리에이트 post"+usedcar_replyVO.getU_no());
    mav.setViewName("redirect:/usedcar_reply/list.do?u_no="+usedcar_replyVO.getU_no());
    return mav;
  }
    

  
  
  @RequestMapping(value = "/usedcar_reply/list.do", method = RequestMethod.GET)
  public ModelAndView list(HttpServletRequest request, int u_no, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    System.out.println("리스트 post"+u_no);
    
    
    
    
    
   // String userid = session.getAttribute("userid").toString();
   // String nickname = session.getAttribute("nickname").toString();
    mav.setViewName("/usedcar_reply/list");
    mav.addObject("list", usedcar_replyDAO.usedcar_replyList(u_no));
    mav.addObject("u_no", u_no);
    //mav.addObject("nickname", nickname);
    //mav.addObject("userid", userid);
    return mav;
  }
  
  
  @RequestMapping(value = "/usedcar_reply/reply.do", method = RequestMethod.GET)
  public ModelAndView reply() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/usedcar_reply/reply"); // /webapp/blog/reply.jsp
    
    return mav;
  }
  
  
  @RequestMapping(value = "/usedcar_reply/reply.do", method = RequestMethod.POST)
  public ModelAndView reply(Usedcar_replyVO usedcar_replyVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("redirect:/usedcar_reply/list.do?u_no="+usedcar_replyVO.getU_no());
    System.out.println("여기 들어옴");
    // ---------- 답변 관련 코드 시작 ---------- 
    Usedcar_replyVO parentVO = usedcar_replyDAO.read(usedcar_replyVO.getRno()); // 부모글 정보 추출
    System.out.println("여기 들어2옴"); 
    System.out.println(usedcar_replyVO.getRno());
    usedcar_replyVO.setGrpno(parentVO.getGrpno()); // 그룹 번호

    usedcar_replyVO.setAnsnum(parentVO.getAnsnum()); // 답변 순서
    
    usedcar_replyDAO.updateAnsnum(usedcar_replyVO); // 현재 등록된 답변 뒤로 +1 처리함.
    
    usedcar_replyVO.setIndent(parentVO.getIndent()+1); // 답변 차수 증가
    usedcar_replyVO.setAnsnum(parentVO.getAnsnum()+1); //부모 바로 아래 등록
    // ---------- 답변 관련 코드 종료 ---------- 
   
   
    usedcar_replyDAO.reply(usedcar_replyVO); 
    return mav;
  }
  
  @RequestMapping(value = "/usedcar_reply/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(Usedcar_replyVO vo) {
    ModelAndView mav = new ModelAndView();
    usedcar_replyDAO.delete(vo.getRno());
    mav.setViewName("redirect:/usedcar_reply/list.do?u_no="+vo.getU_no());// 확장자 명시
    return mav;
  }
  
  
}
