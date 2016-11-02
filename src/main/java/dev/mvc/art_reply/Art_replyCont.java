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
      ("<script>alert('�α��� �� ����ڸ� ����� �����մϴ�.');" 
        +  "location.href = './login_ck_form.jsp';"
       + " window.onload = function(){"
       + " var url = '../login.do';"
       + " var win = window.open(url, '�α���', 'width=600px, height=700px');"
       + " var x = (screen.width - 500) / 2;"
       + " var y = (screen.height - 440) / 2;"
       + " win.moveTo(x, y); // ȭ�� ����� �̵�"
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
    System.out.println("���� ����");
    // ---------- �亯 ���� �ڵ� ���� ---------- 
    Art_replyVO parentVO = art_replyDAO.read(art_replyVO.getRno()); // �θ�� ���� ����
    System.out.println("���� ���2��"); 
    System.out.println(art_replyVO.getRno());
    art_replyVO.setGrpno(parentVO.getGrpno()); // �׷� ��ȣ

    art_replyVO.setAnsnum(parentVO.getAnsnum()); // �亯 ����
    
   art_replyDAO.updateAnsnum(art_replyVO); // ���� ��ϵ� �亯 �ڷ� +1 ó����.
    
   art_replyVO.setIndent(parentVO.getIndent()+1); // �亯 ���� ����
   art_replyVO.setAnsnum(parentVO.getAnsnum()+1); //�θ� �ٷ� �Ʒ� ���
    // ---------- �亯 ���� �ڵ� ���� ---------- 
   
   
    art_replyDAO.reply(art_replyVO); 
    return mav;
  }
  
  @RequestMapping(value = "/art_reply/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(Art_replyVO vo) {
    ModelAndView mav = new ModelAndView();
    art_replyDAO.delete(vo.getRno());
    mav.setViewName("redirect:/art_reply/list.do?ano="+vo.getAno());// Ȯ���� ���
    return mav;
  }
}
