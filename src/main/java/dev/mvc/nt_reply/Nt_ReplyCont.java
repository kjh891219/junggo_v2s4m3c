package dev.mvc.nt_reply;

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

import dev.mvc.game_reply.Game_ReplyVO;
import dev.mvc.notice.NoticeDAOInter;

@Controller
public class Nt_ReplyCont {

  @Autowired
  @Qualifier("dev.mvc.nt_reply.Nt_ReplyDAO")
  private Nt_ReplyDAOInter nt_replyDAO;
  
  @Autowired
  @Qualifier("dev.mvc.notice.NoticeDAO")
  private NoticeDAOInter noticeDAO;
  

  @RequestMapping(value = "/nt_reply/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session) {

    String userid = session.getAttribute("userid").toString();
    String nickname = session.getAttribute("nickname").toString();

    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nt_reply/create"); // /webapp/blog/create.jsp
    mav.addObject("userid", userid);
    mav.addObject("nickname", nickname);

    
       return mav;
  }

  @RequestMapping(value = "/nt_reply/create.do", method = RequestMethod.POST)
  public ModelAndView create(Nt_ReplyVO nt_replyVO, HttpServletResponse response, HttpSession session)throws IOException  {
    // System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
  
    
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    if (session.getAttribute("userid") == null) {
      PrintWriter writer = response.getWriter();
      writer.println   
      ("<script>alert('�α��� �� ����ڸ� ����� �����մϴ�.');" 
       + "location.href = '../member/login.do';"
       + "</script>"); 
      session.setAttribute("url", "/nt_reply/list.do?noticeno="+nt_replyVO.getNoticeno());
      
    } 
    
    
    nt_replyDAO.create(nt_replyVO);
    
    mav.setViewName("redirect:/nt_reply/list.do?noticeno="+nt_replyVO.getNoticeno());
    return mav;
  }



 
  
  /**
   * ������
   * @param blogno
   * @return
   */
    
  @RequestMapping(value = "/nt_reply/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(Nt_ReplyVO vo) {
    ModelAndView mav = new ModelAndView();
    nt_replyDAO.delete(vo.getRno());
    mav.setViewName("redirect:/nt_reply/list.do?noticeno="+vo.getNoticeno());// Ȯ���� ���
    return mav;
  }
  

  
  @RequestMapping(value = "/nt_reply/list.do", method = RequestMethod.GET)
  public ModelAndView list(HttpServletRequest request, int noticeno, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nt_reply/list");
  
    mav.addObject("list", nt_replyDAO.nt_replyList(noticeno));
    mav.addObject("noticeno", noticeno);
 
   
    return mav;
  }  
  
  @RequestMapping(value = "/nt_reply/reply.do", method = RequestMethod.GET)
  public ModelAndView reply() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nt_reply/reply"); // /webapp/blog/reply.jsp
    
    return mav;
  }
  
  
  
  @RequestMapping(value = "/nt_reply/reply.do", method = RequestMethod.POST)
  public ModelAndView reply(Nt_ReplyVO nt_replyVO, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
    ModelAndView mav = new ModelAndView();

    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    if (session.getAttribute("userid") == null){
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('�α��� �� ����ڸ� ����� �����մϴ�.');" 
       + "location.href = '../member/login.do';"
       + "</script>"); 
      session.setAttribute("url", "/nt_reply/list.do?noticeno="+nt_replyVO.getNoticeno());
      
    } 
    
        
    
    mav.setViewName("redirect:/nt_reply/list.do?noticeno="+nt_replyVO.getNoticeno());
    System.out.println("���� ����");
    // ---------- �亯 ���� �ڵ� ���� ---------- 
    Nt_ReplyVO parentVO = nt_replyDAO.read(nt_replyVO.getRno()); // �θ�� ���� ����
    System.out.println("���� ���2��"); 
    System.out.println(nt_replyVO.getRno());
    nt_replyVO.setGrpno(parentVO.getGrpno()); // �׷� ��ȣ
          
    nt_replyVO.setAnsnum(parentVO.getAnsnum()); // �亯 ����
    
    nt_replyDAO.updateAnsnum(nt_replyVO); // ���� ��ϵ� �亯 �ڷ� +1 ó����.
    
   nt_replyVO.setIndent(parentVO.getIndent()+1); // �亯 ���� ����
   nt_replyVO.setAnsnum(parentVO.getAnsnum()+1); //�θ� �ٷ� �Ʒ� ���
    // ---------- �亯 ���� �ڵ� ���� ---------- 
   
   
    nt_replyDAO.reply(nt_replyVO); 
    return mav;
  
  }
  
}
