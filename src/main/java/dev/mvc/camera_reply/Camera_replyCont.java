package dev.mvc.camera_reply;

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

import dev.mvc.camera.CameraDAOInter;
import dev.mvc.camera.CameraVO;
import dev.mvc.tmember.MemberVO;







@Controller
public class Camera_replyCont {
  @Autowired
  @Qualifier("dev.mvc.camera_reply.Camera_replyDAO")
  private Camera_replyDAOInter camera_replyDAO;
  
  
  @Autowired
  @Qualifier("dev.mvc.camera.CameraDAO")
  private CameraDAOInter cameraDAO;
  
  
  
  public Camera_replyCont() {
    System.out.println("--> Camera_replyCont created.");
  }
  
  @RequestMapping(value = "/camera_reply/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session ) {
    System.out.println("--> create() GET called.");
    
    String userid = session.getAttribute("userid").toString();
    String nickname = session.getAttribute("nickname").toString();

    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/camera_reply/create"); // /webapp/member/create.jsp
    mav.addObject("userid", userid);
    mav.addObject("nickname", nickname);

    return mav;
  }

  @RequestMapping(value = "/camera_reply/create.do", method = RequestMethod.POST)
  public ModelAndView create(Camera_replyVO camera_replyVO, HttpServletResponse response, HttpSession session) throws IOException {
    System.out.println("--> create() POST called.");
    ModelAndView mav = new ModelAndView();
    
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    if (session.getAttribute("userid") == null){
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('�α��� �� ����ڸ� ����� �����մϴ�.');" 
       + "parent.location.href = '../member/login.do';" 
       + "</script>"); 
      //session.setAttribute("url", "/camera_reply/list.do?ctno="+camera_replyVO.getCtno());
      session.setAttribute("url", "/camera/read.do?ctno="+camera_replyVO.getCtno());
    } 
    
    
    camera_replyDAO.create(camera_replyVO);
    
    mav.setViewName("redirect:/camera_reply/list.do?ctno="+camera_replyVO.getCtno());
    return mav;
  }
    

  
  
  @RequestMapping(value = "/camera_reply/list.do", method = RequestMethod.GET)
  public ModelAndView list(HttpServletRequest request, int ctno, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    
    
    
    
   // String userid = session.getAttribute("userid").toString();
   // String nickname = session.getAttribute("nickname").toString();
    mav.setViewName("/camera_reply/list");
    mav.addObject("list", camera_replyDAO.camera_replyList(ctno));
    mav.addObject("ctno", ctno);
    //mav.addObject("nickname", nickname);
    //mav.addObject("userid", userid);
    return mav;
  }
  
  
  @RequestMapping(value = "/camera_reply/reply.do", method = RequestMethod.GET)
  public ModelAndView reply() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/camera_reply/reply"); // /webapp/blog/reply.jsp
    
    return mav;
  }
  
  
  @RequestMapping(value = "/camera_reply/reply.do", method = RequestMethod.POST)
  public ModelAndView reply(Camera_replyVO camera_replyVO, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
    ModelAndView mav = new ModelAndView();
    
    
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    if (session.getAttribute("userid") == null){
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('�α��� �� ����ڸ� ����� �����մϴ�.');" 
       + "parent.location.href = '../member/login.do';" 
       + "</script>"); 
      //session.setAttribute("url", "/camera_reply/list.do?ctno="+camera_replyVO.getCtno());
      session.setAttribute("url", "/camera/read.do?ctno="+camera_replyVO.getCtno());
    } 
    
    
    
    
    mav.setViewName("redirect:/camera_reply/list.do?ctno="+camera_replyVO.getCtno());
    System.out.println("���� ����");
    // ---------- �亯 ���� �ڵ� ���� ---------- 
    Camera_replyVO parentVO = camera_replyDAO.read(camera_replyVO.getRno()); // �θ�� ���� ����
    System.out.println("���� ���2��"); 
    System.out.println(camera_replyVO.getRno());
    camera_replyVO.setGrpno(parentVO.getGrpno()); // �׷� ��ȣ

    camera_replyVO.setAnsnum(parentVO.getAnsnum()); // �亯 ����
    
   camera_replyDAO.updateAnsnum(camera_replyVO); // ���� ��ϵ� �亯 �ڷ� +1 ó����.
    
   camera_replyVO.setIndent(parentVO.getIndent()+1); // �亯 ���� ����
   camera_replyVO.setAnsnum(parentVO.getAnsnum()+1); //�θ� �ٷ� �Ʒ� ���
    // ---------- �亯 ���� �ڵ� ���� ---------- 
   
   
    camera_replyDAO.reply(camera_replyVO); 
    return mav;
  }
  
  
  
  @RequestMapping(value = "/camera_reply/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(Camera_replyVO vo) {
    ModelAndView mav = new ModelAndView();
    camera_replyDAO.delete(vo.getRno());
    mav.setViewName("redirect:/camera_reply/list.do?ctno="+vo.getCtno());// Ȯ���� ���
    return mav;
  }
  
  
}
