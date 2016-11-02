package dev.mvc.cloth_reply;

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

import dev.mvc.cloth.ClothDAOInter;
import dev.mvc.cloth.ClothVO;
import dev.mvc.tmember.MemberVO;




@Controller
public class Cloth_replyCont {
  @Autowired
  @Qualifier("dev.mvc.cloth_reply.Cloth_replyDAO")
  private Cloth_replyDAOInter cloth_replyDAO;
  
  
  @Autowired
  @Qualifier("dev.mvc.cloth.ClothDAO")
  private ClothDAOInter clothDAO;
  
  
  
  public Cloth_replyCont() {
    System.out.println("--> Cloth_replyCont created.");
  }
  
  @RequestMapping(value = "/cloth_reply/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session ) {
    System.out.println("--> create() GET called.");
    
    String userid = session.getAttribute("userid").toString();
    String nickname = session.getAttribute("nickname").toString();

    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cloth_reply/create"); // /webapp/member/create.jsp
    mav.addObject("userid", userid);
    mav.addObject("nickname", nickname);

    return mav;
  }

  @RequestMapping(value = "/cloth_reply/create.do", method = RequestMethod.POST)
  public ModelAndView create(Cloth_replyVO cloth_replyVO, HttpServletResponse response, HttpSession session) throws IOException {
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
      session.setAttribute("url", "/cloth_reply/list.do?clothno="+cloth_replyVO.getClothno());
      
    } 
    
    
    cloth_replyDAO.create(cloth_replyVO);
    
    mav.setViewName("redirect:/cloth_reply/list.do?clothno="+cloth_replyVO.getClothno());
    return mav;
  }
    

  
  
  @RequestMapping(value = "/cloth_reply/list.do", method = RequestMethod.GET)
  public ModelAndView list(HttpServletRequest request, int clothno, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    
    
    
    
   // String userid = session.getAttribute("userid").toString();
   // String nickname = session.getAttribute("nickname").toString();
    mav.setViewName("/cloth_reply/list");
    mav.addObject("list", cloth_replyDAO.cloth_replyList(clothno));
    mav.addObject("clothno", clothno);
    //mav.addObject("nickname", nickname);
    //mav.addObject("userid", userid);
    return mav;
  }
  
  
  @RequestMapping(value = "/cloth_reply/reply.do", method = RequestMethod.GET)
  public ModelAndView reply() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cloth_reply/reply"); // /webapp/blog/reply.jsp
    
    return mav;
  }
  
  
  @RequestMapping(value = "/cloth_reply/reply.do", method = RequestMethod.POST)
  public ModelAndView reply(Cloth_replyVO cloth_replyVO, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
    ModelAndView mav = new ModelAndView();
    
    
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    if (session.getAttribute("userid") == null){
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('로그인 한 사용자만 사용이 가능합니다.');" 
       + "location.href = '../member/login.do';"
       + "</script>"); 
      session.setAttribute("url", "/cloth_reply/list.do?clothno="+cloth_replyVO.getClothno());
      
    } 
    
    
    
    
    mav.setViewName("redirect:/cloth_reply/list.do?clothno="+cloth_replyVO.getClothno());
    System.out.println("여기 들어옴");
    // ---------- 답변 관련 코드 시작 ---------- 
    Cloth_replyVO parentVO = cloth_replyDAO.read(cloth_replyVO.getRno()); // 부모글 정보 추출
    System.out.println("여기 들어2옴"); 
    System.out.println(cloth_replyVO.getRno());
    cloth_replyVO.setGrpno(parentVO.getGrpno()); // 그룹 번호

    cloth_replyVO.setAnsnum(parentVO.getAnsnum()); // 답변 순서
    
   cloth_replyDAO.updateAnsnum(cloth_replyVO); // 현재 등록된 답변 뒤로 +1 처리함.
    
   cloth_replyVO.setIndent(parentVO.getIndent()+1); // 답변 차수 증가
   cloth_replyVO.setAnsnum(parentVO.getAnsnum()+1); //부모 바로 아래 등록
    // ---------- 답변 관련 코드 종료 ---------- 
   
   
    cloth_replyDAO.reply(cloth_replyVO); 
    return mav;
  }
  
  
  
  @RequestMapping(value = "/cloth_reply/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(Cloth_replyVO vo) {
    ModelAndView mav = new ModelAndView();
    cloth_replyDAO.delete(vo.getRno());
    mav.setViewName("redirect:/cloth_reply/list.do?clothno="+vo.getClothno());// 확장자 명시
    return mav;
  }
  
  
}
