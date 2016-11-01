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
      ("<script>alert('�α��� �� ����ڸ� ����� �����մϴ�.');" 
       + "parent.location.href = '../member/login.do';" 
       + "</script>"); 
      session.setAttribute("url", "game/read.do?gno="+game_replyVO.getGno());
      
    } 
    
    
    game_replyDAO.create(game_replyVO);
    
    mav.setViewName("redirect:/game_reply/list.do?gno="+game_replyVO.getGno());
    return mav;
  }



 
  
  /**
   * ������
   * @param blogno
   * @return
   */
    
  @RequestMapping(value = "/game_reply/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(Game_ReplyVO vo) {
    ModelAndView mav = new ModelAndView();
    game_replyDAO.delete(vo.getRno());
    mav.setViewName("redirect:/game_reply/list.do?gno="+vo.getGno());// Ȯ���� ���
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
      ("<script>alert('�α��� �� ����ڸ� ����� �����մϴ�.');" 
          + "parent.location.href = '../member/login.do';" 
       + "</script>"); 
      session.setAttribute("url", "game/read.do?gno="+game_replyVO.getGno());
      
    } 
    
        
    
    mav.setViewName("redirect:/game_reply/list.do?gno="+game_replyVO.getGno());
    System.out.println("���� ����");
    // ---------- �亯 ���� �ڵ� ���� ---------- 
   Game_ReplyVO parentVO = game_replyDAO.read(game_replyVO.getRno()); // �θ�� ���� ����
    System.out.println("���� ���2��"); 
    System.out.println(game_replyVO.getRno());
    game_replyVO.setGrpno(parentVO.getGrpno()); // �׷� ��ȣ
          
    game_replyVO.setAnsnum(parentVO.getAnsnum()); // �亯 ����
    
   game_replyDAO.updateAnsnum(game_replyVO); // ���� ��ϵ� �亯 �ڷ� +1 ó����.
    
   game_replyVO.setIndent(parentVO.getIndent()+1); // �亯 ���� ����
   game_replyVO.setAnsnum(parentVO.getAnsnum()+1); //�θ� �ٷ� �Ʒ� ���
    // ---------- �亯 ���� �ڵ� ���� ---------- 
   
   
    game_replyDAO.reply(game_replyVO); 
    return mav;
  
  }
}
