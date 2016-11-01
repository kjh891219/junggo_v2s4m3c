package dev.mvc.music_reply;

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

import dev.mvc.music.MusicDAOInter;
import dev.mvc.reviews.ReviewsDAOInter;
import dev.mvc.tmember.MemberVO;


@Controller
public class Music_replyCont {
  @Autowired
  @Qualifier("dev.mvc.music_reply.Music_replyDAO")
  private Music_replyDAOInter music_replyDAO;
  
  
  @Autowired
  @Qualifier("dev.mvc.music.MusicDAO")
  private MusicDAOInter MusicDAO;
  
  
  
  public Music_replyCont() {
    System.out.println("--> Music_replyCont created.");
  }
  
  @RequestMapping(value = "/music_reply/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session ) {
    System.out.println("--> create() GET called.");
    
    String userid = session.getAttribute("userid").toString();
    String nickname = session.getAttribute("nickname").toString();

    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/music_reply/create"); // /webapp/member/create.jsp
    mav.addObject("userid", userid);
    mav.addObject("nickname", nickname);

    return mav;
  }

  @RequestMapping(value = "/music_reply/create.do", method = RequestMethod.POST)
  public ModelAndView create(Music_replyVO music_replyVO, HttpServletResponse response, HttpSession session) throws IOException {
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
      session.setAttribute("url", "/music_reply/list.do?ctno="+music_replyVO.getCtno());
      
    } 
    
    
    music_replyDAO.create(music_replyVO);
    
    mav.setViewName("redirect:/music_reply/list.do?ctno="+music_replyVO.getCtno());
    return mav;
  }
    

  
  
  @RequestMapping(value = "/music_reply/list.do", method = RequestMethod.GET)
  public ModelAndView list(HttpServletRequest request, int ctno, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    System.out.println(ctno);
    
    
    
    
    
   // String userid = session.getAttribute("userid").toString();
   // String nickname = session.getAttribute("nickname").toString();
    mav.setViewName("/music_reply/list");
    mav.addObject("list", music_replyDAO.music_replyList(ctno));
    mav.addObject("ctno", ctno);
    //mav.addObject("nickname", nickname);
    //mav.addObject("userid", userid);
    return mav;
  }
  
  
  @RequestMapping(value = "/music_reply/reply.do", method = RequestMethod.GET)
  public ModelAndView reply() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/music_reply/reply"); // /webapp/blog/reply.jsp
    
    return mav;
  }
  
  
  @RequestMapping(value = "/music_reply/reply.do", method = RequestMethod.POST)
  public ModelAndView reply(Music_replyVO music_replyVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("redirect:/music_reply/list.do?ctno="+music_replyVO.getCtno());
    System.out.println("여기 들어옴");
    // ---------- 답변 관련 코드 시작 ---------- 
    Music_replyVO parentVO = music_replyDAO.read(music_replyVO.getRno()); // 부모글 정보 추출
    System.out.println("여기 들어2옴"); 

    music_replyVO.setGrpno(parentVO.getGrpno()); // 그룹 번호

    music_replyVO.setAnsnum(parentVO.getAnsnum()); // 답변 순서
    
    music_replyDAO.updateAnsnum(music_replyVO); // 현재 등록된 답변 뒤로 +1 처리함.
    
    music_replyVO.setIndent(parentVO.getIndent()+1); // 답변 차수 증가
    music_replyVO.setAnsnum(parentVO.getAnsnum()+1); //부모 바로 아래 등록
    System.out.println(music_replyVO.getIndent());
    System.out.println(music_replyVO.getAnsnum());
    // ---------- 답변 관련 코드 종료 ---------- 
   
   
    music_replyDAO.reply(music_replyVO); 
    return mav;
  }
  
  @RequestMapping(value = "/music_reply/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(Music_replyVO vo) {
    ModelAndView mav = new ModelAndView();
    music_replyDAO.delete(vo.getRno());
    mav.setViewName("redirect:/music_reply/list.do?ctno="+vo.getCtno());// 확장자 명시
    return mav;
  }
  
  
}
