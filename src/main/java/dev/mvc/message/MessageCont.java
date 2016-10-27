package dev.mvc.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import web.tool.SearchDTO;
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
   * ���� �޽��� �� ��ȸ - 1��
   * @param session ���̵�
   * @return
   */
  @RequestMapping(value = "/message/read_msg.do", method = RequestMethod.GET)
  public ModelAndView read_msg(int msg_no, String flag, SearchDTO searchDTO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/message/read"); //  /webapp/member/read.jsp
    MessageVO messageVO = messageDAO.read_msg(msg_no);
    mav.addObject("messageVO", messageVO);
    mav.addObject("flag", flag);
    mav.addObject("searchDTO", searchDTO);
    return mav;
  }
  
  
  /**
   * ����¡ + �˻� ��ü ���
   * @param searchDTO
   * @param request
   * @param session
   * @param flag
   * @return
   */
  @RequestMapping(value = "/message/list.do", method = RequestMethod.GET)
  public ModelAndView list(SearchDTO searchDTO, HttpServletRequest request, HttpSession session, String flag) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/message/list_msg"); // /webapp/member/list.jsp
    
    // HashMap hashMap = new HashMap();
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("col", searchDTO.getCol());
    hashMap.put("word", searchDTO.getWord());
    
    String userid = (String) session.getAttribute("userid");
    hashMap.put("userid", userid);
    hashMap.put("flag", flag);
    
    int recordPerPage = 10; // �������� ����� ���ڵ� ����
    // ���������� ����� ���� ���ڵ� ��ȣ ���, nowPage�� 1���� ����
    int beginOfPage = (searchDTO.getNowPage() - 1) * 10; 
    // 1 page: 0
    // 2 page: 10
    // 3 page: 20
    int startNum = beginOfPage + 1; // ���� rownum, 1
    int endNum = beginOfPage + recordPerPage; // ���� rownum, 10
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    int totalRecord = 0;
    List<MessageVO> list = messageDAO.list(hashMap);
    Iterator<MessageVO> iter = list.iterator();
    
    while (iter.hasNext() == true) { // ���� ��� �˻�
      MessageVO vo = iter.next(); // ��� ����
      vo.setMsg_no(vo.getMsg_no()); 
      vo.setReceiveid(vo.getReceiveid()); 
      vo.setSendid(vo.getSendid()); 
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));  
      vo.setContent(Tool.textLength(vo.getContent(), 10));
      vo.setMsg_date(vo.getMsg_date().substring(0, 10));  // �����
    }
    
    mav.addObject("list", list);
    mav.addObject("root", request.getContextPath());
    
    totalRecord = messageDAO.count(hashMap); // hashMap - col, word, userid, flag, startNum, endNum
    mav.addObject("totalRecord", messageDAO.count(hashMap));
    
    String paging = new MessagePaging().paging5(
        totalRecord, 
        searchDTO.getNowPage(), 
        recordPerPage, 
        searchDTO.getCol(), 
        searchDTO.getWord(), 
        flag);
    mav.addObject("paging", paging);
    mav.addObject("flag", flag);
    mav.addObject("totalRecord", totalRecord);
    
    return mav;
  }

  
  @RequestMapping(value = "/message/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/message/create"); 
    return mav;
  }
  
  /**
   * �޽��� ����
   * @param messageVO
   * @param response
   * @param session
   * @throws IOException
   */
  @RequestMapping(value = "/message/create.do", method = RequestMethod.POST)
  public void create(MessageVO messageVO, HttpServletResponse response, HttpSession session) throws IOException {
    messageVO.setSendid(session.getAttribute("userid").toString());
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    int sendOK = messageDAO.create(messageVO);
    if (sendOK == 1) {
        PrintWriter writer = response.getWriter();
        writer.println
        ("<script>alert('�޽����� ���۵Ǿ����ϴ�');" 
         + "location.href = './create.do';"
         + "</script>"
            );
    } else {
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('ID�� Ȯ���� �ּ���');" 
       + "location.href = './create.do';"
       + "</script>"
          );
    }
    return;
  }
  
  @RequestMapping(value = "/message/visible.do", method = RequestMethod.GET)
  public void visible(MessageVO messageVO, HttpServletResponse response, HttpSession session, String flag
      , @RequestParam List<String> msg_no_arr) throws IOException {
    System.out.println("��Ʈ�ѷ�:"+ msg_no_arr);
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
   
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("msg_no_arr", msg_no_arr);
    hashMap.put("userid", session.getAttribute("userid"));
    hashMap.put("flag", flag);
    
    int sendOK = messageDAO.visible(hashMap);
    System.out.println(sendOK);
    if (sendOK != 0) {
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('�޽����� " + sendOK + "�� �����Ǿ����ϴ�');" 
       + "location.href = './list.do?flag=" + flag + "';"
       + "</script>"
          );
  } else {
    PrintWriter writer = response.getWriter();
    writer.println
    ("<script>alert('������ �޽����� ������ �ּ���');" 
     + "location.href = './list.do?flag=" + flag + "';"
     + "</script>"
        );
  }
  return;
  }
  
  
}
