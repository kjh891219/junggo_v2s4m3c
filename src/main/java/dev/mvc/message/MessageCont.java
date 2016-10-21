package dev.mvc.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import web.tool.Tool;


@Controller
public class MessageCont {
  @Autowired
  @Qualifier("dev.mvc.tmember.MessageDAO")
  private MessageDAOInter messageDAO;
  
  public MessageCont(){
    System.out.println("--> MessageCont created.");
  }
  
  /**
   * 받은 메시지 전체 조회
   * @param session 아이디
   * @return
   */
  @RequestMapping(value = "/message/receive_list.do", method = RequestMethod.GET)
  public ModelAndView receive_list(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/message/receive_list"); //  /webapp/member/list.jsp
    
    String userid = (String) session.getAttribute("userid");
    
    List<MessageVO> list =  messageDAO.receive_list(userid);
    Iterator<MessageVO> iter = list.iterator();

    int cnt = 0;
    
    while(iter.hasNext() == true){  // 다음 요소 검사
      MessageVO vo = iter.next();  // 요소 추출
      vo.setMsg_no(vo.getMsg_no()); 
      vo.setSendid(vo.getSendid()); 
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));  
      vo.setContent(Tool.textLength(vo.getContent(), 10));
      vo.setMsg_date(vo.getMsg_date().substring(0, 10));  // 년월일
      cnt ++;
    }
    mav.addObject("list", list);
    mav.addObject("cnt", cnt);
    
    return mav;
  }
  
  /**
   * 보낸 메시지 전체 조회
   * @param session 아이디
   * @return
   */
  @RequestMapping(value = "/message/send_list.do", method = RequestMethod.GET)
  public ModelAndView send_list(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/message/send_list"); //  /webapp/member/list.jsp
    
    String userid = (String) session.getAttribute("userid");
    
    List<MessageVO> list =  messageDAO.send_list(userid);
    Iterator<MessageVO> iter = list.iterator();
    
    int cnt = 0;
    
    while(iter.hasNext() == true){  // 다음 요소 검사
      MessageVO vo = iter.next();  // 요소 추출
      vo.setMsg_no(vo.getMsg_no()); 
      vo.setSendid(vo.getSendid()); 
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));  
      vo.setContent(Tool.textLength(vo.getContent(), 10));
      vo.setMsg_date(vo.getMsg_date().substring(0, 10));  // 년월일
      cnt ++;
    }
    mav.addObject("list", list);
    mav.addObject("cnt", cnt);
    
    return mav;
  }
  
  /**
   * 받은 메시지 상세 조회 - 1개
   * @param session 아이디
   * @return
   */
  @RequestMapping(value = "/message/read_msg.do", method = RequestMethod.GET)
  public ModelAndView read_msg(int msg_no, String flag) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/message/read"); //  /webapp/member/read.jsp
    MessageVO messageVO = messageDAO.read_msg(msg_no);
    mav.addObject("messageVO", messageVO);
    mav.addObject("flag", flag);
    return mav;
  }
  
  /**
   * 메시지 목록 조회
   * @param session 아이디
   * @param flag r: 받은 목록, s: 보낸 목록
   * @return
   */
  @RequestMapping(value = "/message/list_msg.do", method = RequestMethod.GET)
  public ModelAndView list_msg(HttpSession session, String flag) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/message/list_msg"); //  /webapp/member/list.jsp
    
    List<MessageVO> list = null;
    
    String userid = (String) session.getAttribute("userid");
    if(flag.equals("recv")) {
      list =  messageDAO.receive_list(userid);
    } else {
      list =  messageDAO.send_list(userid);
    }
    Iterator<MessageVO> iter = list.iterator();

    int cnt = 0;
    
    while(iter.hasNext() == true){  // 다음 요소 검사
      MessageVO vo = iter.next();  // 요소 추출
      vo.setMsg_no(vo.getMsg_no()); 
      vo.setReceiveid(vo.getReceiveid()); 
      vo.setSendid(vo.getSendid()); 
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));  
      vo.setContent(Tool.textLength(vo.getContent(), 10));
      vo.setMsg_date(vo.getMsg_date().substring(0, 10));  // 년월일
      cnt ++;
    }
    mav.addObject("list", list);
    mav.addObject("cnt", cnt);
    mav.addObject("flag", flag);
    
    return mav;
  }
  
  @RequestMapping(value = "/message/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/message/create"); 
 
    return mav;
  }
  
  @RequestMapping(value = "/message/create.do", method = RequestMethod.POST)
  public void create(MessageVO messageVO, HttpServletResponse response, HttpSession session) throws IOException {
    messageVO.setSendid(session.getAttribute("userid").toString());

    ModelAndView mav = new ModelAndView();
    mav.setViewName("/message/create"); 
    
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    
    int sendOK = messageDAO.create(messageVO);
    System.out.println(sendOK);
    if (sendOK == 1) {
        PrintWriter writer = response.getWriter();
        writer.println
        ("<script>alert('메시지가 전송되었습니다');" 
         + "location.href = './create.do';"
          // + "self.close();" 
         + "</script>"
            );
    } else {
    }
    
    return;
  }
  
  
  
}
