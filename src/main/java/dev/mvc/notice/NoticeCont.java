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
     ("<script>alert('로그인 한 사용자만 사용이 가능합니다.');" 
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
     noticeVO.setFile2(file2); // 전송된 파일명 저장
     noticeVO.setSize2(file2MF.getSize());
     // Thumb 파일 생성
     // -------------------------------------------------------------------
     if (Tool.isImage(file2)) {
       file1 = Tool.preview(upDir, file2, 120, 80);
     } else {
       file1 = "";
     }
     // -------------------------------------------------------------------
   }
   noticeVO.setFile1(file1); // Thumb 이미지
   noticeVO.setFile2(file2); // 원본 이미지
   noticeVO.setSize2(size2); // 원본 이미지
   
   
   if (noticeDAO.create(noticeVO) == 1) {
     msgs.add("상품 등록 완료했습니다.");
     msgs.add("등록해주셔서 감사합니다.");
     links.add("<button type='button' onclick=\"location.href='./login.do'\">로그인</button>");
     links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
   } else {
     msgs.add("상품 등록에 실패했습니다.");
     msgs.add("죄송하지만 다시한번 시도해주세요.");
     links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
     links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
   }

   links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");

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
  
  int recordPerPage = 10; // 페이지당 출력할 레코드 갯수
  // 페이지에서 출력할 시작 레코드 번호 계산, nowPage는 0부터 시작
  int beginOfPage = (searchDTO.getNowPage() - 1) * 10; // nowPage는 1부터 시작
  // 1 page: 0
  // 2 page: 10
  // 3 page: 20
  int startNum = beginOfPage + 1; // 시작 rownum 1 
  int endNum = beginOfPage + recordPerPage; // 종료 rownum 10
  hashMap.put("startNum", startNum);
  hashMap.put("endNum", endNum);
  
  
  int totalRecord = 0;
  List<NoticeVO> list = noticeDAO.list3(hashMap);
  Iterator<NoticeVO> iter = list.iterator();
  while(iter.hasNext() == true){  // 다음 요소 검사
    NoticeVO vo = iter.next();  // 요소 추출
    vo.setTitle(Tool.textLength(vo.getTitle(), 10)); // 문자열 10자 분리
    vo.setWdate(vo.getWdate().substring(0, 10));  // 년월일
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
  noticeDAO.increaseCnt(noticeno);// 조회수 증가
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
  // 파일 전송
  // -------------------------------------------------------------------
  String file1 = "";
  String file2 = "";
  long size2 = 0;
  String upDir = Tool.getRealPath(request, "/notice/storage");
  MultipartFile file2MF = noticeVO.getFile2MF();
  NoticeVO oldVO = noticeDAO.read(noticeVO.getNoticeno());
       
 
  size2 = file2MF.getSize();
  if (file2MF.getSize() > 0) { // 새로운 파일을 전송하는지 확인
    Tool.deleteFile(upDir, oldVO.getFile2()); // 기존 등록된 파일 삭제
    file2 = Upload.saveFileSpring(file2MF, upDir);// 새로운 파일 저장
    // -------------------------------------------------------------------
    // Thumb 파일 생성
    // -------------------------------------------------------------------
    if (Tool.isImage(file2)) { // 이미지인지 검사
      Tool.deleteFile(upDir, oldVO.getFile1()); // 파일 삭제
      file1 = Tool.preview(upDir, file2, 120, 80); // thumb 이미지 생성
    } else {
      file1 = "";
    }
  } else {
    file1 = oldVO.getFile1(); // 파일 업로드를하지 않는 경우
    file2 = oldVO.getFile2();
    size2 = oldVO.getSize2();
  }
  noticeVO.setFile1(file1); // Thumb 이미지
  noticeVO.setFile2(file2); // 원본 이미지
  noticeVO.setSize2(size2); // 원본 이미지
  
  if (noticeDAO.update(noticeVO) == 1) {
    msgs.add("상품 수정 완료했습니다.");
    mav.setViewName("redirect:/notice/read.do?noticeno=" + noticeVO.getNoticeno());

  } else {
    msgs.add("상품 정보 변경에 실패했습니다.");
    msgs.add("죄송하지만 다시한번 시도해주세요.");
    links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
    links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
    links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");

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
 * 레코드 1건을 삭제합니다.
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
    msgs.add("삭제에 실패했습니다.");
    msgs.add("죄송하지만 다시한번 시도해주세요.");
    links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
    links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
    links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");

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
    ("<script>alert('로그인 한 사용자만 사용이 가능합니다.');" 
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
    ("<script>alert('로그인 한 사용자만 사용이 가능합니다.');" 
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
    noticeVO.setFile2(file2); // 전송된 파일명 저장
    noticeVO.setSize2(file2MF.getSize());
    // Thumb 파일 생성
    // -------------------------------------------------------------------
    if (Tool.isImage(file2)) {
      file1 = Tool.preview(upDir, file2, 120, 80);
    } else {
      file1 = "";
    }
    // -------------------------------------------------------------------
  }
  noticeVO.setFile1(file1); // Thumb 이미지
  noticeVO.setFile2(file2); // 원본 이미지
  noticeVO.setSize2(size2); // 원본 이미지
   
  // ---------- 답변 관련 코드 시작 ---------- 
  NoticeVO parentVO = noticeDAO.read(noticeVO.getNoticeno()); // 부모글 정보 추출
  noticeVO.setGrpno(parentVO.getGrpno()); // 그룹 번호
  noticeVO.setAnsnum(parentVO.getAnsnum()); // 답변 순서
  
  noticeDAO.updateAnsnum(noticeVO); // 현재 등록된 답변 뒤로 +1 처리함.
  
  noticeVO.setIndent(parentVO.getIndent()+1); // 답변 차수 증가
  noticeVO.setAnsnum(parentVO.getAnsnum()+1); //부모 바로 아래 등록
  // ---------- 답변 관련 코드 종료 ---------- 
  
  if (noticeDAO.reply(noticeVO) == 1) {
   
    msgs.add("글을 등록했습니다.");
    links
        .add("<button type='button' onclick=\"location.href='./create.do?'\">공지사항 등록</button>");
  } else {
    msgs.add("글 등록에 실패했습니다.");
    msgs.add("다시 시도해주세요.");
    links.add("<button type='button' onclick=\"history.back()\">다시시도</button>");
  }

  links.add("<button type='button' onclick=\"location.href='./home.do'\">홈페이지</button>");
  links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
  mav.addObject("msgs", msgs);
  mav.addObject("links", links);

  return mav;
} 
  
}

 

