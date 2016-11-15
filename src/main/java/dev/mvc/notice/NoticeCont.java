package dev.mvc.notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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


import dev.mvc.game.GameVO;
import dev.mvc.tmember.MemberVO;
import web.tool.Paging;
import web.tool.SearchDTO;
import web.tool.Tool;
import web.tool.Upload;

@Controller
public class NoticeCont {
 @Autowired
 @Qualifier("dev.mvc.notice.NoticeDAO")
 private NoticeDAOInter noticeDAO;

 @RequestMapping(value = "/notice/create.do", method = RequestMethod.GET)
 public ModelAndView create(HttpSession session, HttpServletResponse response) throws IOException{
   System.out.println("--> create() GET called.");
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/notice/create"); // /webapp/member/create.jsp
  
   
   
   response.setCharacterEncoding("UTF-8");
   response.setContentType("text/html; charset=UTF-8");
   if (session.getAttribute("userid") == null ){
     PrintWriter writer = response.getWriter();
     writer.println
     ("<script>alert('�α��� �� ����ڸ� ����� �����մϴ�.');" 
      + "location.href = '../member/login.do';"
      + "</script>"); 
     session.setAttribute("url", "notice/list.do");//
   
        
     } else {
       PrintWriter writer = response.getWriter();
       writer.println
       ("<script>" 
           + "location.href = './create.jsp';"
           + "</script>");
       
     }
     String userid = session.getAttribute("userid").toString();
     String pwd = session.getAttribute("pwd").toString();
     String tel = session.getAttribute("tel").toString();
     MemberVO memberVO = noticeDAO.test(userid);
        
     mav.addObject("memberVO", memberVO);
     mav.addObject("userid", userid);
     mav.addObject("pwd", pwd);
     mav.addObject("tel", tel);
     System.out.println(memberVO);
   
   
   return mav;
 }

 @RequestMapping(value = "/notice/create.do", method = RequestMethod.POST)
 public ModelAndView create(NoticeVO noticeVO, HttpServletRequest request, HttpSession session) {
   System.out.println("--> create() POST called.");
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/notice/message"); // webapp/member/message.jsp

   ArrayList<String> msgs = new ArrayList<String>();
   ArrayList<String> links = new ArrayList<String>(); 
 
   String file1 = "";
   String file2 = "";
   long size2 = 0;
   String upDir = Tool.getRealPath(request, "/notice/storage");
   MultipartFile file2MF = noticeVO.getFile2MF();
   
   
   size2 = file2MF.getSize();
   if (file2MF.getSize() > 0) {
     file2 = Upload.saveFileSpring(file2MF, upDir);
     noticeVO.setFile2(file2); // ���۵� ���ϸ� ����
     noticeVO.setSize2(file2MF.getSize());
     // Thumb ���� ����
     // -------------------------------------------------------------------
     if (Tool.isImage(file2)) {
       file1 = Tool.preview(upDir, file2, 120, 80);
     } else {
       file1 = "";
     }
     // -------------------------------------------------------------------
   }
   noticeVO.setFile1(file1); // Thumb �̹���
   noticeVO.setFile2(file2); // ���� �̹���
   noticeVO.setSize2(size2); // ���� �̹���
   
   
   if (noticeDAO.create(noticeVO) == 1) {
     msgs.add("��ǰ ��� �Ϸ��߽��ϴ�.");
     msgs.add("������ּż� �����մϴ�.");
     links.add("<button type='button' onclick=\"location.href='./login.do'\">�α���</button>");
     links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
   } else {
     msgs.add("��ǰ ��Ͽ� �����߽��ϴ�.");
     msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
     links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
     links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
   }

   links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");

   mav.addObject("msgs", msgs);
   mav.addObject("links", links);

   return mav;
 
 }
 
 
@RequestMapping(value = "/notice/list.do", method = RequestMethod.GET)
public ModelAndView list2(SearchDTO searchDTO, HttpServletRequest request) {
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/notice/list");// /webapp/game/list.jsp

  
  // HashMap hashMap = new HashMap();
  HashMap<String, Object> hashMap = new HashMap<String, Object>();
  hashMap.put("col", searchDTO.getCol());
  hashMap.put("word", searchDTO.getWord());
  
  int recordPerPage = 10; // �������� ����� ���ڵ� ����
  // ���������� ����� ���� ���ڵ� ��ȣ ���, nowPage�� 0���� ����
  int beginOfPage = (searchDTO.getNowPage() - 1) * 10; // nowPage�� 1���� ����
  // 1 page: 0
  // 2 page: 10
  // 3 page: 20
  int startNum = beginOfPage + 1; // ���� rownum 1 
  int endNum = beginOfPage + recordPerPage; // ���� rownum 10
  hashMap.put("startNum", startNum);
  hashMap.put("endNum", endNum);
  
  
  int totalRecord = 0;
  List<NoticeVO> list = noticeDAO.list3(hashMap);
  Iterator<NoticeVO> iter = list.iterator();
  while(iter.hasNext() == true){  // ���� ��� �˻�
    NoticeVO vo = iter.next();  // ��� ����
    vo.setTitle(Tool.textLength(vo.getTitle(), 10)); // ���ڿ� 10�� �и�
    vo.setWdate(vo.getWdate().substring(0, 10));  // �����
    //vo.setFile1(Tool.textLength(vo.getFile1(), 10));
    //vo.setFile2(Tool.textLength(vo.getFile2(), 10));
    vo.setSize2Label(Tool.unit(vo.getSize2()));
  }
  mav.addObject("list", list);
  mav.addObject("root", request.getContextPath());
  
  
  totalRecord = noticeDAO.count(hashMap);
  mav.addObject("totalRecord", noticeDAO.count(hashMap));
  
  
  String paging = new Paging().paging5( totalRecord,             
                                        searchDTO.getNowPage(), 
                                        recordPerPage, 
                                        searchDTO.getCol(), 
                                        searchDTO.getWord());
  mav.addObject("paging", paging);
  return mav;
}   
@RequestMapping(value = "/notice/read.do", method = RequestMethod.GET)
public ModelAndView read(int noticeno, SearchDTO searchDTO, HttpServletRequest request) {
  ModelAndView mav = new ModelAndView();
  noticeDAO.increaseCnt(noticeno);// ��ȸ�� ����
  mav.setViewName("/notice/read");
  mav.addObject("noticeVO", noticeDAO.read(noticeno));
  NoticeVO noticeVO = noticeDAO.read(noticeno);
  noticeVO.setSize2Label(Tool.unit(noticeVO.getSize2()));
  String content = noticeVO.getContent();
  content = Tool.convertChar(content);
  mav.addObject("searchDTO", searchDTO);
 
  
  return mav;
}
@RequestMapping(value = "/notice/update.do", method = RequestMethod.GET)
public ModelAndView update(int noticeno) {
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/notice/update"); // /webapp/blogcategory/update.jsp

  NoticeVO vo = noticeDAO.read(noticeno);
  mav.addObject("vo", vo);

  return mav;
  }

@RequestMapping(value = "/notice/update.do", method = RequestMethod.POST)
public ModelAndView update(NoticeVO noticeVO, HttpServletRequest request) {
  ModelAndView mav = new ModelAndView();


  ArrayList<String> msgs = new ArrayList<String>();
  ArrayList<String> links = new ArrayList<String>();

  // -------------------------------------------------------------------
  // ���� ����
  // -------------------------------------------------------------------
  String file1 = "";
  String file2 = "";
  long size2 = 0;
  String upDir = Tool.getRealPath(request, "/notice/storage");
  MultipartFile file2MF = noticeVO.getFile2MF();
  NoticeVO oldVO = noticeDAO.read(noticeVO.getNoticeno());
       
 
  size2 = file2MF.getSize();
  if (file2MF.getSize() > 0) { // ���ο� ������ �����ϴ��� Ȯ��
    Tool.deleteFile(upDir, oldVO.getFile2()); // ���� ��ϵ� ���� ����
    file2 = Upload.saveFileSpring(file2MF, upDir);// ���ο� ���� ����
    // -------------------------------------------------------------------
    // Thumb ���� ����
    // -------------------------------------------------------------------
    if (Tool.isImage(file2)) { // �̹������� �˻�
      Tool.deleteFile(upDir, oldVO.getFile1()); // ���� ����
      file1 = Tool.preview(upDir, file2, 120, 80); // thumb �̹��� ����
    } else {
      file1 = "";
    }
  } else {
    file1 = oldVO.getFile1(); // ���� ���ε带���� �ʴ� ���
    file2 = oldVO.getFile2();
    size2 = oldVO.getSize2();
  }
  noticeVO.setFile1(file1); // Thumb �̹���
  noticeVO.setFile2(file2); // ���� �̹���
  noticeVO.setSize2(size2); // ���� �̹���
  
  if (noticeDAO.update(noticeVO) == 1) {
    msgs.add("��ǰ ���� �Ϸ��߽��ϴ�.");
    mav.setViewName("redirect:/notice/read.do?noticeno=" + noticeVO.getNoticeno());

  } else {
    msgs.add("��ǰ ���� ���濡 �����߽��ϴ�.");
    msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
    links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
    links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
  }
  return mav;
}

@RequestMapping(value = "/notice/delete.do", method = RequestMethod.GET)
public ModelAndView delete(int noticeno) {
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/notice/delete"); // /webapp/member/delete.jsp
  mav.addObject("noticeno", noticeno);

  return mav;
}

/**
 * ���ڵ� 1���� �����մϴ�.
 * 
 * @param codeno
 * @return
 */
@RequestMapping(value = "/notice/delete.do", method = RequestMethod.POST)
public ModelAndView delete(NoticeVO noticeVO) {
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/notice/message");

  ArrayList<String> msgs = new ArrayList<String>();
  ArrayList<String> links = new ArrayList<String>();

  if (noticeDAO.delete(noticeVO.getNoticeno()) == 1) {
    mav.setViewName("redirect:/notice/list.do");
  } else {
    msgs.add("������ �����߽��ϴ�.");
    msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
    links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
    links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
    links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

  }

  return mav;
}
@RequestMapping(value = "/notice/reply.do", method = RequestMethod.GET)
public ModelAndView reply(NoticeVO noticeVO, HttpSession session, HttpServletResponse response) throws IOException {
  System.out.println("--> reply() GET called.");
  ModelAndView mav = new ModelAndView();
  mav.setViewName("/notice/reply"); // /webapp/member/create.jsp
   
  response.setCharacterEncoding("UTF-8");
  response.setContentType("text/html; charset=UTF-8");
  if (session.getAttribute("userid") == null ){
    PrintWriter writer = response.getWriter();
    writer.println
    ("<script>alert('�α��� �� ����ڸ� ����� �����մϴ�.');" 
     + "location.href = '../member/login.do';"
     + "</script>"); 
    session.setAttribute("url", "notice/list.do");//
  
       
    } else {
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>" 
          + "location.href = './reply.jsp';"
          + "</script>");
      
    }
    String userid = session.getAttribute("userid").toString();
    String pwd = session.getAttribute("pwd").toString();
    String tel = session.getAttribute("tel").toString();
    MemberVO memberVO = noticeDAO.test(userid);
       
    mav.addObject("memberVO", memberVO);
    mav.addObject("userid", userid);
    mav.addObject("pwd", pwd);
    mav.addObject("tel", tel);
    System.out.println(memberVO);

return mav;
}

@RequestMapping(value = "/notice/reply.do", method = RequestMethod.POST)
public ModelAndView reply(NoticeVO noticeVO, HttpServletRequest request, HttpServletResponse response, HttpSession session)throws IOException {
  ModelAndView mav = new ModelAndView();
  response.setCharacterEncoding("UTF-8");
  response.setContentType("text/html; charset=UTF-8");
  if (session.getAttribute("userid") == null){
    PrintWriter writer = response.getWriter();
    writer.println
    ("<script>alert('�α��� �� ����ڸ� ����� �����մϴ�.');" 
     + "location.href = '../member/login.do';"
     + "</script>"); 
    session.setAttribute("url", "/notice/list.do?noticeno="+noticeVO.getNoticeno());
    
  } 
  
     
  mav.setViewName("redirect:/notice/list.do?noticeno="+noticeVO.getNoticeno());
  
  
  ArrayList<String> msgs = new ArrayList<String>();
  ArrayList<String> links = new ArrayList<String>();
  
  String file1 = "";
  String file2 = "";
  long size2 = 0;
  String upDir = Tool.getRealPath(request, "/notice/storage");
  MultipartFile file2MF = noticeVO.getFile2MF();
  
  
  size2 = file2MF.getSize();
  if (file2MF.getSize() > 0) {
    file2 = Upload.saveFileSpring(file2MF, upDir);
    noticeVO.setFile2(file2); // ���۵� ���ϸ� ����
    noticeVO.setSize2(file2MF.getSize());
    // Thumb ���� ����
    // -------------------------------------------------------------------
    if (Tool.isImage(file2)) {
      file1 = Tool.preview(upDir, file2, 120, 80);
    } else {
      file1 = "";
    }
    // -------------------------------------------------------------------
  }
  noticeVO.setFile1(file1); // Thumb �̹���
  noticeVO.setFile2(file2); // ���� �̹���
  noticeVO.setSize2(size2); // ���� �̹���
   
  // ---------- �亯 ���� �ڵ� ���� ---------- 
  NoticeVO parentVO = noticeDAO.read(noticeVO.getNoticeno()); // �θ�� ���� ����
  noticeVO.setGrpno(parentVO.getGrpno()); // �׷� ��ȣ
  noticeVO.setAnsnum(parentVO.getAnsnum()); // �亯 ����
  
  noticeDAO.updateAnsnum(noticeVO); // ���� ��ϵ� �亯 �ڷ� +1 ó����.
  
  noticeVO.setIndent(parentVO.getIndent()+1); // �亯 ���� ����
  noticeVO.setAnsnum(parentVO.getAnsnum()+1); //�θ� �ٷ� �Ʒ� ���
  // ---------- �亯 ���� �ڵ� ���� ---------- 
  
  if (noticeDAO.reply(noticeVO) == 1) {
   
    msgs.add("���� ����߽��ϴ�.");
    links
        .add("<button type='button' onclick=\"location.href='./create.do?'\">�������� ���</button>");
  } else {
    msgs.add("�� ��Ͽ� �����߽��ϴ�.");
    msgs.add("�ٽ� �õ����ּ���.");
    links.add("<button type='button' onclick=\"history.back()\">�ٽýõ�</button>");
  }

  links.add("<button type='button' onclick=\"location.href='./home.do'\">Ȩ������</button>");
  links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
  mav.addObject("msgs", msgs);
  mav.addObject("links", links);

  return mav;
} 
  
}

 

