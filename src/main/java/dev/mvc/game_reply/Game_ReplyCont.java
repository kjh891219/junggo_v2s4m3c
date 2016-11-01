package dev.mvc.game_reply;

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


import dev.mvc.game.*;
import web.tool.SearchDTO;
import web.tool.Tool;           
import web.tool.*;

@Controller
public class Game_ReplyCont {
  @Autowired
  @Qualifier("dev.mvc.game_reply.Game_ReplyDAO")
  private Game_ReplyDAOInter game_replyDAO;

  @Autowired
  @Qualifier("dev.mvc.game.GameDAO")
  private GameDAOInter gameDAO;

  public Game_ReplyCont() {
    System.out.println("--> gamereplyCont created.");
  }

  @RequestMapping(value = "/game_reply/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session) {

    String userid = session.getAttribute("userid").toString();
    String nickname = session.getAttribute("nickname").toString();

    ModelAndView mav = new ModelAndView();
    mav.setViewName("/game_reply/create"); // /webapp/blog/create.jsp
    mav.addObject("userid", userid);
    mav.addObject("nickname", nickname);

    
       return mav;
  }

  @RequestMapping(value = "/game_reply/create.do", method = RequestMethod.POST)
  public ModelAndView create(Game_ReplyVO game_replyVO, HttpServletResponse response, HttpSession session)throws IOException  {
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
      session.setAttribute("url", "game/read.do?gno="+game_replyVO.getGno());
      
    } 
    
    
    game_replyDAO.create(game_replyVO);
    
    mav.setViewName("redirect:/game_reply/list.do?gno="+game_replyVO.getGno());
    return mav;
  }



 
  
  /**
   * 삭제폼
   * @param blogno
   * @return
   */
    
  @RequestMapping(value = "/game_reply/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(Game_ReplyVO vo) {
    ModelAndView mav = new ModelAndView();
    game_replyDAO.delete(vo.getRno());
    mav.setViewName("redirect:/game_reply/list.do?gno="+vo.getGno());// 확장자 명시
    return mav;
  }
  

  
  @RequestMapping(value = "/game_reply/list.do", method = RequestMethod.GET)
  public ModelAndView list(HttpServletRequest request, int gno, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/game_reply/list");
  
    mav.addObject("list", game_replyDAO.game_replyList(gno));
    mav.addObject("gno", gno);
 
   
    return mav;
  }  
  
  @RequestMapping(value = "/game_reply/reply.do", method = RequestMethod.GET)
  public ModelAndView reply() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/game_reply/reply"); // /webapp/blog/reply.jsp
    
    return mav;
  }
  
  
  
  @RequestMapping(value = "/game_reply/reply.do", method = RequestMethod.POST)
  public ModelAndView reply(Game_ReplyVO game_replyVO, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
    ModelAndView mav = new ModelAndView();

    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    if (session.getAttribute("userid") == null){
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('로그인 한 사용자만 사용이 가능합니다.');" 
          + "parent.location.href = '../member/login.do';" 
       + "</script>"); 
      session.setAttribute("url", "game/read.do?gno="+game_replyVO.getGno());
      
    } 
    
        
    
    mav.setViewName("redirect:/game_reply/list.do?gno="+game_replyVO.getGno());
    System.out.println("여기 들어옴");
    // ---------- 답변 관련 코드 시작 ---------- 
   Game_ReplyVO parentVO = game_replyDAO.read(game_replyVO.getRno()); // 부모글 정보 추출
    System.out.println("여기 들어2옴"); 
    System.out.println(game_replyVO.getRno());
    game_replyVO.setGrpno(parentVO.getGrpno()); // 그룹 번호
          
    game_replyVO.setAnsnum(parentVO.getAnsnum()); // 답변 순서
    
   game_replyDAO.updateAnsnum(game_replyVO); // 현재 등록된 답변 뒤로 +1 처리함.
    
   game_replyVO.setIndent(parentVO.getIndent()+1); // 답변 차수 증가
   game_replyVO.setAnsnum(parentVO.getAnsnum()+1); //부모 바로 아래 등록
    // ---------- 답변 관련 코드 종료 ---------- 
   
   
    game_replyDAO.reply(game_replyVO); 
    return mav;
  
  }
}
