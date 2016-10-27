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
   * 받은 메시지 상세 조회 - 1개
   * @param session 아이디
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
   * 페이징 + 검색 전체 목록
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
    
    int recordPerPage = 10; // 페이지당 출력할 레코드 갯수
    // 페이지에서 출력할 시작 레코드 번호 계산, nowPage는 1부터 시작
    int beginOfPage = (searchDTO.getNowPage() - 1) * 10; 
    // 1 page: 0
    // 2 page: 10
    // 3 page: 20
    int startNum = beginOfPage + 1; // 시작 rownum, 1
    int endNum = beginOfPage + recordPerPage; // 종료 rownum, 10
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    int totalRecord = 0;
    List<MessageVO> list = messageDAO.list(hashMap);
    Iterator<MessageVO> iter = list.iterator();
    
    while (iter.hasNext() == true) { // 다음 요소 검사
      MessageVO vo = iter.next(); // 요소 추출
      vo.setMsg_no(vo.getMsg_no()); 
      vo.setReceiveid(vo.getReceiveid()); 
      vo.setSendid(vo.getSendid()); 
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));  
      vo.setContent(Tool.textLength(vo.getContent(), 10));
      vo.setMsg_date(vo.getMsg_date().substring(0, 10));  // 년월일
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
   * 메시지 전송
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
        ("<script>alert('메시지가 전송되었습니다');" 
         + "location.href = './create.do';"
         + "</script>"
            );
    } else {
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('ID를 확인해 주세요');" 
       + "location.href = './create.do';"
       + "</script>"
          );
    }
    return;
  }
  
  @RequestMapping(value = "/message/visible.do", method = RequestMethod.GET)
  public void visible(MessageVO messageVO, HttpServletResponse response, HttpSession session, String flag
      , @RequestParam List<String> msg_no_arr) throws IOException {
    System.out.println("컨트롤러:"+ msg_no_arr);
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
      ("<script>alert('메시지가 " + sendOK + "개 삭제되었습니다');" 
       + "location.href = './list.do?flag=" + flag + "';"
       + "</script>"
          );
  } else {
    PrintWriter writer = response.getWriter();
    writer.println
    ("<script>alert('삭제할 메시지를 선택해 주세요');" 
     + "location.href = './list.do?flag=" + flag + "';"
     + "</script>"
        );
  }
  return;
  }
  
  
}
