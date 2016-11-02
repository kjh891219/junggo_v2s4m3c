package dev.mvc.art_reply;

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

import dev.mvc.art.ArtDAOInter;
import dev.mvc.camera_reply.Camera_replyVO;
import dev.mvc.tmember.MemberVO;

@Controller
public class Art_replyCont {
  @Autowired
  @Qualifier("dev.mvc.art_reply.Art_replyDAO")
  private Art_replyDAOInter art_replyDAO;
  
  
  @Autowired
  @Qualifier("dev.mvc.art.ArtDAO")
  private ArtDAOInter artDAO;
  
  
  
  public Art_replyCont() {
    System.out.println("--> Art_replyCont created.");
  }
  
  @RequestMapping(value = "/art_reply/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session ) {
    System.out.println("--> create() GET called.");
    
    String userid = session.getAttribute("userid").toString();
    String nickname = session.getAttribute("nickname").toString();

    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/art_reply/create"); // /webapp/member/create.jsp
    mav.addObject("userid", userid);
    mav.addObject("nickname", nickname);

    return mav;
  }

  @RequestMapping(value = "/art_reply/create.do", method = RequestMethod.POST)
  public ModelAndView create(Art_replyVO art_replyVO, HttpServletResponse response, HttpSession session) throws IOException {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
/*    if (session.getAttribute("userid") == null){
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('로그인 한 사용자만 사용이 가능합니다.');" 
        +  "location.href = './login_ck_form.jsp';"
       + " window.onload = function(){"
       + " var url = '../login.do';"
       + " var win = window.open(url, '로그인', 'width=600px, height=700px');"
       + " var x = (screen.width - 500) / 2;"
       + " var y = (screen.height - 440) / 2;"
       + " win.moveTo(x, y); // 화면 가운데로 이동"
       + " }"
       + "</script>"); 
       session.setAttribute("url", "/art_reply/list.do?ano="+art_replyVO.getAno());
    } 
    else {    
    }*/
    art_replyDAO.create(art_replyVO);
    mav.setViewName("redirect:/art/read.do?ano="+art_replyVO.getAno());
    return mav;
  }
    

  
  
  @RequestMapping(value = "/art_reply/list.do", method = RequestMethod.GET)
  public ModelAndView list(HttpServletRequest request, int ano, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    String userid = null;
    if(session.getAttribute("userid") != null) {
      userid = session.getAttribute("userid").toString();
    }
   // String nickname = session.getAttribute("nickname").toString();
    mav.setViewName("/art_reply/list");
    mav.addObject("list", art_replyDAO.art_replyList(ano));
    mav.addObject("ano", ano);
    //mav.addObject("nickname", nickname);
    mav.addObject("userid", userid);
    return mav;
  }
  
  
  @RequestMapping(value = "/art_reply/reply.do", method = RequestMethod.GET)
  public ModelAndView reply() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/art_reply/reply"); // /webapp/blog/reply.jsp
    
    return mav;
  }
  
  
  @RequestMapping(value = "/art_reply/reply.do", method = RequestMethod.POST)
  public ModelAndView reply(Art_replyVO art_replyVO, HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("redirect:/art_reply/list.do?ano="+art_replyVO.getAno());
    System.out.println("여기 들어옴");
    // ---------- 답변 관련 코드 시작 ---------- 
    Art_replyVO parentVO = art_replyDAO.read(art_replyVO.getRno()); // 부모글 정보 추출
    System.out.println("여기 들어2옴"); 
    System.out.println(art_replyVO.getRno());
    art_replyVO.setGrpno(parentVO.getGrpno()); // 그룹 번호

    art_replyVO.setAnsnum(parentVO.getAnsnum()); // 답변 순서
    
   art_replyDAO.updateAnsnum(art_replyVO); // 현재 등록된 답변 뒤로 +1 처리함.
    
   art_replyVO.setIndent(parentVO.getIndent()+1); // 답변 차수 증가
   art_replyVO.setAnsnum(parentVO.getAnsnum()+1); //부모 바로 아래 등록
    // ---------- 답변 관련 코드 종료 ---------- 
   
   
    art_replyDAO.reply(art_replyVO); 
    return mav;
  }
  
  @RequestMapping(value = "/art_reply/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(Art_replyVO vo) {
    ModelAndView mav = new ModelAndView();
    art_replyDAO.delete(vo.getRno());
    mav.setViewName("redirect:/art_reply/list.do?ano="+vo.getAno());// 확장자 명시
    return mav;
  }
}
